package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.Units_Tybes_Response;
import com.akaarat.Model.Units_response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.GetUnitsTypes_View;
import com.akaarat.View.GetUnits_View;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUnitsTypes_Present {

    Context context;
     GetUnitsTypes_View getUnitsTypes_view;

    public GetUnitsTypes_Present(Context context, GetUnitsTypes_View GetUnitsView) {
        this.context = context;
        this.getUnitsTypes_view = GetUnitsView;
    }

    public void getUnitsTypes(String lang) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Units_Tybes_Response> call = service.GetUnitsTypes(hashMap);
        call.enqueue(new Callback<Units_Tybes_Response>() {
            @Override
            public void onResponse(Call<Units_Tybes_Response> call, Response<Units_Tybes_Response> response) {
                if (response.isSuccessful()) {

                    if(response.body().getCode().equals("200")) {
                        getUnitsTypes_view.GetUnitsTypes(response.body().getData());
                    }else if(response.body().getCode().equals("905")){
                        getUnitsTypes_view.EmptyUnits();
                    }
                }
            }

            @Override
            public void onFailure(Call<Units_Tybes_Response> call, Throwable t) {
                getUnitsTypes_view.Error();
            }
        });
    }
}

