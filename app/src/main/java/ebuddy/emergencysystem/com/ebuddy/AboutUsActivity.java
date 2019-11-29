package ebuddy.emergencysystem.com.ebuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {
    String num="+917038282324",num1="+917038282324";
    TextView  mbno,mbnop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mbno=(TextView)findViewById(R.id.textViewMobileNumber_about_us);
        mbnop=(TextView)findViewById(R.id.textViewMobileNumber_about_us1);
        mbno.setText(num);
        mbnop.setText(num1);

        Toast.makeText(getApplicationContext(),"Click on Mobile Number to call Developer",Toast.LENGTH_SHORT).show();
        mbno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                Toast.makeText(getApplicationContext(),""+num,Toast.LENGTH_SHORT).show();
                if(num==""){
                    i.setData(Uri.parse("tel:"));
                }
                else{
                    i.setData(Uri.parse("tel:"+num));
                }
                if (ActivityCompat.checkSelfPermission(AboutUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Please grant the  permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(i);
                }
            }
            private void requestPermission() {
                ActivityCompat.requestPermissions(AboutUsActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });

        mbnop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                Toast.makeText(getApplicationContext(),""+num,Toast.LENGTH_SHORT).show();
                if(num1==""){
                    i.setData(Uri.parse("tel:"));
                }
                else{
                    i.setData(Uri.parse("tel:"+num1));
                }
                if (ActivityCompat.checkSelfPermission(AboutUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Please grant the  permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(i);
                }
            }
            private void requestPermission() {
                ActivityCompat.requestPermissions(AboutUsActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
        });

    }
}
