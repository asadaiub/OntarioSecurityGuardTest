package com.techplato.ontariosecurityguardtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.techplato.ontariosecurityguardtest.ExamActivity;
import com.techplato.ontariosecurityguardtest.Model.SubcategoryModel;
import com.techplato.ontariosecurityguardtest.R;
import com.techplato.ontariosecurityguardtest.ShowQuestion;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder> {
    List<SubcategoryModel> subcategoryModelList=new ArrayList<>();
    int type;
    Context context;

    public SubCategoryAdapter(List<SubcategoryModel> subcategoryModelList, int type, Context context) {
        this.subcategoryModelList = subcategoryModelList;
        this.type = type;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.part_single_layout,viewGroup,false);
        return new SubCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryHolder subCategoryHolder, final int i) {
        subCategoryHolder.partProgress.setProgressWithAnimation(subcategoryModelList.get(i).getIsRight());
        subCategoryHolder.partProgressTitle.setText("Section "+subcategoryModelList.get(i).getSectionId());
        subCategoryHolder.partProgressValue.setText(subcategoryModelList.get(i).getIsRight()+"%");
        subCategoryHolder.partRightTV.setText(": "+subcategoryModelList.get(i).getIsRight());
        subCategoryHolder.partWrongTV.setText(": "+(subcategoryModelList.get(i).getIsAnswered()-subcategoryModelList.get(i).getIsRight()));

        subCategoryHolder.partParentCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ExamActivity.class);
                intent.putExtra("sectionId",subcategoryModelList.get(i).getSectionId());
                intent.putExtra("difType",type);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcategoryModelList.size();
    }

    public void setContent(List<SubcategoryModel> subcategoryModelList){
        this.subcategoryModelList=subcategoryModelList;
        notifyDataSetChanged();
    }

    class SubCategoryHolder extends RecyclerView.ViewHolder {
        TextView partProgressTitle,partProgressValue,partRightTV,partWrongTV;
        CircularProgressBar partProgress;
        ConstraintLayout partParentCL;

        public SubCategoryHolder(@NonNull View itemView) {
            super(itemView);
            partProgressTitle=itemView.findViewById(R.id.partProgressTitle);
            partProgressValue=itemView.findViewById(R.id.partProgressValue);
            partProgress=itemView.findViewById(R.id.partProgress);
            partParentCL=itemView.findViewById(R.id.partParentCL);
            partRightTV=itemView.findViewById(R.id.partRightTV);
            partWrongTV=itemView.findViewById(R.id.partWrongTV);
        }
    }
}
