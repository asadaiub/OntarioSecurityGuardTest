package com.techplato.ontariosecurityguardtest.Fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.techplato.ontariosecurityguardtest.DB.Question;
import com.techplato.ontariosecurityguardtest.DB.QuestionViewModel;
import com.techplato.ontariosecurityguardtest.R;
import com.techplato.ontariosecurityguardtest.ShowQuestion;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private QuestionViewModel questionViewModel;



    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_question, container, false);
        questionViewModel= ViewModelProviders.of(this).get(QuestionViewModel.class);

        int index=getArguments().getInt("index",-1);
        Toast.makeText(getActivity(), "frag: "+index, Toast.LENGTH_SHORT).show();


        return view;
    }

}
