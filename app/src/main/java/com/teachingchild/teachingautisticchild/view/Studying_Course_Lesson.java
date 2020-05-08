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
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.adapter.BadgesRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.DoctorRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.ProblemandSolutionRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.ScientificMethodRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.StepsRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.TestRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Bold;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.model.Badges;
import com.teachingchild.teachingautisticchild.model.DoctorAdvice;
import com.teachingchild.teachingautisticchild.model.Item;
import com.teachingchild.teachingautisticchild.model.ProblemSolution;
import com.teachingchild.teachingautisticchild.model.Profiles;
import com.teachingchild.teachingautisticchild.model.ScitentificMethods;
import com.teachingchild.teachingautisticchild.viewmodel.DoctorListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.FoodListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ProblemandSolutionListViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.UserActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class Studying_Course_Lesson extends AppCompatActivity {

    RecyclerView recyclerView,recycleViewDoctor,recycleViewProblemSolution;
    StepsRecyclerViewAdapter stepsAdapter;
    ScientificMethodRecyclerViewAdapter scientificAdapter;
    DoctorRecyclerViewAdapter doctorlistAdapter;
    ProblemandSolutionRecyclerViewAdapter problemandsolutionAdapter;
    ArrayList<Profiles> itemList;
    ArrayList<DoctorAdvice> doctorList;
    ArrayList<ProblemSolution> problemsolutionList;
    TextView foodListTxt,doctorTxt,problemandsolutionTxt;
    View view2;
    LinearLayout imageLayout,linear1,linear2,linear3,linear4;
    CardView cardView;
    ImageView imageView,backImg;
    String user_id;
    FoodListViewModel foodListViewModel;
    DoctorListViewModel doctorListViewModel;
    ProblemandSolutionListViewModel problemandSolutionListViewModel;
    MyTextView_HK_Grotesk_Display_Bold foodlist1,foodlist2,foodlist3,foodlist4;
    JustifyTextView foodlist1description,foodlist2description,foodlist3description,foodlist4description;
    Intent intent;
    String value;
    TextView title;
    int size;
    int y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studying__course__lesson);

        user_id = PreferenceMangement.getPreference(Studying_Course_Lesson.this,"user_id");

        itemList = new ArrayList<>();
        doctorList = new ArrayList<>();
        problemsolutionList = new ArrayList<>();

        intent = getIntent();
        value = intent.getStringExtra("value");

        title = findViewById(R.id.title);
        recyclerView = findViewById(R.id.recycleView);
        recycleViewDoctor = findViewById(R.id.recycleViewDoctor);
        recycleViewProblemSolution = findViewById(R.id.recycleViewProblemAndSolution);

        foodListTxt = findViewById(R.id.foodListTxt);
        doctorTxt = findViewById(R.id.doctorTxt);
        problemandsolutionTxt = findViewById(R.id.problemandsolutionTxt);
        view2 = findViewById(R.id.view2);
        imageLayout = findViewById(R.id.imageLinearLayout);
        linear1 = findViewById(R.id.linearLayout1);
        linear2 = findViewById(R.id.linearLayout2);
        linear3 = findViewById(R.id.linearLayout3);
        linear4 = findViewById(R.id.linearLayout4);
        cardView = findViewById(R.id.cardView);
        imageView = findViewById(R.id.imageView);
        backImg = findViewById(R.id.image);

        foodlist1 = findViewById(R.id.foodlist1);
        foodlist1description = findViewById(R.id.foodlist1description);

        foodlist2 = findViewById(R.id.foodlist2);
        foodlist2description = findViewById(R.id.foodlist2description);

        foodlist3 = findViewById(R.id.foodlist3);
        foodlist3description = findViewById(R.id.foodlist3description);

        foodlist4 = findViewById(R.id.foodlist4);
        foodlist4description = findViewById(R.id.foodlist4description);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HKGrotesk-Regular.otf");
        foodlist1description.setTypeface(tf);
        foodlist2description.setTypeface(tf);
        foodlist3description.setTypeface(tf);
        foodlist4description.setTypeface(tf);

        if(value.equalsIgnoreCase("0")){
            title.setText("Food List");
            view2.setVisibility(View.VISIBLE);
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.VISIBLE);
            linear4.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
            recycleViewDoctor.setVisibility(View.GONE);
            recycleViewProblemSolution.setVisibility(View.GONE);

            getFoodlist();
        }else if(value.equalsIgnoreCase("1")){
            title.setText("Doctor Advices");
            itemList.clear();
            doctorList.clear();
            problemsolutionList.clear();

            view2.setVisibility(View.GONE);
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.GONE);
            linear3.setVisibility(View.GONE);
            linear4.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            recycleViewDoctor.setVisibility(View.VISIBLE);
            recycleViewProblemSolution.setVisibility(View.GONE);

            getDoctorList();
        }else if(value.equalsIgnoreCase("2")){
            title.setText("Problem And Solutions");
            itemList.clear();
            problemsolutionList.clear();
            doctorList.clear();

            view2.setVisibility(View.GONE);
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.GONE);
            linear3.setVisibility(View.GONE);
            linear4.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            recycleViewProblemSolution.setVisibility(View.VISIBLE);
            recycleViewDoctor.setVisibility(View.GONE);

            getProblemandSolution();
        }


        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Studying_Course_Lesson.this,Courses_Courses.class);
                startActivity(intent);
                finish();
            }
        });


