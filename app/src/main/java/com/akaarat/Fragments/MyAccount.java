package com.akaarat.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.akaarat.Activity.Login;
import com.akaarat.R;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccount extends Fragment {


    public MyAccount() {
        // Required empty public constructor
    }
    View view;
    @BindView(R.id.card_aboutus)
    RelativeLayout Card_AboutUs;
    @BindView(R.id.card_bitrealstate)
    RelativeLayout Card_BitRealState;
    @BindView(R.id.card_contactus)
    RelativeLayout Card_ContactUs;
    @BindView(R.id.card_newestadds)
    RelativeLayout Card_NewAdds;
    @BindView(R.id.card_rentcontract)
    RelativeLayout Card_RentContract;
    @BindView(R.id.card_specialstate)
    RelativeLayout Crad_SpecialStates;
    @BindView(R.id.log_in)
    Button log_in;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_account, container, false);
        ButterKnife.bind(this,view);
        Card_AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Card_BitRealState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Search_Detalis detailsHomeProductFragment=new Search_Detalis();
                Bundle bundle=new Bundle();
                bundle.putString("type","bit");
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_MyAcc,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
        Card_ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Card_NewAdds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search_Detalis detailsHomeProductFragment=new Search_Detalis();
                Bundle bundle=new Bundle();
                bundle.putString("type","newads");
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_MyAcc,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
        Card_RentContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Crad_SpecialStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search_Detalis detailsHomeProductFragment=new Search_Detalis();
                Bundle bundle=new Bundle();
                bundle.putString("type","special");
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_MyAcc,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);

            }
        });

        return view;
    }



}
