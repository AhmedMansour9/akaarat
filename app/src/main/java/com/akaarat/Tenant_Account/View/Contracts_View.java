package com.akaarat.Tenant_Account.View;

import com.akaarat.Tenant_Account.Model.Contracts_Details;
import com.akaarat.Tenant_Account.Model.Reservation_Details;

import java.util.List;

public interface Contracts_View {

    void list(List<Contracts_Details> list);
    void Error();

}
