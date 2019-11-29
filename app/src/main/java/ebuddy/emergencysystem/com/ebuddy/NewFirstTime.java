package ebuddy.emergencysystem.com.ebuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gsheets1.UserList.UserListForLogin;

public class NewFirstTime extends AppCompatActivity {

    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;

    SharedPreferences sharedPreferences;
    Boolean firsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_first_time);

        myLayout=(ConstraintLayout) findViewById(R.id.myLayout);
        animationDrawable=(AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


       sharedPreferences=getSharedPreferences("MyPref",MODE_PRIVATE);

       firsTime =  sharedPreferences.getBoolean("firstTime",true);

       if(firsTime)
       {
                   SharedPreferences.Editor editor=sharedPreferences.edit();
                   firsTime=false;
                   editor.putBoolean("firstTime",firsTime);
                    editor.apply();


       }else
       {
           Intent i = new Intent(getApplicationContext(), MainActivity.class);
           startActivity(i);
           finish();
       }




        findViewById(R.id.buttonNew1SIGNIN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserListForLogin.class);
                startActivity(i);
            }
        });
        findViewById(R.id.buttonNew2SIGNUP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
