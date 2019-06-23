package com.akaarat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akaarat.Model.UserDetails;
import com.akaarat.Model.UserRegister;
import com.akaarat.Presenter.Register_Presenter;
import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.Tenant_Account.Tabs_TenentAccount;
import com.akaarat.View.RegisterView;
import com.fourhcode.forhutils.FUtilsValidation;

public class Register extends AppCompatActivity implements RegisterView {
    @BindView(R.id.relative_owner)
    RelativeLayout relativeOwner;
    @BindView(R.id.relative_owner_selected)
    RelativeLayout relativeOwnerSelected;
    @BindView(R.id.relative_tenent)
    RelativeLayout relativeTenent;
    @BindView(R.id.relative_tenent_selected)
    RelativeLayout relativeTenentSelected;
    String UnitId,Price,Type,ShareAria,CountOfSoldShare,NumberOfshare;
    @BindView(R.id.E_User_Name)
    EditText E_User_Name;
    @BindView(R.id.E_Email)
    EditText E_Email;
    @BindView(R.id.E_Phone)
    EditText E_Phone;
    @BindView(R.id.E_NoationlId)
    EditText E_NoationlId;
    @BindView(R.id.E_Password)
    EditText E_Password;
    @BindView(R.id.Sign_Up)
    Button Register;
    @BindView(R.id.E_Full_Name)
    EditText E_Full_Name;
    Register_Presenter register;
    @BindView(R.id.progross)
    ProgressBar progross;
    @BindView(R.id.Sign_In)
    TextView Sign_In;
    String lastprice,Purpustype;
    String UserType="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


