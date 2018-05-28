package com.macbitsgoa.events.feed;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    RecyclerView recyclerView;
    ArrayList<feedRecyclerViewItem> arrayList;
    LinearLayoutManager linearLayoutManager;
    feedAdapter adapter;
    NestedScrollView scrollView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initUI();
        FirebaseDatabase.getInstance().getReference().child("feed").orderByKey().addChildEventListener(this);
    }

    /*
    * Method called when the activity is started
    * initialises the adapter and layout for recycler view
    * */

    void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        scrollView = findViewById(R.id.scroll_view);
        arrayList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new feedAdapter(arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onChildAdded(final DataSnapshot dataSnapshot, final String s) {
        arrayList.add(0, dataSnapshot.getValue(feedRecyclerViewItem.class));
        adapter.notifyDataSetChanged();
        Snackbar.make(recyclerView, "New Posts Added", Snackbar.LENGTH_LONG).setAction("scroll", this).show();

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
        Snackbar.make(recyclerView, "New Posts Added", Snackbar.LENGTH_LONG).setAction("scroll", this).show();

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
        scrollView.smoothScrollTo(0, 0);
    }
}