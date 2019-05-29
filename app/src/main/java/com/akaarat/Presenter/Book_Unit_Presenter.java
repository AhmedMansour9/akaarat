package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.BookUnits_Response;
import com.akaarat.Model.RegisterResponse;
import com.akaarat.Model.UserRegister;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.BookUnit_View;
import com.akaarat.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Book_Unit_Presenter {

    BookUnit_View bookUnit_view;

    public Book_Unit_Presenter(Context context, BookUnit_View registerView) {
        this.bookUnit_view = registerView;

    }


    public void register(String Unitid,String ClintId,String NewPrice) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("unitid", Unitid);
        queryMap.put("clientid", ClintId);
        queryMap.put("countofshare", "0");
        queryMap.put("newprice",NewPrice);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<BookUnits_Response> call = apiInterface.Book_Unit(queryMap);
        call.enqueue(new Callback<BookUnits_Response>() {
            @Override
            public void onResponse(Call<BookUnits_Response> call, Response<BookUnits_Response> response) {
                if(response.isSuccessful()) {
                    if (response.body().getData().equals("1")) {
                        bookUnit_view.success();
                    } else if (response.body().getCode().equals("0")) {
                        bookUnit_view.Error();
                    } else {
                        bookUnit_view.Error();
                    }
                }else {
                    bookUnit_view.Error();
                }
            }
            @Override
            public void onFailure(Call<BookUnits_Response> call, Throwable t) {
                bookUnit_view.Error();

            }
        });
    }

    public void bookshare(String Unitid,String ClintId,String count,String NewPrice) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("unitid", Unitid);
        queryMap.put("clientid", ClintId);
        queryMap.put("countofshare", count);
        queryMap.put("newprice",NewPrice);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<BookUnits_Response> call = apiInterface.Book_Unit(queryMap);
        call.enqueue(new Callback<BookUnits_Response>() {
            @Override
            public void onResponse(Call<BookUnits_Response> call, Response<BookUnits_Response> response) {
                if(response.isSuccessful()) {
                    if (response.body().getData().equals("1")) {
                        bookUnit_view.success();
                    } else if (response.body().getCode().equals("0")) {
                        bookUnit_view.Error();
                    } else {
                        bookUnit_view.Error();
                    }
                }else {
                    bookUnit_view.Error();
                }
            }
            @Override
            public void onFailure(Call<BookUnits_Response> call, Throwable t) {
                bookUnit_view.Error();

            }
        });
    }

}
