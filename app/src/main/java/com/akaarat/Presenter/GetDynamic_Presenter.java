package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.Dynamic_Attributes_Response;
import com.akaarat.Model.Units_Tybes_Response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.GetDynamic_Attributes_View;
import com.akaarat.View.GetUnitsTypes_View;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDynamic_Presenter {

    Context context;
    GetDynamic_Attributes_View getUnitsTypes_view;

    public GetDynamic_Presenter(Context context, GetDynamic_Attributes_View GetUnitsView) {
        this.context = context;
        this.getUnitsTypes_view = GetUnitsView;
    }

    public void getUnitsAttributes(String lang,String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("unitid", id);
        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<Dynamic_Attributes_Response> call = service.GetUnitsAttributes(hashMap);
        call.enqueue(new Callback<Dynamic_Attributes_Response>() {
            @Override
            public void onResponse(Call<Dynamic_Attributes_Response> call, Response<Dynamic_Attributes_Response> response) {
                if (response.isSuccessful()) {

                    if(response.body().getCode().equals("200")) {
                        getUnitsTypes_view.GetAttributes(response.body().getData());
                    }else if(response.body().getCode().equals("905")){
                        getUnitsTypes_view.EmptyAttributes();
                    }
                }else {
                    getUnitsTypes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Dynamic_Attributes_Response> call, Throwable t) {
                getUnitsTypes_view.Error();
            }
        });
    }
}


