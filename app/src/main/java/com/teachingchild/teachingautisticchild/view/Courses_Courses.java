package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyachi.stepview.bean.StepBean;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListenerHome;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewAdapterCourseListAll;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewCoursesAdapter;
import com.teachingchild.teachingautisticchild.model.Courses;
import com.teachingchild.teachingautisticchild.model.Item;
import com.teachingchild.teachingautisticchild.viewmodel.DailyActivitySubTopicListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.DailyElementsListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.TopoicListViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class Courses_Courses extends AppCompatActivity {

    RecyclerView recyclerView,recycleViewCourses;
    ArrayList<Item> itemList;
    ArrayList<StepBean> addList;
    RecyclerViewCoursesAdapter recyclerViewAdapter;
    RecyclerViewAdapterCourseListAll recycleviewCoursesAll;
    TextView visual,speechandlanguage,outdoorTxt;
    SmoothBottomBar smoothBottomBar;
    Intent intent;
    String user_id;
    String topic_id,topic_name,topic_image;
    int total_sub_topic,user_sub_topic;
    TopoicListViewModel topoicListViewModel;
    double progress;
    DailyActivitySubTopicListViewModel subTopicListViewModel;
    NestedScrollView nestedScrollView;
    List<StepBean> stepsBeanList = new ArrayList<StepBean>();
    List<List<StepBean>> stepsBeanListmain = new ArrayList<>();
    String age,weight;
    String element_id,element_name,element_description;
    DailyElementsListViewModel dailyElementsListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses__courses);
        Utils.fullScreenView(this,false);

        user_id = PreferenceMangement.getPreference(Courses_Courses.this,"user_id");
        age = PreferenceMangement.getPreference(Courses_Courses.this,"age");
        weight = PreferenceMangement.getPreference(Courses_Courses.this,"weight");

        Log.e("user_id",user_id);
        Log.e("age",age);
        Log.e("weight",weight);

        addList = new ArrayList<>();
        visual = findViewById(R.id.visualcomparison);
        speechandlanguage = findViewById(R.id.speechandlanguage);
        outdoorTxt = findViewById(R.id.outdoorActivitites);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        getData();

        visual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Courses_Courses.this,Visual_Compression.class);
                startActivity(intent);
                finish();
            }
        });

        speechandlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Courses_Courses.this,SpeechAndLanguage.class);
                startActivity(intent);
                finish();
            }
        });
        outdoorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Courses_Courses.this,OutdoorActivities.class);
                startActivity(intent);
                finish();
            }
        });


        itemList = new ArrayList<>();
        itemList.add(new Item("1",R.drawable.daily_activities,"Daily Activities"));
        itemList.add(new Item("2",R.drawable.daily_elements,"Daily Elements"));
        itemList.add(new Item("3",R.drawable.pharmacist,"Child therapy Center"));
        itemList.add(new Item("4",R.drawable.study,"Others Feature"));


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewCoursesAdapter(Courses_Courses.this,itemList);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListenerHome(getApplicationContext(),recyclerView, new RecyclerTouchListenerHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                LinearLayout cardView = view.findViewById(R.id.cardview);
                Log.e("cardviewlog",cardView.getTag().toString());

                if(cardView.getTag().toString().equals("1")){
                    getData();
                    recyclerView.smoothScrollToPosition(0);
                }else if(cardView.getTag().toString().equals("2")){
                    getElementsData();
                    recyclerView.smoothScrollToPosition(2);

                }else if(cardView.getTag().toString().equals("3")){
                    Intent intent = new Intent(Courses_Courses.this,ChildTherapyCenter.class);
                    intent.putExtra("value","0");
                    startActivity(intent);
                    finish();

                } else{
                    Intent intent = new Intent(Courses_Courses.this,Studying_Course_Lesson.class);
                    intent.putExtra("value","0");
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) );


        smoothBottomBar = findViewById(R.id.bottomBar);
        smoothBottomBar.setActiveItem(1);
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int pos) {
                Log.e("post", String.valueOf(pos));
                if(pos ==0){
                    intent = new Intent(Courses_Courses.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pos == 1){
                    intent = new Intent(Courses_Courses.this,Courses_Courses.class);
                    startActivity(intent);
                    finish();
                }else if(pos == 2){
                    intent = new Intent(Courses_Courses.this,ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void getElementsData() {
        stepsBeanList.clear();
        stepsBeanListmain.clear();
        Log.e("userid",user_id);
        Helper.showLoader(Courses_Courses.this,"");


        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        dailyElementsListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DailyElementsListViewModel.class);
        dailyElementsListViewModel.initialize(getApplication(),reqJsonObj);
        dailyElementsListViewModel.getTopics().observe(Courses_Courses.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x = 0; x < hashMaps.size(); x++) {
                    element_id = hashMaps.get(x).get("element_id");
                    element_name = hashMaps.get(x).get("element_name");
                    element_description = hashMaps.get(x).get("element_description");

                    StepBean stepbean = new StepBean(element_name,element_description,1,0.0);
                    stepsBeanList.add(stepbean);
                    stepsBeanListmain.add(stepsBeanList);
                }


                recycleViewCourses = findViewById(R.id.recycleViewCourses);
                recycleViewCourses.setNestedScrollingEnabled(false);
                GridLayoutManager layoutManager1 = new GridLayoutManager(Courses_Courses.this,1);
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recycleViewCourses.setLayoutManager(layoutManager1);
                recycleviewCoursesAll = new RecyclerViewAdapterCourseListAll(Courses_Courses.this,stepsBeanListmain);
                recycleViewCourses.setAdapter(recycleviewCoursesAll);
            }
        });
    }

    private void getData() {
        stepsBeanList.clear();
        stepsBeanListmain.clear();
        Log.e("userid",user_id);
        Helper.showLoader(Courses_Courses.this,"");


        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        topoicListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TopoicListViewModel.class);
        topoicListViewModel.initialize(getApplication(),reqJsonObj);
        topoicListViewModel.getTopics().observe(Courses_Courses.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                for (int x = 0; x < hashMaps.size(); x++) {
                    topic_id = hashMaps.get(x).get("topic_id");
                    topic_name = hashMaps.get(x).get("topic_name");
                    topic_image = hashMaps.get(x).get("topic_image");
                    total_sub_topic = Integer.parseInt(hashMaps.get(x).get("total_sub_topic"));
                    user_sub_topic = Integer.parseInt(hashMaps.get(x).get("user_sub_topic"));

                    if (total_sub_topic == 0) {
                        progress = 0.0;
                    } else {
                        progress = user_sub_topic / total_sub_topic*100;
                    }

                    getSubTopicData(topic_name,topic_id,progress);

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Log.e("size",stepsBeanListmain.size()+"");
                    }
                },5000);

            }
        });
    }


    private void getSubTopicData(String topic_name,String topic_id,double progress) {
        Log.e("topic_idvalue",topic_id+"   topic_name="+topic_name);
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("topic_id",topic_id);
            reqJsonObj.put("user_id",user_id);

            Log.e("userjson",reqJsonObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        subTopicListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DailyActivitySubTopicListViewModel.class);
        subTopicListViewModel.initialize(getApplication(),reqJsonObj);
        subTopicListViewModel.getTopics().observe(Courses_Courses.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Log.e("hasmap", String.valueOf(hashMaps.size()));
                Helper.cancelLoader();
                stepsBeanList.clear();
                for (int x = 0; x < hashMaps.size(); x++) {
                    List <StepBean> step = new ArrayList<>();
                    String subtopic_id = hashMaps.get(x).get("subtopic_id");
                    String subtopic_name = hashMaps.get(x).get("subtopic_name");
                    String short_des = hashMaps.get(x).get("short_des");
                    String statusvalue = hashMaps.get(x).get("status");

                    Log.e("subtopicname",subtopic_name);
                    int status = Integer.parseInt(statusvalue);

                    StepBean stepbean = new StepBean(topic_name,subtopic_name,status,progress);
                    step.add(stepbean);
                    stepsBeanListmain.add(step);
                }

                recycleViewCourses = findViewById(R.id.recycleViewCourses);
                recycleViewCourses.setNestedScrollingEnabled(false);
                GridLayoutManager layoutManager1 = new GridLayoutManager(Courses_Courses.this,1);
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recycleViewCourses.setLayoutManager(layoutManager1);
                recycleviewCoursesAll = new RecyclerViewAdapterCourseListAll(Courses_Courses.this,stepsBeanListmain);
                recycleViewCourses.setAdapter(recycleviewCoursesAll);



            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Courses_Courses.this,HomeActivity.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
