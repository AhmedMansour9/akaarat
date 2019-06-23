package com.akaarat.Tenant_Account.View;

import com.akaarat.Tenant_Account.Model.Messages;

import java.util.List;

public interface Messages_Inbox_View {

    void GetMessagesParent(List<Messages> inbox_details);
    void Error();

}
