package ebuddy.emergencysystem.com.ebuddy;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import gsheets1.util.InternetConnection;


public class RegisterActivity extends AppCompatActivity {

    TextView tvFirstName,tvMobno,tvEmail,tvPassword;
    Button BtRegister;
    EditText etFristName,etMobno,etEmail,etPassword;
    String FirstName,Mobno,Email,Password;
    String BG;
    Spinner spinnerGender,spinnerBloodGroup;
    Intent intent;
    EditText etAge,etCity,etState;
    ConstraintLayout myLayoutReg;
    AnimationDrawable animationDrawableReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BtRegister=(Button)findViewById(R.id.buttonRegister);
        etFristName=(EditText)findViewById(R.id.editTextFirstName);

        etMobno=(EditText)findViewById(R.id.editTextPhoneNumber);
        etEmail=(EditText)findViewById(R.id.editTextEmail);
        etPassword=(EditText)findViewById(R.id.editTextPassword);
        spinnerGender=(Spinner)findViewById(R.id.spinnerGender);
        spinnerBloodGroup=(Spinner)findViewById(R.id.spinnerBloodGroup);


        myLayoutReg =(ConstraintLayout) findViewById(R.id.myLayoutRegistration);
        animationDrawableReg=(AnimationDrawable) myLayoutReg.getBackground();
        animationDrawableReg.setEnterFadeDuration(1500);
        animationDrawableReg.setExitFadeDuration(1500);
        animationDrawableReg.start();



        String[] Gender={"Male","Female"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Gender);
        spinnerGender.setAdapter(adapter);


        final String[] BloodGroup={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,BloodGroup);
        spinnerBloodGroup.setAdapter(adapter1);


        spinnerBloodGroup.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                BG = (String) spinnerBloodGroup.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(),"You Pressed on Yes",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton1:
                        Toast.makeText(getApplicationContext(),"You Pressed on No",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });






        BtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetConnection.checkConnection(getApplicationContext()))
                {
            FirstName=(tvFirstName=(TextView)findViewById(R.id.editTextFirstName)).getText().toString();

            Mobno=(tvMobno=(TextView)findViewById(R.id.editTextPhoneNumber)).getText().toString();
            Email=(tvEmail=(TextView)findViewById(R.id.editTextEmail)).getText().toString();
            Password=(tvPassword=(TextView)findViewById(R.id.editTextPassword)).getText().toString();


            etAge=(EditText)findViewById(R.id.editTextDatePicker);
            etCity=(EditText)findViewById(R.id.editTextCity);
            etState=(EditText)findViewById(R.id.editTextState);

               //Validation

                if(etFristName.getText().toString().equals("") || etFristName.getText().toString().equals(null))
                {
                   etFristName.setError("Please Enter Name");
                   etFristName.requestFocus();
                   return;
                }
                if(etAge.getText().toString().equals("") || etAge.getText().toString().equals(null))
                {
                    etAge.setError("Please Enter Age");
                    etAge.requestFocus();
                    return;
                }
                if(etMobno.getText().toString().equals("") || etMobno.getText().toString().equals(null))
                {
                    tvMobno.setError("Please Enter Mobile Number");
                    tvMobno.requestFocus();
                    return;
                }
                if(etCity.getText().toString().equals("") || etCity.getText().toString().equals(null))
                {
                    etCity.setError("Please Enter City");
                    etCity.requestFocus();
                    return;
                }
                if(etState.getText().toString().equals("") || etState.getText().toString().equals(null))
                {
                    etState.setError("Please Enter State");
                    etState.requestFocus();
                    return;
                }
                if(etEmail.getText().toString().equals("") || etEmail.getText().toString().equals(null))
                {
                    tvEmail.setError("Please Enter Email");
                    tvEmail.requestFocus();
                    return;
                }
                if (!(etEmail.getText().toString()).matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    etEmail.setError("Invalid Email Address");
                    etEmail.requestFocus();
                    return;
                }

                if(etPassword.getText().toString().equals("") || etPassword.getText().toString().equals(null))
                {
                    etPassword.setError("Please Enter Password");
                    etPassword.requestFocus();
                    return;
                }

    intent = new Intent(RegisterActivity.this, gsheets1.UserList.UserListForLogin.class);
 //   intent.putExtra("Name", FirstName);
  //  intent.putExtra("Email", Email);

    new SendRequest().execute();


                } else {
                    Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_LONG).show();
                }

            }

        }   );


    }

    public class SendRequest extends AsyncTask<String, Void, String> {


        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("https://script.google.com/macros/s/AKfycbx-SMcJJacLPQMj-1N7d1WnICI89WSAXCNQoqmb9iCBg1iCqcp9/exec");


                JSONObject postDataParams = new JSONObject();

                String id= "1C_ij3vFJyyU5In_7EBGQY4FWHCWmr9dgGZ1eqTNgcz4";
                //1C_ij3vFJyyU5In_7EBGQY4FWHCWmr9dgGZ1eqTNgcz4
                postDataParams.put("FirstName",FirstName);

                postDataParams.put("MobNo",Mobno);
                postDataParams.put("Email",Email);
                postDataParams.put("Password",Password);
                postDataParams.put("BG",BG);
                postDataParams.put("id",id);





                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
         //   Toast.makeText(getApplicationContext(), result,
          //          Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(),"Welcome You Are Registered",Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);


        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}