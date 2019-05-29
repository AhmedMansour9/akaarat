package com.akaarat.Tenant_Account.Fragments;


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
public class MyReservations_Fragment extends Fragment {


    public MyReservations_Fragment() {
        // Required empty public constructor
    }

    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_reservations_, container, false);
        viewPager = view.findViewById(R.id.viewpa);
        setupViewPager(viewPager);
        tabLayout = view.findViewById(R.id.tas);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).select();

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

        if (Language.isRTL()) {
            // The view has RTL layout
            adapter.addFragment(new Rent(), getResources().getString(R.string.rent));
            adapter.addFragment(new purchasing(), getResources().getString(R.string.purchasing));
        } else {
            // The view has LTR layout
            adapter.addFragment(new purchasing(), getResources().getString(R.string.purchasing));
            adapter.addFragment(new Rent(), getResources().getString(R.string.rent));

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
