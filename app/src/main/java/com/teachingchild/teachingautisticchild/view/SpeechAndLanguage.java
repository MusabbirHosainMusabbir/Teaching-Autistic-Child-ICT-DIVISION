package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.MyBounce;
import com.teachingchild.teachingautisticchild.Utils.PrimaryTools;
import com.teachingchild.teachingautisticchild.Utils.Shaker;

import java.util.Random;

public class SpeechAndLanguage extends AppCompatActivity {

    ImageView back,sorob,banjo,sonk,reload;
    LinearLayout img;
    PrimaryTools tools;
    TextView option;
    ImageView answ;

    int[] soroborno ={R.drawable.soro_a,R.drawable.soro_b,R.drawable.soro_c,R.drawable.soro_d,R.drawable.soro_e,R.drawable.soro_f,R.drawable.soro_g,
            R.drawable.soro_h,R.drawable.soro_i,R.drawable.soro_j,R.drawable.soro_k};
    int[] soroImgResource = {R.drawable.img_ojogor,R.drawable.img_amm,R.drawable.img_ilish,R.drawable.img_eagle,R.drawable.img_ut,R.drawable.img_usha,R.drawable.img_rishi,
            R.drawable.img_ektara,R.drawable.img_oirabot,R.drawable.img_ojon,R.drawable.img_oishod};
    //int[] soroAnsResource={R.drawable.ans_oa,R.drawable.ans_aa,R.drawable.ans_e,R.drawable.ans_ee,R.drawable.ans_u,R.drawable.ans_uu,R.drawable.ans_ri,
    //        R.drawable.ans_a,R.drawable.ans_oi,R.drawable.ans_o,R.drawable.ans_ou};
    int[] soroQesResource={R.drawable.q_oa,R.drawable.q_aa,R.drawable.q_e,R.drawable.q_ee,R.drawable.q_u,R.drawable.q_uu,R.drawable.q_ri,
            R.drawable.q_a,R.drawable.q_oi,R.drawable.q_o,R.drawable.q_ou};
    String[] soroOptResource={"অজগর","আম","ইলিশ","ঈগল","উট","ঊষা","ঋষি","একতারা","ঐরাবত","ওজন","ঔষধ"};


