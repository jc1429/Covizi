package com.example.covizi.ui.stats;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covizi.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticFragment extends Fragment {

    private StatisticViewModel mViewModel;

    private static final int NUM_PAGES = 3;
    //The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
    public static ViewPager2 viewPager;
    // The pager adapter, which provides the pages to the view pager widget.
    private FragmentStateAdapter pagerAdapter;
    // Arrey of strings FOR TABS TITLES
    private String[] titles = new String[]{"COVID-19 Updates", "COVID-19 States", "COVID-19 Global Update"};


    public static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_fragment, container, false);

        // Initialization of the viewpager and tablayout and the adapter
        viewPager = (ViewPager2) view.findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        //inflating tab layout
        TabLayout tabLayout =(TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        //displaying tabs
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(titles[position])).attach();


        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StatisticViewModel.class);
        // TODO: Use the ViewModel
    }

}

class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(StatisticFragment fa) {super(fa);}

    @Override
    public Fragment createFragment(int pos){
        switch(pos) {
            case 0: {
                return MalaysiaStatsFragment.newInstance();
            }
            case 1:{
                return CountryStatsFragment.newInstance();
            }
            case 2:{
                return GlobalStatsFragment.newInstance();
            }
            default:
                return MalaysiaStatsFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() { return 3; }
}