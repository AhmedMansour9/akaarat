package com.akaarat.View;

import com.akaarat.Model.UserDetails;

public interface RegisterView {

    void openMain(UserDetails userDetails);

    void EmailisUsed();
    void showError(String error);

}
