package app.managementapp.college.com.collegemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth.RegularLoginResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeManagementApiService;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.DataList;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.FeedbackListResponse;
import app.managementapp.college.com.collegemanagement.api.FeedbackReply.FeedbackReplyRequest;
import app.managementapp.college.com.collegemanagement.api.ServiceGenerator;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackReply extends AppCompatActivity {
    EditText replyView;
    DataList messagedata;
    FeedbackReplyRequest reply = null;
    private TextView repliedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_reply);
        messagedata = (DataList) getIntent().getParcelableExtra("data");
        TextView messageTitle = (TextView) findViewById(R.id.message_title);
        messageTitle.setText(messagedata.getMessageTitle().toString().trim());
        TextView messageContent = (TextView) findViewById(R.id.message);
        messageContent.setText(messagedata.getMessage());
        replyView = (EditText) findViewById(R.id.reply_field);
        repliedTextView = (TextView) findViewById(R.id.replied_message);
        repliedTextView.setText(messagedata.getReply().trim());
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onReplyClick(View view) {
        String feedback = replyView.getText().toString();
        if (feedback.trim().length() == 0) {
            Toast.makeText(getApplication(), "Enter some feedback", Toast.LENGTH_SHORT).show();
        }
        if (messagedata.getReply().trim().length() != 0) {
            Toast.makeText(getApplicationContext(), "You Already Replied", Toast.LENGTH_SHORT).show();

        } else {
            reply = new FeedbackReplyRequest();
            reply.setReply(feedback.trim());
            reply.setRefID(messagedata.getRefID());
            final CollegeManagementApiService collegeManagementApiService = ServiceGenerator.createService(CollegeManagementApiService.class);


            CredentialManager credentialManager = new CredentialManager(getApplicationContext());
            Call<RegularLoginResponse> firstcall = collegeManagementApiService.doRegularLogin(credentialManager.getUserName(), credentialManager.getPassword());
            firstcall.enqueue(new Callback<RegularLoginResponse>() {

                @Override
                public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
                    Log.i("token", response.body().toString());

                    final Call<FeedbackListResponse> sendReplyCall = collegeManagementApiService.giveReplyForFeedback(response.body().getToken(), reply);
                    sendReplyCall.enqueue(new Callback<FeedbackListResponse>() {
                        @Override
                        public void onResponse(Call<FeedbackListResponse> call, Response<FeedbackListResponse> response) {
                            try {
                                Toast.makeText(getApplicationContext(), "Feedback Sent", Toast.LENGTH_SHORT).show();
                            } catch (NullPointerException e) {
                                Toast.makeText(getApplicationContext(), "No Data from Server", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<FeedbackListResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

                        }

                    });


                }

                @Override
                public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }


            });


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, FeedbackList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}