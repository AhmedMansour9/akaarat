package com.akaarat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akaarat.Model.UserRegister;
import com.akaarat.Presenter.Book_Unit_Presenter;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.View.BookUnit_View;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Enter_Bit_Now extends AppCompatActivity implements BookUnit_View {
    String Price,lastprice,Type,UnitId,CountOfSoldShare;
    @BindView(R.id.E_StartPrice)
    TextView E_StartPrice;
    @BindView(R.id.E_LastPrice)
    TextView E_LastPrice;
    @BindView(R.id.E_Price)
    EditText E_Price;
    @BindView(R.id.bookunit)
    FloatingActionButton bookunit;
    Book_Unit_Presenter book_unit_presenter;
    @BindView(R.id.progross)
    ProgressBar progross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.activity_enter_bitnow);
        ButterKnife.bind(this);
        getData();

        bookunit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_Price, "");
               String Clintid= SharedPrefManager.getInstance(getBaseContext()).getClintid();
                if (!E_Price.getText().toString().equals("")) {


                    bookunit.setEnabled(false);
                    progross.setVisibility(View.VISIBLE);

                   book_unit_presenter.register(UnitId,Clintid,E_Price.getText().toString());

                }


            }
        });

    }

    public void getData(){
        Price=getIntent().getStringExtra("Price");
        lastprice=getIntent().getStringExtra("lastprice");
        Type=getIntent().getStringExtra("type");
        UnitId=getIntent().getStringExtra("UnitId");
        E_StartPrice.setText(Price+" "+getResources().getString(R.string.sar));
        E_LastPrice.setText(lastprice+" "+getResources().getString(R.string.sar));
        CountOfSoldShare=getIntent().getStringExtra("CountOfSoldShare");
        book_unit_presenter=new Book_Unit_Presenter(this,this);
    }

    @Override
    public void success() {
        bookunit.setEnabled(true);
        progross.setVisibility(View.GONE);
        Toast.makeText(this, ""+getResources().getString(R.string.booksuccess), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Error() {
        bookunit.setEnabled(true);
        progross.setVisibility(View.GONE);
        Toast.makeText(this, ""+getResources().getString(R.string.failed), Toast.LENGTH_SHORT).show();


    }
}
