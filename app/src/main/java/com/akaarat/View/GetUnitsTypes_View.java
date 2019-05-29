package com.akaarat.View;

import com.akaarat.Model.Units_Tybes_Response;
import com.akaarat.Model.Units_Types;

import java.util.List;

public interface GetUnitsTypes_View
{

    void GetUnitsTypes(List<Units_Types> list);
    void EmptyUnits();
    void Error();


}
