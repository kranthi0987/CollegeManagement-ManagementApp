package app.managementapp.college.com.collegemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.model.InternalInvigilationItem;
import app.managementapp.college.com.collegemanagement.model.util.Converter;

public class InternalInvigilationSingleDetail extends AppCompatActivity {
    public String dateTitle;
    public String showingCalander;
    private static String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToInternalInvigilationDetails();

    }

    private void moveToInternalInvigilationDetails() {
        Intent i = new Intent(InternalInvigilationSingleDetail.this, InvigilationDetails.class);
        i.putExtra("showingCalander", showingCalander);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invigilation_single_detail);

        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToInternalInvigilationDetails();
            }
        };


        showingCalander = getIntent().getExtras().getString("showingCalander");
        dateTitle = getIntent().getExtras().getString("dateTitle");
        ((TextView) findViewById(R.id.dateTitle)).setText(showingCalander);

        String extrs = getIntent().getExtras().getString("MyClass");
        Log.d("yes", "onCreate: " + extrs);
        Map<String, List<InternalInvigilationItem>> data = new Converter().convertInvigilationSingleItemsString(extrs);
        Log.d("yes", "onCreate: " + data.keySet());
        if(data.containsKey("0")){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.invigilationItemsCont);
            LayoutInflater inflater = LayoutInflater.from(this);
            List<InternalInvigilationItem> dataO = data.get("0");
            Iterator<InternalInvigilationItem> it = dataO.iterator();
            while (it.hasNext()) {
                InternalInvigilationItem exam = it.next();
                View view  = inflater.inflate(R.layout.row_invigilation_item, linearLayout, false);
                ((TextView) view.findViewById(R.id.subject)).setText(exam.getExamName().replaceAll(",",""));
                ((TextView) view.findViewById(R.id.courseDesc)).setText(dateTitle);
                ((TextView) view.findViewById(R.id.location)).setText(exam.getRoomDescription());
                ((TextView) view.findViewById(R.id.subjectsList)).setText(exam.getSubjects());
                ((TextView) view.findViewById(R.id.time1)).setText(exam.getSession());
                ((TextView) view.findViewById(R.id.time2)).setVisibility(View.GONE);
                // set item content in view
                linearLayout.addView(view);
                view.setOnClickListener(new InvigilationOnclickListener(exam.getSessionID().toString(), exam.getRoomID().toString(), exam));
            }
            Log.d("yes", "onCreate: " + dataO.size());
        }
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);
    }


    public class  InvigilationOnclickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        private String sessionID;
        private String roomID;
        InternalInvigilationItem exam;

        public InvigilationOnclickListener(String sessionID, String roomID, InternalInvigilationItem exam) {
            this.sessionID = sessionID;
            this.roomID = roomID;
            this.exam = exam;
        }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(InternalInvigilationSingleDetail.this, InvigilationAttendance.class);
            //TODO send date, SessionID, RoomID.
            String examDate = "";
            String[] rw = dateTitle.split("/");
            examDate = (rw[0].length() == 1 ? "0" : "") + rw[0] + "%20" + months[Integer.parseInt(rw[1])] + "%20" + rw[2];
            i.putExtra("MyClass", getIntent().getExtras().getString("MyClass"));
            i.putExtra("dateTitle", getIntent().getExtras().getString("dateTitle"));
            i.putExtra("examDate", examDate );
            i.putExtra("sessionID", sessionID );
            i.putExtra("roomID", roomID );
            i.putExtra("intentClassDate", exam.getIntentClassDate());
            i.putExtra("intentStartTime", exam.getIntentStartTime());
            i.putExtra("intentEndTime", exam.getIntentEndTime());
            i.putExtra("showingCalander", showingCalander);
            startActivity(i);
            finish();
        }
    }
}
