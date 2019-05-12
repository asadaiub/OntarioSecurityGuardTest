package com.techplato.ontariosecurityguardtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;
import com.techplato.ontariosecurityguardtest.Model.SubcategoryModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategory extends AppCompatActivity {
    LiveData<List<Question>> subListProgress;
    List<SubcategoryModel> subcategoryModelList;
    SubCategoryAdapter subCategoryAdapter;

    Toolbar subcategoryToolbar;

    private QuestionViewModel questionViewModel;
    int type;
    RecyclerView subCategoryRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        subCategoryRV=findViewById(R.id.subCategoryRV);
        subcategoryToolbar=findViewById(R.id.subcategoryToolbar);
        setSupportActionBar(subcategoryToolbar);

        subcategoryModelList=new ArrayList<>();
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        Intent intent = getIntent();
        type = intent.getIntExtra("difType", -1);


        subCategoryRV.setLayoutManager(new LinearLayoutManager(this));
        subCategoryAdapter=new SubCategoryAdapter(subcategoryModelList,type,this);
        subCategoryRV.setAdapter(subCategoryAdapter);


        questionViewModel.getSubcategoryList(type).observe(this, new Observer<List<SubcategoryModel>>() {
            @Override
            public void onChanged(@Nullable List<SubcategoryModel> subcategoryModels) {
                subCategoryAdapter.setContent(subcategoryModels);

            }
        });
        findViewById(R.id.subcategoryCloseIB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
