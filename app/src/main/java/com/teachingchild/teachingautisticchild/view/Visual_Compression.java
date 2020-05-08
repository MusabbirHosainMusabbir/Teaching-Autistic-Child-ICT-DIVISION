package com.teachingchild.teachingautisticchild.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.PaintView;
import com.teachingchild.teachingautisticchild.Utils.RecyclerTouchListener;
import com.teachingchild.teachingautisticchild.adapter.LikhiRecyclerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Visual_Compression extends AppCompatActivity {

    ArrayList<Integer> images;
    ArrayList<Integer>opacity_images;
    RecyclerView recyclerView;
    ImageView backBtn,hideBtn,erraseBtn,colorPicker, save;
    LinearLayout drawBoard,blueBoard;
    PaintView paintView;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    int defaultColor;
    private static final int REQUEST_STORAGE = 1;
    int status = 1;
    boolean blue = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual__compression);


        blueBoard = findViewById(R.id.blue_board);
        recyclerView = findViewById(R.id.likhi_recycle);
        drawBoard = findViewById(R.id.draw);
        backBtn = findViewById(R.id.back);
        hideBtn = findViewById(R.id.hide);
        erraseBtn = findViewById(R.id.errase);
        colorPicker = findViewById(R.id.color_picker);
        save = findViewById(R.id.save_drawing);

        defaultColor = ContextCompat.getColor(Visual_Compression.this,R.color.colorPrimary);
        paintView = new PaintView(this);
        blueBoard.addView(paintView);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        status = 0;

        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });


        erraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(Likhi.this,Likhi.class);
                //startActivity(intent);
                //finish();

                //PaintView.path.reset();
                //paintView.invalidate();

                paintView.clear();

            }
        });


        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBoard.setBackgroundResource(0);
                drawBoard.setVisibility(View.GONE);
                if (status == 1) {
                    drawBoard.removeView(paintView);
                    blueBoard.addView(paintView);

                    status = 0;
                }

                // PaintView.path.reset();
                // paintView.invalidate();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion >= Build.VERSION_CODES.M) {
                    requestStoragePermission();
                } else {
                    showPop();
                }
                //showPop();
                //getPermissions();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Visual_Compression.this,Courses_Courses.class);
                startActivity(intent);
                finish();
            }
        });

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideNav();


        images = new ArrayList<>();

        images.add(Integer.parseInt("0"), R.drawable.soro_a);
        images.add(Integer.parseInt("1"), R.drawable.soro_b);
        images.add(Integer.parseInt("2"), R.drawable.soro_c);
        images.add(Integer.parseInt("3"), R.drawable.soro_d);
        images.add(Integer.parseInt("4"), R.drawable.soro_e);
        images.add(Integer.parseInt("5"), R.drawable.soro_f);
        images.add(Integer.parseInt("6"), R.drawable.soro_g);
        images.add(Integer.parseInt("7"), R.drawable.soro_h);
        images.add(Integer.parseInt("8"), R.drawable.soro_i);
        images.add(Integer.parseInt("9"), R.drawable.soro_j);
        images.add(Integer.parseInt("10"), R.drawable.soro_k);

        opacity_images = new ArrayList<>();

        opacity_images.add(Integer.parseInt("0"), R.drawable.opacity_oa);
        opacity_images.add(Integer.parseInt("1"), R.drawable.opacity_aa);
        opacity_images.add(Integer.parseInt("2"), R.drawable.opacity_e);
        opacity_images.add(Integer.parseInt("3"), R.drawable.opacity_ee);
        opacity_images.add(Integer.parseInt("4"), R.drawable.opacity_u);
        opacity_images.add(Integer.parseInt("5"), R.drawable.opacity_uu);
        opacity_images.add(Integer.parseInt("6"), R.drawable.opacity_ri);
        opacity_images.add(Integer.parseInt("7"), R.drawable.opacity_a);
        opacity_images.add(Integer.parseInt("8"), R.drawable.opacity_oi);
        opacity_images.add(Integer.parseInt("9"), R.drawable.opacity_o);
        opacity_images.add(Integer.parseInt("10"), R.drawable.opacity_ou);

        initRecycler();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (status==0) {

                   /* blueBoard.removeView(paintView);
                    status = 1;
                    *//*drawBoard.removeView(paintView);*//*
                    drawBoard.addView(paintView);*/
                    PaintView.path.reset();
                }

                drawBoard.setVisibility(View.VISIBLE);
                drawBoard.setBackgroundResource(opacity_images.get(position));
                PaintView.path.reset();
                paintView.clear();
                paintView.invalidate();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) );
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Visual_Compression.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        } else {
            Log.i("Main", "Storage permissions have already been granted. Download the file");
            showPop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showPop();
            } else  {
                Toast.makeText(this, "ছবিটি সংরক্ষণ করা হয়নি !!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void openColorPicker() {

        AmbilWarnaDialog choseColor = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                hideNav();

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                defaultColor = color;
                //PaintView.brush.setColor(defaultColor);
                paintView.brush.setColor(defaultColor);
                hideNav();

            }
        });

        choseColor.show();
    }


    public static Bitmap loadBitmapFromView(View view) {

        // width measure spec
        int widthSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
        // height measure spec
        int heightSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredHeight(), View.MeasureSpec.EXACTLY);
        // measure the view
        view.measure(widthSpec, heightSpec);
        // set the layout sizes
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth() + view.getLeft(), view.getMeasuredHeight() + view.getTop());
        // create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // create a canvas used to get the view's image and draw it on the bitmap
        Canvas c = new Canvas(bitmap);
        // position the image inside the canvas
        c.translate(-view.getScrollX(), -view.getScrollY());
        // get the canvas
        view.draw(c);

        return bitmap;
    }

    private void initRecycler(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);

        LikhiRecyclerAdapter adapter = new LikhiRecyclerAdapter(this,images);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNav();
    }

    public void showPop() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Visual_Compression.this);
        dialog.setCancelable(false);
        dialog.setTitle("সংরক্ষণ করুন");
        dialog.setMessage("আপনি কি এটি সংরক্ষণ করতে চান?" );


        dialog.setPositiveButton("হাঁ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {


                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "BornoApps");
                if(!imagesFolder.exists()){
                    imagesFolder.mkdir();
                }


                String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
                // generate the image path
                String imagePath = imagesFolder.toString() + File.separator +  fileName + ".png";
                Log.e("Image path", "onClick: "+imagePath );

                try {

                    // save the image as png
                    FileOutputStream out = new FileOutputStream(imagePath);
                    // compress the image to png and pass it to the output stream
                    loadBitmapFromView(blueBoard).compress(Bitmap.CompressFormat.PNG, 90, out);

                    // save the image
                    out.flush();
                    out.close();
                    Toast.makeText(Visual_Compression.this,"অক্ষরটি ইন্টারনাল স্টোরেজ এর BornoApps নামক ফোল্ডার এ সংরক্ষণ করা হয়েছে !",Toast.LENGTH_LONG).show();

                } catch (Exception error) {
                    Log.e("Error saving image", error.getMessage());
                }

                //hideNav();



            }
        })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        hideNav();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.getWindow().getAttributes().windowAnimations =R.style.DialogAnimation;
        alert.show();

        alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextSize(18);
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextSize(18);

        TextView textView = (TextView) alert.findViewById(android.R.id.message);
        textView.setTextSize((float) 19.0);

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
}
