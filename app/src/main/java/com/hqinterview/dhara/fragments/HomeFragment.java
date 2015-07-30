package com.hqinterview.dhara.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.common.collect.Collections2;
import com.hqinterview.dhara.HQInterviewDharaApp;
import com.hqinterview.dhara.R;
import com.hqinterview.dhara.activities.DetailViewActivity;
import com.hqinterview.dhara.adapters.HomeAdapter;
import com.hqinterview.dhara.asynctasks.GetBaseDataAsyncTask;
import com.hqinterview.dhara.interfaces.IResponseListener;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.models.MenuModules;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.service.CacheWebviewService;
import com.hqinterview.dhara.utils.FileUtilities;
import com.hqinterview.dhara.utils.constants.DataAccess;
import com.hqinterview.dhara.utils.constants.Global;
import com.hqinterview.dhara.utils.network.NetworkUtilities;
import com.hqinterview.dhara.utils.search.CachedNonCachedPredicate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by USER on 25-07-2015.
 */
public class HomeFragment extends Fragment implements IResponseListener, AdapterView.OnItemClickListener {
    private View mView;
    private IReceivedData mReceivedDataListener;
    private IResponseListener mResponseListener;
    private static HomeFragment mFragment;
    private MenuModules mMenuModule;
    private ProgressBar mProgressBar;
    private BaseResponseHolder mBaseHolderResponse;
    private List<String> mMenuFieldNames;
    private List<Field> mFieldList;
    private ListView mListView;
    private HomeAdapter mHomeAdapter;

    public interface IReceivedData {
        /**
         * Receives the list of fields that are part of the side menu </br>
         * Implemented by {@Link NavigationDrawerFragment}
         * @param fieldList
         * @param holder
         */
        void onDataReceived(List<Field> fieldList,BaseResponseHolder holder);
    }

    public void setIReceivedData(IReceivedData receivedDataListener){
        mReceivedDataListener = receivedDataListener;
    }

    public static HomeFragment newInstance() {
        mFragment = new HomeFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container, false);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListView = (ListView)mView.findViewById(android.R.id.list);
        mResponseListener = this;
        mFieldList = new ArrayList<>();
        mListView.setOnItemClickListener(this);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mBaseHolderResponse !=null) {
            // set data here
            setData();
        }else {
            // make webservice call
            if(NetworkUtilities.isNetworkAvailable()) {
                mProgressBar.setVisibility(View.VISIBLE);
                new GetBaseDataAsyncTask(mResponseListener).execute();
            }else {
                // there is no internet
                // show message
                Toast.makeText(HQInterviewDharaApp.getAppContext(),
                        HQInterviewDharaApp.getAppContext().getString(R.string.no_internet),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Field field = mFieldList.get(position);
        BaseResponseHolder baseHolder = mBaseHolderResponse;
        try {
            for (Field fieldObj : mBaseHolderResponse.getClass().getDeclaredFields()) {
                if(fieldObj.getName().equals(field.getName())) {
                    fieldObj.setAccessible(true);
                    Object moduleValue = fieldObj.get(baseHolder);
                    Module moduleToSend = ((Module)moduleValue);

                    // there is a param value missing in the api
                    // to prevent restricted access error
                    if(fieldObj.getName().equals("ordersSavings")) {
                        moduleToSend.getParams().add(DataAccess.APP_SECRET_KEY);
                    }

                    Intent intent =  new Intent(HQInterviewDharaApp.getAppContext(),
                            DetailViewActivity.class);
                    intent.putExtra(Global.INTENT_MODULE,moduleToSend);
                    startActivity(intent);
                    break;
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseReceived(Object object) {
        mProgressBar.setVisibility(View.GONE);
        if (object != null) {
            if (object instanceof BaseResponseHolder) {
                BaseResponseHolder baseResponseHolder = (BaseResponseHolder) object;
                mBaseHolderResponse = baseResponseHolder;
                saveToFile();
                setData();
            }
        }
    }

    private void saveToFile() {
        FileUtilities.writeObjectToFile(mBaseHolderResponse);
    }

    private void setData() {
        // Get a list of fields with the  value cache =  true
        Collection<Field> cacheTrueFields =
                Collections2.filter(new ArrayList<>(Arrays.asList(mBaseHolderResponse.getClass().getDeclaredFields())),
                        new CachedNonCachedPredicate(mBaseHolderResponse, "true"));
        List<Field> fieldList = new ArrayList<>();
        fieldList.addAll(cacheTrueFields);

        // start a service to cache the links or pre-fetch them
        HQInterviewDharaApp.getAppContext().startService(new Intent(HQInterviewDharaApp.getAppContext(),
                CacheWebviewService.class));

        // Get a list of fields with cache value != false </br>
        // Display the list as part of the homescreen </br>
        // the title of the webview will be the pagetitle </br>
        Collection<Field> cacheFalseFields =
                Collections2.filter(new ArrayList<>(Arrays.asList(mBaseHolderResponse.getClass().getDeclaredFields())),
                        new CachedNonCachedPredicate(mBaseHolderResponse, "false"));
        mFieldList.addAll(cacheFalseFields);

        // sets the side menu
        if (mReceivedDataListener != null) {
            mReceivedDataListener.onDataReceived(fieldList,mBaseHolderResponse);
        }

        // now set the main home screen
        setAdapter();
    }

    private void setAdapter() {
        mHomeAdapter = new HomeAdapter(HQInterviewDharaApp.getAppContext(),
                R.layout.individual_home_row,
                mFieldList);
        mListView.setAdapter(mHomeAdapter);
    }
}
