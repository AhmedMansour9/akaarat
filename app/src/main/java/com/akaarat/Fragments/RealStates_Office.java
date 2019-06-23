package com.akaarat.Fragments;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.akaarat.Adapter.Units_Adapter;
import com.akaarat.EndlessRecyclerViewScrollListener;
import com.akaarat.Language;
import com.akaarat.Model.Units;
import com.akaarat.Model.Units_Detail;
import com.akaarat.Model.Units_Types;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.R;
import com.akaarat.View.GetUnits_View;
import com.akaarat.View.ListUnitDetails_View;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealStates_Office extends Fragment implements ListUnitDetails_View,SwipeRefreshLayout.OnRefreshListener, GetUnits_View {


    public RealStates_Office() {
        // Required empty public constructor
    }
    View view;
    Toolbar toolbars;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Boolean recycle=true;
    int PAGE=1;
    GetUnits_Presenter getUnits_presenter;
    List<Units_Detail> list_Units=new ArrayList<>();
    Units_Adapter units_adapter;
    Units_Detail units_detail;
    AppCompatSpinner Units_Spinner;
    ArrayAdapter<Units_Types> listUnitsTypes;
    String Unit_Type,Tybe_id;
    GetUnitsTypes_Present getUnitsTypes_present;
    String Lang;
    String id,phone,name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_real_states__office, container, false);
        ButterKnife.bind(this,view);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        GetData();
        Language();
        init();
        SwipRefresh();
        onScroll();




        return view;
    }

    public void GetData(){
        Bundle bundle=getArguments();
        if(bundle!=null){
            name=bundle.getString("name");
            id=bundle.getString("id");
            phone=bundle.getString("phone");

        }

    }
    private void init() {

        recyclerView=view.findViewById(R.id.recycler_Units);
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        units_adapter=new Units_Adapter(list_Units,getContext());
        units_adapter.setClickListener(this);
        Units_Spinner=view.findViewById(R.id.UnitsTybes_Spinner);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(units_adapter);
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
                    if(Language.isRTL()) {
                        getUnits_presenter.getUnitsOffice("ar", String.valueOf(PAGE),id);
                    }else {
                        getUnits_presenter.getUnitsOffice("en", String.valueOf(PAGE),id);
                    }
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
                list_Units.clear();
                if(Language.isRTL()) {
                    PAGE = 1;
                    getUnits_presenter.getUnitsOffice(String.valueOf(PAGE),"ar",id);

                }else {
                    PAGE = 1;
                    getUnits_presenter.getUnitsOffice(String.valueOf(PAGE),"en",id);

                }
            }
        });
    }

    @Override
    public void onRefresh() {
     }

    @Override
    public void list_Units(List<Units> list) {

    }

    @Override
    public void list_Search_Units(List<Units> list) {

    }

    @Override
    public void EmptyUnits() {

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void List(Units_Detail list) {
        Details_Units_Fragment detailsHomeProductFragment=new Details_Units_Fragment();
        Bundle bundle=new Bundle();
        bundle.putString("from","menu");
        bundle.putString("purpustype",String.valueOf(list.getPurposetype()));
        bundle.putString("id",String.valueOf(list.getId()));
        bundle.putString("price",String.valueOf(list.getSellingprice()));
        bundle.putString("address",String.valueOf(list.getLocationdescription()));
        bundle.putString("name",String.valueOf(list.getUnitNamear()));
        bundle.putString("date",String.valueOf(list.getDate()));
        bundle.putString("unitdescrip",String.valueOf(list.getUnitdescription()));
        bundle.putString("areasize",String.valueOf(list.getAreasize()));
        bundle.putString("unittypeid",String.valueOf(list.getUnittypeid()));
        bundle.putString("officeid",String.valueOf(list.getOfficeid()));
        bundle.putString("enabledid",String.valueOf(list.getEnablebid()));
        bundle.putString("lastprice",String.valueOf(list.getLastbidprice()));
        bundle.putString("enabledvision",String.valueOf(list.getEnabledivision()));
        bundle.putString("shareAria",String.valueOf(list.getShareAria()));
        bundle.putString("countOfSoldShare",String.valueOf(list.getCountOfSoldShare()));
        bundle.putString("numberOfshare",String.valueOf(list.getNumberOfshare()));
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Menu_Frame,detailsHomeProductFragment)
                .addToBackStack(null).commit();
    }

    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
    }
}
