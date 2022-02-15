package com.example.covizi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ThingstoKnowFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<ThingsToKnow> lstThings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_thingsto_know, container,false);
        recyclerView = v.findViewById(R.id.things_recylerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstThings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstThings = new ArrayList<>();
        lstThings.add(new ThingsToKnow("COVID-19 IN MALAYSIA AS OF 25 OCTOBER 2021","25 Oct 2021, 12:00PM",R.drawable.covid_case));
        lstThings.add(new ThingsToKnow("COVID-19 IN MALAYSIA AS OF 26 OCTOBER 2021","26 Oct 2021, 12:00PM",R.drawable.covid_case));
    }
}