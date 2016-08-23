package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth.RegularLoginResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeManagementApiService;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.DataList;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.FeedbackListResponse;
import app.managementapp.college.com.collegemanagement.api.ServiceGenerator;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FeedbackFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private CollegeManagementApiService collegeApiService;
    private CredentialManager credentialManager;
    List<DataList> data;
    private FeedbackRecyclerViewAdapter feedbackRecyclerViewAdapter;
    private FrameLayout progressBarHolder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FeedbackFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FeedbackFragment newInstance(int columnCount) {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        collegeApiService = ServiceGenerator.createService(CollegeManagementApiService.class);
        progressBarHolder = (FrameLayout) getActivity().findViewById(R.id.progressBarHolder);
        progressBarHolder.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_list, container, false);
        credentialManager=new CredentialManager(getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            feedbackRecyclerViewAdapter =new FeedbackRecyclerViewAdapter(data, mListener);
            recyclerView.setAdapter(feedbackRecyclerViewAdapter);
        }

        Call<RegularLoginResponse> firstcall=collegeApiService.doRegularLogin(credentialManager.getUserName(),credentialManager.getPassword());
        firstcall.enqueue(new Callback<RegularLoginResponse>() {

            @Override
            public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
//                Log.i("token",response.body().toString());

                final Call<FeedbackListResponse> feedbacklistcall=collegeApiService.getFeedbackList(response.body().getToken(),0);
                feedbacklistcall.enqueue(new Callback<FeedbackListResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackListResponse> call, Response<FeedbackListResponse> response) {
                        try{
                            Log.i("feed",response.body().toString());
                            data=response.body().getDataList();
                            feedbackRecyclerViewAdapter.mValues=data;
                            feedbackRecyclerViewAdapter.notifyDataSetChanged();
                            progressBarHolder.setVisibility(View.INVISIBLE);

                        }
                        catch (NullPointerException e){
                            Toast.makeText(getContext(),"No Data from Server",Toast.LENGTH_SHORT).show();
                            progressBarHolder.setVisibility(View.INVISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<FeedbackListResponse> call, Throwable t) {
                        Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();
                        progressBarHolder.setVisibility(View.INVISIBLE);
                    }

                });


            }

            @Override
            public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();
                progressBarHolder.setVisibility(View.INVISIBLE);

            }


        });



        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DataList item);
    }
}
