package com.hqinterview.dhara.utils;

import com.hqinterview.dhara.HQInterviewDharaApp;

/**
 * Created by USER on 05-07-2015.
 */
public class CommonUtilities {
    public static int getScreenHeight(){
        return HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getOriginalScreenWidth(){
        return ((int)((float)HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().widthPixels /
                HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().density));
    }

    public static int getOriginalScreenHeight(){
        return ((int)((float)HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().heightPixels /
                HQInterviewDharaApp.getAppContext().getResources().getDisplayMetrics().density));
    }
}
