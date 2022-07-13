package com.example.cardiacrecorder.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.Model.RecordModel;
import com.example.cardiacrecorder.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<RecordModel> usersList;

    public recyclerAdapter(ArrayList<RecordModel> usersList) {
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




 /*   @Override
    public recyclerAdapter.MyViewHolder2 onCreateViewHolder( ViewGroup parent, int viewType) {

        View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_for_recycler_view,parent,false);
        return new MyViewHolder2(itemView2);
    }
    */




    @Override
    public void onBindViewHolder( recyclerAdapter.MyViewHolder holder, int position) {
        String sdate = usersList.get(position).getDate();
        String sheartRate = usersList.get(position).getHeartrate();
        /*if(position%2 == 1)
        {*/
            holder.Date.setText(sdate);
            holder.HeartRate.setText(sheartRate);
        /*}*/

    }



    @Override
    public int getItemCount() {
        return usersList.size();
    }

   /* public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView Date2;
        TextView HeartRate2;
        public MyViewHolder2(final View view2) {
            super(view2);
            Date2 = view2.findViewById(R.id.date2);
            HeartRate2 = view2.findViewById(R.id.heartRate2);
        }
    }*/
}
