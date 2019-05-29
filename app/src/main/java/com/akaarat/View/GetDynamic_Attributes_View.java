package com.akaarat.View;

import com.akaarat.Model.Dynamic_Attributes;

import java.util.List;

public interface GetDynamic_Attributes_View {

    void GetAttributes(List<Dynamic_Attributes> list);
    void EmptyAttributes();
    void Error();

}
