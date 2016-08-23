package app.managementapp.college.com.collegemanagement.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import app.managementapp.college.com.collegemanagement.R;

/**
 * Created by new on 3/20/2016.
 */
public class HomeMenuAdapter extends ArrayAdapter<String> {
    private final Activity context;

    private final String[] itemName;
    public HomeMenuAdapter(Activity context, String[] itemName) {
        super(context, R.layout.row_home_menu_item, itemName);
        this.context = context;
        this.itemName = itemName;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.row_home_menu_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
        txtTitle.setText(itemName[position]);

        /*TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText("Description "+itemname[position]);*/
        return rowView;

    };
}
