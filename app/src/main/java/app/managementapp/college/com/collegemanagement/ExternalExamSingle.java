package app.managementapp.college.com.collegemanagement;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.model.ExternalExamItem;
import app.managementapp.college.com.collegemanagement.model.InternalInvigilationItem;
import app.managementapp.college.com.collegemanagement.model.util.Converter;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class ExternalExamSingle extends AppCompatActivity {
    public String dateTitle;
    public String showingCalander;
    private CredentialManager credentialManager;
    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToExternalExams();

    }

    private void moveToExternalExams() {
        Intent i = new Intent(ExternalExamSingle.this, ExternalExams.class);
        i.putExtra("showingCalander", showingCalander);
        startActivity(i);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_exam_single);

        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToExternalExams();
            }
        };


        showingCalander = getIntent().getExtras().getString("showingCalander");
        dateTitle = getIntent().getExtras().getString("dateTitle");
        ((TextView) findViewById(R.id.dateTitle)).setText(showingCalander);

        String extrs = getIntent().getExtras().getString("examItemsString");
        Log.d("yes", "onCreate: " + extrs);
        Map<String, List<ExternalExamItem>> data = new Converter().convertExternalExamSingleItemsString(extrs);
        Log.d("yes", "onCreate: " + data.keySet());
        if(data.containsKey("0")){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.invigilationItemsCont);
            LayoutInflater inflater = LayoutInflater.from(this);
            List<ExternalExamItem> dataO = data.get("0");
            Iterator<ExternalExamItem> it = dataO.iterator();
            while (it.hasNext()) {
                ExternalExamItem exam = it.next();
                View view  = inflater.inflate(R.layout.row_invigilation_item, linearLayout, false);
                ((TextView) view.findViewById(R.id.subject)).setText(exam.getExamName().replaceAll(",",""));
                ((TextView) view.findViewById(R.id.courseDesc)).setText(exam.getSubjectName());
                ((TextView) view.findViewById(R.id.location)).setText(exam.getSection());
                ((TextView) view.findViewById(R.id.subjectsList)).setText(exam.getSubjectCode());
                ((TextView) view.findViewById(R.id.time1)).setText(exam.getStartTime());
                ((TextView) view.findViewById(R.id.time2)).setText(exam.getEndTime());
//                ((TextView) view.findViewById(R.id.time1)).setText(exam.getSession());
                // set item content in view
                linearLayout.addView(view);
                view.setOnClickListener(new InvigilationOnclickListener());
            }
            Log.d("yes", "onCreate: " + dataO.size());
        }
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);
    }


    public class  InvigilationOnclickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        public InvigilationOnclickListener() {
        }
        @Override
        public void onClick(View v) {
//            Toast.makeText(ExternalExamSingle.this, "API pending", Toast.LENGTH_SHORT).show();
        }
    }
}
