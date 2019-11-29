
package gsheets1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import gsheets1.model.MyDataModel;
import ebuddy.emergencysystem.com.ebuddy.R;

public class MyArrayAdapterForLogin extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;


    public MyArrayAdapterForLogin(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder1 vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.activity_login, parent, false);
            vh = ViewHolder1.createLogin((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder1) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewEmail.setText(item.getEmail());
        vh.textViewPassword.setText(item.getPassword());
        return vh.rootView;
    }


    private static class ViewHolder1 {
        public final RelativeLayout rootView;

        public final TextView textViewEmail;
        public final TextView textViewPassword;


        private ViewHolder1(RelativeLayout rootView, TextView textEditTextEmail,TextView textEditTextPassword) {
            this.rootView = rootView;

            this.textViewEmail = textEditTextEmail;
            this.textViewPassword = textEditTextPassword;
        }

        public static ViewHolder1 createLogin(RelativeLayout rootView1) {
            TextView textViewEmail = (TextView) rootView1.findViewById(R.id.textInputEditTextEmail);

                TextView textViewPassword =(TextView) rootView1.findViewById(R.id.textInputEditTextPassword);


            return new ViewHolder1(rootView1, textViewEmail, textViewPassword);
        }
    }
}