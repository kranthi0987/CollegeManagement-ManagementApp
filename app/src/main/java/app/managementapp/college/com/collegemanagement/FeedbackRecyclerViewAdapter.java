package app.managementapp.college.com.collegemanagement;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import app.managementapp.college.com.collegemanagement.FeedbackFragment.OnListFragmentInteractionListener;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.DataList;

public class FeedbackRecyclerViewAdapter extends RecyclerView.Adapter<FeedbackRecyclerViewAdapter.ViewHolder> {

    List<DataList> mValues= Collections.emptyList();
    private final OnListFragmentInteractionListener mListener;

    public FeedbackRecyclerViewAdapter(List<DataList> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_feedback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getMessageTitle());
        holder.mContentView.setText(mValues.get(position).getMessage());
        if(mValues.get(position).getReply()==null||mValues.get(position).getReply().isEmpty()) {
            holder.mStatus.setText("Not Replied");
        }else{
            holder.mStatus.setText("Replied");
            holder.mStatus.setTextColor(ColorStateList.valueOf(Color.GREEN));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
       try {
           return mValues.size();
       }catch (NullPointerException e){
           return 0;
       }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DataList mItem;
        public final TextView mStatus;

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
