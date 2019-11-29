package ebuddy.emergencysystem.com.ebuddy;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;

public class Location2Activity extends AppCompatActivity implements LocationListener {

    Button btFB, btPolice, btwomens, btAmbulance;
    ConstraintLayout myLayoutLocation;
    AnimationDrawable animationDrawablE;

    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    String FireBrigadeNo="70382324";
    String PoliceNo="7038282324";
    String AmbulanceNo="7038282324";
    String WomensSeftyNo="7038282324";

    String tvLatitude, tvLongitude, tvTime;
    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location2);

        btFB = (Button) findViewById(R.id.button1);
        btPolice = (Button) findViewById(R.id.button2);
        btwomens = (Button) findViewById(R.id.button3);
        btAmbulance = (Button) findViewById(R.id.button4);


        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        myLayoutLocation =(ConstraintLayout) findViewById(R.id.myLayoutLocation);
        animationDrawablE=(AnimationDrawable) myLayoutLocation.getBackground();
        animationDrawablE.setEnterFadeDuration(1500);
        animationDrawablE.setExitFadeDuration(1500);
        animationDrawablE.start();

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);


        if (!isGPS && !isNetwork) {

            showSettingsAlert();
            getLastLocation();
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);

                    canGetLocation = false;
                }
            }

            getLocation();


            btFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext(),"Latitude ="+tvLatitude+" Logitude " +
                            "="+tvLongitude+" at Time ="+tvTime,Toast.LENGTH_LONG).show();


                    if(tvLatitude.equals(null) || tvLongitude.equals(null) || tvLatitude.equals("") || tvLongitude.equals("") )
                    {
                        Toast.makeText(getApplicationContext(), "Message is not send due to no location is found", Toast.LENGTH_SHORT).show();
                    }else
                    {


                        if (TextUtils.isDigitsOnly(FireBrigadeNo)) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(FireBrigadeNo, null, "http://maps.google.com/maps?q=" + tvLatitude + "," + tvLongitude ,
                    null, null);
                    Intent ii=new Intent(Location2Activity.this,GifShowActivity.class);
                    startActivity(ii);

                } else {
                    Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
                }
            }


                }
            });




            btPolice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Toast.makeText(getApplicationContext(),"Latitude ="+tvLatitude+" Logitude " +
                            "="+tvLongitude+" at Time ="+tvTime,Toast.LENGTH_LONG).show();
                    if(tvLatitude.equals(null) || tvLongitude.equals(null) || tvLatitude.equals("") || tvLongitude.equals("") )
                    {
                        Toast.makeText(getApplicationContext(), "Message is not send due to no location is found", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        if (TextUtils.isDigitsOnly(PoliceNo)) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(PoliceNo, null, "http://maps.google.com/maps?q=" + tvLatitude + "," + tvLongitude ,
                                    null, null);
                            Intent ii=new Intent(Location2Activity.this,GifShowActivity.class);
                            startActivity(ii);

                        } else {
                            Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            btwomens.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Toast.makeText(getApplicationContext(),"Latitude ="+tvLatitude+" Logitude " +
                            "="+tvLongitude+" at Time ="+tvTime,Toast.LENGTH_LONG).show();


                    if(tvLatitude.equals(null) || tvLongitude.equals(null) || tvLatitude.equals("") || tvLongitude.equals("") )
                    {
                        Toast.makeText(getApplicationContext(), "Message is not send due to no location is found", Toast.LENGTH_SHORT).show();
                    }else
                    {

                        if (TextUtils.isDigitsOnly(AmbulanceNo)) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(AmbulanceNo, null, "http://maps.google.com/maps?q=" + tvLatitude + "," + tvLongitude ,
                                    null, null);
                            Intent ii=new Intent(Location2Activity.this,GifShowActivity.class);
                            startActivity(ii);

                        } else {
                            Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });


            btAmbulance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Toast.makeText(getApplicationContext(),"Latitude ="+tvLatitude+" Logitude " +
                            "="+tvLongitude+" at Time ="+tvTime,Toast.LENGTH_LONG).show();


                    if(tvLatitude.equals(null) || tvLongitude.equals(null) || tvLatitude.equals("") || tvLongitude.equals("") )
                        {
                            Toast.makeText(getApplicationContext(), "Message is not send due to no location is found", Toast.LENGTH_SHORT).show();
                        }else
                        {

                        if (TextUtils.isDigitsOnly(WomensSeftyNo)) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(WomensSeftyNo, null, "http://maps.google.com/maps?q=" + tvLatitude + "," + tvLongitude ,
                                    null, null);
                            Intent ii=new Intent(Location2Activity.this,GifShowActivity.class);
                            startActivity(ii);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
                        }
                    }





                }
            });
        }
    }







    @Override
    public void onLocationChanged(Location location) {
       updateUI(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        try {
            if (canGetLocation) {
               if (isGPS) {
                    locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER , MIN_TIME_BW_UPDATES , MIN_DISTANCE_CHANGE_FOR_UPDATES , this );

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            } else {

            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:

                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(
                                                        new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                } else {

                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void updateUI(Location loc) {

        tvLatitude=Double.toString(loc.getLatitude());
        tvLongitude=Double.toString(loc.getLongitude());
        tvTime= DateFormat.getTimeInstance().format(loc.getTime());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
}















