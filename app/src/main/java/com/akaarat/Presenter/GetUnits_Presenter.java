package com.akaarat.Presenter;

import android.app.Service;
import android.content.Context;

import com.akaarat.Model.Units_response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.GetUnits_View;
import com.google.android.gms.common.api.Api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUnits_Presenter {


    Context context;
    GetUnits_View GetUnitsView;

    public GetUnits_Presenter(Context context, GetUnits_View GetUnitsView) {
        this.context = context;
        this.GetUnitsView = GetUnitsView;
    }

    public void getUnits(String Page, String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("page", Page);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetUnits(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }

    public void getUnitsOffice(String Page, String lang,String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("page", Page);
         hashMap.put("officeid",id);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetUnitsOffice(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }


    public void getSearchUnits(String unittypeid, String lang,String Lat,String Lng,String Perpustype
    ,String areasize,String noofroom,String pricefrom,String priceto) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("unittypeid", unittypeid);
        hashMap.put("purposetype", Perpustype);
        hashMap.put("areasize", areasize);
        if(noofroom.equals("")){
            hashMap.put("noofroom", "0");
        }else {
            hashMap.put("noofroom", noofroom);
        }

        hashMap.put("pricefrom",pricefrom);
        hashMap.put("priceto",priceto);
        if(Lat.equals("0.0")) {
            hashMap.put("lat", "null");
            hashMap.put("lng", "null");
        }else {
            hashMap.put("lat", Lat);
            hashMap.put("lng", Lng);
        }

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetSearchUnits(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Search_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }

    public void getSpecialUnits(String Page, String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("page", Page);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetSpecialUnits(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }
    public void getbitUnits(String Page, String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("page", Page);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetbitUnits(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }
    public void getNewestUnits(String Page, String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("page", Page);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_response> call = service.GetSNewestUnits(hashMap);
        call.enqueue(new Callback<Units_response>() {
            @Override
            public void onResponse(Call<Units_response> call, Response<Units_response> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode().equals("200")) {
                        GetUnitsView.list_Units(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        GetUnitsView.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_response> call, Throwable t) {
                GetUnitsView.Error();
            }
        });
    }
}
