package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
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
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {
    Toolbar examToolbar;
    ImageButton examCloseIB;
    TextView examQuestionTV, option1TV, option2TV, option3TV, option4TV;
    LinearLayout option1, option2, option3, option4;
    MaterialButton examNextBtn;
    int counter = 0;
    int isTapped = 0;
    List<Question> mq = new ArrayList<>();
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
        //Toast.makeText(this, "type: " + type + "section: " + section, Toast.LENGTH_SHORT).show();
        questionViewModel.getTestQuestion(type, section).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mq = questions;
                //Toast.makeText(ExamActivity.this, "mq : "+mq.size(), Toast.LENGTH_SHORT).show();
                if (mq.size() > 0) {
                    initQuestion(mq);
                }

            }
        });


    }

    private void initQuestion(final List<Question> mq) {
        if (counter < mq.size()) {
            if (counter == 0) {
                showData(counter);
                counter++;
            }
            examNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (counter < mq.size()) {

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
        option1TV.setText(mQuestion.getOptionOne());
        option2TV.setText(mQuestion.getOptionTwo());
        option3TV.setText(mQuestion.getOptionThree());
        option4TV.setText(mQuestion.getOptionFour());
        Toast.makeText(this, "" + mQuestion.getId(), Toast.LENGTH_SHORT).show();


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkResult(option1TV.getText().toString(), item, option1);

                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkResult(option2TV.getText().toString(), item, option2);

                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkResult(option3TV.getText().toString(), item, option3);

                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTapped == 0) {
                    checkResult(option4TV.getText().toString(), item, option4);

                }
            }
        });


    }

    private void init() {
        examToolbar = findViewById(R.id.examToolbar);
        examCloseIB = findViewById(R.id.examCloseIB);
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

        examCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void resetColor() {
        option1.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option2.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option3.setBackgroundColor(getResources().getColor(R.color.grey_10));
        option4.setBackgroundColor(getResources().getColor(R.color.grey_10));


    }

    private void checkResult(String uAnswer, int item, LinearLayout layout) {

        if (mq.get(item).getAnswer().equals(uAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            layout.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        } else {
            layout.setBackgroundColor(getResources().getColor(R.color.colorRed));

        }

        examNextBtn.setVisibility(View.VISIBLE);
        isTapped = 1;

    }
}
