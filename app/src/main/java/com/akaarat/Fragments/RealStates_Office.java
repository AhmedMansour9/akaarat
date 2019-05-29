package com.akaarat.Fragments;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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
import com.akaarat.Model.Units_Detail;
import com.akaarat.Model.Units_Types;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealStates_Office extends Fragment {


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_real_states__office, container, false);
        ButterKnife.bind(this,view);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
//        Language();
//        init();
//        initMap();
//        SwipRefresh();
//        onScroll();
//        SearchUnits();



        return view;
    }

}
