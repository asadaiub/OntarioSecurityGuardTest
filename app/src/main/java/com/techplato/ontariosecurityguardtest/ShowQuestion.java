package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.techplato.ontariosecurityguardtest.Adapter.ViewPagerAdapter;
import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;
import com.techplato.ontariosecurityguardtest.Fragment.QuestionFragment;

import java.util.List;

public class ShowQuestion extends AppCompatActivity {
    Toolbar showQuestionToolbar;
    ImageButton showQuestionCloseIB;
    MaterialButton showQuestionNextBtn;
    ProgressBar showQuestionProgressBar;
    ViewPager showQuestionViewpager;
    ViewPagerAdapter viewPagerAdapter;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);
        init();
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);


        setSupportActionBar(showQuestionToolbar);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        Intent intent = getIntent();
        int type = intent.getIntExtra("difType", -1);
        int section = intent.getIntExtra("sectionId", -1);
        //Toast.makeText(this, "type: " + type + "section: " + section, Toast.LENGTH_SHORT).show();

        questionViewModel.getTestQuestion(type, section).observe(this, new Observer<List<Question>>() {

            @Override
            public void onChanged(@Nullable List<Question> questions) {
                //Toast.makeText(ShowQuestion.this, ""+questions.size(), Toast.LENGTH_SHORT).show();

                getFragmentList(questions);
            }
        });


        showQuestionNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuestionViewpager.setCurrentItem(1, true);
            }
        });
    }


    private void getFragmentList(List<Question> questions) {
        Toast.makeText(ShowQuestion.this, "total: " + questions.size(), Toast.LENGTH_SHORT).show();

        if (questions.size() > 0) {
            for (int i = 0; i < questions.size(); i++) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                QuestionFragment questionFragment = new QuestionFragment();
                questionFragment.setArguments(bundle);
                viewPagerAdapter.addFragment(questionFragment);
            }
            showQuestionViewpager.setAdapter(viewPagerAdapter);
        }
    }

    public void init() {
        showQuestionToolbar = findViewById(R.id.showQuestionToolbar);
        showQuestionCloseIB = findViewById(R.id.showQuestionCloseIB);
        showQuestionNextBtn = findViewById(R.id.showQuestionNextBtn);
        showQuestionProgressBar = findViewById(R.id.showQuestionProgressBar);
        showQuestionViewpager = findViewById(R.id.showQuestionViewpager);

        showQuestionCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
