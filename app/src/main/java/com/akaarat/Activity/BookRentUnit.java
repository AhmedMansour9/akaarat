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

import com.akaarat.Presenter.Book_Unit_Presenter;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.Tenant_Account.Tabs_TenentAccount;
import com.akaarat.View.BookUnit_View;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BookRentUnit extends AppCompatActivity implements BookUnit_View {
    @BindView(R.id.E_Exceptedstartdate)
    TextView E_Exceptedstartdate;
    DatePickerDialog.OnDateSetListener dateSetListener;
    @BindView(R.id.E_RentTime)
    EditText E_RentTime;
    @BindView(R.id.RentTimeType)
    Spinner RentTimeType;
    @BindView(R.id.bookunit)
    FloatingActionButton bookunit;
    Book_Unit_Presenter book_unit_presenter;
    String UnitId;
    @BindView(R.id.progross)
    ProgressBar progross;
    ArrayAdapter<String> ListServices;
    String RentType;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.activity_book_unit);
        book_unit_presenter=new Book_Unit_Presenter(this,this);
        ButterKnife.bind(this);
        GetData();
        GetDate();
        BookUnit();
        Spin_RentType();

    }
    public void GetData(){
        UnitId=getIntent().getStringExtra("unitid");

    }

    public void GetDate(){
        E_Exceptedstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(BookRentUnit.this,
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

                FUtilsValidation.isEmpty(E_RentTime, "");


                String Clintid= SharedPrefManager.getInstance(getBaseContext()).getClintid();
                if (!E_RentTime.getText().toString().equals("")) {

                    bookunit.setEnabled(false);
                    progross.setVisibility(View.VISIBLE);
                    book_unit_presenter.bookrentunit(UnitId,Clintid,date,
                            E_RentTime.getText().toString(),RentType);

                }
            }
        });
    }

    public void Spin_RentType() {

        List<String> categories = new ArrayList<String>();
//        categories.add(getResources().getString(R.string.day));
        categories.add(getResources().getString(R.string.month));
        categories.add(getResources().getString(R.string.year));

        ListServices = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
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

                 if (RentTimeType.getSelectedItem().toString().equals("Month")) {
                    RentType = "1";
                } else if (RentTimeType.getSelectedItem().toString().equals("Year")) {
                    RentType = "2";
                } else if (RentTimeType.getSelectedItem().toString().equals("شهر")) {
                    RentType = "1";
                } else if (RentTimeType.getSelectedItem().toString().equals("سنة")) {
                    RentType = "2";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
    public void Error() {

        bookunit.setEnabled(true);
        progross.setVisibility(View.GONE);
        Toast.makeText(this, ""+getResources().getString(R.string.failed), Toast.LENGTH_SHORT).show();

    }
}
