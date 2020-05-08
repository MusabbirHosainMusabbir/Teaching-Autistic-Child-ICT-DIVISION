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

public class RecyclerViewCoursesAdapter extends RecyclerView.Adapter<RecyclerViewCoursesAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    int index = 0;
    //vars
    private List<Item> itemsList = new ArrayList<>();

    private Context mContext;

    public RecyclerViewCoursesAdapter(Context context, List<Item> items) {
        itemsList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //holder.cardView.setBackgroundResource(R.drawable.combinedshape);
        holder.cardView.setTag(itemsList.get(position).getId());
        Glide.with(mContext)
                .asBitmap()
                .load(itemsList.get(position).getIcon())
                .into(holder.image);
        holder.title.setText(itemsList.get(position).getTitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                notifyDataSetChanged();
            }
        });

        if(index == position){
            holder.cardView.setBackgroundResource(R.drawable.combinedshape);
        }
        else{
            holder.cardView.setBackgroundResource(R.drawable.normalshape);
        }

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout cardView;
        ImageView image;
        TextView title;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);

        }
    }
}