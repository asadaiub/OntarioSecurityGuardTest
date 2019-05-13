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
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

import java.util.List;

public class AppActivity extends AppCompatActivity {
    BottomAppBar bottomBar;
    CircularProgressBar easyProgress, mediumProgress, hardProgress;
    TextView easyProgressValue, mediumProgressValue, hardProgressValue;
    BottomSheetDialog mBottomSheetDialog;
    View bottomSheet;
    BottomSheetBehavior mBehavior;
    MaterialButton examBtn;
    private long backPressedTime;

    ConstraintLayout easyParentCL, mediumParentCL, hardParentCL;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        setSupportActionBar(bottomBar);
        bottomBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        examBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AppActivity.this,TestActivity.class));
                Toast.makeText(AppActivity.this, "For checking Database, " + DebugDB.getAddressLog(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AppActivity.this,SplashActivity.class));
            }
        });

        initDB();


    }

    private void initDB() {
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        questionViewModel.setAnswered(4);


        /*questionViewModel.getSubcategoryProgressList(1,1).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
               // Toast.makeText(AppActivity.this, "progress size is "+questions.size(), Toast.LENGTH_SHORT).show();

            }
        });*/


        questionViewModel.getMProgress(1).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                easyProgress.setProgressWithAnimation(questions.size());
                easyProgressValue.setText(questions.size() + "%");
            }
        });

        questionViewModel.getMProgress(2).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mediumProgress.setProgressWithAnimation(questions.size());
                mediumProgressValue.setText(questions.size() + "%");
            }
        });

        questionViewModel.getMProgress(3).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                hardProgress.setProgressWithAnimation(questions.size());
                hardProgressValue.setText(questions.size() + "%");
            }
        });
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


        //

        easyParentCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSubCategoryActivity(1);
            }
        });

        mediumParentCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSubCategoryActivity(2);
            }
        });

        hardParentCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSubCategoryActivity(3);
            }
        });

    }

    public void goToSubCategoryActivity(int mType) {
        Intent intent = new Intent(AppActivity.this, SubCategory.class);
        intent.putExtra("difType", mType);
        startActivity(intent);
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


    @Override
    public void onBackPressed() {

        if(backPressedTime+2000>System.currentTimeMillis()){
           // backtoast.cancel();

            super.onBackPressed();
            return;
        }else{
            //backtoast=Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_SHORT);
            //backtoast.show();
            Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }
}
