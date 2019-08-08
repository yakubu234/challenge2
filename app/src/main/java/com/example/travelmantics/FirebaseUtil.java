package com.example.travelmantics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtil implements View.OnClickListener {
    private Context mContext;
    private DealsAdapter mDealsAdapter;
    public void setDeal(RecyclerView recyclerView, Context context, List<TravelDeal> Travelmantics, List<String> keys){
        mContext = context;
        mDealsAdapter = new DealsAdapter(Travelmantics, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mDealsAdapter);
    }

    @Override
    public void onClick(View v) {
//        int position = getAdapterPosition();
    }

    class DealItemView extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPrice;

        private  String key;

        public DealItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.travelmantics_list, parent, false));
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
        public void bind(TravelDeal travelDeal, String key){
            tvTitle.setText(travelDeal.getTitle());
            tvDescription.setText(travelDeal.getDescription());
            tvPrice.setText(travelDeal.getPrice());
            this.key = key;
        }
    }
    class DealsAdapter extends RecyclerView.Adapter<DealItemView>{
        private List<TravelDeal> mDealsList;
        private List<String> mKeys;

        public DealsAdapter(List<TravelDeal> mDealsList, List<String> mKeys) {
            this.mDealsList = mDealsList;
            this.mKeys = mKeys;
        }

        public DealsAdapter() {
            super();
        }

        @NonNull
        @Override
        public DealItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DealItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DealItemView holder, int position) {
        holder.bind(mDealsList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mDealsList.size();
        }
    }
}
