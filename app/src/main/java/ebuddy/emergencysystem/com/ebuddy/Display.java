package ebuddy.emergencysystem.com.ebuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import gsheets1.UserList.UserList;

public class Display extends AppCompatActivity {
String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ((TextView)findViewById(R.id.FN)).setText(UserList.fn);
        ((TextView)findViewById(R.id.MONO)).setText(UserList.mo);
        ((TextView)findViewById(R.id.EML)).setText(UserList.em);
        ((TextView)findViewById(R.id.BLG)).setText(UserList.bg);
        Toast.makeText(getApplicationContext(),"Click on Mobile Number to call Donor",Toast.LENGTH_SHORT).show();
        ((TextView)findViewById(R.id.MONO)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                 num = (UserList.mo).toString();
                 Toast.makeText(getApplicationContext(),""+num,Toast.LENGTH_SHORT).show();
                if(num==""){
                    i.setData(Uri.parse("tel:"));
                }
                else{
                    i.setData(Uri.parse("tel:"+num));
                }
                if (ActivityCompat.checkSelfPermission(Display.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Please grant the  permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(i);
                }
            }
            private void requestPermission() {
                ActivityCompat.requestPermissions(Display.this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });

    }
}
