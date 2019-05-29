package com.akaarat.Fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akaarat.Activity.Register;
import com.akaarat.Adapter.Dynamic_Attributes_ِAdapter;
import com.akaarat.Adapter.PannersDetailsUnits_Adapter;
import com.akaarat.Adapter.Units_Adapter;
import com.akaarat.Language;
import com.akaarat.Model.Banner_details;
import com.akaarat.Model.Dynamic_Attributes;
import com.akaarat.Model.Office_Profile;
import com.akaarat.Model.Units;
import com.akaarat.Model.Units_Detail;
import com.akaarat.Model.Units_Details;
import com.akaarat.Presenter.BannerPresenter;
import com.akaarat.Presenter.GetDynamic_Presenter;
import com.akaarat.Presenter.GetUnitsTypes_Present;
import com.akaarat.Presenter.GetUnits_Presenter;
import com.akaarat.Presenter.Office_Profile_Presenter;
import com.akaarat.R;
import com.akaarat.View.BannerView;
import com.akaarat.View.GetDynamic_Attributes_View;
import com.akaarat.View.GetUnits_View;
import com.akaarat.View.ListUnitDetails_View;
import com.akaarat.View.OfficeProfile_View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Units_Fragment extends Fragment implements OfficeProfile_View,GetDynamic_Attributes_View,ListUnitDetails_View,
        GetUnits_View,BannerView,SwipeRefreshLayout.OnRefreshListener {


    public Details_Units_Fragment() {
        // Required empty public constructor
    }
    Boolean end;
    View view;
    String UnitId,Price,Address,Name,Date,SizeArea,Unit_Description,Enabledid,LastPrice;
    BannerPresenter baner;
    @BindView(R.id.recycler_Panners)
    RecyclerView recycler_Panners;
    @BindView(R.id.recycler_SimliarStates)
    RecyclerView recycler_SimliarStates;
    @BindView(R.id.swipe_UnitsPanners)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.Title_Unit)
    TextView Title_Unit;
    @BindView(R.id.Price)
    TextView price;
    @BindView(R.id.Address) TextView address;
    @BindView(R.id.Date)
    TextView Datee;
    @BindView(R.id.Description)
    TextView Description;
    @BindView(R.id.T_LastPrice)
    TextView T_LastPrice;
    PannersDetailsUnits_Adapter banerAdapter;
    List<Banner_details> banne=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    Timer timer;
    int position;
    GetUnits_Presenter getUnits_presenter;
    List<Units_Detail> list_Units=new ArrayList<>();
    Units_Detail units_detail;
    Units_Adapter units_adapter;
    String Lang,Typeid,officeid,From;
    Dynamic_Attributes_ِAdapter dynamic_attributes_ِAdapter;
    @BindView(R.id.recycler_Details)RecyclerView recyclerAttributes;
    @BindView(R.id.logo) CircleImageView logo_office;
    @BindView(R.id.Title_unit_Office) TextView Title_unit_Office;
    @BindView(R.id.phone_unit_office) TextView phone_unit_office;
    @BindView(R.id.Rela_DynamicAttributes)
    RelativeLayout Rela_DynamicAttributes;
    @BindView(R.id.Img_Phone)
    ImageView Img_Phone;
    @BindView(R.id.book_realstate)
    Button Btn_book_realstate;
    @BindView(R.id.bit_realstate)
    Button Btn_bit_realstate;
    GetDynamic_Presenter getDynamic_presenter;
    Office_Profile_Presenter office_profile_presenter;
    String EnableVesion,ShareAria,CountOfSoldShare,NumberOfshare;
    @BindView(R.id.Rela_Shares) RelativeLayout Rela_Shares;
    @BindView(R.id.numberofremain) TextView T_NumberOfRemain;
    @BindView(R.id.T_NumberofSoldshare) TextView T_NumberofSoldshare;
    @BindView(R.id.ShareRea) TextView T_ShareRea;
    @BindView(R.id.book_numberofshare) Button Btn_BookNumberOfShare;
    @BindView(R.id.RelaOffice)
    RelativeLayout RelaOffice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details_units, container, false);
        ButterKnife.bind(this,view);

        init();
        Language();
        GetData();
        SwipRefresh();
        CallPhone();
        RegisterAsBit();
        RegisterAsSaleOrRent();
        RegisterAsShare();

        return view;
    }
    public void RegisterAsBit(){
        Btn_bit_realstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent intent=new Intent(getActivity(), Register.class);
           intent.putExtra("unitid",UnitId);
           intent.putExtra("price",Price);
           intent.putExtra("lastprice",LastPrice);
           intent.putExtra("type","bit");
           startActivity(intent);

            }
        });

    }
    public void RegisterAsSaleOrRent(){
        Btn_book_realstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Register.class);
                intent.putExtra("unitid",UnitId);
                intent.putExtra("price",Price);
                intent.putExtra("type","book");
                startActivity(intent);


            }
        });


    }

    public void RegisterAsShare(){
        Btn_BookNumberOfShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), Register.class);
                int share=Integer.parseInt(NumberOfshare);
                int shareSold=Integer.parseInt(CountOfSoldShare);
                int shareremain=share-shareSold;

                intent.putExtra("unitid",UnitId);
                intent.putExtra("price",Price);
                intent.putExtra("type","numberofshare");
                intent.putExtra("ShareAria",ShareAria);
                intent.putExtra("CountOfSoldShare",CountOfSoldShare);
                intent.putExtra("NumberOfshare",String.valueOf(shareremain));
                startActivity(intent);


            }
        });


    }

    private void init() {
        office_profile_presenter=new Office_Profile_Presenter(getContext(),this);
        baner=new BannerPresenter(getContext(),this);
        getDynamic_presenter=new GetDynamic_Presenter(getContext(),this);
        getUnits_presenter=new GetUnits_Presenter(getContext(),this);
        units_adapter=new Units_Adapter(list_Units,getContext());
        units_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler_SimliarStates.setLayoutManager(linearLayoutManager);
        recycler_SimliarStates.setItemAnimator(new DefaultItemAnimator());
        recycler_SimliarStates.setAdapter(units_adapter);
    }
    public void CallPhone(){
        Img_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone_unit_office.getText().toString(), null));
                startActivity(intent);

            }
        });
    }

    public void GetData(){
        Bundle bundle=getArguments();
        if(bundle!=null) {
           UnitId=bundle.getString("id");
            Price=bundle.getString("price");
            Address=bundle.getString("address");
            Name=bundle.getString("name");
            Date=bundle.getString("date");
            SizeArea=bundle.getString("areasize");
            Unit_Description=bundle.getString("unitdescrip");
            Typeid=bundle.getString("unittypeid");
            officeid=bundle.getString("officeid");
            Enabledid=bundle.getString("enabledid");
            EnableVesion=bundle.getString("enabledvision");
            ShareAria=bundle.getString("shareAria");
            CountOfSoldShare=bundle.getString("countOfSoldShare");
            NumberOfshare=bundle.getString("numberOfshare");
            From=bundle.getString("from");
            Datee.setText(Date);
            RelaOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(From.equals("myaccount")) {
                        TabsOffice detailsHomeProductFragment = new TabsOffice();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", officeid);
                        detailsHomeProductFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.Rela_MyAcc, detailsHomeProductFragment)
                                .addToBackStack(null).commit();
                    }else if(From.equals("search")){
                        TabsOffice detailsHomeProductFragment = new TabsOffice();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", officeid);
                        detailsHomeProductFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.Rela_Search, detailsHomeProductFragment)
                                .addToBackStack(null).commit();

                    }
                    else if(From.equals("menu")){
                        TabsOffice detailsHomeProductFragment = new TabsOffice();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", officeid);
                        detailsHomeProductFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.Menu_Frame , detailsHomeProductFragment)
                                .addToBackStack(null).commit();

                    }
                }
            });
            if(!Enabledid.equals("null")){
                Btn_bit_realstate.setVisibility(View.VISIBLE);
                Btn_book_realstate.setVisibility(View.INVISIBLE);
                LastPrice=bundle.getString("lastprice");
                T_LastPrice.setVisibility(View.VISIBLE);
                T_LastPrice.setText(LastPrice+" "+getResources().getString(R.string.sar));
                price.setText(getResources().getString(R.string.lastprice) +": ");
            }else {
                price.setText(getResources().getString(R.string.price)+": "+Price+" "+getResources().getString(R.string.sar));
            }

            if(!EnableVesion.equals("null")){
                int share=Integer.parseInt(NumberOfshare);
                int shareSold=Integer.parseInt(CountOfSoldShare);
                int shareremain=share-shareSold;
                Rela_Shares.setVisibility(View.VISIBLE);
                Btn_book_realstate.setVisibility(View.INVISIBLE);
                Btn_BookNumberOfShare.setVisibility(View.VISIBLE);
                T_NumberOfRemain.setText(getResources().getString(R.string.numberofsharemaining)+": "+String.valueOf(shareremain));
                T_NumberofSoldshare.setText(getResources().getString(R.string.numberofsoldshare)+": "+CountOfSoldShare);
                T_ShareRea.setText(getResources().getString(R.string.shareAria)+": "+ShareAria);
            }
            Title_Unit.setText(getResources().getString(R.string.unitname)+": "+Name);
            address.setText(getResources().getString(R.string.address)+": "+Address);
            Description.setText(Unit_Description);
