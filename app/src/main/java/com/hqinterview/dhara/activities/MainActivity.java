package com.hqinterview.dhara.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.hqinterview.dhara.HQInterviewDharaApp;
import com.hqinterview.dhara.R;
import com.hqinterview.dhara.fragments.HomeFragment;
import com.hqinterview.dhara.fragments.NavigationDrawerFragment;
import com.hqinterview.dhara.fragments.WebviewFragment;
import com.hqinterview.dhara.models.BaseResponseHolder;
import com.hqinterview.dhara.models.MenuModules;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.utils.CommonUtilities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by USER on 23-07-2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerListener,
        HomeFragment.IReceivedData{
    /**
     * The root of the side menu. Currently names base instead of home
     */
    private String CURRENT_SELECTION = "base";
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationDrawerFragment mNavigationFragment;
    private NavigationDrawerFragment.NavigationDrawerListener mDrawerListener;
    private HomeFragment.IReceivedData mIReceivedDataListener;
    private HashMap<String, Fragment> fragmentMap;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.closeDrawer(mFrameLayout);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mFrameLayout =(FrameLayout)findViewById(R.id.left_drawer);
        mDrawerListener =  this;
        mIReceivedDataListener = this;
        fragmentMap = new HashMap<>();

        DrawerLayout.LayoutParams params =
                new DrawerLayout.LayoutParams((int)((float) CommonUtilities.getScreenWidth() * 0.75),
                        DrawerLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.LEFT;
        mFrameLayout.setLayoutParams(params);
        mFrameLayout.invalidate();

        setupToolbar();
        initViews();
        setupDrawer();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Loads the home fragment and the side menu fragment
     */
    private void initViews(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mNavigationFragment = NavigationDrawerFragment.newInstance();
        mNavigationFragment.setListener(mDrawerListener);
        ft.replace(R.id.left_drawer, mNavigationFragment);
        ft.commit();

        HomeFragment fragment = HomeFragment.newInstance();
        fragment.setIReceivedData(mIReceivedDataListener);
        replaceFragment(fragment);
        fragmentMap.put("base", fragment);
    }

    /**
     * Sets up the toolbar
     */
    private void setupToolbar() {
        if(mToolbar != null) {
            mToolbar.setTitle(HQInterviewDharaApp.getAppContext().getString(R.string.app_name));
            mToolbar.setTitleTextColor(HQInterviewDharaApp.getAppContext()
                    .getResources().getColor(android.R.color.white));
            setSupportActionBar(mToolbar);
        }
    }

    /**
     * Sets up the navigation drawer, and associated listeners
     */
    private void setupDrawer() {
        mDrawerToggle  =  new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void onNavigationDrawerItemClicked(Module menuModule, String fieldName) {
        mDrawerLayout.closeDrawer(mFrameLayout);

        //  do something with the menu here
        if(CURRENT_SELECTION.equals(fieldName)) {
            // its the home tab selected
            // do nothing
        }else {
            // the base field has a title that is empty
            // will be called from the side menu only
            if(TextUtils.isEmpty(menuModule.getPageTitle())) {
                if(fragmentMap.containsKey(fieldName)) {
                    fragmentMap.get(CURRENT_SELECTION).onPause();
                    replaceFragment(fragmentMap.get(fieldName));

                    if(mToolbar != null) {
                        mToolbar.setTitle(HQInterviewDharaApp.getAppContext().getString(R.string.app_name));
                    }
                }
            }else {
                // load the webview content since it is not the base field
                WebviewFragment webviewFragment = WebviewFragment.newInstance(menuModule);
                replaceFragment(webviewFragment);
                fragmentMap.put(fieldName, webviewFragment);

                if(mToolbar != null) {
                    mToolbar.setTitle(menuModule.getPageTitle());
                }
            }
            CURRENT_SELECTION = fieldName;
        }
    }

    /**
     * Replaces the content frame with the fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    /**
     * Receives the side menu list to display as menu items </br>
     * All items with field cache = true, are part of the side menu.
     * @param fieldList
     * @param holder
     */
    @Override
    public void onDataReceived(List<Field> fieldList, BaseResponseHolder holder) {
        if(mNavigationFragment != null)  {
            mNavigationFragment.dataReceived(fieldList, holder);
        }
    }
}
