package com.hqinterview.dhara;

import android.app.Application;
import android.content.Context;

import com.hqinterview.dhara.utils.constants.DataAccess;
import com.hqinterview.dhara.utils.network.NetworkUtilities;

/**
 * Created by USER on 23-07-2015.
 */
public class HQInterviewDharaApp extends Application{
    private static HQInterviewDharaApp mApp;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        setupNetwork();
        setupAccess();
    }

    private void setupNetwork() {
        NetworkUtilities.init();
    }

    private void setupAccess(){
        DataAccess.init();
    }

    /**
     * returns the app context
     * @return
     */
    public static HQInterviewDharaApp getAppContext(){
        if(mApp == null) {
            mApp = (HQInterviewDharaApp)mContext;
        }
        return mApp;
    }
}
