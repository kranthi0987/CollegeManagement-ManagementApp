package app.managementapp.college.com.collegemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import app.managementapp.college.com.collegemanagement.api.CollegeManagementApiService;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.DataList;
import app.managementapp.college.com.collegemanagement.management.ManagementHome;

public class FeedbackList extends AppCompatActivity implements FeedbackFragment.OnListFragmentInteractionListener {

    FrameLayout progressBarHolder;

    private CollegeManagementApiService collegeApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        FeedbackFragment feedbackFragment=FeedbackFragment.newInstance(1);
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.list_container, feedbackFragment).commit();
//        getSupportActionBar().setTitle("Feedback");
  //      getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
       moveToLanding();
    }

    private void moveToLanding() {
        Intent i = new Intent(FeedbackList.this, ManagementHome.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onListFragmentInteraction(DataList item) {
        Intent gotoFeedbackReply=new Intent(this,FeedbackReply.class);
        gotoFeedbackReply.putExtra("data", (Parcelable) item);
        startActivity(gotoFeedbackReply);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, ManagementHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
