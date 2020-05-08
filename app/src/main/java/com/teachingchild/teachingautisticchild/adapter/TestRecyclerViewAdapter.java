package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.CircleImageView;
import com.teachingchild.teachingautisticchild.model.ScitentificMethods;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<ScitentificMethods> itemsList = new ArrayList<>();

    private Context mContext;

    public TestRecyclerViewAdapter(Context context, List<ScitentificMethods> items) {
        itemsList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_tests, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(itemsList.get(position).getImage())
                .into(holder.image);
        holder.title.setText(itemsList.get(position).getTitle());
        holder.subtitle.setText(itemsList.get(position).getSubtitle());
        holder.stepcount.setText(itemsList.get(position).getSteps());
        if(itemsList.get(position).getStatus() == 1){
           holder.beginBtn.setBackgroundResource(R.drawable.button_background_enable);
           holder.beginBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.drawblesize_enable, 0);
        }else{
            holder.beginBtn.setBackgroundResource(R.drawable.button_background_disable);
            holder.beginBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.drawblesize_disable, 0);
        }

        holder.linearLayout.setTag(itemsList.get(position).getSubtopicid());
        //holder.image.setBackgroundResource(R.drawable.pharmacist);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        CircleImageView image;
        TextView title,subtitle,stepcount;
        Button beginBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.titlestep);
            subtitle = itemView.findViewById(R.id.text);
            stepcount = itemView.findViewById(R.id.stepcount);
            beginBtn = itemView.findViewById(R.id.beginBtn);
        }
    }
}