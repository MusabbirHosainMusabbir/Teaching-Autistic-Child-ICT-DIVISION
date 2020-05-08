package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.CircleImageView;
import com.teachingchild.teachingautisticchild.model.Badges;
import com.teachingchild.teachingautisticchild.model.Profiles;

import java.util.ArrayList;
import java.util.List;

public class BadgesRecyclerViewAdapter extends RecyclerView.Adapter<BadgesRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Badges> itemsList = new ArrayList<>();

    private Context mContext;

    public BadgesRecyclerViewAdapter(Context context, List<Badges> items) {
        itemsList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_badges, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //getDrawable(holder.image);
        //holder.image.setBackgroundResource(getDrawable(););
        holder.image.setBackgroundResource(itemsList.get(position).getImage());
        holder.title.setText(itemsList.get(position).getName());
        holder.subtitle.setText(itemsList.get(position).getSubtitles());

        //holder.image.setBackgroundResource(R.drawable.pharmacist);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,subtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profileImg);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);

        }
    }
}