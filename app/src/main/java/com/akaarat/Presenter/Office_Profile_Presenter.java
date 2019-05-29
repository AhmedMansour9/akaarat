package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.Banner_Response;
import com.akaarat.Model.OfficeProfile_Response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.BannerView;
import com.akaarat.View.OfficeProfile_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Office_Profile_Presenter {

    OfficeProfile_View officeProfile_view;

    public Office_Profile_Presenter(Context context, OfficeProfile_View ColorView)
    {
        this.officeProfile_view=ColorView;

    }

    public void GetOfficeProfile(String Lang,String Officeid) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", Lang);
        queryMap.put("officeid", Officeid);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<OfficeProfile_Response> call = apiInterface.GetOfficeProfile(queryMap);
        call.enqueue(new Callback<OfficeProfile_Response>() {
            @Override
            public void onResponse(Call<OfficeProfile_Response> call, Response<OfficeProfile_Response> response) {

                if (response.isSuccessful()) {
                    officeProfile_view.officeProfile(response.body().getData());

                } else {
                    officeProfile_view.Error();
                }
            }


            @Override
            public void onFailure(Call<OfficeProfile_Response> call, Throwable t) {
                officeProfile_view.Error();

            }
        });
    }

}
