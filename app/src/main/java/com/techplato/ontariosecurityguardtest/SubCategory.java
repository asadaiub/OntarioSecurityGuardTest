package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;

import java.util.List;

public class SubCategory extends AppCompatActivity {
    List<Integer> subListCounter;
    LiveData<List<Question>> subListProgress;
    private QuestionViewModel questionViewModel;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        Intent intent = getIntent();
        type = intent.getIntExtra("difType", -1);


        questionViewModel.getSubcategoryList(type).observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                for (int i=0;i<integers.size();i++){
                    Toast.makeText(SubCategory.this, "ypp "+integers.get(i), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
