package com.teachingchild.teachingautisticchild.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyachi.stepview.VerticalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.teachingchild.teachingautisticchild.R;

import java.util.ArrayList;
import java.util.List;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class RecyclerViewAdapterCourseListAll extends RecyclerView.Adapter<RecyclerViewAdapterCourseListAll.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    static private List<List<StepBean>> itemsList = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapterCourseListAll(Context context, List<List<StepBean>> items) {
        itemsList = items;

        Log.e("itelist", String.valueOf(itemsList.size()));
//        for(int i=0; i<itemsList.size(); i++){
//            Log.e("valuesss",items.get(i).getName());
//        }
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_all_list_courses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called.");

       //Log.e("status", String.valueOf(itemsList.get(position).get(0).getState()));
        holder.title.setText(itemsList.get(position).get(0).getTopicname());
        //holder.image.setBackgroundResource(R.drawable.pharmacist);
        holder.verticalStepView
                .reverseDraw(false)
                .setStepViewTexts(itemsList.get(position))//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorComplectingPosition(itemsList.get(position).get(0).getState())
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(mContext, R.color.linecolor1))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(mContext, R.color.linecolor))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(mContext, R.color.textcolor))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(mContext, R.color.subtextcolor))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(mContext, R.drawable.right))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(mContext, R.drawable.defaulticon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(mContext, R.drawable.oval));//设置StepsViewIndicator AttentionIcon

        holder.circularProgressIndicator.setMaxProgress(100);
        //circularProgressIndicator.setCurrentProgress(5000);
// or all at once
        //int progress = pos.size()+1/itemsList.size();
        //Log.e("progress", String.valueOf(progress));
        holder.circularProgressIndicator.setProgress(itemsList.get(position).get(0).getPercentage(),100);

        holder.circularProgressIndicator.setProgressColor(Color.parseColor("#00D9CD"));

        //holder.cardView.addView(holder.verticalStepView);
    }


    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;
        CircularProgressIndicator circularProgressIndicator;
        VerticalStepView verticalStepView;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            verticalStepView = itemView.findViewById(R.id.step_view);
            circularProgressIndicator = itemView.findViewById(R.id.circular_progress);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}