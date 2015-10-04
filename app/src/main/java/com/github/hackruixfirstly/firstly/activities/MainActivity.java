package com.github.hackruixfirstly.firstly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.hackruixfirstly.firstly.FirstlyApplication;
import com.github.hackruixfirstly.firstly.R;
import com.github.hackruixfirstly.firstly.adapters.ExperienceListAdapter;
import com.github.hackruixfirstly.firstly.depdendencies.AccessTokenHolder;
import com.github.hackruixfirstly.firstly.models.Experience;
import com.github.hackruixfirstly.firstly.network.FirstlyAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject FirstlyAPI        API;
    @Inject AccessTokenHolder tokenHolder;

    @Bind (R.id.toolbar)                Toolbar              toolbar;
    @Bind (R.id.fab)                    FloatingActionButton fab;
    @Bind (R.id.activity_main_recycler) RecyclerView         recycler;

    ExperienceListAdapter adapter;

    List<Experience> experienceList = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ((FirstlyApplication) getApplication()).getComponent().inject(this);

        adapter = new ExperienceListAdapter(experienceList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        API.getFeed(tokenHolder.token).enqueue(new Callback<List<Experience>>() {
            @Override
            public void onResponse (Response<List<Experience>> response, Retrofit retrofit) {
                experienceList.clear();
                experienceList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure (Throwable t) {
                Timber.e(t.toString());
            }
        });

        ((FirstlyApplication) getApplication()).getComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick (R.id.fab)
    public void onFabClick (View v) {
        Intent intent = new Intent(this, NewExperienceActivity.class);
        startActivity(intent);
    }
}
