package com.akaarat.Tenant_Account.Presenter;

import android.content.Context;

import com.akaarat.Model.BookUnits_Response;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.Tenant_Account.Model.Contracts_Response;
import com.akaarat.Tenant_Account.Model.Reservation_Response;
import com.akaarat.Tenant_Account.View.Contracts_View;
import com.akaarat.Tenant_Account.View.Reservation_View;
import com.akaarat.View.BookUnit_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reservation_Presenter  {

    Reservation_View bookUnit_view;
    Contracts_View contracts_view;

    public Reservation_Presenter(Context context, Reservation_View registerView) {
        this.bookUnit_view = registerView;

    }

    public Reservation_Presenter(Context context, Contracts_View registerView) {
        this.contracts_view = registerView;

    }

    public void reservation(String page,String ClintId,String lang,String bookingtype) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("page", page);
        queryMap.put("customerid", ClintId);
        queryMap.put("bookingtype", bookingtype);
        queryMap.put("lang",lang);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Reservation_Response> call = apiInterface.Reservations(queryMap);
        call.enqueue(new Callback<Reservation_Response>() {
            @Override
            public void onResponse(Call<Reservation_Response> call, Response<Reservation_Response> response) {
                if(response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        bookUnit_view.list(response.body().getData());
                    } else {
                        bookUnit_view.Error();
                    }
                }else {
                    bookUnit_view.Error();
                }
            }
            @Override
            public void onFailure(Call<Reservation_Response> call, Throwable t) {
                bookUnit_view.Error();

            }
        });
    }

    public void contracts(String page,String ClintId,String lang,String bookingtype) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("page", page);
        queryMap.put("customerid", ClintId);
        queryMap.put("contracttype", bookingtype);
        queryMap.put("lang",lang);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Contracts_Response> call = apiInterface.Contracts(queryMap);
        call.enqueue(new Callback<Contracts_Response>() {
            @Override
            public void onResponse(Call<Contracts_Response> call, Response<Contracts_Response> response) {
                if(response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        contracts_view.list(response.body().getData());
                    } else {
                        contracts_view.Error();
                    }
                }else {
                    contracts_view.Error();
                }
            }
            @Override
            public void onFailure(Call<Contracts_Response> call, Throwable t) {
                contracts_view.Error();

            }
        });
    }

}
