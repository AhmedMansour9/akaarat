package com.akaarat.Retrofit;

import com.akaarat.Model.Banner_Response;
import com.akaarat.Model.BookUnits_Response;
import com.akaarat.Model.Dynamic_Attributes_Response;
import com.akaarat.Model.OfficeProfile_Response;
import com.akaarat.Model.PayMentType_Details;
import com.akaarat.Model.PayMentType_Response;
import com.akaarat.Model.RegisterResponse;
import com.akaarat.Model.Units_Tybes_Response;
import com.akaarat.Model.Units_response;
import com.akaarat.Tenant_Account.Model.Contracts_Response;
import com.akaarat.Tenant_Account.Model.Messages_Response;
import com.akaarat.Tenant_Account.Model.Reservation_Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Ahmed on 13/12/2018.
 */

public interface Apiinterface {

    @GET("getunitsBylang")
    Call<Units_response> GetUnits(@QueryMap Map<String, String> queryMab);

    @GET("getfeaturedunitsBylang")
    Call<Units_response> GetSpecialUnits(@QueryMap Map<String, String> queryMab);

    @GET("getnewestunitsBylang")
    Call<Units_response> GetSNewestUnits(@QueryMap Map<String, String> queryMab);

    @GET("getbidunitsBylang")
    Call<Units_response> GetbitUnits(@QueryMap Map<String, String> queryMab);

    @GET("AddUnitClientPrice")
    Call<BookUnits_Response> Book_Unit(@QueryMap Map<String, String> queryMab);

    @GET("GetBookingsBYCustomer")
    Call<Reservation_Response> Reservations(@QueryMap Map<String, String> queryMab);

    @GET("GetContractsBYCustomer")
    Call<Contracts_Response> Contracts(@QueryMap Map<String, String> queryMab);


    @GET("GetMessagesByUser")
    Call<Messages_Response> Messages(@QueryMap Map<String, String> queryMab);


    @GET("bookrentunit")
    Call<BookUnits_Response> Book_Rent_Unit(@QueryMap Map<String, String> queryMab);

    @GET("booksaleunit")
    Call<BookUnits_Response> Book_Sale_Unit(@QueryMap Map<String, String> queryMab);


    @GET("GetPaymentMethodTypesByUnitID")
    Call<PayMentType_Response> GetPayMentMethod(@QueryMap Map<String, String> queryMab);



    @GET("GetAllUnitsByFilterParameters")
    Call<Units_response> GetSearchUnits(@QueryMap Map<String, String> queryMab);


    @GET("GetAllUnitTypes")
    Call<Units_Tybes_Response> GetUnitsTypes(@QueryMap Map<String, String> queryMab);

    @GET("getAllUnitattributesByUnitId")
    Call<Dynamic_Attributes_Response> GetUnitsAttributes(@QueryMap Map<String, String> queryMab);

    @GET("GetAllUnitImages")
    Call<Banner_Response> GetBanner(@QueryMap Map<String,String> queryMab);

    @GET("getofficequikdata")
    Call<OfficeProfile_Response> GetOfficeProfile(@QueryMap Map<String,String> queryMab);

    @GET("InsertCustomer")
    Call<RegisterResponse> register(@QueryMap Map<String,String> queryMab);

    @GET("login")
    Call<RegisterResponse> login(@QueryMap Map<String,String> queryMab);

}
