package com.macbitsgoa.events.feed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.events.R;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.macbitsgoa.events.Utilities.TAG_PREFIX;
import static com.macbitsgoa.events.feed.GetGoogleSignInActivity.KEY_TOKEN;

public class FeedActivity extends Activity implements ChildEventListener, View.OnClickListener {
    private static final String TAG = TAG_PREFIX + FeedActivity.class.getSimpleName();
    public static final int SIGN_IN_REQUEST_CODE = 4461;
    public static final int TEXT_SIZE_SNACKBAR = 12;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private ArrayList<FeedRecyclerViewItem> arrayList = new ArrayList<>(0);
    private FeedAdapter adapter;
    private NestedScrollView scrollView;
    private ImageButton shareButton;
    private EditText txtCaption;
    private TextView txtImage;
    private ImageView addImageButton;
    private String imagePath;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initui();

        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("feed");
        db.orderByKey().addChildEventListener(this);

        shareButton.setOnClickListener(this);
        txtImage.setOnClickListener(this);
        addImageButton.setOnClickListener(this);

    }

    /*
    * Method called when the activity is started
    * initialises the adapter and layout for recycler view
    * */
    private void initui() {
        appBarLayout = findViewById(R.id.appBarLayout);
        addImageButton = findViewById(R.id.btn_image);
        txtImage = findViewById(R.id.text_image);
        txtCaption = findViewById(R.id.text_caption);
        shareButton = findViewById(R.id.btn_share);
        recyclerView = findViewById(R.id.recyclerView);
        scrollView = findViewById(R.id.scroll_view);

        final LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        adapter = new FeedAdapter(arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, final String s) {
        arrayList.add(0, dataSnapshot.getValue(FeedRecyclerViewItem.class));
        adapter.notifyDataSetChanged();
        showSnackBar(recyclerView);

    }

    @Override
    public void onChildChanged(@NonNull final DataSnapshot dataSnapshot, final String s) {
        final FeedRecyclerViewItem item = dataSnapshot.getValue(FeedRecyclerViewItem.class);
        for (final FeedRecyclerViewItem arrayItem : arrayList) {
            if (Objects.equals(item.getId(), arrayItem.getId())) {
                arrayList.remove(arrayItem);
                arrayList.add(0, item);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        showSnackBar(recyclerView);

    }

    @Override
    public void onChildRemoved(@NonNull final DataSnapshot dataSnapshot) {
        final FeedRecyclerViewItem item = dataSnapshot.getValue(FeedRecyclerViewItem.class);
        for (final FeedRecyclerViewItem arrayItem : arrayList) {
            if (arrayItem.equals(item)) {
                arrayList.remove(arrayItem);
                break;
            }
        }
    }

    @Override
    public void onChildMoved(@NonNull final DataSnapshot dataSnapshot, final String s) {
        Log.i(TAG, "Child Moved");
    }

    @Override
    public void onCancelled(@NonNull final DatabaseError databaseError) {
        Log.e(TAG, databaseError.getMessage());
        showToast("Database Error");
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_share:

                scrollView.smoothScrollTo(0, 0);
                appBarLayout.setExpanded(true);

                if (Objects.equals(txtImage.getText(), "Please Select an Image")) {
                    showToast("Please select a picture to post.");
                    break;
                }
                if (txtCaption.getText().length() == 0) {
                    showToast("Please add a caption for your pic.");
                    break;
                }

                final Intent intent = new Intent(FeedActivity.this, GetGoogleSignInActivity.class);
                startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
                break;

            case R.id.text_image:
                showImagePicker();
                break;
            case R.id.btn_image:
                showImagePicker();
                break;
            default:
                Log.e(TAG, "onClickListener:ShareButton: default executed");
                break;
        }
    }

    private void showSnackBar(final View view) {
        final Snackbar sb;
        sb = Snackbar.make(view, "New Posts Added", BaseTransientBottomBar.LENGTH_INDEFINITE);
        if (!sb.isShownOrQueued()) {
            // styling for background of snackbar
            final View snackView = sb.getView();
            snackView.setBackgroundColor(getResources().getColor(R.color.feedGrey));
            final Button button = snackView.findViewById(R.id.snackbar_action);
            button.setBackgroundColor(getResources().getColor(R.color.feedGrey));
            button.setTextSize(TEXT_SIZE_SNACKBAR);
            sb.setActionTextColor(Color.YELLOW);
            sb.setAction("scroll", view1 -> scrollView.smoothScrollTo(0, 0));
            sb.show();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode,
                                    final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE && resultCode == RESULT_OK) {
            final UploadFile uploadFile = new UploadFile(imagePath,
                    data.getStringExtra(KEY_TOKEN), txtCaption.getText().toString());
            Log.e("PATH", imagePath);
            uploadFile.execute();

        } else if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            final Image image = ImagePicker.getFirstImageOrNull(data);
            txtImage.setText(image.getName());
            imagePath = image.getPath();
        }
    }

    private void showImagePicker() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.ALL)
                .single()// Activity or Fragment
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(getResources().getColor(R.color.feedGrey)) // Toolbar 'up' arrow
                .showCamera(true) // show camera or not (true by default)
                .start();

    }

    private void showToast(final CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}