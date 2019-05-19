package com.techplato.ontariosecurityguardtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;

public class ShowDialog {

    public void showCustomDialog(final Context context, String title, String desc, final InterstitialAd interstitialAd){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.show_dialog_layout,null);
        dialogBuilder.setView(dialogView);

        TextView mTitle= (TextView) dialogView.findViewById(R.id.dialogTitle);
        TextView mDesc = (TextView) dialogView.findViewById(R.id.dialogDesc);
        Button dialogBtn=dialogView.findViewById(R.id.dialogBtn);
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
}
