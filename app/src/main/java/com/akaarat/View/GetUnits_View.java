package com.akaarat.View;

import com.akaarat.Model.Units;

import java.util.List;

public interface GetUnits_View {

    void list_Units(List<Units> list);
    void list_Search_Units(List<Units> list);
    void EmptyUnits();
    void  Error();
}
