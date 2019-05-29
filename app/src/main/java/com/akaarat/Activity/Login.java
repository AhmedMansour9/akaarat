package com.akaarat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akaarat.Model.UserDetails;
import com.akaarat.Model.UserRegister;
import com.akaarat.Presenter.Register_Presenter;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.View.RegisterView;
import com.fourhcode.forhutils.FUtilsValidation;

public class Login extends AppCompatActivity implements RegisterView {
    String UnitId,Price,Type,ShareAria,CountOfSoldShare,NumberOfshare;
    @BindView(R.id.E_User_Name)
    EditText E_User_Name;
    @BindView(R.id.E_Password)
    EditText E_Password;
    @BindView(R.id.Sign_Up)
    Button Register;
    Register_Presenter register;
    @BindView(R.id.progross)
    ProgressBar progross;
    String UserType,lastprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getData();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_User_Name, getResources().getString(R.string.insertfirstname));
                FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));
                FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16);

                    if (!E_User_Name.getText().toString().equals("")
                            && !E_Password.getText().toString().equals("")
                            && !Type.equals("null")&&
                            (FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))) {


                        UserRegister user = new UserRegister();
                        user.setFirstName(E_User_Name.getText().toString());
                        user.setUsertype(UserType);
                        user.setPassword(E_Password.getText().toString());
                        Register.setEnabled(false);
                        progross.setVisibility(View.VISIBLE);
                        register.login(user);
                }}
        });
    }

    private void getData() {
        UnitId = getIntent().getStringExtra("unitid");
        Price = getIntent().getStringExtra("price");
        Type = getIntent().getStringExtra("type");
        ShareAria = getIntent().getStringExtra("ShareAria");
        CountOfSoldShare = getIntent().getStringExtra("CountOfSoldShare");
        NumberOfshare = getIntent().getStringExtra("NumberOfshare");
        lastprice = getIntent().getStringExtra("lastprice");
        register = new Register_Presenter(this, this);

    }

    @Override
    public void openMain(UserDetails userDetails) {
        SharedPrefManager.getInstance(this).saveUserToken(userDetails.getAccessToken());
        SharedPrefManager.getInstance(this).saveClintid(userDetails.getCustomerid());
        Toast.makeText(this, getResources().getString(R.string.loginsuccess), Toast.LENGTH_SHORT).show();
        progross.setVisibility(View.GONE);
        Register.setEnabled(true);
        if(Type.equals("bit")||Type.equals("numberofshare")){
            Intent intent=new Intent(Login.this,Enter_Bit_Now.class);
            intent.putExtra("type",Type);
            intent.putExtra("lastprice",lastprice);
            intent.putExtra("NumberOfshare",NumberOfshare);
            intent.putExtra("CountOfSoldShare",CountOfSoldShare);
            intent.putExtra("ShareAria",ShareAria);
            intent.putExtra("Price",Price);
            intent.putExtra("UnitId",UnitId);

            startActivity(intent);

        }
        else if(Type.equals("book")){


        }else if(Type.equals("null")){


        }


    }

    @Override
    public void EmailisUsed() {
        progross.setVisibility(View.GONE);
        Toast.makeText(this, getResources().getString(R.string.wrongemail), Toast.LENGTH_SHORT).show();
        Register.setEnabled(true);

    }

    @Override
    public void showError(String error) {
        progross.setVisibility(View.GONE);
        Register.setEnabled(true);

    }
}
