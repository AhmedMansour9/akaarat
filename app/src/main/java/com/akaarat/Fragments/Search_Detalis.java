package com.akaarat.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akaarat.Adapter.Units_Adapter;
import com.akaarat.EndlessRecyclerViewScrollListener;
import com.akaarat.Language;
import com.akaarat.Model.Units;
import com.akaarat.Model.Units_Detail;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.R;
import com.akaarat.View.GetUnits_View;
import com.akaarat.View.ListUnitDetails_View;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Detalis extends Fragment implements ListUnitDetails_View,
        SwipeRefreshLayout.OnRefreshListener,GetUnits_View {


    public Search_Detalis() {
        // Required empty public constructor
    }

    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    GetUnits_Presenter getUnits_presenter;
    List<Units_Detail> list_Units=new ArrayList<>();
    Units_Adapter units_adapter;
    Units_Detail units_detail;
    String Lang,Type;
    TextView noresult,Title;
    String perpusid,Tybe_id,SizebyMeter,NumberByrRooms,PriceFrom,PriceTo,lat,lng;
    Boolean recycle=true;
    int PAGE=1;
    Boolean Bolean_Search=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search__detalis, container, false);
        Language();
        init();
        getData();
        SwipRefresh();
        onScroll();
        return view;
    }

    public void getData(){
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        Title=view.findViewById(R.id.Title);
        noresult=view.findViewById(R.id.noresult);
        Bundle bundle=getArguments();
        perpusid=bundle.getString("perpusid");
        Tybe_id=bundle.getString("Tybe_id");
        SizebyMeter=bundle.getString("SizebyMeter");
        NumberByrRooms=bundle.getString("NumberByrRooms");
        PriceFrom=bundle.getString("PriceFrom");
        PriceTo=bundle.getString("PriceTo");
        lat=bundle.getString("lat");
        lng=bundle.getString("lng");
        Type=bundle.getString("type");
        if(Type.equals("search")) {
            Title.setText(getResources().getString(R.string.search));
        }else if(Type.equals("bit")){
            Title.setText(getResources().getString(R.string.realstatebit));
        }else if(Type.equals("newads")){
            Title.setText(getResources().getString(R.string.newads));
        }else if(Type.equals("special")){
            Title.setText(getResources().getString(R.string.specialrealstate));
        }

    }
    private void init() {
        recyclerView=view.findViewById(R.id.recycler_Units);
        Title=view.findViewById(R.id.Title);
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        units_adapter=new Units_Adapter(list_Units,getContext());
        units_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(units_adapter);
    }

    @Override
    public void list_Units(List<Units> list) {
        list_Units.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        for (int i=0;i<list.size();i++){
            units_detail=new Units_Detail();
            units_detail.setId(list.get(i).getId());
            units_detail.setDate(list.get(i).getCreatedat());
            units_detail.setAnnualrent(list.get(i).getAnnualrent());
            units_detail.setSellingprice(list.get(i).getSellingprice());
            units_detail.setDefaultimg(list.get(i).getDefaultimg());
            units_detail.setId(list.get(i).getId());
            units_detail.setUnittype(list.get(i).getUnittype());
            units_detail.setLastbidprice(list.get(i).getLastbidprice());
            units_detail.setUnitNamear(list.get(i).getUnitNamear());
            units_detail.setPurposetype(list.get(i).getPurposetype());
            units_detail.setAreasize(list.get(i).getAreasize());
            units_detail.setUnittypeid(list.get(i).getUnittypeid());
            units_detail.setOfficeid(list.get(i).getOfficeid());
            units_detail.setUnitdescription(list.get(i).getUnitdescription());
            units_detail.setLocationdescription(String.valueOf(list.get(i).getLocationdescription()));
            list_Units.add(units_detail);
        }
        units_adapter.notifyDataSetChanged();
    }

    @Override
    public void list_Search_Units(List<Units> list) {
        list_Units.clear();
        mSwipeRefreshLayout.setRefreshing(false);
        for (int i=0;i<list.size();i++){
            units_detail=new Units_Detail();
            units_detail.setId(list.get(i).getId());
            units_detail.setDate(list.get(i).getCreatedat());
            units_detail.setAnnualrent(list.get(i).getAnnualrent());
            units_detail.setSellingprice(list.get(i).getSellingprice());
            units_detail.setUnitdescription(list.get(i).getUnitdescription());
            units_detail.setDefaultimg(list.get(i).getDefaultimg());
            units_detail.setLastbidprice(list.get(i).getLastbidprice());
            units_detail.setId(list.get(i).getId());
            units_detail.setUnittype(list.get(i).getUnittype());
            units_detail.setUnitNamear(list.get(i).getUnitNamear());
            units_detail.setPurposetype(list.get(i).getPurposetype());
            units_detail.setAreasize(list.get(i).getAreasize());
            units_detail.setUnittypeid(list.get(i).getUnittypeid());
            units_detail.setOfficeid(list.get(i).getOfficeid());
            units_detail.setLocationdescription(String.valueOf(list.get(i).getLocationdescription()));
            list_Units.add(units_detail);
        }
        units_adapter.notifyDataSetChanged();

    }

    @Override
    public void EmptyUnits() {
        if(PAGE==1) {
            noresult.setVisibility(View.VISIBLE);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
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
                PAGE = 1;
                if(Type.equals("search")) {
                    getUnits_presenter.getSearchUnits(Tybe_id, Lang, String.valueOf(lat), String.valueOf(lng), perpusid, SizebyMeter,
                            NumberByrRooms, PriceFrom, PriceTo);
                }else if(Type.equals("bit")){
                    getUnits_presenter.getbitUnits(String.valueOf(PAGE),Lang);
                }else if(Type.equals("newads")){
                    getUnits_presenter.getNewestUnits(String.valueOf(PAGE),Lang);
                }else if(Type.equals("special")){
                    getUnits_presenter.getSpecialUnits(String.valueOf(PAGE),Lang);
                }
            }
        });
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
                    PAGE++;
                    if(Type.equals("search")) {
                        getUnits_presenter.getSearchUnits(Tybe_id, Lang, String.valueOf(lat), String.valueOf(lng), perpusid, SizebyMeter,
                                NumberByrRooms, PriceFrom, PriceTo);
                    }else if(Type.equals("bit")){
                        getUnits_presenter.getbitUnits(String.valueOf(PAGE),Lang);
                    }else if(Type.equals("newads")){
                        getUnits_presenter.getNewestUnits(String.valueOf(PAGE),Lang);
                    }else if(Type.equals("special")){
                        getUnits_presenter.getSpecialUnits(String.valueOf(PAGE),Lang);
                    }
                }

        });
    }
    @Override
    public void onRefresh() {
        recycle=true;
        list_Units.clear();
        units_adapter.notifyDataSetChanged();
        PAGE=1;
        mSwipeRefreshLayout.setRefreshing(true);

        if(Type.equals("search")) {
            getUnits_presenter.getSearchUnits(Tybe_id, Lang, String.valueOf(lat), String.valueOf(lng), perpusid, SizebyMeter,
                    NumberByrRooms, PriceFrom, PriceTo);
        }else if(Type.equals("bit")){
            getUnits_presenter.getbitUnits(String.valueOf(PAGE),Lang);
        }else if(Type.equals("newads")){
            getUnits_presenter.getNewestUnits(String.valueOf(PAGE),Lang);
        }else if(Type.equals("special")){
            getUnits_presenter.getSpecialUnits(String.valueOf(PAGE),Lang);
        }


    }

    @Override
    public void List(Units_Detail list) {
        if(Type.equals("search")) {
            Details_Units_Fragment detailsHomeProductFragment = new Details_Units_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("from","search");
            bundle.putString("id", String.valueOf(list.getId()));
            bundle.putString("price", String.valueOf(list.getSellingprice()));
            bundle.putString("address", String.valueOf(list.getLocationdescription()));
            bundle.putString("name", String.valueOf(list.getUnitNamear()));
            bundle.putString("date", String.valueOf(list.getDate()));
            bundle.putString("unitdescrip", String.valueOf(list.getUnitdescription()));
            bundle.putString("areasize", String.valueOf(list.getAreasize()));
            bundle.putString("unittypeid", String.valueOf(list.getUnittypeid()));
            bundle.putString("officeid", String.valueOf(list.getOfficeid()));
            bundle.putString("enabledid", String.valueOf(list.getEnablebid()));
            bundle.putString("lastprice", String.valueOf(list.getLastbidprice()));
            detailsHomeProductFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.Rela_Search, detailsHomeProductFragment)
                    .addToBackStack(null).commit();
        }else {
            Details_Units_Fragment detailsHomeProductFragment = new Details_Units_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("from","myaccount");
            bundle.putString("id", String.valueOf(list.getId()));
            bundle.putString("price", String.valueOf(list.getSellingprice()));
            bundle.putString("address", String.valueOf(list.getLocationdescription()));
            bundle.putString("name", String.valueOf(list.getUnitNamear()));
            bundle.putString("date", String.valueOf(list.getDate()));
            bundle.putString("unitdescrip", String.valueOf(list.getUnitdescription()));
            bundle.putString("areasize", String.valueOf(list.getAreasize()));
            bundle.putString("unittypeid", String.valueOf(list.getUnittypeid()));
            bundle.putString("officeid", String.valueOf(list.getOfficeid()));
            bundle.putString("enabledid", String.valueOf(list.getEnablebid()));
            bundle.putString("lastprice", String.valueOf(list.getLastbidprice()));
            detailsHomeProductFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.Rela_MyAcc, detailsHomeProductFragment)
                    .addToBackStack(null).commit();

        }

    }
}
