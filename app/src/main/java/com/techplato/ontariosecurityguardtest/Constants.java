package com.techplato.ontariosecurityguardtest;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

public class Constants {
    public static final String  PREFERANCE_NAME = "ontariosecurityguardtest";

    //ca-app-pub-3940256099942544~3347511713

    public static AdRequest mAdRequest(){
        AdRequest adRequest = new AdRequest.Builder().build();
        return adRequest;
    }


}
