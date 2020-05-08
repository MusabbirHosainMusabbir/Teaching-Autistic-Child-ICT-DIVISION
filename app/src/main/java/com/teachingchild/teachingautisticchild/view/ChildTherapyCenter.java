package com.teachingchild.teachingautisticchild.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.teachingchild.teachingautisticchild.R;
import com.teachingchild.teachingautisticchild.Utils.Utils;

public class ChildTherapyCenter extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private ImageView imgHeader;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_therapy_center);
        Utils.fullScreenView(this,false);


        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd = new ProgressDialog(ChildTherapyCenter.this);
                pd.setMessage("অপেক্ষা করুন...");
                pd.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
        });

        webView.loadUrl("https://www.gettyimages.com/photos/autism-school-in-dhaka?mediatype=photography&phrase=autism%20school%20in%20dhaka&sort=mostpopular");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ChildTherapyCenter.this,Courses_Courses.class);
            startActivity(intent);
            finish();
            // back was pressed
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
