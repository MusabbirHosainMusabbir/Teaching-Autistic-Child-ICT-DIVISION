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

import com.bumptech.glide.Glide;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Bold;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.viewmodel.DoctorAdviewViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.SubTopicViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class Doctor_Advice_Description extends AppCompatActivity {

    Intent intent;
    String advice_id;
    DoctorAdviewViewModel doctorAdviewViewModel;
    TextView doctorName;
    MyTextView_HK_Grotesk_Display_Bold advice_nameTxt;
    JustifyTextView advice_descriptionTxt;
    ImageView backimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__advice__description);

        intent = getIntent();
        advice_id = intent.getStringExtra("advice_id");

        doctorName = findViewById(R.id.title);
        advice_nameTxt = findViewById(R.id.advice_name);
        advice_descriptionTxt = findViewById(R.id.advice_description);
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/HKGrotesk-Regular.otf");
        advice_descriptionTxt.setTypeface(tf);

        backimg = findViewById(R.id.image);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Doctor_Advice_Description.this,Studying_Course_Lesson.class);
                intent.putExtra("value","1");
                startActivity(intent);
                finish();
            }
        });

        getDetails();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Doctor_Advice_Description.this,Studying_Course_Lesson.class);
            intent.putExtra("value","1");
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void getDetails() {
        Helper.showLoader(Doctor_Advice_Description.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("advice_id",advice_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        doctorAdviewViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DoctorAdviewViewModel.class);
        doctorAdviewViewModel.initialize(getApplication(),reqJsonObj);
        doctorAdviewViewModel.getTopics().observe(Doctor_Advice_Description.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                        String advice_id = hashMaps.get(x).get("advice_id");
                        String doctor_name = hashMaps.get(x).get("doctor_name");
                        String advice_name = hashMaps.get(x).get("advice_name");
                        String advice_description = hashMaps.get(x).get("advice_description");

                        doctorName.setText(doctor_name);
                        advice_nameTxt.setText(advice_name);
                        advice_descriptionTxt.setText(advice_description);
                    }
               }
        });
    }
}