    int[] benjonborno = {R.drawable.b_1,R.drawable.b_2,R.drawable.b_3,R.drawable.b_4,R.drawable.b_5,R.drawable.b_6,R.drawable.b_7,R.drawable.b_8,R.drawable.b_9,R.drawable.b_10,R.drawable.r2_1,R.drawable.r2_2,R.drawable.r2_3,R.drawable.r2_4,R.drawable.r2_5,R.drawable.r2_6,R.drawable.r2_7,R.drawable.r2_8,R.drawable.r2_9,R.drawable.r2_10,R.drawable.r3_1,R.drawable.r3_2,R.drawable.r3_3,R.drawable.r3_4,R.drawable.r3_5,R.drawable.r3_6,R.drawable.r3_7,R.drawable.r3_8,R.drawable.r3_10,R.drawable.r4_1,R.drawable.r4_2,R.drawable.r4_3,R.drawable.r4_4,R.drawable.r4_5,R.drawable.r4_6,R.drawable.r4_7,R.drawable.r4_8,R.drawable.r4_9,R.drawable.r4_10};
    int[] benjonImgResource = {R.drawable.img_kolom,R.drawable.img_khorgosh,R.drawable.img_golap,R.drawable.img_ghori,R.drawable.img_beng,R.drawable.img_chosma,R.drawable.img_chagol,R.drawable.img_jiraf,R.drawable.img_jhuri,R.drawable.img_miaow,R.drawable.img_tomato,R.drawable.img_thelagari,R.drawable.img_dalim,R.drawable.img_dhol,R.drawable.img_horin,R.drawable.img_tormuz,R.drawable.img_thala,R.drawable.img_doel,R.drawable.img_dhan,R.drawable.img_nouka,R.drawable.img_putul,R.drawable.img_foring,R.drawable.img_boi,R.drawable.img_vera,R.drawable.img_mach,R.drawable.img_jadukor,R.drawable.img_rajarani,R.drawable.img_lichu,R.drawable.img_shapla,R.drawable.img_shar,R.drawable.img_shingho,R.drawable.img_hash,R.drawable.img_ghuri,R.drawable.img_ashar,R.drawable.img_aina,R.drawable.img_hotat,R.drawable.img_rong,R.drawable.img_dukho,R.drawable.img_chad};
    int[] banjonQuesResource={R.drawable.q_ka,R.drawable.q_kha,R.drawable.q_ga,R.drawable.q_gha,R.drawable.q_umo,R.drawable.q_cha,
            R.drawable.q_chha,R.drawable.q_ja,R.drawable.q_jha,R.drawable.q_eo,R.drawable.q_ta,R.drawable.q_tha,R.drawable.q_da,
            R.drawable.q_ddha,R.drawable.q_murdhonna,R.drawable.q_toa,R.drawable.q_thaa,R.drawable.q_doa,R.drawable.q_dha,
            R.drawable.q_na,R.drawable.q_pa,R.drawable.q_fa,R.drawable.q_ba,R.drawable.q_va,R.drawable.q_ma,R.drawable.q_ontoj,
            R.drawable.q_ra,R.drawable.q_la,R.drawable.q_talisha,R.drawable.q_petkata,R.drawable.q_donto,R.drawable.q_ha,R.drawable.q_d_ra,
            R.drawable.q_ddha_ra,R.drawable.q_anosoya,R.drawable.q_khondetto,R.drawable.q_onessor,R.drawable.q_bisorgo,R.drawable.q_chandra
    };
    String[] benjonOptResource={"কলম","খরগোশ","গোলাপ","ঘড়ি","ব্যাঙ","চশমা","ছাগল","জিরাফ","ঝুড়ি","মিঞ","টমেটো","ঠেলাগাড়ি","ডালিম","ঢোল","হরিণ","তরমুজ","থালা","দোয়েল","ধান","নৌকা","পুতুল","ফড়িং","বই","ভেড়া","মাছ","যাদুকর","রাজা-রানী","লিচু","শাপলা","ষাঁড়","সিংহ","হাঁস","ঘুড়ি","আষাঢ়","আয়না","হটাৎ","রং","দুঃখ","চাঁদ"};


