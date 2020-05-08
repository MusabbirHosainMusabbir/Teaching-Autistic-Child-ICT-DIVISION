package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.CircleImageView;
import com.teachingchild.teachingautisticchild.model.Profiles;

import java.util.ArrayList;
import java.util.List;

public class StepsRecyclerViewAdapter extends RecyclerView.Adapter<StepsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Profiles> itemsList = new ArrayList<>();

    private Context mContext;

    public StepsRecyclerViewAdapter(Context context, List<Profiles> items) {
        itemsList = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_steps, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(itemsList.get(position).getImage())
//                .into(holder.image);
        holder.title.setText(itemsList.get(position).getName());
        holder.subtitle.setText(itemsList.get(position).getNumbers());


        //holder.image.setBackgroundResource(R.drawable.pharmacist);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView title,subtitle,number;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profileImg);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);


        }
    }
}