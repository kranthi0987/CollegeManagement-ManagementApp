package app.managementapp.college.com.collegemanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.AcademicCalendar;
import app.managementapp.college.com.collegemanagement.ApplyLeave;
import app.managementapp.college.com.collegemanagement.ExternalExams;
import app.managementapp.college.com.collegemanagement.FeedbackList;
import app.managementapp.college.com.collegemanagement.InternalExams;
import app.managementapp.college.com.collegemanagement.InvigilationDetails;
import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.model.MenuItem;
import app.managementapp.college.com.collegemanagement.model.StudentItem;
import app.managementapp.college.com.collegemanagement.widget.CircleTransform;

public class MenuGridAdapter extends RecyclerView.Adapter<MenuGridAdapter.ViewHolder> implements Filterable {

    private final int mBackground;
    private final TypedValue mTypedValue = new TypedValue();
    private List<MenuItem> original_items = new ArrayList<>();
    private List<MenuItem> filtered_items = new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();
    private Context ctx;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MenuGridAdapter(Context ctx, List<MenuItem> items) {
        this.ctx = ctx;
        original_items = items;
        filtered_items = items;
        ctx.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public MenuGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_menu_item, parent, false);
        v.setBackgroundResource(mBackground);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuItem g = filtered_items.get(position);
        holder.title.setText(g.getName());

        holder.clickListener.setOnClickListener(new ItemOnClickListener(position));
        Picasso.with(ctx).load(g.getPhoto()).resize(100, 100).transform(new CircleTransform()).into(holder.image);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filtered_items.size();
    }

    @Override
    public long getItemId(int position) {
        return filtered_items.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView title;
        public TextView content;
        public ImageView image;
        public LinearLayout clickListener;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
//            content = (TextView) v.findViewById(R.id.content);
            image = (ImageView) v.findViewById(R.id.image);
            clickListener = (LinearLayout) v.findViewById(R.id.clickListener);
        }

        @Override
        public void onClick(View v) {

            Log.e("emoveeee", getPosition() + " " + v.getId());
        }
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<MenuItem> list = original_items;
            final List<MenuItem> result_list = new ArrayList<>(list.size());

            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getName();
                if (str_title.toLowerCase().contains(query)) {
                    result_list.add(list.get(i));
                }
            }

            results.values = result_list;
            results.count = result_list.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered_items = (List<MenuItem>) results.values;
            notifyDataSetChanged();
        }

    }

    public class  ItemOnClickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        private StudentItem studentItem;
        private int position;
        public ItemOnClickListener(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: StudentOnClickListener ");
            Intent i = null;
            if(position == 0) {
                i = new Intent(ctx, FeedbackList.class);
            }
            if(position == 1){
                i = new Intent(ctx, InvigilationDetails.class);
            }
            if(position == 2){
                i = new Intent(ctx, InvigilationDetails.class);
            }
            if(position == 3){
                i = new Intent(ctx, ApplyLeave.class);
            }
            if(position == 4){
                i = new Intent(ctx, ExternalExams.class);
            }
            if(position == 5){
                i = new Intent(ctx, InternalExams.class);
            }
            if(position == 6){
                i = new Intent(ctx, InvigilationDetails.class);
            }
            if(position == 7){
                i = new Intent(ctx, FeedbackList.class);
            }
            if (position == 8) {
                i = new Intent(ctx, AcademicCalendar.class);
            }
            if(i != null) ctx.startActivity(i);
            }
        }
}
