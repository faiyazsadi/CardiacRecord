package com.example.cardiacrecorder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.Model.User;
import com.example.cardiacrecorder.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<User> usersList;

    public recyclerAdapter(ArrayList<User> usersList) {
        this.usersList = usersList;
    }
    public class  MyViewHolder extends RecyclerView.ViewHolder {
         TextView Date;
         TextView HeartRate;
        public MyViewHolder(final View view) {
            super(view);
            Date = view.findViewById(R.id.date);
            HeartRate = view.findViewById(R.id.heartRate);
        }
    }

    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_recycler_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( recyclerAdapter.MyViewHolder holder, int position) {
        String sdate = usersList.get(position).getDate();
        String sheartRate = usersList.get(position).getHeartrate();
        holder.Date.setText(sdate);
        holder.HeartRate.setText(sheartRate);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
