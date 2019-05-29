package com.akaarat.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.akaarat.Adapter.PlaceAutocompleteAdapter;
import com.akaarat.Language;
import com.akaarat.Model.PlaceInfo;
import com.akaarat.Model.Units_Types;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.R;
import com.akaarat.View.GetUnitsTypes_View;
import com.akaarat.View.GetUnits_View;
import com.fourhcode.forhutils.FUtilsValidation;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements OnMapReadyCallback,
        com.google.android.gms.location.LocationListener,GetUnitsTypes_View,SwipeRefreshLayout.OnRefreshListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    public Search() {
    }
    @BindView(R.id.UnitsTybes_Spinner)
    AppCompatSpinner Units_Types_Spinner;
    @BindView(R.id.Types_Spinner)
    AppCompatSpinner Types_Spinner;
    @BindView(R.id.E_SearchPlace)
    AutoCompleteTextView E_SearchPlace;
    View view;
    @BindView(R.id.SizebyMeter)
    EditText SizebyMeter;
    @BindView(R.id.NumberByrRooms)
    EditText NumberByrRooms;
    @BindView(R.id.PriceFrom)
    EditText PriceFrom;
    @BindView(R.id.PriceTo)
    EditText PriceTo;
    @BindView(R.id.Btn_Search)
    Button Btn_Search;
    ArrayAdapter<Units_Types> listUnitsTypes;
    String Unit_Type,Tybe_id;
    GetUnitsTypes_Present getUnitsTypes_present;
    private double lat,lng=0;
    String Lang;
    @BindView(R.id.swipe_Units)
    SwipeRefreshLayout mSwipeRefreshLayout;
    String Types,Perpustypeid;
    ArrayAdapter<String> ListTypes;
    GoogleMap googleMap;
    Context context;
    PlaceInfo placeT;
    LocationRequest locationReques;
    GoogleApiClient mGoogleApiClient;
    PlaceAutocompleteAdapter placeAutocompleteAdapter;
    LatLng latlongplace;
    LatLng latlaang;
    int MY_PERMISSIONS_REQUEST_LOCATION=99;
    final int REQUEST_LOCATION_CODE =99;
    LatLngBounds latLngBounds;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        init();
        SwipRefresh();
        Types();
        Filter();

        return view;
    }

    public void Filter() {
        Btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FUtilsValidation.isEmpty(E_Emai, getResources().getString(R.string.insertemail));
//                FUtilsValidation.isEmpty(E_FirstName, getResources().getString(R.string.insertfirstname));
//                FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.insertphone));
//                FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));

                if(Perpustypeid!=null && Tybe_id!=null){
                  Search_Detalis detailsHomeProductFragment=new Search_Detalis();
                  Bundle bundle=new Bundle();
                  bundle.putString("perpusid",Perpustypeid);
                  bundle.putString("Tybe_id",Tybe_id);
                  bundle.putString("SizebyMeter",SizebyMeter.getText().toString());
                  bundle.putString("NumberByrRooms",NumberByrRooms.getText().toString());
                  bundle.putString("PriceFrom",PriceFrom.getText().toString());
                  bundle.putString("PriceTo",PriceTo.getText().toString());
                  bundle.putString("type","search");
                  bundle.putString("lat",String.valueOf(lat));
                  bundle.putString("lng",String.valueOf(lng));
                  detailsHomeProductFragment.setArguments(bundle);
                  getFragmentManager().beginTransaction().add(R.id.Rela_Search,detailsHomeProductFragment)
                          .addToBackStack(null).commit();
              }
            }
        });


    }

    public void SwipRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(Language.isRTL()) {
                    getUnitsTypes_present.getUnitsTypes("ar");
                }else {
                    getUnitsTypes_present.getUnitsTypes("en");
                }
            }
        });
    }
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

    public void init(){
        getUnitsTypes_present=new GetUnitsTypes_Present(getContext(),this);
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



    @Override
    public void GetUnitsTypes(List<Units_Types> list) {
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
        Units_Types_Spinner.setAdapter(listUnitsTypes);
        Units_Types_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Unit_Type= Units_Types_Spinner.getSelectedItem().toString();
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
    public void EmptyUnits() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void Types(){
        List<String> categories = new ArrayList<String>();
        categories.add(getResources().getString(R.string.rent));
        categories.add(getResources().getString(R.string.sell));

        ListTypes = new ArrayAdapter<String>(getContext(), R.layout.textcolorspinner, categories) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Types_Spinner.setAdapter(ListTypes);
        Types_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(Types_Spinner.getSelectedItem().toString().equals("Rent")){
                    Perpustypeid="1";
                }else if(Types_Spinner.getSelectedItem().toString().equals("ايجار")){
                    Perpustypeid="1";
                }
                if(Types_Spinner.getSelectedItem().toString().equals("Sell")){
                    Perpustypeid="2";
                }else if(Types_Spinner.getSelectedItem().toString().equals("بيع")){
                    Perpustypeid="2";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                        .enableAutoManage(getActivity(),4,this)
                        .addApi(Places.GEO_DATA_API)
                        .addApi(Places.PLACE_DETECTION_API)
                        .build();
                mGoogleApiClient.connect();

        placeAutocompleteAdapter=new PlaceAutocompleteAdapter(getContext(),mGoogleApiClient,latLngBounds,null);
        E_SearchPlace.setAdapter(placeAutocompleteAdapter);
        E_SearchPlace.setOnItemClickListener(mAutocomplete);

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
            latlaang=placeT.getLatlong();
            latlongplace=placeT.getLatlong();
            lat = latlongplace.latitude;
            lng = latlongplace.longitude;

        }
    };

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUnitsTypes_present.getUnitsTypes("en");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleapiclint();

                    }

                } else {
                }
                return;
            }

        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }

}
