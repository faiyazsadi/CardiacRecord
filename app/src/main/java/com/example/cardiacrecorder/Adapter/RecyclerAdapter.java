package com.example.cardiacrecorder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.Activity.DetailsActivity;
import com.example.cardiacrecorder.Model.RecordModel;
import com.example.cardiacrecorder.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<RecordModel> usersListAll;
    private Context context;
    private String userId;
    private String userName;
    public RecyclerAdapter(List<RecordModel> usersList,Context context, String userId,String userName) {
        this.usersListAll = new ArrayList<>(usersList);
        this.context = context;
        this.userId = userId;
        this.userName = userName;
    }
    public class  MyViewHolder extends RecyclerView.ViewHolder {
         TextView Date,HeartRate,Systolic,Diastolic;
        public MyViewHolder(final View view) {
            super(view);
            Date = view.findViewById(R.id.date);
            HeartRate = view.findViewById(R.id.heartRate);
            Systolic = view.findViewById(R.id.sSystolic);
            Diastolic = view.findViewById(R.id.sDiastolic);

        }
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        String sdate = usersListAll.get(position).getDate();
        String sheartRate = usersListAll.get(position).getHeartrate();
        String systolic = usersListAll.get(position).getSystolic();
        String diastolic = usersListAll.get(position).getDiastolic();
        String time = usersListAll.get(position).getTimes();
        String timeStamp = usersListAll.get(position).getTimestamp();
        String comment = usersListAll.get(position).getComment();

            holder.Date.setText(sdate);
            holder.HeartRate.setText(sheartRate);
            holder.Systolic.setText(systolic);
            holder.Diastolic.setText(diastolic);

            int HeartInt = Integer.parseInt(sheartRate);
            int SystoInt = Integer.parseInt(systolic);
            int DiastoInt = Integer.parseInt(diastolic);

            if(HeartInt >= 70 && HeartInt <= 80)
            {
                holder.HeartRate.setTextColor(Color.parseColor("#92f10d"));
            }
            else
            {
                holder.HeartRate.setTextColor(Color.RED);
            }

            if (SystoInt >=100 && SystoInt <=120)
            {
                holder.Systolic.setTextColor(Color.parseColor("#92f10d"));
            }
            else if(SystoInt >120 && SystoInt <= 129)
            {

                holder.Systolic.setTextColor(Color.parseColor("#febd46"));
            }
            else if(SystoInt >= 130 && SystoInt <= 140)
            {

                holder.Systolic.setTextColor(Color.parseColor("#f99f2c"));
            }
            else
            {
                holder.Systolic.setTextColor(Color.RED);
            }


            if(DiastoInt >= 70 && DiastoInt <= 80)
            {
                holder.Diastolic.setTextColor(Color.parseColor("#92f10d"));
            }
            else if(DiastoInt > 80 && DiastoInt <= 89)
            {
                holder.Diastolic.setTextColor(Color.parseColor("#f99f2c"));
            }
            else
            {
                holder.Diastolic.setTextColor(Color.RED);
            }






            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("Date",sdate);
                    intent.putExtra("Time",time);
                    intent.putExtra("Heartrate",sheartRate);
                    intent.putExtra("Systolic",systolic);
                    intent.putExtra("Diastolic",diastolic);
                    intent.putExtra("Comment",comment);
                    intent.putExtra("UserId",userId);
                    intent.putExtra("recordId",timeStamp);
                    intent.putExtra("UserName",userName);
                    context.startActivity(intent);
                }
            });

    }



    @Override
    public int getItemCount() {
        return usersListAll.size();
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
