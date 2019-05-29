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
import com.akaarat.Language;
import com.akaarat.Model.PlaceInfo;
import com.akaarat.Model.Units;
import com.akaarat.Model.Units_Detail;
import com.akaarat.Model.Units_Types;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.R;
import com.akaarat.View.GetUnitsTypes_View;
import com.akaarat.View.GetUnits_View;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class Maps extends Fragment implements  OnMapReadyCallback,
        com.google.android.gms.location.LocationListener, GetUnitsTypes_View, GetUnits_View,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{



    public Maps() {
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
    LatLngBounds latLngBounds;
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
    String Lang;
    Marker m;
    @BindView(R.id.Btn_Filtter) ImageView Btn_Filter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_maps, container, false);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        ButterKnife.bind(this,view);
        Language();
        initMap();
        SearchUnits();

        return view;
    }

    private void SearchUnits() {

        Btn_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getUnits_presenter.getSearchUnits(Tybe_id,Lang,String.valueOf(lat),String.valueOf(lng),"","0"
                    ,"","","");
            }
        });
    }


    public void initMap(){
        Units_Spinner=view.findViewById(R.id.Units_Spinner);
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        auto=view.findViewById(R.id.E_SearchPlace);
        getUnitsTypes_present=new GetUnitsTypes_Present(getContext(),this);
        getUnitsTypes_present.getUnitsTypes(Lang);
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
    public void onMapReady(GoogleMap googleMaps) {
        googleMap=googleMaps;
        buildGoogleapiclint();


    }
    private synchronized void buildGoogleapiclint(){

        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()){
            try {

        mGoogleApiClient=new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .enableAutoManage(getActivity(),this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            latlaang=placeT.getLatlong();
            latlongplace=placeT.getLatlong();
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

    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
    }

    @Override
    public void GetUnitsTypes(List<Units_Types> list) {
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
    public void list_Units(List<Units> list) {

    }

    @Override
    public void list_Search_Units(List<Units> list) {
        for (int i=0;i<list.size();i++){
            if(list.get(i).getLocationLatit()!=null) {
                LatLng l = new LatLng(Double.parseDouble(list.get(i).getLocationLatit()), Double.parseDouble(list.get(i).getLocationLang()));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(l)
                        .title(list.get(i).getUnitNamear())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                m = googleMap.addMarker(markerOptions);
            }

        }
    }

    @Override
    public void EmptyUnits() {

    }

    @Override
    public void Error() {

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


}
