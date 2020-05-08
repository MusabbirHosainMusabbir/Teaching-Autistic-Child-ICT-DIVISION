package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Bold;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.viewmodel.DoctorAdviewViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ProblemSolutionViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class ProblemandSolutionDescription extends AppCompatActivity {

    TextView number,what;
    MyTextView_HK_Grotesk_Display_Bold solution_headTxt;
    JustifyTextView solution_descriptionTxt;
    Intent intent;
    String problem_id;
    ProblemSolutionViewModel problemSolutionViewModel;
    ImageView backimg;
    int size,numberval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problemand_solution_description);

        intent = getIntent();
        problem_id = intent.getStringExtra("problem_id");
        size = intent.getIntExtra("size",0);

//        number = findViewById(R.id.number);
//        what = findViewById(R.id.ofwhat);

        solution_headTxt = findViewById(R.id.shortDescription);
        solution_descriptionTxt = findViewById(R.id.description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HKGrotesk-Regular.otf");
        solution_descriptionTxt.setTypeface(tf);

        backimg = findViewById(R.id.image);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ProblemandSolutionDescription.this,Studying_Course_Lesson.class);
                intent.putExtra("value","2");
                startActivity(intent);
                finish();
            }
        });

        getDetails();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ProblemandSolutionDescription.this,Studying_Course_Lesson.class);
            intent.putExtra("value","2");
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getDetails() {
        Helper.showLoader(ProblemandSolutionDescription.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("ps_id",problem_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

       // what.setText("of"+" "+size);


        problemSolutionViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProblemSolutionViewModel.class);
        problemSolutionViewModel.initialize(getApplication(),reqJsonObj);
        problemSolutionViewModel.getTopics().observe(ProblemandSolutionDescription.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                    String ps_id = hashMaps.get(x).get("ps_id");
                    String ps_name = hashMaps.get(x).get("ps_name");
                    String ps_description = hashMaps.get(x).get("ps_description");
                    solution_headTxt.setText(ps_name);
                    solution_descriptionTxt.setText(ps_description);

                    //number.setText(""+numberval);
                }
            }
        });
    }
}
