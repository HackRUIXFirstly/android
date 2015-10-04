package com.github.hackruixfirstly.firstly.depdendencies;

import com.github.hackruixfirstly.firstly.activities.LoginActivity;
import com.github.hackruixfirstly.firstly.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by trevor on 10/3/15.
 */

@Component(modules = {FirstlyModule.class})
@Singleton
public interface FirstlyComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

}
