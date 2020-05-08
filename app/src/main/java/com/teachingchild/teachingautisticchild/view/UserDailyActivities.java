package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.TestRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.model.Item;
import com.teachingchild.teachingautisticchild.model.ScitentificMethods;
import com.teachingchild.teachingautisticchild.viewmodel.SubTopicListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.TopoicListViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDailyActivities extends AppCompatActivity {

    Intent intent;
    String topic_id;
    SubTopicListViewModel subTopicListViewModel;
    RecyclerView recyclerView;
    ArrayList<ScitentificMethods> subTopicLIst;
    RecyclerView recycleViewSteps;
    TestRecyclerViewAdapter testAdapter;
    ImageView backimg;
    TextView ofWhat;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_daily_activities);

        recycleViewSteps = findViewById(R.id.recycleViewSteps);
        ofWhat = findViewById(R.id.ofwhat);
        backimg = findViewById(R.id.image);
        subTopicLIst = new ArrayList<>();
        intent = getIntent();
        topic_id = intent.getStringExtra("topic_id");
        Log.e("topicid",topic_id);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserDailyActivities.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Log.e("topic_id",topic_id);
        getListOfSubActivity();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(UserDailyActivities.this,HomeActivity.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getListOfSubActivity() {
        Helper.showLoader(UserDailyActivities.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("topic_id",topic_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        subTopicListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SubTopicListViewModel.class);
        subTopicListViewModel.initialize(getApplication(),reqJsonObj);
        subTopicListViewModel.getTopics().observe(UserDailyActivities.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Log.e("hasmap", String.valueOf(hashMaps.size()));
                Helper.cancelLoader();
                for (int x = 0; x < hashMaps.size(); x++) {
                    String subtopic_id = hashMaps.get(x).get("subtopic_id");
                    String subtopic_name = hashMaps.get(x).get("subtopic_name");
                    String short_des = hashMaps.get(x).get("short_des");
                    String subtopic_image = hashMaps.get(x).get("subtopic_image");
                    int status = 1;

                    num = x + 1;
                    String subtopic_header = "SubTopic"+" "+num;

                    ofWhat.setText(""+hashMaps.size()+" lessons");

                    subTopicLIst.add(new ScitentificMethods(subtopic_id,subtopic_header, subtopic_name, short_des, subtopic_image, status));
                    GridLayoutManager layoutManager = new GridLayoutManager(UserDailyActivities.this, 1);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycleViewSteps.setLayoutManager(layoutManager);
                    testAdapter = new TestRecyclerViewAdapter(UserDailyActivities.this, subTopicLIst);
                    recycleViewSteps.setAdapter(testAdapter);
                    recycleViewSteps.scrollToPosition(1);
                }

                recycleViewSteps.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recycleViewSteps, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
                        Intent intent = new Intent(UserDailyActivities.this, SubTopicsDetails.class);
                        intent.putExtra("topic_id", topic_id);
                        intent.putExtra("sub_topic_id", linearLayout.getTag().toString());
                        startActivity(intent);
                        Log.e("cardviewlog", linearLayout.getTag().toString());
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

        });
    }

}
