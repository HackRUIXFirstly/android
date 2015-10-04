package com.github.hackruixfirstly.firstly.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.hackruixfirstly.firstly.models.Experience;

import java.util.List;

/**
 * Created by trevor on 10/4/15.
 */
public class ExperienceListAdapter extends RecyclerView.Adapter<ExperienceListAdapter.ExperienceViewHolder> {

    private LayoutInflater   inflater;
    private List<Experience> experienceList;

    public ExperienceListAdapter(List<Experience> experiences) {
        this.experienceList = experiences;
    }


    @Override
    public ExperienceViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        return null;
    }

    @Override
    public void onBindViewHolder (ExperienceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount () {
        return experienceList.size();
    }

    class ExperienceViewHolder extends RecyclerView.ViewHolder {

        public ExperienceViewHolder (View view) {
            super(view);
        }

    }
}