    int[] songkha = {R.drawable.num_1,R.drawable.num_2,R.drawable.num_3,R.drawable.num_4,R.drawable.num_5,R.drawable.num_6,R.drawable.num_7,R.drawable.num_8,R.drawable.num_9};
    int[] songkhaimgResource = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6,R.drawable.img_7,R.drawable.img_8,R.drawable.img_9};
    int[] sonkhaQuesResource ={R.drawable.son_1,R.drawable.son_2,R.drawable.son_3,R.drawable.son_4,R.drawable.son_5,R.drawable.son_6,R.drawable.son_7,R.drawable.son_8,R.drawable.son_9};
    String[] shongkhaOptResource={"এক","দুই","তিন","চার","পাঁচ","ছয়","সাত","আট","নয়"};

    int[] soroclips= { R.raw.sentences_1, R.raw.sentences_2, R.raw.sentences_3, R.raw.sentences_4,R.raw.sentences_5,R.raw.sentences_6,R.raw.sentences_7,R.raw.sentences_8,R.raw.sentences_9,R.raw.sentences_10,R.raw.sentences_11};
    int[] benjonclips = { R.raw.sentences_12, R.raw.sentences_13, R.raw.sentences_14,R.raw.sentences_15,R.raw.sentences_16,R.raw.sentences_17,R.raw.sentences_18,R.raw.sentences_19,R.raw.sentences_20,R.raw.sentences_21,R.raw.sentences_22,R.raw.sentences_23,R.raw.sentences_24,R.raw.sentences_25,R.raw.sentences_26,R.raw.sentences_27,R.raw.sentences_28,R.raw.sentences_29,R.raw.sentences_30,R.raw.sentences_31,R.raw.sentences_32,R.raw.sentences_33,R.raw.sentences_34,R.raw.sentences_35,R.raw.sentences_36,R.raw.sentences_37,R.raw.sentences_38,R.raw.sentences_39,R.raw.sentences_40,R.raw.sentences_41,R.raw.sentences_42,R.raw.sentences_43,R.raw.sentences_44,R.raw.sentences_45,R.raw.sentences_46,R.raw.sentences_47,R.raw.sentences_48,R.raw.sentences_49,R.raw.sentences_50};
    int[] shonkhaclips = { R.raw.lt_1, R.raw.lt_2, R.raw.lt_3,R.raw.lt_4,R.raw.lt_5,R.raw.lt_6,R.raw.lt_7,R.raw.lt_8,R.raw.lt_9};

    ImageView[] rightView;


    private int random,random1,random2;
    int[] positionArray;
    int[] rightPart;
    String opt;
    int ansImg;
    int ans;
    int media;
    int prev=-100,firstPrev=-200, secPrev=-300;
    private Handler myHandler;
    private Runnable myRunnable;
    private String sectionStatus = "sor";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_and_language);

        hideNav();


        sorob = findViewById(R.id.sor);
        banjo = findViewById(R.id.ban);
        sonk = findViewById(R.id.son);

        sorob.setBackgroundColor(Color.parseColor("#dac386"));
        sorob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    stopSound();
                }
                sorob.setBackgroundColor(Color.parseColor("#dac386"));
                banjo.setBackgroundColor(Color.TRANSPARENT);
                sonk.setBackgroundColor(Color.TRANSPARENT);
                for (int i = 0; i<rightView.length; i++){
                    rightView[i].setEnabled(true);
                }
                sectionStatus = "sor";
                reSet();
                if (myHandler != null) {
                    myHandler.removeCallbacks(myRunnable);
                }
                setUp(soroborno,soroQesResource,soroImgResource,soroOptResource,soroclips,10);
            }
        });

        banjo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    stopSound();
                }
                banjo.setBackgroundColor(Color.parseColor("#dac386"));
                sorob.setBackgroundColor(Color.TRANSPARENT);
                sonk.setBackgroundColor(Color.TRANSPARENT);
                for (int i = 0; i<rightView.length; i++){
                    rightView[i].setEnabled(true);
                }
                sectionStatus = "ban";
                reSet();
                if (myHandler != null) {
                    myHandler.removeCallbacks(myRunnable);
                }
                setUp(benjonborno,banjonQuesResource,benjonImgResource,benjonOptResource,benjonclips,38);
            }
        });
        sonk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    stopSound();
                }
                sonk.setBackgroundColor(Color.parseColor("#dac386"));
                banjo.setBackgroundColor(Color.TRANSPARENT);
                sorob.setBackgroundColor(Color.TRANSPARENT);
                for (int i = 0; i<rightView.length; i++){
                    rightView[i].setEnabled(true);
                }
                sectionStatus = "son";
                reSet();
                if (myHandler != null) {
                    myHandler.removeCallbacks(myRunnable);
                }
                setUp(songkha,sonkhaQuesResource,songkhaimgResource,shongkhaOptResource,shonkhaclips,8);
            }
        });


        tools = new PrimaryTools();
        rightView = new ImageView[]{findViewById(R.id.up1), findViewById(R.id.up2), findViewById(R.id.down1), findViewById(R.id.down2)};
        positionArray = new int[4];
        rightPart = new int[4];

        back = findViewById(R.id.Back);
        option = findViewById(R.id.opt);
        answ = findViewById(R.id.answer);

