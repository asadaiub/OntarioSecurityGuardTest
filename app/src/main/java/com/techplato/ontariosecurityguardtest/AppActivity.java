package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {
    BottomAppBar bottomBar;
    CircularProgressBar easyProgress, mediumProgress, hardProgress;
    TextView easyProgressValue, mediumProgressValue, hardProgressValue;
    BottomSheetDialog mBottomSheetDialog;
    View bottomSheet;
    BottomSheetBehavior mBehavior;
    MaterialButton examBtn;
    private QuestionViewModel questionViewModel;


    ConstraintLayout easyParentCL, mediumParentCL, hardParentCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        setSupportActionBar(bottomBar);
        initProgress();
        bottomBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        easyParentCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppActivity.this, "Work is going on! be patient", Toast.LENGTH_SHORT).show();
            }
        });
        examBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AppActivity.this,TestActivity.class));
                Toast.makeText(AppActivity.this, "Ops! We are working on this! Thanks", Toast.LENGTH_SHORT).show();
            }
        });

        initDB();



    }

    private void initDB() {
        questionViewModel= ViewModelProviders.of(this).get(QuestionViewModel.class);

        questionViewModel.getEasyAnsweredList().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                Toast.makeText(AppActivity.this, ""+questions.size(), Toast.LENGTH_SHORT).show();
                easyProgress.setProgressWithAnimation(questions.size());
                easyProgressValue.setText(questions.size()+"%");

            }
        });
    }

    private void initProgress() {
        //int easyDuration = 600;
        int mediumDuration = 800;
        int hardDuration = 900;

        //easyProgress.setProgressWithAnimation(60, easyDuration);
        mediumProgress.setProgressWithAnimation(85, mediumDuration);
        hardProgress.setProgressWithAnimation(90, hardDuration);

        easyProgressValue.setText("60%");
        mediumProgressValue.setText("85%");
        hardProgressValue.setText("95%");


    }

    void init() {
        easyParentCL = findViewById(R.id.easyParentCL);
        mediumParentCL = findViewById(R.id.mediumParentCL);
        hardParentCL = findViewById(R.id.hardParentCL);

        examBtn = findViewById(R.id.examBtn);

        bottomBar = findViewById(R.id.bottomBar);
        bottomSheet = findViewById(R.id.bottomSheet);

        //circular progress
        easyProgress = findViewById(R.id.easyProgress);
        mediumProgress = findViewById(R.id.mediumProgress);
        hardProgress = findViewById(R.id.hardProgress);

        //textView for percentage
        easyProgressValue = findViewById(R.id.easyProgressValue);
        mediumProgressValue = findViewById(R.id.mediumProgressValue);
        hardProgressValue = findViewById(R.id.hardProgressValue);


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
