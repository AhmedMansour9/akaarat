package com.akaarat.Tenant_Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akaarat.Fragments.Menu;
import com.akaarat.Language;
import com.akaarat.Tenant_Account.Fragments.MyAccount_Malek;
import com.akaarat.Tenant_Account.Fragments.MyContracts_Fragment;
import com.akaarat.Tenant_Account.Fragments.MyReservations_Fragment;
import com.akaarat.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Tabs_MalekAccount extends AppCompatActivity {

    public static TabLayout tabLayout;
    private ViewPager viewPager;
    View view,view1,view2,view3,view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs__rent_account);
        viewPager =findViewById(R.id.viewpager);
        tabLayout =findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setRotationX(180);
        RefreshTabs();
        setupTabIcons();

        tabLayout.getTabAt(0).select();
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
        view1 = getLayoutInflater().inflate(R.layout.iconmenu, null);
        view2 = getLayoutInflater().inflate(R.layout.icon_reservation, null);
        view3 = getLayoutInflater().inflate(R.layout.icon_contracts, null);
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
//        if(Language.isRTL()){
            adapter.addFrag(new Menu(), getResources().getString(R.string.map));
            adapter.addFrag(new MyReservations_Fragment(), getResources().getString(R.string.menu));
            adapter.addFrag(new MyContracts_Fragment(), getResources().getString(R.string.search));
            adapter.addFrag(new MyAccount_Malek(), getResources().getString(R.string.myacc));
//        }else {
//            adapter.addFrag(new My_Units_Fragment(), getResources().getString(R.string.map));
//            adapter.addFrag(new Menu(), getResources().getString(R.string.menu));
//            adapter.addFrag(new Search(), getResources().getString(R.string.search));
//            adapter.addFrag(new MyAccount(), getResources().getString(R.string.myacc));
//        }
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
        ImageView reservation=view2.findViewById(R.id.reservation);
        ImageView menu=view1.findViewById(R.id.menu);
        ImageView contract=view3.findViewById(R.id.contract);
        ImageView profile=view4.findViewById(R.id.profile);
        TextView T_Reservation=view2.findViewById(R.id.T_Reservation);
        TextView  T_Contracts=view3.findViewById(R.id.T_Contracts);
        TextView  T_Menu=view1.findViewById(R.id.T_Menu);
        TextView  T_Profile=view4.findViewById(R.id.T_Profile);



        if (poistion == 0) {
            menu.setImageResource(R.drawable.ic_menu_active);
            reservation.setImageResource(R.drawable.icon_reservation);
            T_Reservation.setTextColor(Color.parseColor("#b3b3b3"));
            T_Contracts.setTextColor(Color.parseColor("#b3b3b3"));
            T_Menu.setTextColor(Color.parseColor("#3883ab"));
            T_Profile.setTextColor(Color.parseColor("#b3b3b3"));
            contract.setImageResource(R.drawable.icon_contracts);
            profile.setImageResource(R.drawable.ic_profile);
        }
        if (poistion == 1) {
            menu.setImageResource(R.drawable.ic_tab_menu);
            contract.setImageResource(R.drawable.icon_contracts);
            profile.setImageResource(R.drawable.ic_profile);
            reservation.setImageResource(R.drawable.icon_reservation_active);
            T_Contracts.setTextColor(Color.parseColor("#b3b3b3"));
            T_Reservation.setTextColor(Color.parseColor("#3883ab"));
            T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
            T_Profile.setTextColor(Color.parseColor("#b3b3b3"));
        }
        if (poistion == 2) {
            contract.setImageResource(R.drawable.icon_contracts_active);
            profile.setImageResource(R.drawable.ic_profile);
            reservation.setImageResource(R.drawable.icon_reservation);
            menu.setImageResource(R.drawable.ic_tab_menu);
            T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
            T_Contracts.setTextColor(Color.parseColor("#3883ab"));
            T_Reservation.setTextColor(Color.parseColor("#b3b3b3"));
            T_Profile.setTextColor(Color.parseColor("#b3b3b3"));
        }
        if (poistion == 3) {
            profile.setImageResource(R.drawable.ic_user);
            reservation.setImageResource(R.drawable.icon_reservation);
            menu.setImageResource(R.drawable.ic_tab_menu);
            contract.setImageResource(R.drawable.icon_contracts);
            T_Profile.setTextColor(Color.parseColor("#3883ab"));
            T_Menu.setTextColor(Color.parseColor("#b3b3b3"));
            T_Contracts.setTextColor(Color.parseColor("#b3b3b3"));
            T_Reservation.setTextColor(Color.parseColor("#b3b3b3"));
        }
    }
}
