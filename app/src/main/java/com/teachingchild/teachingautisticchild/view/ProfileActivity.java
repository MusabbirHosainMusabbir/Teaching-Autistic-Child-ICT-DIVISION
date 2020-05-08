package com.teachingchild.teachingautisticchild.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.CircleImageView;
import com.teachingchild.teachingautisticchild.Utils.Helper;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utility;
import com.teachingchild.teachingautisticchild.Utils.Utils;
import com.teachingchild.teachingautisticchild.adapter.BadgesRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.ProfileRecyclerViewAdapter;
import com.teachingchild.teachingautisticchild.adapter.RecyclerViewCoursesAdapter;
import com.teachingchild.teachingautisticchild.customfont.MyTextView_HK_Grotesk_Display_Regular;
import com.teachingchild.teachingautisticchild.model.Badges;
import com.teachingchild.teachingautisticchild.model.Courses;
import com.teachingchild.teachingautisticchild.model.Item;
import com.teachingchild.teachingautisticchild.model.Profiles;
import com.teachingchild.teachingautisticchild.viewmodel.DoctorAdviewViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ProfileUpdateViewModel;
import com.teachingchild.teachingautisticchild.viewmodel.ProfileviewViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView profilepic,roundedImageView;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String base64String;
    private String userChoosenTask;
    String encoded1 =null;
    RecyclerView recyclerView;
    ArrayList<Profiles> itemList;
    ArrayList<Badges> badgesList;
    ProfileRecyclerViewAdapter profileAdapter;
    BadgesRecyclerViewAdapter badgeAdapter;
    TextView badges,friends,scores;
    SmoothBottomBar smoothBottomBar;
    Intent intent;
    ImageView backImg;
    MyTextView_HK_Grotesk_Display_Regular nameTxt,emailTxt,phoneTxt,ageTxt,weightTxt,saveTxt;
    EditText switchname,switchemail,switchphone,switchage,switchweight;
    ImageView pickImg;
    String user_id;
    ProfileviewViewModel profileviewViewModel;
    ProfileUpdateViewModel profileUpdateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Utils.fullScreenView(this,false);

        user_id = PreferenceMangement.getPreference(ProfileActivity.this,"user_id");
