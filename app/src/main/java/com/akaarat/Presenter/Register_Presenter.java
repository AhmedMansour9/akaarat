package com.akaarat.Presenter;

import android.content.Context;

import com.akaarat.Model.RegisterResponse;
import com.akaarat.Model.UserRegister;
import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Presenter {

    RegisterView registerView;

    public Register_Presenter(Context context, RegisterView registerView) {
        this.registerView = registerView;

    }

    public void register(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("username", user.getFirstName());
        queryMap.put("name", user.getFullName());
        queryMap.put("password", user.getPassword());
        queryMap.put("email", user.getEmail());
        queryMap.put("phone", user.getPhone());
        queryMap.put("id", user.getNationaltyId());
        queryMap.put("usertype", user.getUsertype());
        queryMap.put("client_id", "AndroidApp");
        queryMap.put("client_secret","321456");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<RegisterResponse> call = apiInterface.register(queryMap);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
               if(response.isSuccessful()) {
                   if (response.body().getCode().equals("200")) {
                       registerView.openMain(response.body().getData());
                   } else if (response.body().getCode().equals("905")) {
                       registerView.EmailisUsed();
                   } else {
                       registerView.showError("");
                   }
               }else {
                   registerView.showError("");
               }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.showError("");

            }
        });
    }

    public void login(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("username", user.getFirstName());
        queryMap.put("password", user.getPassword());
        queryMap.put("client_id", "AndroidApp");
        queryMap.put("client_secret","321456");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<RegisterResponse> call = apiInterface.login(queryMap);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body().getCode().equals("200")) {
                        registerView.openMain(response.body().getData());
                    } else if (response.body().getCode().equals("905")) {
                        registerView.EmailisUsed();
                    } else {
                        registerView.showError("");
                    }
                }else {
                    registerView.showError("");
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.showError("");

            }
        });
    }


}
