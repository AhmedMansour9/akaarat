package com.akaarat.Tenant_Account.Model;

import android.content.Context;

import com.akaarat.Retrofit.ApiCLint;
import com.akaarat.Retrofit.Apiinterface;
import com.akaarat.Tenant_Account.View.Messages_Inbox_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Inbox_Presenter {

    Messages_Inbox_View Routes_view;

    public Message_Inbox_Presenter(Context context, Messages_Inbox_View cart_view) {
        this.Routes_view = cart_view;
    }

    public void Inbox_SuperVisor(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("userid", "2");
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Messages_Response> call = apiInterface.Messages(queryMap);
        call.enqueue(new Callback<Messages_Response>() {
            @Override
            public void onResponse(Call<Messages_Response> call, Response<Messages_Response> response) {

                if (response.code()==200) {
                    Routes_view.GetMessagesParent(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Messages_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
}