//        if (!Utils.hasNavBar(this)){
//            Log.e("hasNav--->", "onCreate: YES---> " );
//            Utils.adjustBottomNav(this,R.id.soft_key_layout,R.id.main_layout);
//            //navLayout.setVisibility(View.GONE);
//
//        }
        
        getProfileInfo();

        profilepic = findViewById(R.id.imageview_account_profiless);
        pickImg = findViewById(R.id.pickImg);
        saveTxt = findViewById(R.id.saveTxt);
        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        ageTxt = findViewById(R.id.ageTxt);
        weightTxt = findViewById(R.id.weightTxt);

        switchname = findViewById(R.id.switchname);
        switchemail = findViewById(R.id.switchemail);
        switchphone = findViewById(R.id.switchphone);
        switchage = findViewById(R.id.switchage);
        switchweight = findViewById(R.id.switchweight);

        recyclerView = findViewById(R.id.recycleView);
        badges = findViewById(R.id.badges);
        friends = findViewById(R.id.friends);
        scores = findViewById(R.id.scores);
        backImg = findViewById(R.id.back_btn);

        pickImg.setVisibility(View.INVISIBLE);

        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String text = saveTxt.getText().toString();
                 if(text.equals("Edit")){
                     pickImg.setVisibility(View.VISIBLE);
                     enableView();
                     saveTxt.setText("Save");
                 }else{
                     updateProfile();
                     disableView();
                 }
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, Courses_Courses.class);
                startActivity(intent);
                finish();
            }
        });

        smoothBottomBar = findViewById(R.id.bottomBar);
        smoothBottomBar.setActiveItem(2);
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int pos) {
                Log.e("post", String.valueOf(pos));
                if(pos ==0){
                    intent = new Intent(ProfileActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(pos == 1){
                    intent = new Intent(ProfileActivity.this,Courses_Courses.class);
                    startActivity(intent);
                    finish();
                }else if(pos == 2){
                    intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        itemList = new ArrayList<>();
        badgesList = new ArrayList<>();

        badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
        badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
        badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
        badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));

        GridLayoutManager layoutManager0 = new GridLayoutManager(ProfileActivity.this,1);
        layoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager0);
        badgeAdapter = new BadgesRecyclerViewAdapter(ProfileActivity.this,badgesList);
        recyclerView.setAdapter(badgeAdapter);
        recyclerView.scrollToPosition(1);

        badges.setTextColor(getResources().getColor(R.color.textcolor));




        badges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                badges.setTextColor(getResources().getColor(R.color.textcolor));
                badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
                badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
                badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));
                badgesList.add(new Badges(R.drawable.perfoctionist,"Perfectionist","Finish all lesons of a chapter"));

                GridLayoutManager layoutManager1 = new GridLayoutManager(ProfileActivity.this,1);
                layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager1);
                badgeAdapter = new BadgesRecyclerViewAdapter(ProfileActivity.this,badgesList);
                recyclerView.setAdapter(badgeAdapter);
                recyclerView.scrollToPosition(1);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.clear();
                friends.setTextColor(getResources().getColor(R.color.textcolor));

                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));

                GridLayoutManager layoutManager2 = new GridLayoutManager(ProfileActivity.this,1);
                layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager2);
                profileAdapter = new ProfileRecyclerViewAdapter(ProfileActivity.this,itemList);
                recyclerView.setAdapter(profileAdapter);
                recyclerView.scrollToPosition(1);

            }
        });

        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.clear();
                scores.setTextColor(getResources().getColor(R.color.textcolor));

                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));
                itemList.add(new Profiles(1,"","Nell Hudson","2,349 XP"));

                GridLayoutManager layoutManager3 = new GridLayoutManager(ProfileActivity.this,1);
                layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager3);
                profileAdapter = new ProfileRecyclerViewAdapter(ProfileActivity.this,itemList);
                recyclerView.setAdapter(profileAdapter);
                recyclerView.scrollToPosition(1);

            }
        });
    }

    private void updateProfile() {
        Helper.showLoader(ProfileActivity.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);
            reqJsonObj.put("full_name",switchname.getText().toString());
            reqJsonObj.put("email",switchemail.getText().toString());
            reqJsonObj.put("phone_number",switchphone.getText().toString());
            reqJsonObj.put("child_age",switchage.getText().toString());
            reqJsonObj.put("child_weight",switchweight.getText().toString());
            reqJsonObj.put("image",base64String);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        profileUpdateViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProfileUpdateViewModel.class);
        profileUpdateViewModel.initialize(getApplication(),reqJsonObj);
        profileUpdateViewModel.getStatus().observe(ProfileActivity.this, new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                 Helper.cancelLoader();
                  if(stringStringHashMap.get("status").equalsIgnoreCase("1")){
                      PreferenceMangement.savePreference(ProfileActivity.this, "name", switchname.getText().toString());
                      Toast.makeText(ProfileActivity.this, ""+stringStringHashMap.get("message"), Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(ProfileActivity.this, ""+stringStringHashMap.get("message"), Toast.LENGTH_SHORT).show();
                  }
            }
        });
    }

    private void getProfileInfo() {
        Helper.showLoader(ProfileActivity.this,"");
        JSONObject reqJsonObj = new JSONObject();

        try {
            reqJsonObj.put("user_id",user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        profileviewViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProfileviewViewModel.class);
        profileviewViewModel.initialize(getApplication(),reqJsonObj);
        profileviewViewModel.getProfile().observe(ProfileActivity.this, new Observer<ArrayList<HashMap<String, String>>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(ArrayList<HashMap<String, String>> hashMaps) {
                Helper.cancelLoader();
                for (int x= 0; x<hashMaps.size();x++) {
                    String full_name = hashMaps.get(x).get("full_name");
                    String email = hashMaps.get(x).get("email");
                    String phone = hashMaps.get(x).get("phone");
                    String child_age = hashMaps.get(x).get("child_age");
                    String weight = hashMaps.get(x).get("weight");
                    String location = hashMaps.get(x).get("location");
                    String user_pic = hashMaps.get(x).get("user_pic");


                    if(!user_pic.equalsIgnoreCase("")){
                        Glide.with(ProfileActivity.this)
                                .asBitmap()
                                .load(user_pic)
                                .into(profilepic);
                    }else{
                        //profileImg.setBackgroundResource(R.drawable.avatar);
                    }

                    nameTxt.setText(full_name);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);
                    ageTxt.setText(child_age);
                    weightTxt.setText(weight);

                }
            }
        });
    }

    private void disableView() {
        ViewSwitcher switcher1 = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
        switcher1.showNext(); //or switcher.showPrevious();
        EditText nameEdt = (EditText) switcher1.findViewById(R.id.switchname);
        nameEdt.setCursorVisible(false);
        nameTxt.setText(nameEdt.getText().toString());

        ViewSwitcher switcher2 = (ViewSwitcher) findViewById(R.id.viewSwitcher2);
        switcher2.showNext(); //or switcher.showPrevious();
        EditText emailEdt = (EditText) switcher2.findViewById(R.id.switchemail);
        emailEdt.setCursorVisible(false);
        emailTxt.setText(emailEdt.getText().toString());

        ViewSwitcher switcher3 = (ViewSwitcher) findViewById(R.id.viewSwitcher3);
        switcher3.showNext(); //or switcher.showPrevious();
        EditText phoneEdt = (EditText) switcher3.findViewById(R.id.switchphone);
        phoneEdt.setCursorVisible(false);
        phoneTxt.setText(phoneEdt.getText().toString());

        ViewSwitcher switcher4 = (ViewSwitcher) findViewById(R.id.viewSwitcher4);
        switcher4.showNext(); //or switcher.showPrevious();
        EditText age = (EditText) switcher4.findViewById(R.id.switchage);
        age.setCursorVisible(false);
        ageTxt.setText(age.getText().toString());

        ViewSwitcher switcher5 = (ViewSwitcher) findViewById(R.id.viewSwitcher5);
        switcher5.showNext(); //or switcher.showPrevious();
        EditText weight = (EditText) switcher5.findViewById(R.id.switchweight);
        weight.setCursorVisible(false);
        weightTxt.setText(weight.getText().toString());

    }

    private void enableView() {
        pickImg.setVisibility(View.VISIBLE);
        pickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                //chooseImage();
            }
        });
        
        ViewSwitcher switcher1 = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
        switcher1.showNext(); //or switcher.showPrevious();
        TextView myTV = (TextView) switcher1.findViewById(R.id.nameTxt);
        switchname.setText(myTV.getText().toString());
        switchname.requestFocus();
        switchname.setCursorVisible(true);


        ViewSwitcher switcher2 = (ViewSwitcher) findViewById(R.id.viewSwitcher2);
        switcher2.showNext(); //or switcher.showPrevious();
        TextView myTV2 = (TextView) switcher2.findViewById(R.id.emailTxt);
        switchemail.setText(myTV2.getText().toString());
        switchemail.requestFocus();
        switchemail.setCursorVisible(true);

        ViewSwitcher switcher3 = (ViewSwitcher) findViewById(R.id.viewSwitcher3);
        switcher3.showNext(); //or switcher.showPrevious();
        TextView myTV3 = (TextView) switcher3.findViewById(R.id.phoneTxt);
        switchphone.setText(myTV3.getText().toString());
        switchphone.requestFocus();
        switchphone.setCursorVisible(true);

        ViewSwitcher switcher4 = (ViewSwitcher) findViewById(R.id.viewSwitcher4);
        switcher4.showNext(); //or switcher.showPrevious();
        TextView myTV4 = (TextView) switcher4.findViewById(R.id.ageTxt);
        switchage.setText(myTV4.getText().toString());
        switchage.requestFocus();
        switchage.setCursorVisible(true);

        ViewSwitcher switcher5 = (ViewSwitcher) findViewById(R.id.viewSwitcher5);
        switcher5.showNext(); //or switcher.showPrevious();
        TextView myTV5 = (TextView) switcher5.findViewById(R.id.weightTxt);
        switchweight.setText(myTV5.getText().toString());
        switchweight.requestFocus();
        switchweight.setCursorVisible(true);
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(ProfileActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ProfileActivity.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void captureProfileImage() {

        Log.e("ttapped--->", "captureProfileImage: " );


        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utils.checkPermission(ProfileActivity.this);

                Log.e("PERMISSION-->", "onClick: "+result );

                if (result){
                    if (items[item].equals("Take Photo")) {
                        userChoosenTask ="Take Photo";
                        cameraIntent();

                    } else if (items[item].equals("Choose from Library")) {
                        userChoosenTask ="Choose from Library";

                        galleryIntent();

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }



            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Log.e("camera","carmera");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
              /*  try {

                    profileImg.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("exception",e.getMessage());
                }*/
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        final Bitmap bitmap = (Bitmap) data.getExtras().get("data");

        //encode  to byte format
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();


        base64String = Base64.encodeToString(byteArray, Base64.DEFAULT);
        encoded1 = base64String;

        Log.e("BASE64--->", "onCaptureImageResult: "+base64String );

        /*switch (imageViewTosetCapturedImage.getId()){
            case R.id.image1:
                encoded1 = base64String;
                break;
        }*/

        profilepic.post(new Runnable() {
            @Override
            public void run()
            {
                base64String = getStringImage(thumbnail);
                profilepic.setImageBitmap(thumbnail);
            }
        });



    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        base64String = getStringImage(bm);
        profilepic.setImageBitmap(bm);
    }

    private String getStringImage(Bitmap thumbnail) {
        String imgString="",convertbase64="";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        convertbase64 = "data:image/png;base64,"+imgString;
        //convertbase64 = imgString;
        return convertbase64;
        //
        //return encoded1;
    }
}
