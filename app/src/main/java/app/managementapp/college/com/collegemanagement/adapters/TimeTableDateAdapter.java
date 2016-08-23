package app.managementapp.college.com.collegemanagement.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.managementapp.college.com.collegemanagement.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.managementapp.college.com.collegemanagement.model.MenuItem;
import app.managementapp.college.com.collegemanagement.widget.CircleTransform;

public class TimeTableDateAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Date[] date;

    public TimeTableDateAdapter(Activity context, Date[] date) {
        super(context, R.layout.row_time_table_date_item);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.date=date;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_time_table_date_item, null,true);

       /* TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText("Description "+itemname[position]);
        */
        return rowView;

    };
}