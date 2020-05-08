package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.model.Item;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Item> itemsList = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Item> items) {
        itemsList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_course_list, parent, false);
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
        holder.number.setText(""+itemsList.get(position).getNumber());
        holder.progressBar.setProgress(itemsList.get(position).getPercentage());
        holder.total.setText("of"+" "+itemsList.get(position).getTotalnumber()+" "+"lessons");
        holder.cardView.setTag(itemsList.get(position).getTopic_id());
        //holder.image.setBackgroundResource(R.drawable.pharmacist);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView image;
        TextView title,subtitle,number,total;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtext);
            total = itemView.findViewById(R.id.total);
            number = itemView.findViewById(R.id.number);
            progressBar = itemView.findViewById(R.id.myprogressbar);
        }
    }
}