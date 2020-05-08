package com.teachingchild.teachingautisticchild.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PaintView;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.model.Item;
import com.teachingchild.teachingautisticchild.viewmodel.TopoicListViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.ibrahimsn.lib.OnItemReselectedListener;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> itemList;
    RecyclerViewAdapter recyclerViewAdapter;
    SmoothBottomBar smoothBottomBar;
    Intent intent;
    String user_id,name;
    TopoicListViewModel topoicListViewModel;
    MyTextView_HK_Grotesk_Display_Regular nameTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Utils.fullScreenView(this,false);

        PreferenceMangement.savePreference(HomeActivity.this,"looggedin","1");

         user_id = PreferenceMangement.getPreference(HomeActivity.this,"user_id");
         Log.e("userid",user_id);
         name = PreferenceMangement.getPreference(HomeActivity.this,"name");

        if (isOnline()) {
            getData();
            //do whatever you want to do
        } else {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("No internet Connection");
                builder.setMessage("Please turn on internet connection to continue");
                builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } catch (Exception e) {
                // Log.d(Constants.TAG, "Show Dialog: " + e.getMessage());
            }
        }


        itemList = new ArrayList<>();

         nameTxt = findViewById(R.id.name);
         nameTxt.setText(name);

        smoothBottomBar = findViewById(R.id.bottomBar);
        smoothBottomBar.setActiveItem(0);

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int pos) {
                Log.e("post", String.valueOf(pos));
                if(pos == 1){
                    intent = new Intent(HomeActivity.this,Courses_Courses.class);
                    startActivity(intent);
                    finish();
                }else if(pos == 2){
                    intent = new Intent(HomeActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(HomeActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void getData() {
        Log.e("userid",user_id);
        Helper.showLoader(HomeActivity.this,"");


        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        topoicListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TopoicListViewModel.class);
        topoicListViewModel.initialize(getApplication(),reqJsonObj);
        topoicListViewModel.getTopics().observe(HomeActivity.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                double progress;
                for (int x= 0; x<hashMaps.size();x++) {
                   String topic_id = hashMaps.get(x).get("topic_id");
                   String topic_name = hashMaps.get(x).get("topic_name");
                   String topic_image = hashMaps.get(x).get("topic_image");
                   int total_sub_topic = Integer.parseInt(hashMaps.get(x).get("total_sub_topic"));
                   int user_sub_topic = Integer.parseInt(hashMaps.get(x).get("user_sub_topic"));

                   if(total_sub_topic == 0){
                       progress = 0.0;
                   }else{
                       progress = user_sub_topic/total_sub_topic*100;
                   }



                    itemList.add(new Item(topic_id,topic_image, (int) progress,topic_name,topic_name,user_sub_topic,total_sub_topic));

                    recyclerView = findViewById(R.id.recycleView);
                    GridLayoutManager layoutManager = new GridLayoutManager(HomeActivity.this,1);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerViewAdapter = new RecyclerViewAdapter(HomeActivity.this,itemList);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        CardView cardView = view.findViewById(R.id.cardView);

                        Intent intent = new Intent(HomeActivity.this,UserDailyActivities.class);
                        intent.putExtra("topic_id",cardView.getTag().toString());
                        startActivity(intent);
                        finish();
                        Log.e("cardviewlog",cardView.getTag().toString());
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }) );
            }


        });
    }


}
