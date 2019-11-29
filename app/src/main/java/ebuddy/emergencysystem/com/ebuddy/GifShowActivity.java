package ebuddy.emergencysystem.com.ebuddy;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GifShowActivity extends AppCompatActivity {
    ConstraintLayout myLayoutGifShowActivity;
    AnimationDrawable animationDrawableGifShowActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_show);

        myLayoutGifShowActivity =(ConstraintLayout) findViewById(R.id.myLayoutGifShowActivity);
        animationDrawableGifShowActivity=(AnimationDrawable) myLayoutGifShowActivity.getBackground();
        animationDrawableGifShowActivity.setEnterFadeDuration(1500);
        animationDrawableGifShowActivity.setExitFadeDuration(1500);
        animationDrawableGifShowActivity.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(GifShowActivity.this,Location2Activity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, 2000);
    }
}
