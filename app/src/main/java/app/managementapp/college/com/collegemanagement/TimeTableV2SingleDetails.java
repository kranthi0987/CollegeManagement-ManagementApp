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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.model.util.Converter;

public class TimeTableV2SingleDetails extends AppCompatActivity {
    public static final String DEBUG_TAG = "TimeTbleV2Single";
    public String showingCalander;
    private Map<String, List<ClassData>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_v2_single_details);


        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToTimeTableV3();
            }
        };


        showingCalander = getIntent().getExtras().getString("showingCalander");
        ((TextView) findViewById(R.id.dateTitle)).setText(showingCalander);


        ((ImageView)findViewById(R.id.backTimeTable)).setOnClickListener(onFilterbackTimeTableclickListener);
        Log.d(DEBUG_TAG, "MyClass: " + getIntent().getExtras().getString("MyClass"));
        data = new Converter().convertClassDataSingleItemsString(getIntent().getExtras().getString("MyClass"));
        if(data.containsKey("0")) {
            List<ClassData> classDataList = data.get("0");
            Log.d(DEBUG_TAG, "onCreate: size: " + data.size() + "---" + data.keySet() + "---" + classDataList);
            initClasses(classDataList);
        }
    }


    private void initClasses(List<ClassData> classDataList) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.classItemsCont);
        LayoutInflater inflater = LayoutInflater.from(this);
        Log.d(DEBUG_TAG, "initClasses: "+ "workin");
//        removeAnimated(linearLayout);
        for (ClassData classData: classDataList ) {
            View view  = inflater.inflate(R.layout.row_time_table_class_item, linearLayout, false);
            Log.d(DEBUG_TAG, "initClasses: "+ classData.getCourse());
            String course = "Course: ";
            if(classData.getCourse().toString().equals("null")){
                course += "N/A";
            } else {
                course += classData.getCourse();
            }
            ((TextView) view.findViewById(R.id.courseDesc)).setText(course);
            String subject = "";
            if(classData.getTitle().toString().equals("null")){
                subject += "N/A";
            } else {
                subject += classData.getTitle();
            }
            ((TextView) view.findViewById(R.id.subject)).setText(subject);
            ((TextView) view.findViewById(R.id.section)).setText(classData.getSection());
            ((TextView) view.findViewById(R.id.room)).setText(classData.getPlace());
            String time1 = "";
            if(classData.getStartTime().toString().equals("null")){
                time1 += "N/A";
            } else {
                time1 += classData.getStartTime();
            }
            String time2 = "";
            if(classData.getEndTime().toString().equals("null")){
                time2 += "N/A";
            } else {
                time2 += classData.getEndTime();
            }
            ((TextView) view.findViewById(R.id.time1)).setText(time1);
            ((TextView) view.findViewById(R.id.time2)).setText(time2);
            String title = subject + ", " + time1 + " : " + time2;
            view.setOnClickListener(new ClassOnclickListener(classData, title));
            linearLayout.addView(view);
        }

    }


    public class  ClassOnclickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        ClassData classData;
        String title = "Attendance";
        public ClassOnclickListener(ClassData classData, String title) {
            this.classData = classData;
            this.title = title;
        }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(TimeTableV2SingleDetails.this, AttendanceForClass.class);
            i.putExtra("MyClass", getIntent().getExtras().getString("MyClass"));
            i.putExtra("ClassSchedID", classData.getClassSchedID());
            i.putExtra("intentClassDate", classData.getIntentClassDate());
            i.putExtra("intentStartTime", classData.getIntentStartTime());
            i.putExtra("intentEndTime", classData.getIntentEndTime());
            i.putExtra("showingCalander", showingCalander);
            i.putExtra("title", title);
            startActivity(i);
            finish();
        }
    }




    @Override
    public void onBackPressed () {
        Log.d("onBackPressed", "onBackPressed: ");
        moveToTimeTableV3();
    }
    private void moveToTimeTableV3() {
        Intent i = new Intent(TimeTableV2SingleDetails.this, TimeTableV3.class);
        i.putExtra("showingCalander", showingCalander);
        startActivity(i);
        finish();
    }
}
