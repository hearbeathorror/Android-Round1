package com.hqinterview.dhara.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.webkit.WebView;

import com.google.common.cache.Cache;
import com.google.common.collect.Collections2;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.utils.FileUtilities;
import com.hqinterview.dhara.utils.constants.Global;
import com.hqinterview.dhara.utils.search.CachedNonCachedPredicate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by USER on 30-07-2015.
 */
public class CacheWebviewService extends Service{
    private BaseResponseHolder  mBaseHolderResponse;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBaseHolderResponse = (BaseResponseHolder)FileUtilities.readObjectFromFile(Global.CACHE_DATA);
        Collection<Field> cacheTrueFields =
                Collections2.filter(new ArrayList<>(Arrays.asList(mBaseHolderResponse.getClass().getDeclaredFields())),
                        new CachedNonCachedPredicate(mBaseHolderResponse, "true"));

        // pick only those fields from the main obj </br>
        for(Field fieldObj : cacheTrueFields) {
            String fieldName = fieldObj.getName();
            try {
                BaseResponseHolder menuModule = mBaseHolderResponse;
                for (Field field : mBaseHolderResponse.getClass().getDeclaredFields()) {
                    if(field.getName().equals(fieldName)) {
                        field.setAccessible(true);
                        Object moduleValue = field.get(menuModule);

                        // cache the data
                        WebView webView = new WebView(CacheWebviewService.this);
                        webView.loadUrl(((Module)moduleValue).getUrl());
                    }
                }
                stopSelf();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
