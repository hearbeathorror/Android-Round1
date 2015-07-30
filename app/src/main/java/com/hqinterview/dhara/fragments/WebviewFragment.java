package com.hqinterview.dhara.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hqinterview.dhara.R;
import com.hqinterview.dhara.models.Module;
import com.hqinterview.dhara.utils.constants.DataAccess;
import com.hqinterview.dhara.utils.constants.Global;

import java.util.List;

/**
 * Created by USER on 26-07-2015.
 */
public class WebviewFragment extends Fragment{
    private View mView;
    private String mURL;
    private Module mModule;
    private WebView mWebView;
    private List<String> mParams;
    private ProgressBar mProgressBar;
    private static WebviewFragment mFragment;

    public static WebviewFragment newInstance(Module moduleObj)  {
        mFragment = new WebviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(Global.INTENT_MODULE,  moduleObj);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_webview,container,false);
        mWebView = (WebView)mView.findViewById(R.id.webView);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        setUpWebview();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setUpWebview() {
        if(getArguments() != null) {
            mModule = (Module)getArguments().getSerializable(Global.INTENT_MODULE);
            mURL = mModule.getUrl();
            mParams = mModule.getParams();
        }

        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.setInitialScale(100);
        mWebView.setWebViewClient(new MyWebClient());

        // modules with cache value = true, will be preloaded
        if(mModule.getCache().equals("true")){
            // this does not use params
            mProgressBar.setVisibility(View.INVISIBLE);
        }else {
            mProgressBar.setVisibility(View.VISIBLE);
            if(mParams != null && mParams.size() > 0)  {
                for(String strParam : mParams)  {
                    mURL = mURL.replace(new String("{").concat(strParam).concat("}"),
                            DataAccess.getValue(strParam));
                }
            }
        }

        mWebView.loadUrl(mURL);
    }

    public class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(mModule.getCache().equals("false")) {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }
}
