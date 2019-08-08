package com.example.travelmantics;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DealAdapter {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private List<TravelDeal> Travelmantics = new ArrayList<>();

    public interface DataStatus{
        void DataisLoaded(List<TravelDeal> Travelmantics, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public DealAdapter() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Travelmantics");
    }
    public void readDeals(final DataStatus dataStatus){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Travelmantics.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    TravelDeal traveldeal = keyNode.getValue(TravelDeal.class);
                    Travelmantics.add(traveldeal);
                }
                dataStatus.DataisLoaded(Travelmantics,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
