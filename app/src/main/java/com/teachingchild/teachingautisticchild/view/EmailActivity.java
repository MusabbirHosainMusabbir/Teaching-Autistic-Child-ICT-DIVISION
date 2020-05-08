package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.PreferenceMangement;
import com.teachingchild.teachingautisticchild.Utils.Utils;

public class EmailActivity extends AppCompatActivity {

    Button buttoncontinue;
    TextView signin;
    EditText nameEdt, emailEdt;
    String loggedin;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loggedin = PreferenceMangement.getPreference(EmailActivity.this, "looggedin");
        Log.e("value", loggedin);

        if (loggedin.equalsIgnoreCase("1")) {
            intent = new Intent(EmailActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_email);
        Utils.fullScreenView(this, false);

        if (isOnline()) {
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

        buttoncontinue = findViewById(R.id.button);
        signin = findViewById(R.id.signin);

        nameEdt = findViewById(R.id.nameEdt);
        emailEdt = findViewById(R.id.emailEdt);

        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreferenceMangement.savePreference(EmailActivity.this, "name", nameEdt.getText().toString());

                Intent intent = new Intent(EmailActivity.this, AgeandWeightActivity.class);
                intent.putExtra("name", nameEdt.getText().toString());
                intent.putExtra("email", emailEdt.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmailActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(EmailActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

