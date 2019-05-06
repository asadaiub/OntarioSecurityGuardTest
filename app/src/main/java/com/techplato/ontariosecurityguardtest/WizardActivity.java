package com.techplato.ontariosecurityguardtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WizardActivity extends AppCompatActivity {

    private static final int MAX_STEP = 4;

    private ViewPager wizardViewPager;
    private WizardPagerAdapter wizardPagerAdapter;
    private Button wizardBtnNext;

    ImageButton wizardCloseIB;

    private String title_array[] = {
            "Smooth UI",
            "Test Yourself",
            "Chase Your Dream",
            "Enjoy Your App"
    };
    private String description_array[] = {
            "It is focused on developer experience, productivity. You can focus on what you want to build instead of on how to build it.",
            "With the help of Professional questions, give yourself a chance! ",
            "Be the best in your field",
            "Good luck Have fun!",
    };
    private int images_array[] = {
            R.drawable.img_wizard_1,
            R.drawable.img_wizard_2,
            R.drawable.img_wizard_3,
            R.drawable.img_wizard_4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        init();

        setSystemBarColor(this, R.color.grey_5);
        setSystemBarLight(this);

    }

    private void init() {
        wizardViewPager = findViewById(R.id.wizardViewPager);
        wizardBtnNext = findViewById(R.id.wizardBtnNext);
        wizardCloseIB = findViewById(R.id.wizardCloseIB);

        bottomProgressDots(0);
        wizardPagerAdapter=new WizardPagerAdapter();
        wizardViewPager.setAdapter(wizardPagerAdapter);
        wizardViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomProgressDots(i);

                if (i == title_array.length - 1) {
                    wizardBtnNext.setText("Got it");
                    wizardBtnNext.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    wizardBtnNext.setTextColor(Color.WHITE);

                } else {
                    wizardBtnNext.setText("Next");
                    wizardBtnNext.setBackgroundColor(getResources().getColor(R.color.grey_10));
                    wizardBtnNext.setTextColor(getResources().getColor(R.color.grey_90));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        wizardBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = wizardViewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    wizardViewPager.setCurrentItem(current);
                } else {
                    startActivity(new Intent(WizardActivity.this,AppActivity.class));
                    finish();
                }
            }
        });

        wizardCloseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WizardActivity.this,AppActivity.class));
                finish();
            }
        });

    }













    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.wizardDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

        }
    }





    public class WizardPagerAdapter extends PagerAdapter {
        LayoutInflater layoutInflater;

        public WizardPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.wizard_single_layout, container, false);
            ((TextView) view.findViewById(R.id.wizardSingleTV)).setText(title_array[position]);
            ((TextView) view.findViewById(R.id.wizardSingleDescTV)).setText(description_array[position]);
            ((ImageView) view.findViewById(R.id.wizardSingleIV)).setImageResource(images_array[position]);
            container.addView(view);

            return view;
        }


        @Override
        public int getCount() {
            return title_array.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    public void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }



}