//        cardView.setVisibility(View.GONE);
//        recycleViewDoctor.setVisibility(View.GONE);
//        recycleViewProblemSolution.setVisibility(View.GONE);



        foodListTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("Food List");
                view2.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.VISIBLE);
                linear3.setVisibility(View.VISIBLE);
                linear4.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.GONE);
                recycleViewDoctor.setVisibility(View.GONE);
                recycleViewProblemSolution.setVisibility(View.GONE);
                
                getFoodlist();
            }
        });

        doctorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("Doctor Advices");
                itemList.clear();
                doctorList.clear();
                problemsolutionList.clear();

                view2.setVisibility(View.GONE);
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                linear3.setVisibility(View.GONE);
                linear4.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                recycleViewDoctor.setVisibility(View.VISIBLE);
                recycleViewProblemSolution.setVisibility(View.GONE);

                getDoctorList();

            }
        });

        problemandsolutionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("Problem And Solutions");
                itemList.clear();
                problemsolutionList.clear();
                doctorList.clear();

                view2.setVisibility(View.GONE);
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                linear3.setVisibility(View.GONE);
                linear4.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                recycleViewProblemSolution.setVisibility(View.VISIBLE);
                recycleViewDoctor.setVisibility(View.GONE);
                //imageView.setVisibility(View.GONE);

                getProblemandSolution();

            }
        });

    }

    private void getProblemandSolution() {

        problemsolutionList.clear();
        Helper.showLoader(Studying_Course_Lesson.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        problemandSolutionListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProblemandSolutionListViewModel.class);
        problemandSolutionListViewModel.initialize(getApplication(),reqJsonObj);
        problemandSolutionListViewModel.getTopics().observe(Studying_Course_Lesson.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                size = hashMaps.size();
                for (int x= 0; x<hashMaps.size();x++) {
                    String ps_id = hashMaps.get(x).get("ps_id");
                    String ps_name = hashMaps.get(x).get("ps_name");
                    String ps_description = hashMaps.get(x).get("ps_description");
                    String ps_hint = hashMaps.get(x).get("ps_hint");

                    y = x+1;
                    String advice = "Problem-"+y;

                    problemsolutionList.add(new ProblemSolution(ps_id,advice,ps_name,ps_hint,null,1));
                    GridLayoutManager layoutManagerProblemsolution = new GridLayoutManager(Studying_Course_Lesson.this,1);
                    layoutManagerProblemsolution.setOrientation(LinearLayoutManager.VERTICAL);
                    recycleViewProblemSolution.setLayoutManager(layoutManagerProblemsolution);
                    problemandsolutionAdapter = new ProblemandSolutionRecyclerViewAdapter(Studying_Course_Lesson.this,problemsolutionList);
                    recycleViewProblemSolution.setAdapter(problemandsolutionAdapter);
                }

                recycleViewProblemSolution.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recycleViewProblemSolution, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        LinearLayout linearView = view.findViewById(R.id.linearLayout);

                        Intent intent = new Intent(Studying_Course_Lesson.this,ProblemandSolutionDescription.class);
                        intent.putExtra("problem_id",linearView.getTag().toString());
                        intent.putExtra("size",size);
                        startActivity(intent);
                        finish();
                        Log.e("cardviewlog",linearView.getTag().toString());
                        //Log.e("cardviewlog",""+position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }) );
            }
        });
    }

    private void getDoctorList() {
        doctorList.clear();
        Helper.showLoader(Studying_Course_Lesson.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        doctorListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DoctorListViewModel.class);
        doctorListViewModel.initialize(getApplication(),reqJsonObj);
        doctorListViewModel.getTopics().observe(Studying_Course_Lesson.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                        String advice_id = hashMaps.get(x).get("advice_id");
                        String doctor_name = hashMaps.get(x).get("doctor_name");
                        String advice_name = hashMaps.get(x).get("advice_name");
                        String advice_description = hashMaps.get(x).get("advice_description");

                        z = x+1;
                        String advice = "Advice-"+z;

                        doctorList.add(new DoctorAdvice(advice_id,advice,doctor_name,advice_name,null,1));
                        GridLayoutManager layoutManagerdoctorList = new GridLayoutManager(Studying_Course_Lesson.this,1);
                        layoutManagerdoctorList.setOrientation(LinearLayoutManager.VERTICAL);
                        recycleViewDoctor.setLayoutManager(layoutManagerdoctorList);
                        doctorlistAdapter = new DoctorRecyclerViewAdapter(Studying_Course_Lesson.this,doctorList);
                        recycleViewDoctor.setAdapter(doctorlistAdapter);

                }

                recycleViewDoctor.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recycleViewDoctor, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        LinearLayout linearView = view.findViewById(R.id.linearLayout);

                        Intent intent = new Intent(Studying_Course_Lesson.this,Doctor_Advice_Description.class);
                        intent.putExtra("advice_id",linearView.getTag().toString());
                        startActivity(intent);
                        finish();
                        Log.e("cardviewlog1",linearView.getTag().toString());
                        //Log.e("cardviewlog1",""+position);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }) );
            }
        });
    }

    private void getFoodlist() {
        Helper.showLoader(Studying_Course_Lesson.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        foodListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(FoodListViewModel.class);
        foodListViewModel.initialize(getApplication(),reqJsonObj);
        foodListViewModel.getTopics().observe(Studying_Course_Lesson.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                    if(x==0){
                        String food_id = hashMaps.get(0).get("food_id");
                        String food_name = hashMaps.get(0).get("food_name");
                        String food_description = hashMaps.get(0).get("food_description");

                        foodlist1.setText(food_name);
                        foodlist1description.setText(food_description);
                    }
                    if(x==1){
                        String food_id = hashMaps.get(1).get("food_id");
                        String food_name = hashMaps.get(1).get("food_name");
                        String food_description = hashMaps.get(1).get("food_description");

                        foodlist2.setText(food_name);
                        foodlist2description.setText(food_description);
                    }
                    if(x==2){
                        String food_id = hashMaps.get(2).get("food_id");
                        String food_name = hashMaps.get(2).get("food_name");
                        String food_description = hashMaps.get(2).get("food_description");

                        foodlist3.setText(food_name);
                        foodlist3description.setText(food_description);
                    }
                    if(x==3){
                        String food_id = hashMaps.get(3).get("food_id");
                        String food_name = hashMaps.get(3).get("food_name");
                        String food_description = hashMaps.get(3).get("food_description");

                        foodlist4.setText(food_name);
                        foodlist4description.setText(food_description);
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Studying_Course_Lesson.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
