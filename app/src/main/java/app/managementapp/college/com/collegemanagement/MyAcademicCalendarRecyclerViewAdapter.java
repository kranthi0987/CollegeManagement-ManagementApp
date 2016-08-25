package app.managementapp.college.com.collegemanagement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.managementapp.college.com.collegemanagement.FeedbackFragment.OnListFragmentInteractionListener;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.DataList;

public class MyAcademicCalendarRecyclerViewAdapter extends RecyclerView.Adapter<FeedbackRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    List<DataList> mValues = Collections.emptyList();

    public MyAcademicCalendarRecyclerViewAdapter(List<DataList> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_academiccalendar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedbackRecyclerViewAdapter.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        try {
            return mValues.size();
        } catch (NullPointerException e) {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mStatus;
        public DataList mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.message_title);
            mContentView = (TextView) view.findViewById(R.id.message_content);
            mStatus = (TextView) view.findViewById(R.id.message_status);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