//        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "mohanondamj.ttf");
//        Typeface typeface1 = Typeface.createFromAsset(getResources().getAssets(), "sutonnymj.ttf");
//
//        option.setTypeface(typeface);
        //answ.setTypeface(typeface1);

        img = findViewById(R.id.image);
        reload = findViewById(R.id.reload);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reSet();
                if(sectionStatus.equalsIgnoreCase("sor")){
                    setUp(soroborno,soroQesResource,soroImgResource,soroOptResource,soroclips,10);
                }else if(sectionStatus.equalsIgnoreCase("ban")){
                    setUp(benjonborno,banjonQuesResource,benjonImgResource,benjonOptResource,benjonclips,38);
                }else{
                    setUp(songkha,sonkhaQuesResource,songkhaimgResource,shongkhaOptResource,shonkhaclips,8);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpeechAndLanguage.this,Courses_Courses.class);
                startActivity(intent);
                finish();
            }
        });

        setUp(soroborno,soroQesResource,soroImgResource,soroOptResource,soroclips,10);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SpeechAndLanguage.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void setUp(int[] soroborno, int[] soroAnsResource, int[] soroImgResource, String[] soroOptResource, int[] sorosoundClips, int limit){

        /*for (int i = 0; i<4; i++){
            rightView[i].setImageResource(soroborno[i]);
            Log.e("testing.......", "setUp: "+soroborno[i] );
        }*/

        for (int i=0; i<4;i++){
            Log.e("loop test", "setUp:  entered" );
            Random r = new Random();
            random = r.nextInt(limit - 0 + 1) + 0;
            //r.nextInt(max - min + 1) + min
            if (random != prev && random != firstPrev && random != secPrev) {


                rightPart[i] = soroborno[random];
                positionArray[i] = random;

                secPrev = firstPrev;
                firstPrev = prev;
                prev = random;
            }else {
                i--;
            }
        }
        for (int j = 0; j<4;j++){
            rightView[j].setImageResource(soroborno[positionArray[j]]);
            rightView[j].setTag(soroborno[positionArray[j]]);
        }


        Random rnd = new Random();
        //r.nextInt(max - min + 1) + min
        random1 = rnd.nextInt(3 - 0 + 1) + 0;

        ans = soroAnsResource[positionArray[random1]];
        ansImg = soroImgResource[positionArray[random1]];
        opt = soroOptResource[positionArray[random1]];
        // setting text in the view
        media = sorosoundClips[positionArray[random1]];

//        option.setText(opt);
        option.setTag(soroborno[positionArray[random1]]);

        img.setBackgroundResource(ansImg);
        img.setTag(soroImgResource[positionArray[random1]]);


    }

    public void stopSound(){
        mediaPlayer.release();
    }

    public void OnClickzz(View view){
        Log.e("First check....", "OnClickzz: "+view.getTag()+"...> optino tag "+img.getTag() );
        if (view.getTag().toString().equals(option.getTag().toString())){
            for (int i = 0; i<rightView.length; i++){
                rightView[i].setEnabled(false);
            }
            Log.e("Tagzzzz", "OnClickz: "+view.getTag()+"  --- "+img.getTag() );
            option.setText(opt);
            //img.setBackgroundResource(ansImg);
            answ.setBackgroundResource(ans);
            animate(answ);

            mediaPlayer = MediaPlayer.create(this, media);
            mediaPlayer.start();

            myHandler = new Handler();
            myRunnable = new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i<rightView.length; i++){
                        rightView[i].setEnabled(true);
                    }

                    reSet();

                    if (sectionStatus.equals("sor")) {
                        setUp(soroborno,soroQesResource,soroImgResource,soroOptResource,soroclips,10);
                    }else if (sectionStatus.equals("ban")){
                        setUp(benjonborno,banjonQuesResource,benjonImgResource,benjonOptResource,benjonclips,38);
                    }else {
                        setUp(songkha,sonkhaQuesResource,songkhaimgResource,shongkhaOptResource,shonkhaclips,8);
                    }
                }
            };

            myHandler.postDelayed(myRunnable,6000);

        }else {
            vibration();
            Shaker shaker = new Shaker(findViewById(view.getId()), -10, 15);
            shaker.shake();
        }

    }

    private void animate(ImageView answ) {
        final Animation animation = AnimationUtils.loadAnimation(SpeechAndLanguage.this,R.anim.bounce);
        MyBounce interpolator = new MyBounce(0.1,45);
        animation.setInterpolator(interpolator);
        answ.startAnimation(animation);
    }



    public void reSet(){
        prev = -1;
        firstPrev = -2;
        secPrev = -3;

        answ.setBackgroundResource(0);
        img.setBackgroundResource(0);
        option.setText("");
    }


    public void hideNav(){
        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }

    public void vibration(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus || !hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }
}
