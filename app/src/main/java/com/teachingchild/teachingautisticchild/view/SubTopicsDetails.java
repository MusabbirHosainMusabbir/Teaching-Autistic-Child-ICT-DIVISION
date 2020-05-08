package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.adapter.TestRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Bold;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.model.ScitentificMethods;
import com.teachingchild.teachingautisticchild.viewmodel.SubTopicListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.SubTopicViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.UserActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class SubTopicsDetails extends AppCompatActivity {

    Intent intent;
    String user_id,sub_topic_id,topic_id;
    SubTopicViewModel subTopicListViewModel;
    UserActivityViewModel userActivityViewModel;
    TextView titleTxt;
    MyTextView_HK_Grotesk_Display_Bold shortDescriptionTxt;
    JustifyTextView descriptionTxt;
    ImageView image,backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_topics_details);

        backimg = findViewById(R.id.image);

        titleTxt = findViewById(R.id.title);
        shortDescriptionTxt = findViewById(R.id.shortDescription);
        descriptionTxt = findViewById(R.id.description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HKGrotesk-Regular.otf");
        descriptionTxt.setTypeface(tf);

        image = findViewById(R.id.imageView);



        user_id = PreferenceMangement.getPreference(SubTopicsDetails.this,"user_id");
        Log.e("userid",user_id);

        intent = getIntent();
        sub_topic_id = intent.getStringExtra("sub_topic_id");
        topic_id = intent.getStringExtra("topic_id");
        Log.e("subtopicid",sub_topic_id);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SubTopicsDetails.this,UserDailyActivities.class);
                intent.putExtra("topic_id",topic_id);
                startActivity(intent);
                finish();
            }
        });

        SendUserActivity();
        getDetails();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SubTopicsDetails.this,UserDailyActivities.class);
            intent.putExtra("topic_id",topic_id);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void SendUserActivity() {
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("sub_topic_id",sub_topic_id);
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        userActivityViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserActivityViewModel.class);
        userActivityViewModel.initialize(getApplication(),reqJsonObj);
        userActivityViewModel.getStatus().observe(SubTopicsDetails.this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                Helper.cancelLoader();

                Log.e("status",stringStringHashMap.get("status"));
                if (stringStringHashMap.get("status").equals("1")) {
                    //Toast.makeText(SubTopicsDetails.this, ""+stringStringHashMap.get("message"), Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(SubTopicsDetails.this, ""+stringStringHashMap.get("message"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getDetails() {
        Helper.showLoader(SubTopicsDetails.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("sub_topic_id",sub_topic_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        subTopicListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SubTopicViewModel.class);
        subTopicListViewModel.initialize(getApplication(),reqJsonObj);
        subTopicListViewModel.getStatus().observe(SubTopicsDetails.this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                Helper.cancelLoader();

                String subtopic_name = stringStringHashMap.get("sub_topic_name");
                String short_des = stringStringHashMap.get("short_des");
                String description = stringStringHashMap.get("description");
                String subtopic_image = stringStringHashMap.get("sub_topic_img");

                titleTxt.setText(subtopic_name);
                shortDescriptionTxt.setText(short_des);
                descriptionTxt.setText(description);

                Glide.with(SubTopicsDetails.this)
                        .asBitmap()
                        .load(subtopic_image)
                        .into(image);

                }
           });
        }
    }
