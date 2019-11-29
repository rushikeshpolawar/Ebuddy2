
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


public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;


    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
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
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.textViewFirstName.setText(item.getFirstName());
        vh.textViewMobno.setText(item.getMobno());
        vh.textViewBloodGroup.setText(item.getBloodGroup());

        return vh.rootView;
    }


    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView textViewFirstName;
        public final TextView textViewMobno;
        public final TextView textViewBloodGroup;


        private ViewHolder(RelativeLayout rootView, TextView textViewFirstName, TextView textViewMobno, TextView textViewBloodGroup) {
            this.rootView = rootView;
            this.textViewFirstName = textViewFirstName;
            this.textViewMobno = textViewMobno;
            this.textViewBloodGroup = textViewBloodGroup;

        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewFirstName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewMobno = (TextView) rootView.findViewById(R.id.textViewMobileNo);
            TextView textViewBloodGroup = (TextView) rootView.findViewById(R.id.textViewBloodGroup);

            return new ViewHolder(rootView, textViewFirstName, textViewMobno ,textViewBloodGroup);
        }
    }
}