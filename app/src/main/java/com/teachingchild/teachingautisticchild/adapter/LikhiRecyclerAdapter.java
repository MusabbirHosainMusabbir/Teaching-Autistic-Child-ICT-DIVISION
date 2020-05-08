package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.teachingchild.teachingautisticchild.R;

import java.util.ArrayList;

public class LikhiRecyclerAdapter extends RecyclerView.Adapter<LikhiRecyclerAdapter.ViewHolder> {

    private  ArrayList<Integer>images;
    private Context mContext;
    int index = -1;
    View view;

    public LikhiRecyclerAdapter(Context mContext, ArrayList<Integer> images) {
        this.images = images;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.borno_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position)); // You can tweak with the effects here
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        if(index==position){
             animate(holder.imageView);
            //holder.imageView.setBackgroundColor(Color.parseColor("#FF4081"));
            //holder.country.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            //holder.imageView.setBackgroundColor(Color.TRANSPARENT);
            //holder.country.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            imageView = itemView.findViewById(R.id.likhi_image);

        }
    }

    private void animate(ImageView answ) {
        final Animation myAnim = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
        answ.startAnimation(myAnim);
    }
}
