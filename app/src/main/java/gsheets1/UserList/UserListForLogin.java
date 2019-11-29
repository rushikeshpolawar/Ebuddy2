package gsheets1.UserList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ebuddy.emergencysystem.com.ebuddy.MainActivity;
import gsheets1.model.MyDataModel;
import gsheets1.parser.JSONParser;
import gsheets1.util.InternetConnection;
import gsheets1.util.Keys;

import ebuddy.emergencysystem.com.ebuddy.R;


public class UserListForLogin extends AppCompatActivity {

    String Email,Password;
    private ArrayList<MyDataModel> list;
    String pName,pEmail;

    int xx=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        pName=getIntent().getExtras().getString("Name");
 //       pEmail=getIntent().getExtras().getString("Email");
        list = new ArrayList<>();
    findViewById(R.id.appCompatButtonLogin).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (InternetConnection.checkConnection(getApplicationContext()))
            {
                Email=((TextInputEditText)findViewById(R.id.textInputEditTextEmail)).getText().toString();
                Password=((TextInputEditText)findViewById(R.id.textInputEditTextPassword)).getText().toString();
                if(Email.trim().length()==0||Password.trim().length()==0)
                    Toast.makeText(getApplicationContext(), "Please enter valid email or password ", Toast.LENGTH_LONG).show();
                else new GetDataTaskForLogin().execute();
            } else {
                Toast.makeText(getApplicationContext(), "Internet Connection Not Available", Toast.LENGTH_LONG).show();
            }
        }
    });
    }
    class GetDataTaskForLogin extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(UserListForLogin.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Connecting to server...");
            dialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JSONParser.getDataFromWeb();

            try {
                if (jsonObject != null) {
                    if(jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);
                        int lenArray = array.length();

                        if(lenArray > 0) {
                            for(jIndex=0 ; jIndex < lenArray; jIndex++) {
                                MyDataModel model = new MyDataModel();
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String email = innerObject.getString(Keys.KEY_EMAIL);
                                String password = innerObject.getString(Keys.KEY_PASSWORD);

                                if(email.equalsIgnoreCase(Email)){
                                    if(Password.equalsIgnoreCase(password)){ xx=1;break;}
                                    else{xx=2;break;}
                                }
                                else{xx=0;}

                                if(x!=0){break;}
                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
           if(list.size()>0){
                if(xx==1){
                     Toast.makeText(getApplicationContext(), "Sucessful Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserListForLogin.this.getApplicationContext(), MainActivity.class);
                    intent.putExtra("Name",pName);
                    intent.putExtra("Email",pEmail);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                }
                else if(xx==2)
                    Toast.makeText(getApplicationContext(), Email+"Wrong Password"+Password, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), " Please enter Valid Email & Password", Toast.LENGTH_SHORT).show();
            } else {
               Toast.makeText(getApplicationContext(),pEmail,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Invalid 124 User", Toast.LENGTH_LONG).show();
            }
        }
    }
}


