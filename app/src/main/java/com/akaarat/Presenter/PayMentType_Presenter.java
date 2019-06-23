package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.PayMentType_Response;
import com.akaarat.Model.Units_Tybes_Response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.GetUnitsTypes_View;
import com.akaarat.View.PayMentType_View;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayMentType_Presenter {

    Context context;
    PayMentType_View getUnitsTypes_view;

    public PayMentType_Presenter(Context context, PayMentType_View GetUnitsView) {
        this.context = context;
        this.getUnitsTypes_view = GetUnitsView;
    }

    public void getPayMentMethods(String lang,String unitid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lang", lang);
        hashMap.put("unitid", unitid);

        Apiinterface service = ApiCLint.getClient().create(Apiinterface.class);
        Call<PayMentType_Response> call = service.GetPayMentMethod(hashMap);
        call.enqueue(new Callback<PayMentType_Response>() {
            @Override
            public void onResponse(Call<PayMentType_Response> call, Response<PayMentType_Response> response) {
                if (response.isSuccessful()) {

                    if(response.body().getCode().equals("200")) {
                        getUnitsTypes_view.list(response.body().getData());
                    }else if(response.body().getCode().equals("905")){
                        getUnitsTypes_view.Error();
                    }
                }
            }

            @Override
            public void onFailure(Call<PayMentType_Response> call, Throwable t) {
                getUnitsTypes_view.Error();
            }
        });
    }

}
