package com.flj.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by wp on 2018/5/24.
 */

public abstract class WebDelegate extends LatteDelegate {
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setIWebViewInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setIWebViewInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(
                                new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(LatteWebInterface.create(this),"latte");
                mIsWebViewAvailable =true;
            }else{
                throw new NullPointerException("initializer is NULL!");
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}