package com.akaarat.Tenant_Account.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akaarat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyContracts_Fragment extends Fragment {


    public MyContracts_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_contracts_, container, false);
    }

}
