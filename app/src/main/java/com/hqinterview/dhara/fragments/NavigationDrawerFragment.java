package com.hqinterview.dhara.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.hqinterview.dhara.HQInterviewDharaApp;
import com.hqinterview.dhara.R;
import com.hqinterview.dhara.adapters.MenuAdapter;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.models.MenuModules;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.utils.CommonUtilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 05-07-2015.
 */
public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View mView;
    private ListView mLstMenu;
    private BaseResponseHolder mBaseHolder;
    private List<String> mMenuItems;
    private MenuAdapter mMenuAdapter;
    private static NavigationDrawerFragment mFragment;

    private NavigationDrawerListener mListener;

    public interface NavigationDrawerListener{
        void onNavigationDrawerItemClicked(Module menuItem, String fieldName);
    }

    public void setListener(NavigationDrawerListener listener ) {
        mListener = listener;
    }

    public static NavigationDrawerFragment newInstance() {
        mFragment = new NavigationDrawerFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mLstMenu = (ListView)mView.findViewById(R.id.left_drawer);

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams((int)((float) CommonUtilities.getScreenWidth() * 0.75),
                        FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.LEFT;
        mLstMenu.setLayoutParams(params);
        mLstMenu.setOnItemClickListener(this);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMenuItems = new ArrayList<>();
        mMenuAdapter = new MenuAdapter(HQInterviewDharaApp.getAppContext(),
                R.layout.individual_row,
                mMenuItems);
        mLstMenu.setAdapter(mMenuAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String fieldName = mMenuItems.get(position);
        try {
            BaseResponseHolder menuModule = mBaseHolder;
            for (Field field : mBaseHolder.getClass().getDeclaredFields()) {
                if(field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    Object moduleValue = field.get(menuModule);

                    if(mListener != null) {
                        mListener.onNavigationDrawerItemClicked((Module)moduleValue, fieldName);
                    }
                    break;
                }
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the field items for the side menu
     * @param fieldList
     * @param holder
     */
    public void dataReceived(List<Field> fieldList,BaseResponseHolder holder) {
        mBaseHolder  = holder;
        // set side menu here
        mMenuItems = new ArrayList<>();
        for(Field  field: fieldList) {
            mMenuItems.add(field.getName());
        }
        mMenuAdapter = new MenuAdapter(HQInterviewDharaApp.getAppContext(),
                R.layout.individual_row,
                mMenuItems);
        mLstMenu.setAdapter(mMenuAdapter);
    }
}
