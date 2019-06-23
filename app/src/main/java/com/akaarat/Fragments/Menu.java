package com.akaarat.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akaarat.Adapter.PlaceAutocompleteAdapter;
import com.akaarat.Adapter.Units_Adapter;
import com.akaarat.EndlessRecyclerViewScrollListener;
import com.akaarat.Language;
import com.akaarat.Model.PlaceInfo;
import com.akaarat.Model.Units;
import com.akaarat.Model.Units_Detail;
import com.akaarat.Model.Units_Types;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.R;
import com.akaarat.View.GetUnits_View;
import com.akaarat.View.GetUnitsTypes_View;
import com.akaarat.View.ListUnitDetails_View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment implements ListUnitDetails_View, OnMapReadyCallback, GetUnitsTypes_View,
        com.google.android.gms.location.LocationListener, SwipeRefreshLayout.OnRefreshListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GetUnits_View {

    public Menu() {
        // Required empty public constructor
    }
    GoogleMap googleMap;
    Context context;
    PlaceInfo placeT;
    LocationRequest locationReques;
    GoogleApiClient mGoogleApiClient;
    AutoCompleteTextView auto;
    PlaceAutocompleteAdapter placeAutocompleteAdapter;
    View view;
    LatLng latlongplace;
    LatLng latlaang;
    int MY_PERMISSIONS_REQUEST_LOCATION=99;
    final int REQUEST_LOCATION_CODE =99;
    Toolbar toolbars;
    LatLngBounds latLngBounds;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Boolean recycle=true;
    int PAGE=1;
    GetUnits_Presenter getUnits_presenter;
    List<Units_Detail> list_Units=new ArrayList<>();
    Units_Adapter units_adapter;
    Boolean Bolean_Search=true;
    Units_Detail units_detail;
    AppCompatSpinner Units_Spinner;
    ArrayAdapter<Units_Types> listUnitsTypes;
    String Unit_Type,Tybe_id;
    GetUnitsTypes_Present getUnitsTypes_present;
    private double lat,lng=0;
    ImageView Btn_Filter;
    String Lang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this,view);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        Language();
        init();
        initMap();
        SwipRefresh();
        onScroll();
        SearchUnits();

        return view;
    }

    private void SearchUnits() {

        Btn_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeRefreshLayout.setRefreshing(true);
                if(Tybe_id!=null ){
                    Bolean_Search=false;
                    getUnits_presenter.getSearchUnits(Tybe_id,Lang,String.valueOf(lat),String.valueOf(lng)
                    ,"","","0","","");
                }
            }
        });
    }

    private void init() {
        auto=view.findViewById(R.id.E_SearchPlace);
        recyclerView=view.findViewById(R.id.recycler_Units);
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        units_adapter=new Units_Adapter(list_Units,getContext());
        units_adapter.setClickListener(this);
        Units_Spinner=view.findViewById(R.id.UnitsTybes_Spinner);
        getUnitsTypes_present=new GetUnitsTypes_Present(getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(units_adapter);
        Btn_Filter=view.findViewById(R.id.Btn_Filtter);
    }

    public void initMap(){
        context=this.getActivity();
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MAP);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        checkLocationPermission();
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
                if(Bolean_Search) {
                    recycle=false;
//                    mSwipeRefreshLayout.setRefreshing(true);
                    PAGE++;
                    if(Language.isRTL()) {
                        getUnits_presenter.getUnits("ar", String.valueOf(PAGE));
                    }else {
                        getUnits_presenter.getUnits("en", String.valueOf(PAGE));
                    }
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
                        getUnits_presenter.getUnits(String.valueOf(PAGE),"ar");
                        getUnitsTypes_present.getUnitsTypes("ar");
                    }else {
                        PAGE = 1;
                        getUnits_presenter.getUnits(String.valueOf(PAGE),"en");
                        getUnitsTypes_present.getUnitsTypes("en");
                    }
            }
        });
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(1);
        locationReques.setInterval(1000);
//        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);
            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
            task.addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                }
            });

            task.addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                }
            });
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        buildGoogleapiclint();


    }
    private synchronized void buildGoogleapiclint(){
        mGoogleApiClient=new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .enableAutoManage(getActivity(),1,this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        mGoogleApiClient.connect();
        placeAutocompleteAdapter=new PlaceAutocompleteAdapter(context,mGoogleApiClient,latLngBounds,null);
        auto.setAdapter(placeAutocompleteAdapter);
        auto.setOnItemClickListener(mAutocomplete);
    }
    private AdapterView.OnItemClickListener mAutocomplete=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            final AutocompletePrediction autocompletePrediction=placeAutocompleteAdapter.getItem(i);
            final String id=autocompletePrediction.getPlaceId();
            PendingResult<PlaceBuffer> place= Places.GeoDataApi.getPlaceById(mGoogleApiClient,id);
            place.setResultCallback(updateplaceCallback);
        }
    };
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
//                        result.Call(1);
                        buildGoogleapiclint();
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        break;
                    default:
                        break;
                }
                break;
        }

    }

    private ResultCallback<PlaceBuffer> updateplaceCallback=new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                places.release();
                return;
            }
            final Place place=places.get(0);
            placeT=new PlaceInfo();
            try {
                placeT.setName(place.getName().toString());
                placeT.setAddress(place.getAddress().toString());
                placeT.setPhoneNumber(place.getPhoneNumber().toString());
                placeT.setId(place.getId());
                placeT.setWebsite(place.getWebsiteUri());
                placeT.setLatlong(place.getLatLng());
                placeT.setRating(place.getRating());
            }catch (NullPointerException e){
            }
