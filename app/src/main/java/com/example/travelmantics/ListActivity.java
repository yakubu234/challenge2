package com.example.travelmantics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvDeals);
        new DealAdapter().readDeals(new DealAdapter.DataStatus() {
            @Override
            public void DataisLoaded(List<TravelDeal> Travelmantics, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            new FirebaseUtil().setDeal(mRecyclerView, ListActivity.this, Travelmantics, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
