package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.managementapp.college.com.collegemanagement.api.AcademicCalender.AcademicCalenderResponse;
import app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth.RegularLoginResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeManagementApiService;
import app.managementapp.college.com.collegemanagement.api.ServiceGenerator;
import app.managementapp.college.com.collegemanagement.dummy.DummyContent.DummyItem;
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
public class AcademicCalendar extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private CollegeManagementApiService collegeManagementApiService = ServiceGenerator.createService(CollegeManagementApiService.class);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AcademicCalendar() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AcademicCalendar newInstance(int columnCount) {
        AcademicCalendar fragment = new AcademicCalendar();
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
        CredentialManager credentialManager = new CredentialManager(getContext());
        Call<RegularLoginResponse> firstcall = collegeManagementApiService.doRegularLogin(credentialManager.getUserName(), credentialManager.getPassword());
        firstcall.enqueue(new Callback<RegularLoginResponse>() {

            @Override
            public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
                Log.i("token", response.body().toString());

                final Call<AcademicCalenderResponse> sendReplyCall = collegeManagementApiService.getAcademicCalendar(response.body().getToken());
                sendReplyCall.enqueue(new Callback<AcademicCalenderResponse>() {
                    @Override
                    public void onResponse(Call<AcademicCalenderResponse> call, Response<AcademicCalenderResponse> response) {
                        try {
                            Toast.makeText(getContext(), "calendar recieved", Toast.LENGTH_SHORT).show();
                        } catch (NullPointerException e) {
                            Toast.makeText(getContext(), "No Data from the Server", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AcademicCalenderResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

                    }

                });


            }

            @Override
            public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }


        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_academiccalendar_list, container, false);

        // Set the adapter

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
        void onListFragmentInteraction(DummyItem item);
    }

}
