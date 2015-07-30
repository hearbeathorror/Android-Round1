package com.hqinterview.dhara.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hqinterview.dhara.HQInterviewDharaApp;
import com.hqinterview.dhara.R;
import com.hqinterview.dhara.fragments.WebviewFragment;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.utils.constants.Global;

/**
 * Created by USER on 26-07-2015.
 */
public class DetailViewActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Module mModule;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        if(getIntent().getExtras() != null) {
            mModule = (Module)getIntent().getSerializableExtra(Global.INTENT_MODULE);
        }

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setupToolbar();
        setupFragment();
    }

    /**
     * Sets up the toolbar, since it is the next activity we set the backb button also
     */
    private void setupToolbar(){
        if(mToolbar != null) {
            mToolbar.setTitle((mModule != null) ?
                    mModule.getPageTitle() :
                    HQInterviewDharaApp.getAppContext().getString(R.string.app_name));
            mToolbar.setTitleTextColor(HQInterviewDharaApp.getAppContext()
                    .getResources().getColor(android.R.color.white));
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    /**
     * Loads the webview
     */
    private void setupFragment() {
        WebviewFragment fragment = WebviewFragment.newInstance(mModule);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