//            moveCamera(new LatLng(place.getViewport().getCenter().latitude,place.getViewport().getCenter().longitude),18,placeT);
            places.release();
            latlongplace=placeT.getLatlong();
             lat = latlongplace.latitude;
             lng = latlongplace.longitude;

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        }
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.stopAutoManage(getActivity());
            mGoogleApiClient.disconnect();
        }
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
            units_detail.setLastbidprice(list.get(i).getLastbidprice());
            units_detail.setEnablebid(list.get(i).getEnablebid());
            units_detail.setEnabledivision(list.get(i).getEnabledivision());
            units_detail.setUnittype(list.get(i).getUnittype());
            units_detail.setUnitNamear(list.get(i).getUnitNamear());
            units_detail.setUnitdescription(list.get(i).getUnitdescription());
            units_detail.setPurposetype(list.get(i).getPurposetype());
            units_detail.setAreasize(list.get(i).getAreasize());
            units_detail.setUnittypeid(list.get(i).getUnittypeid());
            units_detail.setOfficeid(list.get(i).getOfficeid());
            units_detail.setSellingSharePrice(list.get(i).getSellingSharePrice());
            units_detail.setLocationdescription(String.valueOf(list.get(i).getLocationdescription()));
            units_detail.setNumberOfshare(list.get(i).getNumberOfshare());
            units_detail.setShareAria(list.get(i).getShareAria());
            units_detail.setCountOfSoldShare(list.get(i).getCountOfSoldShare());

            list_Units.add(units_detail);
        }
        units_adapter.notifyItemRangeInserted(units_adapter.getItemCount(), list_Units.size() - 1);
    }

    @Override
    public void list_Search_Units(List<Units> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        list_Units.clear();
        for (int i=0;i<list.size();i++){
            units_detail=new Units_Detail();
            units_detail.setId(list.get(i).getId());
            units_detail.setDate(list.get(i).getCreatedat());
            units_detail.setAnnualrent(list.get(i).getAnnualrent());
            units_detail.setSellingprice(list.get(i).getSellingprice());
            units_detail.setDefaultimg(list.get(i).getDefaultimg());
            units_detail.setId(list.get(i).getId());
            units_detail.setLastbidprice(list.get(i).getLastbidprice());
            units_detail.setEnablebid(list.get(i).getEnablebid());
            units_detail.setEnabledivision(list.get(i).getEnabledivision());
            units_detail.setUnittype(list.get(i).getUnittype());
            units_detail.setUnitNamear(list.get(i).getUnitNamear());
            units_detail.setUnitdescription(list.get(i).getUnitdescription());
            units_detail.setPurposetype(list.get(i).getPurposetype());
            units_detail.setAreasize(list.get(i).getAreasize());
            units_detail.setUnittypeid(list.get(i).getUnittypeid());
            units_detail.setOfficeid(list.get(i).getOfficeid());
            units_detail.setSellingSharePrice(list.get(i).getSellingSharePrice());
            units_detail.setLocationdescription(String.valueOf(list.get(i).getLocationdescription()));
            units_detail.setNumberOfshare(list.get(i).getNumberOfshare());
            units_detail.setShareAria(list.get(i).getShareAria());
            units_detail.setCountOfSoldShare(list.get(i).getCountOfSoldShare());
            list_Units.add(units_detail);
        }
        units_adapter.notifyDataSetChanged();

    }

    @Override
    public void EmptyUnits() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void GetUnitsTypes(final List<Units_Types> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.select));
        listUnitsTypes = new ArrayAdapter<Units_Types>(getContext(), R.layout.textcolorspinner,list) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };

        listUnitsTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Units_Spinner.setAdapter(listUnitsTypes);
        Units_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Unit_Type= Units_Spinner.getSelectedItem().toString();
                for(i = 0; i<list.size(); i++){
                    if(list.get(i).getNamear().equals(Unit_Type)){
                        Tybe_id=String.valueOf(list.get(i).getId());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        recycle=true;
        list_Units.clear();
        units_adapter.notifyDataSetChanged();
        PAGE=1;
        mSwipeRefreshLayout.setRefreshing(true);
        if(Language.isRTL()) {
            PAGE = 1;
            getUnits_presenter.getUnits(String.valueOf(PAGE),"ar");
            getUnitsTypes_present.getUnitsTypes("ar");
        }else {
            PAGE = 1;
            getUnits_presenter.getUnits(String.valueOf(PAGE),"en");
            getUnitsTypes_present.getUnitsTypes("en");
        }
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
