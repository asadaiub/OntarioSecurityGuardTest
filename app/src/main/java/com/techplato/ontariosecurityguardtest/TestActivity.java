package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.amitshekhar.DebugDB;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private QuestionViewModel questionViewModel;
    List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        /*questionViewModel= ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getAllQuestion().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                Toast.makeText(TestActivity.this, ""+questions.size(), Toast.LENGTH_SHORT).show();
            }
        });*/

        Toast.makeText(this, ""+ DebugDB.getAddressLog(), Toast.LENGTH_SHORT).show();


    }
}
