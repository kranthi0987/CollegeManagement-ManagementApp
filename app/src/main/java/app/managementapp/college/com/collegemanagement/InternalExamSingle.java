package app.managementapp.college.com.collegemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.model.ExternalExamItem;
import app.managementapp.college.com.collegemanagement.model.util.Converter;

public class InternalExamSingle extends AppCompatActivity {
    public String dateTitle;
    public String showingCalander;
    public String heading;
    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToInternalExams();

    }

    private void moveToInternalExams() {
        Intent i = new Intent(InternalExamSingle.this, InternalExams.class);
        i.putExtra("showingCalander", showingCalander);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_exam_single);

        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToInternalExams();
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
                view.setOnClickListener(new InvigilationOnclickListener(exam.getExamID()));
            }
            Log.d("yes", "onCreate: " + dataO.size());
        }
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);
    }


    public class  InvigilationOnclickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        String ExamID;
        public InvigilationOnclickListener(String ExamID) {
            this.ExamID = ExamID;
        }
        @Override
        public void onClick(View view) {
            Intent i = new Intent(InternalExamSingle.this, InternalExamsMarksUpdate.class);
            i.putExtra("examItemsString", getIntent().getExtras().getString("examItemsString"));
            i.putExtra("ExamID", ExamID);
            i.putExtra("dateTitle", showingCalander + ", " +  ((TextView) view.findViewById(R.id.subject)).getText()  );
            i.putExtra("showingCalander", showingCalander);
            startActivity(i);
            finish();
        }
    }
}
