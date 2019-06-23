package com.akaarat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.akaarat.Language;
import com.akaarat.Model.PayMentType;
import com.akaarat.Model.PayMentType_Details;
import com.akaarat.Presenter.Book_Unit_Presenter;
import com.akaarat.Presenter.PayMentType_Presenter;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.Tenant_Account.Tabs_TenentAccount;
import com.akaarat.View.BookUnit_View;
import com.akaarat.View.PayMentType_View;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Enter_SaleUnit extends AppCompatActivity implements BookUnit_View , PayMentType_View {

    @BindView(R.id.E_Exceptedstartdate)
    TextView E_Exceptedstartdate;
    DatePickerDialog.OnDateSetListener dateSetListener;
    @BindView(R.id.RentTimeType)
    Spinner RentTimeType;
    @BindView(R.id.bookunit)
    FloatingActionButton bookunit;
    Book_Unit_Presenter book_unit_presenter;
    String UnitId;
    @BindView(R.id.progross)
    ProgressBar progross;
    ArrayAdapter<PayMentType> ListServices;
    String RentType,L_RentType;
    PayMentType_Presenter payMentType_presenter;
    List<PayMentType> list_payments=new ArrayList<>();
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.activity_enter__sale_unit);
        book_unit_presenter=new Book_Unit_Presenter(this,this);
        ButterKnife.bind(this);
        GetData();
        GetDate();
        BookUnit();


    }

    public void GetData(){
        UnitId=getIntent().getStringExtra("unitid");
        payMentType_presenter=new PayMentType_Presenter(this,this);
        if(Language.isRTL()) {
            payMentType_presenter.getPayMentMethods("ar", UnitId);
        }else {
            payMentType_presenter.getPayMentMethods("en", UnitId);
        }
    }

    public void GetDate(){
        E_Exceptedstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Enter_SaleUnit.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                E_Exceptedstartdate.setText(""+i+"-"+i1+"-"+i2);
                date=""+i+"-"+i1+"-"+i2;
            }
        };
    }

    public void BookUnit(){
        bookunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Clintid= SharedPrefManager.getInstance(getBaseContext()).getClintid();
                if (!E_Exceptedstartdate.getText().toString().equals("")) {


                    bookunit.setEnabled(false);
                    progross.setVisibility(View.VISIBLE);

                    book_unit_presenter.booksaleunit(UnitId,Clintid,date,L_RentType);

                }
            }
        });
    }

    @Override
    public void success() {
        bookunit.setEnabled(true);
        progross.setVisibility(View.GONE);
        Toast.makeText(this, ""+getResources().getString(R.string.booksuccess), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getBaseContext(), Tabs_TenentAccount.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void list(List<PayMentType_Details> list) {
        list_payments.clear();
        PayMentType car_detail=new PayMentType();
        car_detail.setId("0");
        car_detail.setName(getResources().getString(R.string.paymenttype));
        list_payments.add(car_detail);
        for(int i=0;i<list.size();i++){
            PayMentType car_details=new PayMentType();
            car_details.setId(String.valueOf(list.get(i).getId()));
            car_details.setName(String.valueOf(list.get(i).getName()));
            list_payments.add(car_details);
        }
        ListServices = new ArrayAdapter<PayMentType>(getApplicationContext(), R.layout.textcolorspinner, list_payments) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RentTimeType.setAdapter(ListServices);
        RentTimeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RentType= RentTimeType.getSelectedItem().toString();
                if(!RentType.equals("PayMent Methods")&&!RentType.equals("طرق الدفع")) {
                    for(i = 0; i<list_payments.size(); i++){
                        if(list_payments.get(i).getName().equals(RentType)){
                            L_RentType=String.valueOf(list_payments.get(i).getId());
                        }
                    }                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void Error() {

        bookunit.setEnabled(true);
        progross.setVisibility(View.GONE);
        Toast.makeText(this, ""+getResources().getString(R.string.failed), Toast.LENGTH_SHORT).show();

    }

}
