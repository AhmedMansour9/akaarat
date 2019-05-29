package com.akaarat.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.akaarat.Tenant_Account.Tabs_MalekAccount;
import com.akaarat.R;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_splash);

        final ImageView logo =  findViewById(R.id.splas_logo);
//        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animationsplash);
        Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        animationSlideInLeft.setDuration(3000);

        logo.startAnimation(animationSlideInLeft);
        animationSlideInLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {


                finish();
                Intent i = new Intent(getBaseContext(), Home_Tabs.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

    }


    }
