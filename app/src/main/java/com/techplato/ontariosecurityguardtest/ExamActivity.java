package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    AdView examActivityAdView;
    Toolbar examToolbar;
    ImageButton examCloseIB;
    TextView examQuestionTV, option1TV, option2TV, option3TV, option4TV, examQuestionTimerTV, examAnsweredTV, examQuestionTitleTV;
    LinearLayout option1, option2, option3, option4;
    MaterialButton examNextBtn;
    int counter = 0;
    int isTapped = 0;
    List<Question> mq = new ArrayList<>();
    List<Question> mainExamQuestion = new ArrayList<>();
    SharedPreferences preferences;
    int sID;
    CountDownTimer mTimer = null;
    private InterstitialAd interstitialAd;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        init();
        setSupportActionBar(examToolbar);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(Constants.mAdRequest());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                //interstitialAd.loadAd(new AdRequest.Builder().build());
               /* Intent intent = new Intent(ExamActivity.this, AppActivity.class);
                startActivity(intent);
                ((Activity) ExamActivity.this).finish();*/
                finish();
            }
        });

        preferences = getSharedPreferences(Constants.PREFERANCE_NAME, MODE_PRIVATE);
        sID = preferences.getInt("sExamId", 1);


        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        Intent intent = getIntent();
        int type = intent.getIntExtra("difType", -1);
        int section = intent.getIntExtra("sectionId", -1);
        int fromActivity = intent.getIntExtra("exmType", -1);


        if (fromActivity == 1) {
            examQuestionTimerTV.setVisibility(View.VISIBLE);
            examQuestionTitleTV.setText("Exam");

            questionViewModel.getMainExamQuestion().observe(this, new Observer<List<Question>>() {
                @Override
                public void onChanged(@Nullable List<Question> questions) {
                    mainExamQuestion = questions;

                    if (mainExamQuestion.size() > 0) {
                        initMainExam();
                    }
                }
            });
            startTimer();
        } else {
            examQuestionTimerTV.setVisibility(View.INVISIBLE);
            examQuestionTitleTV.setText("Test & Learn");

            questionViewModel.getTestQuestion(type, section).observe(this, new Observer<List<Question>>() {
                @Override
                public void onChanged(@Nullable List<Question> questions) {

                    mq = questions;
                    Collections.reverse(mq);
                    if (mq.size() > 0) {
                        initQuestion(mq);
                    }
                }
            });
        }

        examActivityAdView.loadAd(Constants.mAdRequest());


    }

    private void initMainExam() {
        if (counter == 0) {
            showMainData(counter);
            counter++;
        }
        examNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < mainExamQuestion.size()) {
                    if (counter == mainExamQuestion.size() - 1) {
                        examNextBtn.setText("Finish");
                    } else {
                        examNextBtn.setText("Next");
                    }
                    isTapped = 0;
                    resetColor();
                    showMainData(counter);
                    counter++;
                } else {
                    ShowMainScore();
                }

            }
        });


    }

    public void ShowMainScore() {
        if (mTimer != null) mTimer.cancel();
        questionViewModel.getMainExamScore(sID).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                //resultList=questions;
                //if(questions.size()>0){
                showResultDialog(questions.size());
                //}
            }
        });


    }

    public void showResultDialog(int score) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFERANCE_NAME, MODE_PRIVATE).edit();
        editor.putInt("highScore", score);
        editor.apply();
        ShowDialog showDialog = new ShowDialog();
        showDialog.showCustomDialog(this, "Thanks", "You got " + score + " only", interstitialAd);

    }

    private void showMainData(final int mItem) {
        examNextBtn.setVisibility(View.INVISIBLE);
        Question mQuestion = mainExamQuestion.get(mItem);
        examQuestionTV.setText(mQuestion.getSingleQuestion());
        option1TV.setText(mQuestion.getOptionOne());
        option2TV.setText(mQuestion.getOptionTwo());
        option3TV.setText(mQuestion.getOptionThree());
        option4TV.setText(mQuestion.getOptionFour());


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkMainResult(option1TV.getText().toString(), mItem, option1);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkMainResult(option2TV.getText().toString(), mItem, option2);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkMainResult(option3TV.getText().toString(), mItem, option3);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkMainResult(option4TV.getText().toString(), mItem, option4);
                }
            }
        });


    }


    private void initQuestion(final List<Question> mq) {

        if (counter < mq.size()) {
            if (mq.size() == 1) {
                examNextBtn.setText("Finish");
            } else {
                examNextBtn.setText("Next");

            }
            if (mq.get(counter).getOptionType() == 2) {
                option3.setVisibility(View.GONE);
                option4.setVisibility(View.GONE);
            } else {
                option3.setVisibility(View.VISIBLE);
                option4.setVisibility(View.VISIBLE);
            }
            if (counter == 0) {
                showData(counter);
                counter++;
            }
            examNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (counter < mq.size()) {
                        if (mq.get(counter).getOptionType() == 2) {
                            option3.setVisibility(View.GONE);
                            option4.setVisibility(View.GONE);
                        } else {
                            option3.setVisibility(View.VISIBLE);
                            option4.setVisibility(View.VISIBLE);
                        }

                        if (counter == mq.size() - 1) {
                            examNextBtn.setText("Finish");
                        } else {
                            examNextBtn.setText("Next");
                        }
                        isTapped = 0;
                        resetColor();
                        showData(counter);
                        counter++;
                    } else {
                        if (interstitialAd.isLoaded()) {
                            interstitialAd.show();
                        } else {
                            finish();
                        }
                    }
                }
            });
        }

    }

    private void showData(final int item) {
        examNextBtn.setVisibility(View.INVISIBLE);

        final Question mQuestion = mq.get(item);

        examQuestionTV.setText(mQuestion.getSingleQuestion());
        if (mQuestion.getOptionType() == 2) {
            option1TV.setText(mQuestion.getOptionOne());
            option2TV.setText(mQuestion.getOptionTwo());
        } else {
            option1TV.setText(mQuestion.getOptionOne());
            option2TV.setText(mQuestion.getOptionTwo());
            option3TV.setText(mQuestion.getOptionThree());
            option4TV.setText(mQuestion.getOptionFour());
        }


        if (mQuestion.getIsAnswered() == 1 && mQuestion.getIsRight() == 1) {
            examNextBtn.setVisibility(View.VISIBLE);
            examAnsweredTV.setVisibility(View.VISIBLE);

            if (option1TV.getText().toString().trim().equals(mQuestion.getAnswer())) {
                option1.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option1TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option2TV.getText().toString().trim().equals(mQuestion.getAnswer())) {
                option2.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option2TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option3TV.getText().toString().trim().equals(mQuestion.getAnswer())) {
                option3.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option3TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option4TV.getText().toString().trim().equals(mQuestion.getAnswer())) {
                option4.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option4TV.setTextColor(getResources().getColor(R.color.colorWhite));

            }
            isTapped = 1;

        } else {
            examAnsweredTV.setVisibility(View.GONE);

            isTapped = 0;
        }


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mQuestion.getIsRight() != 1) {
                    checkResult(option1TV.getText().toString(), item, 1, option1);

                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mQuestion.getIsRight() != 1) {
                    checkResult(option2TV.getText().toString(), item, 2, option2);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mQuestion.getIsRight() != 1) {
                    checkResult(option3TV.getText().toString(), item, 3, option3);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mQuestion.getIsRight() != 1) {
                    checkResult(option4TV.getText().toString(), item, 4, option4);

                }
            }
        });


    }

    private void init() {
        examToolbar = findViewById(R.id.examToolbar);
        examCloseIB = findViewById(R.id.examCloseIB);
        examQuestionTimerTV = findViewById(R.id.examQuestionTimerTV);
        examAnsweredTV = findViewById(R.id.examAnsweredTV);
        examQuestionTV = findViewById(R.id.examQuestionTV);
        examQuestionTitleTV = findViewById(R.id.examQuestionTitleTV);
        option1TV = findViewById(R.id.option1TV);
        option2TV = findViewById(R.id.option2TV);
        option3TV = findViewById(R.id.option3TV);
        option4TV = findViewById(R.id.option4TV);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        examNextBtn = findViewById(R.id.examNextBtn);
        examActivityAdView = findViewById(R.id.examActivityAdView);
        examNextBtn.setText("Next");


        examCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    finish();
                }
            }
        });
    }

    private void checkResult(String uAnswer, int item, int i, LinearLayout layout) {

        if (mq.get(item).getAnswer().equals(uAnswer)) {
            layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            //questionViewModel.setAnswered(mq.get(item).getId(),1);
            saveData(mq.get(item).getId(), 1);

        } else {
            //questionViewModel.setAnswered(mq.get(item).getId(),0);
            saveData(mq.get(item).getId(), 0);

            layout.setBackground(getResources().getDrawable(R.drawable.custom_border2));
            if (option1TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option1.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option1TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option2TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option2.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option2TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option3TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option3.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option3TV.setTextColor(getResources().getColor(R.color.colorWhite));

            } else if (option4TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option4.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option4TV.setTextColor(getResources().getColor(R.color.colorWhite));

            }


        }


        examNextBtn.setVisibility(View.VISIBLE);
        isTapped = 1;

    }


    public void checkMainResult(String uAnswer, int mItem, LinearLayout layout) {
        if (mainExamQuestion.get(mItem).getAnswer().equals(uAnswer)) {
            layout.setBackground(getResources().getDrawable(R.drawable.custom_border));
            questionViewModel.updateSpecialExam(mainExamQuestion.get(mItem).getId(), sID);

        } else {
            layout.setBackground(getResources().getDrawable(R.drawable.custom_border2));
            if (option1TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option1.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option1TV.setTextColor(getResources().getColor(R.color.colorWhite));


            } else if (option2TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option2.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option2TV.setTextColor(getResources().getColor(R.color.colorWhite));


            } else if (option3TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option3.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option3TV.setTextColor(getResources().getColor(R.color.colorWhite));


            } else if (option4TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option4.setBackground(getResources().getDrawable(R.drawable.custom_border));
                option4TV.setTextColor(getResources().getColor(R.color.colorWhite));


            }
        }

        examNextBtn.setVisibility(View.VISIBLE);
        isTapped = 1;

    }


    public void startTimer() {
        mTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                examQuestionTimerTV.setText("Left: " + new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
            }

            public void onFinish() {
                examQuestionTimerTV.setText("done!");
                ShowMainScore();
            }
        }.start();

    }

    public void resetColor() {

        option1.setBackground(getDrawable(R.drawable.custom_border_def));
        option2.setBackground(getDrawable(R.drawable.custom_border_def));
        option3.setBackground(getDrawable(R.drawable.custom_border_def));
        option4.setBackground(getDrawable(R.drawable.custom_border_def));

        option1TV.setTextColor(getResources().getColor(android.R.color.black));
        option2TV.setTextColor(getResources().getColor(android.R.color.black));
        option3TV.setTextColor(getResources().getColor(android.R.color.black));
        option4TV.setTextColor(getResources().getColor(android.R.color.black));


    }

    private void saveData(int id, int ans) {
        questionViewModel.setAnswered(id, ans);
    }
}
