package com.github.hackruixfirstly.firstly;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.github.hackruixfirstly.firstly.depdendencies.DaggerFirstlyComponent;
import com.github.hackruixfirstly.firstly.depdendencies.FirstlyComponent;
import com.github.hackruixfirstly.firstly.depdendencies.FirstlyModule;

import timber.log.Timber;

/**
 * Created by trevor on 10/3/15.
 */
public class FirstlyApplication extends Application {

    private FirstlyComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        FacebookSdk.sdkInitialize(getApplicationContext());

        mComponent = DaggerFirstlyComponent.builder().firstlyModule(new FirstlyModule(this)).build();

    }

    public FirstlyComponent getComponent() {
        return mComponent;
    }
}
