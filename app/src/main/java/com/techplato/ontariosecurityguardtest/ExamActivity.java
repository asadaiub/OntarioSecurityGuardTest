package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Toast;

import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionDatabase;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    Toolbar examToolbar;
    ImageButton examCloseIB;
    TextView examQuestionTV, option1TV, option2TV, option3TV, option4TV, examQuestionTimerTV;
    LinearLayout option1, option2, option3, option4;
    MaterialButton examNextBtn;
    int counter = 0;
    int isTapped = 0;
    List<Question> mq = new ArrayList<>();
    List<Question> mainExamQuestion = new ArrayList<>();
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        init();
        setSupportActionBar(examToolbar);


        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        Intent intent = getIntent();
        int type = intent.getIntExtra("difType", -1);
        int section = intent.getIntExtra("sectionId", -1);
        int fromActivity = intent.getIntExtra("exmType", -1);


        if (fromActivity == 1) {
            questionViewModel.getMainExamQuestion().observe(this, new Observer<List<Question>>() {
                @Override
                public void onChanged(@Nullable List<Question> questions) {
                    mainExamQuestion=questions;
                    Toast.makeText(ExamActivity.this, "main exm question: " + questions.size(), Toast.LENGTH_SHORT).show();

                    if (mainExamQuestion.size() > 0) {
                        initMainExam();
                    }
                }
            });

         startTimer();

        } else {
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


    }

    private void initMainExam() {
        if (counter == 0) {
            showMainData(counter);
            counter++;
        }
        examNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<mainExamQuestion.size()){
                    if (counter == mainExamQuestion.size() - 1) {
                        examNextBtn.setText("Finish");
                    } else {
                        examNextBtn.setText("Next");
                    }
                    isTapped = 0;
                    resetColor();
                    showMainData(counter);
                    counter++;
                }else{
                    finish();
                }



            }
        });


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
                if(isTapped==0){
                    checkMainResult(option1TV.getText().toString(),mItem,option1);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTapped==0){
                    checkMainResult(option2TV.getText().toString(),mItem,option2);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTapped==0){
                    checkMainResult(option3TV.getText().toString(),mItem,option3);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTapped==0){
                    checkMainResult(option4TV.getText().toString(),mItem,option4);
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
                        finish();
                    }
                }
            });
        }

    }
    private void showData(final int item) {
        examNextBtn.setVisibility(View.INVISIBLE);

        Question mQuestion = mq.get(item);
        examQuestionTV.setText(mQuestion.getSingleQuestion());
        if (mq.get(item).getOptionType() == 2) {
            option1TV.setText(mQuestion.getOptionOne());
            option2TV.setText(mQuestion.getOptionTwo());
        } else {
            option1TV.setText(mQuestion.getOptionOne());
            option2TV.setText(mQuestion.getOptionTwo());
            option3TV.setText(mQuestion.getOptionThree());
            option4TV.setText(mQuestion.getOptionFour());
        }

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mq.get(counter).getIsRight() != 1) {
                    checkResult(option1TV.getText().toString(), item, 1, option1);
                    Toast.makeText(ExamActivity.this, "mq size " + mq.size() + " counter :" + counter, Toast.LENGTH_SHORT).show();

                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mq.get(counter).getIsRight() != 1) {
                    checkResult(option2TV.getText().toString(), item, 2, option2);

                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mq.get(counter).getIsRight() != 1) {
                    checkResult(option3TV.getText().toString(), item, 3, option3);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0 && mq.get(counter).getIsRight() != 1) {
                    checkResult(option4TV.getText().toString(), item, 4, option4);

                }
            }
        });


    }

    private void init() {
        examToolbar = findViewById(R.id.examToolbar);
        examCloseIB = findViewById(R.id.examCloseIB);
        examQuestionTimerTV = findViewById(R.id.examQuestionTimerTV);
        examQuestionTV = findViewById(R.id.examQuestionTV);
        option1TV = findViewById(R.id.option1TV);
        option2TV = findViewById(R.id.option2TV);
        option3TV = findViewById(R.id.option3TV);
        option4TV = findViewById(R.id.option4TV);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        examNextBtn = findViewById(R.id.examNextBtn);
        examNextBtn.setText("Next");

        examCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkResult(String uAnswer, int item, int i, LinearLayout layout) {
        Toast.makeText(this, "mq option " + mq.get(item).getOptionType(), Toast.LENGTH_SHORT).show();
        if (mq.get(item).getAnswer().equals(uAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            //questionViewModel.setAnswered(mq.get(item).getId(),1);
            saveData(mq.get(item).getId(), 1);

        } else {
            //questionViewModel.setAnswered(mq.get(item).getId(),0);
            saveData(mq.get(item).getId(), 0);

            layout.setBackgroundColor(getResources().getColor(R.color.colorRed));
            if (option1TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option2TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option3TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option4TV.getText().toString().trim().equals(mq.get(item).getAnswer())) {
                option4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }


        }


        examNextBtn.setVisibility(View.VISIBLE);
        isTapped = 1;

    }


    public void checkMainResult(String uAnswer,int mItem, LinearLayout layout){
        if (mainExamQuestion.get(mItem).getAnswer().equals(uAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            questionViewModel.updateSpecialExam(mainExamQuestion.get(mItem).getId());

        } else {
            layout.setBackgroundColor(getResources().getColor(R.color.colorRed));
            if (option1TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option2TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option3TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option3.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else if (option4TV.getText().toString().trim().equals(mainExamQuestion.get(mItem).getAnswer())) {
                option4.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            }
        }

        examNextBtn.setVisibility(View.VISIBLE);
        isTapped = 1;

    }



    public void startTimer(){
        new CountDownTimer(80000, 1000) {

            public void onTick(long millisUntilFinished) {
                examQuestionTimerTV.setText("remaining: " +new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                examQuestionTimerTV.setText("done!");
                finish();
            }
        }.start();

    }

    public void resetColor() {
        option1.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option2.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option3.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option4.setBackgroundColor(getResources().getColor(R.color.grey_10));


    }

    private void saveData(int id, int ans) {
        questionViewModel.setAnswered(id, ans);
    }
}
