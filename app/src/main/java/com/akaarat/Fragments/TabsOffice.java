package com.akaarat.Fragments;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akaarat.Language;
import com.akaarat.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsOffice extends Fragment {


    public TabsOffice() {
        // Required empty public constructor
    }

    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details_office, container, false);

        viewPager = view.findViewById(R.id.viewpa);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tas);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(
                ColorStateList.valueOf(Color.BLACK));
        if (Language.isRTL()) {
            tabLayout.getTabAt(1).select();
        } else {
            tabLayout.getTabAt(0).select();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

        if (Language.isRTL()) {
            // The view has RTL layout
            adapter.addFragment(new About_office(), getResources().getString(R.string.aboutoffice));
            adapter.addFragment(new RealStates_Office(), getResources().getString(R.string.realstate));
            adapter.addFragment(new Rating_Office(), getResources().getString(R.string.rating));
        } else {
            // The view has LTR layout
            adapter.addFragment(new Rating_Office(), getResources().getString(R.string.rating));
            adapter.addFragment(new RealStates_Office(), getResources().getString(R.string.realstate));
            adapter.addFragment(new About_office(), getResources().getString(R.string.aboutoffice));
        }

        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}