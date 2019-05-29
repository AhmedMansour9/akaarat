package com.akaarat.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akaarat.Fragments.Maps;
import com.akaarat.Fragments.Menu;
import com.akaarat.Fragments.MyAccount;
import com.akaarat.Fragments.Search;
import com.akaarat.Language;
import com.akaarat.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Tabs extends AppCompatActivity {


    public Home_Tabs() {
        // Required empty public constructor
    }
    public static TabLayout tabLayout;
    private ViewPager viewPager;
     View view,view1,view2,view3,view4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tabs);


        viewPager =findViewById(R.id.viewpager);
        tabLayout =findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setRotationX(180);

//        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));
//        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        RefreshTabs();
        setupTabIcons();

        tabLayout.getTabAt(1).select();
    }



    public void RefreshTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
              Selected_Postion(tab.getPosition());


                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setupTabIcons() {
         view1 = getLayoutInflater().inflate(R.layout.iconmap, null);
         view2 = getLayoutInflater().inflate(R.layout.iconmenu, null);
         view3 = getLayoutInflater().inflate(R.layout.iconsearch, null);
         view4 = getLayoutInflater().inflate(R.layout.iconmyaccount, null);
        if(Language.isRTL()){
            tabLayout.getTabAt(3).setCustomView(view4);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(0).setCustomView(view1);
        }else {
            tabLayout.getTabAt(0).setCustomView(view1);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view4);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(Language.isRTL()){
            adapter.addFrag(new Maps(), getResources().getString(R.string.map));
            adapter.addFrag(new Menu(), getResources().getString(R.string.menu));
            adapter.addFrag(new Search(), getResources().getString(R.string.search));
            adapter.addFrag(new MyAccount(), getResources().getString(R.string.myacc));
        }else {
            adapter.addFrag(new Maps(), getResources().getString(R.string.map));
            adapter.addFrag(new Menu(), getResources().getString(R.string.menu));
            adapter.addFrag(new Search(), getResources().getString(R.string.search));
            adapter.addFrag(new MyAccount(), getResources().getString(R.string.myacc));
        }
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void Selected_Postion(int poistion){
        ImageView map=view1.findViewById(R.id.map);
        ImageView menu=view2.findViewById(R.id.menu);
        ImageView search=view3.findViewById(R.id.search);
        ImageView profile=view4.findViewById(R.id.profile);
        TextView  T_Map=view1.findViewById(R.id.T_Map);
        TextView  T_Menu=view2.findViewById(R.id.T_Menu);
        TextView  T_Search=view3.findViewById(R.id.T_Search);
        TextView  T_Profile=view4.findViewById(R.id.T_Profile);



            if (poistion == 0) {
                map.setImageResource(R.drawable.ic_map_active);
               T_Map.setTextColor(Color.parseColor("#3883ab"));
                T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
                T_Search.setTextColor(Color.parseColor("#b3b3b3"));
                T_Profile.setTextColor(Color.parseColor("#b3b3b3"));
                menu.setImageResource(R.drawable.ic_tab_menu);
                search.setImageResource(R.drawable.ic_search);
                profile.setImageResource(R.drawable.ic_profile);
            }
            if (poistion == 1) {
                menu.setImageResource(R.drawable.ic_menu_active);
                search.setImageResource(R.drawable.ic_search);
                profile.setImageResource(R.drawable.ic_profile);
                map.setImageResource(R.drawable.ic_map);
                T_Menu.setTextColor(Color.parseColor("#3883ab"));
                T_Map.setTextColor(Color.parseColor("#b3b3b3"));
                T_Search.setTextColor(Color.parseColor("#b3b3b3"));
                T_Profile.setTextColor(Color.parseColor("#b3b3b3"));

            }
            if (poistion == 2) {
                search.setImageResource(R.drawable.ic_advanced_search_active);
                profile.setImageResource(R.drawable.ic_profile);
                map.setImageResource(R.drawable.ic_map);
                menu.setImageResource(R.drawable.ic_tab_menu);
                T_Search.setTextColor(Color.parseColor("#3883ab"));
                T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
                T_Map.setTextColor(Color.parseColor("#b3b3b3"));
                T_Profile.setTextColor(Color.parseColor("#b3b3b3"));

            }
            if (poistion == 3) {
                profile.setImageResource(R.drawable.ic_user);
                map.setImageResource(R.drawable.ic_map);
                menu.setImageResource(R.drawable.ic_tab_menu);
                search.setImageResource(R.drawable.ic_search);
                T_Profile.setTextColor(Color.parseColor("#3883ab"));
                T_Search.setTextColor(Color.parseColor("#b3b3b3"));
                T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
                T_Map.setTextColor(Color.parseColor("#b3b3b3"));
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        List<Fragment> listOfFragments =getSupportFragmentManager().getFragments();

        if(listOfFragments.size()>=1){
            for (Fragment fragment : listOfFragments) {
                if(fragment instanceof Maps){
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
                if(fragment instanceof Menu){
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
                if(fragment instanceof Search){
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
