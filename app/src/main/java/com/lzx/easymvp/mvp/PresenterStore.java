package com.lzx.easymvp.mvp;

import android.content.Context;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * create by lzx
 * time:2018/7/26
 */
public class PresenterStore<P extends BasePresenter> {

    private static final String DEFAULT_KEY = "PresenterStore.DefaultKey";
    private final HashMap<String, P> mMap = new HashMap<>();

    public final void put(String key, P presenter) {
        P oldPresenter = mMap.put(DEFAULT_KEY + ":" + key, presenter);
        if (oldPresenter != null) {
            oldPresenter.onCleared();
        }
    }

    public final P get(String key) {
        return mMap.get(DEFAULT_KEY + ":" + key);
    }

    public final void clear() {
        for (P presenter : mMap.values()) {
            presenter.onCleared();
        }
        mMap.clear();
    }

    public void attachView(Context context, BaseContract.View view) {
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            BasePresenter presenter = entry.getValue();
            if (presenter != null) {
                presenter.attachView(context, view);
            }
        }
    }

    public void detachView() {
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            BasePresenter presenter = entry.getValue();
            if (presenter != null) {
                presenter.detachView();
            }
        }
    }

    public void onCreatePresenter(Bundle savedInstanceState){
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            BasePresenter presenter = entry.getValue();
            if (presenter != null) {
                presenter.onCreatePresenter(savedInstanceState);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        for (Map.Entry<String, P> entry : mMap.entrySet()) {
            BasePresenter presenter = entry.getValue();
            if (presenter != null) {
                presenter.onSaveInstanceState(outState);
            }
        }
    }

    public int getSize(){
        return mMap.size();
    }


}
