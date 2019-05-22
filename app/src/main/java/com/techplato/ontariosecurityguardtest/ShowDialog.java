package com.techplato.ontariosecurityguardtest;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

public class ShowDialog {
    public void showCustomDialog(final Context context, String title, String desc, final InterstitialAd interstitialAd) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.show_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        TextView mTitle = (TextView) dialogView.findViewById(R.id.dialogTitle);
        TextView mDesc = (TextView) dialogView.findViewById(R.id.dialogDesc);
        Button dialogBtn = dialogView.findViewById(R.id.dialogBtn);
        mTitle.setText(title);
        mDesc.setText(desc);


        final AlertDialog alertDialog = dialogBuilder.create();
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    alertDialog.dismiss();
                    Intent intent = new Intent(context, AppActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        });
        alertDialog.show();

    }


    public void showSettingsDialog(final Context context, final QuestionViewModel questionViewModel) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.show_settings_layout, null);
        dialogBuilder.setView(dialogView);


        LinearLayout setting1Layout = dialogView.findViewById(R.id.setting1Layout);
        LinearLayout setting2Layout = dialogView.findViewById(R.id.setting2Layout);
        Button settingsOkBtn = dialogView.findViewById(R.id.settingsOkBtn);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        setting1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(questionViewModel);
                Toast.makeText(context, "table reset!", Toast.LENGTH_SHORT).show();
            }
        });
        setting2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Will be available in next update ;)", Toast.LENGTH_SHORT).show();
            }
        });

        settingsOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }

    private void reset(QuestionViewModel questionViewModel) {
        questionViewModel.resetTable();

    }



    public void showAboutDialog(final Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.show_about_layout, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton aboutBtn=(ImageButton)  dialogView.findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }


}
