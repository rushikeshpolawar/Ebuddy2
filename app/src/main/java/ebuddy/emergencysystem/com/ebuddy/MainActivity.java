package ebuddy.emergencysystem.com.ebuddy;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Button b;
      String pName,pEmail;
    ConstraintLayout myLayoutMain;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        myLayoutMain =(ConstraintLayout) findViewById(R.id.myLayoutMain);
        animationDrawable=(AnimationDrawable) myLayoutMain.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();
//    pName = getIntent().getExtras().getString("Name");
//    pEmail = getIntent().getExtras().getString("Email");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);








        final Drawable dw=getApplicationContext().getResources().getDrawable(R.drawable.redbutton);

        final Drawable dw1=getApplicationContext().getResources().getDrawable(R.drawable.greenbutton);

       b =(Button) findViewById(R.id.button);

        b.setCompoundDrawablesWithIntrinsicBounds(null, dw,null,null);



    TextView tv = (TextView)hView.findViewById(R.id.textViewNName);
        tv.setText("Rushikesh Polawar"/*pName*/);

    TextView tv1 = (TextView)hView.findViewById(R.id.textViewEEmail);
    tv1.setText("rushikeshpolawar98@gmail.com"/*pEmail*/);

    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            b.setCompoundDrawablesWithIntrinsicBounds(null, dw1, null, null);
            startActivity(new Intent(getApplicationContext(), Location2Activity.class));
            b.setCompoundDrawablesWithIntrinsicBounds(null, dw,null,null);

        }
    });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.nav_login) {
            startActivity(new Intent(this.getApplicationContext(),gsheets1.UserList.UserListForLogin.class));
        } else if (id == R.id.nav_register) {
            startActivity(new Intent(this.getApplicationContext(),RegisterActivity.class));
        } else if (id == R.id.nav_blood) {
            startActivity(new Intent(this.getApplicationContext(),gsheets1.UserList.UserList.class));
        } else if (id == R.id.nav_aboutus) {
            Intent i=new Intent(this.getApplicationContext(),AboutUsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_help) {
            Intent i=new Intent(this.getApplicationContext(),Help_Activity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
