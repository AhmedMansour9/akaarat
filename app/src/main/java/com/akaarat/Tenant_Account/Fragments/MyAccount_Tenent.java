package com.akaarat.Tenant_Account.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.akaarat.Activity.Home_Tabs;
import com.akaarat.Fragments.Search_Detalis;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccount_Tenent extends Fragment {


    public MyAccount_Tenent() {
        // Required empty public constructor
    }
    @BindView(R.id.log_out)
    Button log_out;
    View view;
    @BindView(R.id.card_specialstate)
    RelativeLayout card_specialstate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_account_tenent, container, false);
        ButterKnife.bind(this,view);

        card_specialstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Messages_Fragment detailsHomeProductFragment=new Messages_Fragment();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_MyAccount,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });


        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getActivity()).saveUserType(null);
                SharedPrefManager.getInstance(getActivity()).saveClintid(null);
                SharedPrefManager.getInstance(getActivity()).saveUserToken(null);
                startActivity(new Intent(getActivity(), Home_Tabs.class));
                getActivity().finish();
            }
        });

        return view;
    }

}
