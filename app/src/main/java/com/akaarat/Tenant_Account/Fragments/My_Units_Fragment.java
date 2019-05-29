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
public class My_Units_Fragment extends Fragment {


    public My_Units_Fragment() {
        // Required empty public constructor
    }

   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my__units, container, false);






        return view;
    }

}
