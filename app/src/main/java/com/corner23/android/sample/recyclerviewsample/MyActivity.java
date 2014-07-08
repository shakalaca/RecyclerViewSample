package com.corner23.android.sample.recyclerviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyActivity extends Activity {

    private static final String TAG = MyActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private final static int[] sPhotos = {
        R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
        R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,
        R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12,
        R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16,
        R.drawable.p17
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        List<PhotoCard> cards = new ArrayList<PhotoCard>();
        for (int i = 0; i < sPhotos.length; i++) {
            cards.add(new PhotoCard(sPhotos[i], null));
        }

        recyclerViewAdapter = new RecyclerViewAdapter(cards);

        recyclerView = (RecyclerView) findViewById(android.R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            recyclerViewAdapter.add(getRandomPhotoCard(), getInsertPosition());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onItemClick(View v) {
        PhotoCard card = (PhotoCard) v.getTag();

        recyclerViewAdapter.remove(card);
    }

    private PhotoCard getRandomPhotoCard() {
        Random rand = new Random();
        int n = rand.nextInt(sPhotos.length);

        Log.d(TAG, "Select " + n);

        return new PhotoCard(sPhotos[n], null);
    }

    private int getInsertPosition() {
        int pos = 0;
        int childCount = recyclerView.getChildCount();
        if (childCount > 0) {
            pos = childCount / 2;
            View v = recyclerView.getChildAt(pos);
            pos = recyclerViewAdapter.getItemPosition((PhotoCard) v.getTag());
        }

        Log.d(TAG, "Insert at " + pos);

        return pos;
    }
}
