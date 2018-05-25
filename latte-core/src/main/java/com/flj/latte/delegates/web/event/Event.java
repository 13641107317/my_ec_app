package com.flj.latte.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.delegates.web.WebDelegate;

/**
 * Created by wangpeng on 2018/5/25.
 */

public abstract class Event implements IEvent {
   private Context mContext = null;
   private String mAction = null;
   private WebDelegate mDelegate = null;
   private  String mUrl = null;
   private WebView mWebView = null;

    public WebView getWebView() {
        return mDelegate.getWebView();
    }
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
