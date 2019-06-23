package com.akaarat.Tenant_Account.View;

import com.akaarat.Tenant_Account.Model.Reservation_Details;

import java.util.List;

public interface Reservation_View {

    void list(List<Reservation_Details> list);
    void Error();
}