//            if(officeid.equals("null")){
                logo_office.setVisibility(View.GONE);
                Title_unit_Office.setVisibility(View.GONE);
                phone_unit_office.setVisibility(View.GONE);
                RelaOffice.setVisibility(View.GONE);
//            }
        }
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                if (networikConntection.isNetworkAvailable(getContext())) {
                    baner.GetBanner(UnitId);
                    getUnits_presenter.getSearchUnits(Typeid,Lang,"null","null","","0"
                    ,"","","");
                getDynamic_presenter.getUnitsAttributes(Lang,UnitId);
                if(officeid!=null){
                    office_profile_presenter.GetOfficeProfile(Lang,officeid);
                }
                }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        baner.GetBanner(UnitId);
        getUnits_presenter.getSearchUnits(Typeid,Lang,"null","null","","0"
                ,"","","");
        if(officeid!=null){
            office_profile_presenter.GetOfficeProfile(Lang,officeid);
        }
    }

    @Override
    public void getBanner(List<Banner_details> banners) {
        mSwipeRefreshLayout.setRefreshing(false);
        banne=banners;
        banerAdapter = new PannersDetailsUnits_Adapter(banners,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_Panners.setLayoutManager(linearLayoutManager);
        recycler_Panners.setAdapter(banerAdapter);
        if(banners.size()>0) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new AutoScrollTask(), 2500, 8000);
        }
    }

    @Override
    public void Errorbaner() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void list_Units(List<Units> list) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void list_Search_Units(List<Units> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        list_Units.clear();
        for (int i=0;i<list.size();i++){
            units_detail=new Units_Detail();
            units_detail.setDate(list.get(i).getCreatedat());
            units_detail.setAnnualrent(list.get(i).getAnnualrent());
            units_detail.setSellingprice(list.get(i).getSellingprice());
            units_detail.setDefaultimg(list.get(i).getDefaultimg());
            units_detail.setId(list.get(i).getId());
            units_detail.setUnittype(list.get(i).getUnittype());
            units_detail.setUnitNamear(list.get(i).getUnitNamear());
            units_detail.setPurposetype(list.get(i).getPurposetype());
            units_detail.setAreasize(list.get(i).getAreasize());
            units_detail.setLocationdescription(String.valueOf(list.get(i).getLocationdescription()));
            list_Units.add(units_detail);
        }
        units_adapter.notifyDataSetChanged();
    }

    @Override
    public void EmptyUnits() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void GetAttributes(List<Dynamic_Attributes> list) {
        dynamic_attributes_ِAdapter=new Dynamic_Attributes_ِAdapter(list,getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerAttributes.setLayoutManager(linearLayoutManager);
        recyclerAttributes.setItemAnimator(new DefaultItemAnimator());
        recyclerAttributes.setAdapter(dynamic_attributes_ِAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void EmptyAttributes() {
        mSwipeRefreshLayout.setRefreshing(false);
        Rela_DynamicAttributes.setVisibility(View.GONE);
    }

    @Override
    public void officeProfile(Office_Profile office_profile) {
        logo_office.setVisibility(View.VISIBLE);
        Title_unit_Office.setVisibility(View.VISIBLE);
        phone_unit_office.setVisibility(View.VISIBLE);
        RelaOffice.setVisibility(View.VISIBLE);
        Title_unit_Office.setText(office_profile.getName());
        phone_unit_office.setText(office_profile.getPhone());
        String i =office_profile.getImage();
        if(i!=null) {
            Uri u = Uri.parse(i);
            Glide.with(getContext())
                    .load("http://gmtccco-001-site1.etempurl.com/wwwroot/upload/unit/" + u)
                    .apply(new RequestOptions().override(500, 500))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(logo_office);
        }

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void List(Units_Detail list) {
        Title_Unit.setText(list.getUnitNamear());
        price.setText(list.getSellingprice());
        address.setText(list.getLocationdescription());
        Description.setText(list.getUnitdescription());
        baner.GetBanner(String.valueOf(list.getUnitid()));
        getDynamic_presenter.getUnitsAttributes(Lang,UnitId);
        getUnits_presenter.getSearchUnits(list.getUnittypeid(),Lang,"null","null","","0"
                ,"","","");
        recycler_SimliarStates.scrollToPosition(0);
        if(list.getOfficeid()==null) {
                logo_office.setVisibility(View.GONE);
                Title_unit_Office.setVisibility(View.GONE);
                phone_unit_office.setVisibility(View.GONE);
        }
        Enabledid=list.getEnablebid();
        if(!Enabledid.equals("null")){
            Btn_bit_realstate.setVisibility(View.VISIBLE);
            Btn_book_realstate.setVisibility(View.INVISIBLE);
            LastPrice=list.getLastbidprice();
            T_LastPrice.setVisibility(View.VISIBLE);
            T_LastPrice.setText(LastPrice+" "+getResources().getString(R.string.sar));
            price.setText(getResources().getString(R.string.lastprice) +": ");
        }else {
            price.setText(getResources().getString(R.string.price)+": "+Price+" "+getResources().getString(R.string.sar));
        }

    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == banne.size()){
                end = true;
            }
            else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            recycler_Panners.smoothScrollToPosition(position);
        }
    }
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
    }
}
