package com.macbitsgoa.events.feed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.macbitsgoa.events.R;

import java.util.ArrayList;
import java.util.Objects;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class feedActivity extends Activity implements ChildEventListener, View.OnClickListener {
    public static final String DATABASE_ERROR = "DatabaseError:";
    public static final int SIGN_IN_REQUEST_CODE = 4461;
    RecyclerView recyclerView;
    ArrayList<feedRecyclerViewItem> arrayList = new ArrayList<>(0);
    LinearLayoutManager linearLayoutManager;
    feedAdapter adapter;
    NestedScrollView scrollView;
    ImageButton shareButton;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initUI();

        FirebaseDatabase.getInstance().getReference().child("feed").orderByKey().addChildEventListener(this);
        shareButton.setOnClickListener(this);

    }

    /*
    * Method called when the activity is started
    * initialises the adapter and layout for recycler view
    * */
    void initUI() {
        shareButton = findViewById(R.id.btn_share);
        recyclerView = findViewById(R.id.recyclerView);
        scrollView = findViewById(R.id.scroll_view);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new feedAdapter(arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onChildAdded(final DataSnapshot dataSnapshot, final String s) {
        arrayList.add(0, dataSnapshot.getValue(feedRecyclerViewItem.class));
        adapter.notifyDataSetChanged();
        showSnackBar(recyclerView, "New Posts Added", Snackbar.LENGTH_LONG);

    }

    @Override
    public void onChildChanged(final DataSnapshot dataSnapshot, final String s) {
        final feedRecyclerViewItem item = dataSnapshot.getValue(feedRecyclerViewItem.class);
        for (final feedRecyclerViewItem arrayItem : arrayList) {
            if (Objects.equals(item.getId(), arrayItem.getId())) {
                arrayList.remove(arrayItem);
                arrayList.add(0, item);
                break;
            }
        }
        adapter.notifyDataSetChanged();
        showSnackBar(recyclerView, "New Posts Added", Snackbar.LENGTH_LONG);

    }

    @Override
    public void onChildRemoved(final DataSnapshot dataSnapshot) {
        final feedRecyclerViewItem item = dataSnapshot.getValue(feedRecyclerViewItem.class);
        for (final feedRecyclerViewItem arrayItem : arrayList) {
            if (arrayItem == item) {
                arrayList.remove(arrayItem);
                break;
            }
        }
    }

    @Override
    public void onChildMoved(final DataSnapshot dataSnapshot, final String s) {

    }

    @Override
    public void onCancelled(final DatabaseError databaseError) {
        Log.e(DATABASE_ERROR, databaseError.getMessage());
        Toast.makeText(feedActivity.this, DATABASE_ERROR, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(final View view) {
        final Intent signInIntent = new Intent(feedActivity.this, getGoogleSignIn.class);
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
    }

    void showSnackBar(final View view, final String message, final int duration) {
        final Snackbar snackbar = Snackbar.make(view, message, duration);

        if (!snackbar.isShownOrQueued()) {

            // styling for background of snackbar
            final View snackView = snackbar.getView();
            snackView.setBackgroundColor(getResources().getColor(R.color.feedGrey));
            Button button = snackView.findViewById(R.id.snackbar_action);
            button.setBackgroundColor(getResources().getColor(R.color.feedGrey));
            button.setTextSize(14);
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.setAction("scroll", view1 -> scrollView.smoothScrollTo(0, 0));
            snackbar.show();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE && resultCode == RESULT_OK) {
            GoogleSignInAccount account = data.getParcelableExtra("account");

        }

    }



}