package com.teachingchild.teachingautisticchild.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.teachingchild.teachingautisticchild.R;


public class Helper {
    static AlertDialog dialog;
    public static void showLoader(final Context context, String msg){
        final Activity activity = (Activity)context;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = activity.getLayoutInflater().inflate(R.layout.loader, null);


        builder.setView(view);

        dialog = builder.create();
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // dialog.getWindow().setLayout(100,100);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);



        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth * 0.5f);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight * 0.3f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);

    }
    public static void cancelLoader(){
        dialog.dismiss();
    }
}
