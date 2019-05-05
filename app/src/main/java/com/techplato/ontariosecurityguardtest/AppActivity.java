package com.techplato.ontariosecurityguardtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class AppActivity extends AppCompatActivity {
    BottomAppBar bottomBar;
    FloatingActionButton fab;

    BottomSheetDialog mBottomSheetDialog;
    View bottomSheet;
    BottomSheetBehavior mBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = findViewById(R.id.bottomBar);
        fab = findViewById(R.id.fab);
        bottomSheet = findViewById(R.id.bottomSheet);
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        setSupportActionBar(bottomBar);

        bottomBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                    bottomBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

                } else {
                    bottomBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);

                }
            }
        });

    }

    private void showBottomSheetDialog() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        final View view = getLayoutInflater().inflate(R.layout.sheet_list, null);
        (view.findViewById(R.id.sheetSetting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Setting  clicked", Toast.LENGTH_SHORT).show();
            }
        });

        (view.findViewById(R.id.sheetShare)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Share clicked", Toast.LENGTH_SHORT).show();
            }
        });

        (view.findViewById(R.id.sheetRate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Rate us clicked", Toast.LENGTH_SHORT).show();
            }
        });

        (view.findViewById(R.id.sheetAboutUs)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "About clicked", Toast.LENGTH_SHORT).show();
            }
        });
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);

        mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ((View) view.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));


        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });


    }
}
