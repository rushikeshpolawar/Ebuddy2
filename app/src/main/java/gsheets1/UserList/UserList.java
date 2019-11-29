

package gsheets1.UserList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gsheets1.adapter.MyArrayAdapter;
import gsheets1.model.MyDataModel;
import gsheets1.parser.JSONParser;
import gsheets1.util.InternetConnection;
import gsheets1.util.Keys;
import ebuddy.emergencysystem.com.ebuddy.Display;
import ebuddy.emergencysystem.com.ebuddy.R;


public class UserList extends AppCompatActivity {

    public static String fn,ln,mo,em,bg;
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;

    int x;
    String tmp1,tmp[]=new String[55];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, list);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserList.fn=list.get(position).getFirstName();
                UserList.ln=list.get(position).getLastName();
                UserList.mo=list.get(position).getMobno();
                UserList.em=list.get(position).getEmail();
                UserList.bg=list.get(position).getBloodGroup();
                startActivity(new Intent(UserList.this.getApplicationContext(),Display.class));
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(), "Click on FloatingActionButton to Load JSON", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    new GetDataTask().execute();
                } else {
                    Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            x=list.size();
            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(UserList.this);
            dialog.setTitle("Hey Wait Please..."+x);
            dialog.setMessage("I am getting your JSON");
            dialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = JSONParser.getDataFromWeb();
            try {
                if (jsonObject!=null) {
                    if(jsonObject.length()>0) {
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);
                        int lenArray = array.length();
                        if(lenArray>0) {
                            for( jIndex = 0 ; jIndex < lenArray; jIndex++) {
                                MyDataModel model = new MyDataModel();
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String name = innerObject.getString(Keys.KEY_FIRSTNAME);
                                String mob = innerObject.getString(Keys.KEY_MOBNO);
                                String bg = innerObject.getString(Keys.KEY_BLOODGROUP);
                                model.setFirstName(name);
                                model.setMobno(mob);
                                model.setBloodGroup(bg);
                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if(list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                HandlerThread ht=new HandlerThread("Hello");
                ht.start();
                Handler h=new Handler(ht.getLooper());
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        for(x=0;x<55;x++){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Snackbar.make(findViewById(R.id.parentLayout), "No Data Found : "+tmp1, Snackbar.LENGTH_LONG).show();
                                }
                            });
                            try{Thread.sleep(1000);}catch (Exception e){}}
                    }
                });
            }
        }
    }

}