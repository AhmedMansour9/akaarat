package com.akaarat.Tenant_Account.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akaarat.Adapter.Units_Adapter;
import com.akaarat.EndlessRecyclerViewScrollListener;
import com.akaarat.Language;
import com.akaarat.Model.Units_Detail;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.Tenant_Account.Adapter.Reservation_Adapter;
import com.akaarat.Tenant_Account.Model.Reservation_Details;
import com.akaarat.Tenant_Account.Model.ReservestionsDetail;
import com.akaarat.Tenant_Account.Presenter.Reservation_Presenter;
import com.akaarat.Tenant_Account.View.Reservation_View;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservations_Fragment extends Fragment implements Reservation_View ,SwipeRefreshLayout.OnRefreshListener{


    public MyReservations_Fragment() {
        // Required empty public constructor
    }

    View view;
    @BindView(R.id.btn_rent_reserv_buy)
    Button btnBuy;
    @BindView(R.id.btn_rent_reserv_rent)
    Button btnRent;
    Reservation_Presenter reservation_presenter;
    String Clintid,Lang,Type;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Boolean recycle=true;
    int PAGE=1;
    Reservation_Adapter reservation_adapter;
    ReservestionsDetail units_detail;
    List<ReservestionsDetail> list_Units=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_reservations_, container, false);
        ButterKnife.bind(this,view);
        reservation_presenter=new Reservation_Presenter(getContext(),this);
        Clintid= SharedPrefManager.getInstance(getContext()).getClintid();
        Language();
        init();
        SwipRefresh();
        onScroll();
        ChooseType();



        return view;
    }
    public void init(){
        Type="1";
        reservation_adapter=new Reservation_Adapter(list_Units,getContext());
        recyclerView=view.findViewById(R.id.recycler_Units);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(reservation_adapter);

    }
    public void onScroll(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//       TabsLayouts.T_Service.setText(getResources().getString(R.string.spare));
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                recycle=false;
//                    mSwipeRefreshLayout.setRefreshing(true);
                PAGE++;
                reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Units);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                list_Units.clear();
                    PAGE = 1;
                reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
    }

    public void ChooseType(){
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_Units.clear();
                reservation_adapter.notifyDataSetChanged();
                Type="2";
                PAGE=1;
                btnBuy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_rent));
                btnBuy.setTextColor(Color.rgb(240, 245, 251));
                btnRent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_sales));
                btnRent.setTextColor(Color.rgb(  54,121,201));
                mSwipeRefreshLayout.setRefreshing(true);
                reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_Units.clear();
                reservation_adapter.notifyDataSetChanged();
                Type="1";
                PAGE=1;
                btnRent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_rent));
                btnRent.setTextColor(Color.rgb(240, 245, 251));
                btnBuy.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_sales));
                btnBuy.setTextColor(Color.rgb(  54,121,201));
                mSwipeRefreshLayout.setRefreshing(true);
                reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
    }


    @Override
    public void list(List<Reservation_Details> list) {
        list_Units.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        for (int i=0;i<list.size();i++){
            units_detail=new ReservestionsDetail();
            units_detail.setId(list.get(i).getId());
            units_detail.setBookingDate(list.get(i).getBookingDate());
            units_detail.setCustomername(list.get(i).getCustomername());
            units_detail.setPaymrntmethod(list.get(i).getPaymrntmethod());
            units_detail.setUnitname(list.get(i).getUnitname());
            units_detail.setRentDate(list.get(i).getRentDate());
            units_detail.setRenttime(list.get(i).getRenttime());
            list_Units.add(units_detail);
        }
        reservation_adapter.notifyItemRangeInserted(reservation_adapter.getItemCount(), list_Units.size() - 1);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }else {
            Lang="en";
        }
    }

    @Override
    public void onRefresh() {
        recycle=true;
        list_Units.clear();
        reservation_adapter.notifyDataSetChanged();
        PAGE=1;
        mSwipeRefreshLayout.setRefreshing(true);
        reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);


    }
}
