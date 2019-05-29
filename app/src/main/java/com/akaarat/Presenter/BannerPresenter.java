package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.Banner_Response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.BannerView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerPresenter {

    BannerView getbanner;

    public BannerPresenter(Context context, BannerView ColorView)
    {
        this.getbanner=ColorView;

    }

    public void GetBanner(String Unitid) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("unitid", Unitid);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Banner_Response> call = apiInterface.GetBanner(queryMap);
        call.enqueue(new Callback<Banner_Response>() {
            @Override
            public void onResponse(Call<Banner_Response> call, Response<Banner_Response> response) {

                if (response.isSuccessful()) {
                    getbanner.getBanner(response.body().getData());

                } else {
                    getbanner.Errorbaner();
                }
            }


            @Override
            public void onFailure(Call<Banner_Response> call, Throwable t) {
                getbanner.Errorbaner();

            }
        });
    }

}