      getData();
      ClickOwner();
      ClickOwnerSelected();
      ClickTenent();
      ClickTenentSelected();
      OpenLogin();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_Email, getResources().getString(R.string.insertemail));
                FUtilsValidation.isEmpty(E_Full_Name, getResources().getString(R.string.insertfirstname));
                FUtilsValidation.isEmpty(E_User_Name, getResources().getString(R.string.insertfirstname));
                FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.insertphone));
                FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));
                FUtilsValidation.isEmpty(E_NoationlId, getResources().getString(R.string.insertnationaltyid));
                FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16);
                if(ValidateEmail()) {
                    if (!E_Email.getText().toString().equals("") &&
                            !E_User_Name.getText().toString().equals("") &&
                            !E_Full_Name.getText().toString().equals("") &&
                            !E_NoationlId.getText().toString().equals("")
                            && !E_Phone.getText().toString().equals("") && !UserType.equals("null")&&
                            (FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))) {


                        UserRegister user = new UserRegister();
                        user.setEmail(E_Email.getText().toString());
                        user.setFirstName(E_User_Name.getText().toString());
                        user.setFullName(E_Full_Name.getText().toString());
                        user.setPhone(E_Phone.getText().toString());
                        user.setUsertype(UserType);
                        user.setPassword(E_Password.getText().toString());
                        user.setNationaltyId(E_NoationlId.getText().toString());
                        Register.setEnabled(false);
                        progross.setVisibility(View.VISIBLE);
                        register.register(user);

                    }
                }}
        });
    }

    public void OpenLogin() {
        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Login.class);
                intent.putExtra("unitid",UnitId);
                intent.putExtra("price",Price);
                intent.putExtra("type","numberofshare");
                intent.putExtra("ShareAria",ShareAria);
                intent.putExtra("CountOfSoldShare",CountOfSoldShare);
                intent.putExtra("NumberOfshare",NumberOfshare);
                intent.putExtra("purpustype",Purpustype);
                startActivity(intent);

            }
        });
    }

    private void getData() {
        UnitId = getIntent().getStringExtra("unitid");
        Price = getIntent().getStringExtra("price");
        Type = getIntent().getStringExtra("type");
        ShareAria = getIntent().getStringExtra("ShareAria");
        CountOfSoldShare = getIntent().getStringExtra("CountOfSoldShare");
        NumberOfshare = getIntent().getStringExtra("NumberOfshare");
        lastprice=getIntent().getStringExtra("lastprice");
        Purpustype=getIntent().getStringExtra("purpustype");
        register = new Register_Presenter(this, this);
        if(Type.equals("numberofshare")||Type.equals("bit")||Type.equals("book")){
            UserType="2";
            relativeOwner.setVisibility(View.VISIBLE);
            relativeOwnerSelected.setVisibility(View.INVISIBLE);
            relativeTenentSelected.setVisibility(View.VISIBLE);
            relativeTenent.setVisibility(View.INVISIBLE);
            relativeOwner.setEnabled(false);
            relativeOwnerSelected.setEnabled(false);
            relativeTenentSelected.setEnabled(false);
            relativeTenent.setEnabled(false);
        }
    }
    public void ClickOwner(){
        relativeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeOwnerSelected.setVisibility(View.VISIBLE);
                relativeOwner.setVisibility(View.INVISIBLE);
                relativeTenentSelected.setVisibility(View.INVISIBLE);
                relativeTenent.setVisibility(View.VISIBLE);
                UserType="3";
            }
        });

    }
    public void ClickOwnerSelected(){
        relativeOwnerSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeOwnerSelected.setVisibility(View.INVISIBLE);
                relativeOwner.setVisibility(View.VISIBLE);
                relativeTenentSelected.setVisibility(View.VISIBLE);
                relativeTenent.setVisibility(View.INVISIBLE);
                UserType=null;
            }
        });

    }
    public void ClickTenent(){
        relativeTenent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeTenentSelected.setVisibility(View.VISIBLE);
                relativeTenent.setVisibility(View.INVISIBLE);
                relativeOwnerSelected.setVisibility(View.INVISIBLE);
                relativeOwner.setVisibility(View.VISIBLE);
                UserType="2";
            }
        });

    }
    public void ClickTenentSelected(){
        relativeTenentSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeTenentSelected.setVisibility(View.INVISIBLE);
                relativeTenent.setVisibility(View.VISIBLE);
                relativeOwnerSelected.setVisibility(View.VISIBLE);
                relativeOwner.setVisibility(View.INVISIBLE);
                UserType=null;
            }
        });

    }
    private Boolean ValidateEmail(){
        String EMAIL=E_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
//            Toast.makeText(getContext(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();
            FUtilsValidation.isEmpty(E_Email, getResources().getString(R.string.insertemail));
            return false;
        }else if(!E_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            FUtilsValidation.isEmpty(E_Email, getResources().getString(R.string.insertemail));
//            Toast.makeText(getContext(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void openMain(UserDetails userDetails) {
        SharedPrefManager.getInstance(this).saveUserToken(userDetails.getAccessToken());
        SharedPrefManager.getInstance(this).saveClintid(userDetails.getCustomerid());
        SharedPrefManager.getInstance(this).saveUserType(String.valueOf(userDetails.getUsertype()));
        Toast.makeText(this, getResources().getString(R.string.registersuccess), Toast.LENGTH_SHORT).show();
        progross.setVisibility(View.GONE);
        Register.setEnabled(true);
        if(Type.equals("null")){
            Intent intent=new Intent(Register.this, Tabs_TenentAccount.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
       else if (Purpustype.equals("1")) {
            Intent intent=new Intent(Register.this,BookRentUnit.class);
            intent.putExtra("type",Type);
            intent.putExtra("unitid",UnitId);
            startActivity(intent);


        } else if(Purpustype.equals("2")){
            if(Type.equals("bit")||Type.equals("numberofshare")){
                Intent intent=new Intent(Register.this,Enter_Bit_Now.class);
                intent.putExtra("type",Type);
                intent.putExtra("lastprice",lastprice);
                intent.putExtra("NumberOfshare",NumberOfshare);
                intent.putExtra("CountOfSoldShare",CountOfSoldShare);
                intent.putExtra("ShareAria",ShareAria);
                intent.putExtra("price",Price);
                intent.putExtra("unitid",UnitId);
                startActivity(intent);

            }
            else if(Type.equals("book")){
                Intent intent=new Intent(Register.this,Enter_SaleUnit.class);
                intent.putExtra("type",Type);
                intent.putExtra("unitid",UnitId);
                startActivity(intent);


            }

      }
    }

    @Override
    public void EmailisUsed() {
        progross.setVisibility(View.GONE);
        Toast.makeText(this, getResources().getString(R.string.emailfailed), Toast.LENGTH_SHORT).show();
        Register.setEnabled(true);
    }

    @Override
    public void showError(String error) {
        progross.setVisibility(View.GONE);
        Register.setEnabled(true);
    }
}
