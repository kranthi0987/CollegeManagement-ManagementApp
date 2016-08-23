package app.managementapp.college.com.collegemanagement.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.model.StudentItem;

/**
 * Created by new on 4/13/2016.
 */
public class StudentMarksListAdapter extends RecyclerView.Adapter<StudentMarksListAdapter.ViewHolder>{

    boolean CodeBlockCompletion = false;
    boolean saved = true;
    boolean warnOneDone = true;
    private List<StudentItem> studentItems = new ArrayList<>();

    private List<String> marksList = new ArrayList<>();
//    private HashMap<String, MarksChangedListener> marksChangedListener = new HashMap<>();
    private Context ctx;
    private ViewGroup parent;
//    MarksChangedListener marksChangedListener = new MarksChangedListener();
    public StudentMarksListAdapter(Context ctx, List<StudentItem> studentItems) {
        this.ctx = ctx;
        this.studentItems = studentItems;
        Iterator<StudentItem> it = studentItems.iterator();
        while (it.hasNext()) {
            marksList.add(it.next().getMarks());
        }
    }

    public boolean getSaved(){
        if(saved) return true;
        if(warnOneDone) {
            return true;
        } else {
            this.warnOneDone = true;
            return false;
        }
    }

    public void setSaved(boolean saved){
        this.warnOneDone = false;
        this.saved = saved;
    }

    public List<StudentItem> getStudentMarksList(){
        return studentItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(ctx).inflate(R.layout.row_marks_update_student_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.toggle.setChecked(studentItem.getPresent());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        StudentItem studentItem = studentItems.get(position);
        Log.d("yes", "marksList: " + marksList.toString());
        holder.studentMarks.clearTextChangedListeners();
        studentItem.setMarks(marksList.get(position));
        holder.studentMarks.addTextChangedListener(new MarksChangedListener(holder.studentMarks, studentItems, position));
        holder.studentMarks.setText(marksList.get(position));
//        if(!studentItem.getIsEditable().equals("true")) holder.studentMarks.setEnabled(false);
        holder.studentRoll.setText(studentItem.getCode());
        holder.studentName.setText(studentItem.getFullName());
        holder.itemView.setTag(studentItem);
        holder.pos = position;
        holder.studentMarks.setTag(new MyTag(position + "", studentItem.getMarks().toString()));
        Log.d("yes", "onBindViewHolder: " + position + "----" + position);
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//  implements View.OnClickListener {
        public MaterialRippleLayout studentCont;
//        public TextView studentId;
        public TextView studentName;
        public TextView studentRoll;
        public ExtendedEditText studentMarks;
        public int pos;

        public ViewHolder(View itemView) {
            super(itemView);
            studentCont = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_parent);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            studentRoll = (TextView) itemView.findViewById(R.id.studentRoll);
            studentMarks = (ExtendedEditText) itemView.findViewById(R.id.studentMarks);
        }

    }

    public class MarksChangedListener implements TextWatcher {

        private String text = "";
        public EditText studentMarks;
        int pos;
        List<StudentItem> studentItemss;
            public MarksChangedListener(EditText studentMarks, List<StudentItem> studentItems, int pos) {
            this.studentMarks = studentMarks;
            this.studentItemss = studentItems;
                this.pos = pos;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            text = s.toString();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            setSaved(false);
            MyTag myTag = (MyTag)studentMarks.getTag();
            if(myTag != null) {
                Log.d("yes", "onBindViewHolder: position:" + pos + "----" +  pos);
                StudentItem studentItem = studentItems.get(pos);
                Log.d("yes", "onTextChanged: " + "CharSequence " + s +"," + " int " +start+", int " + before + ", int " + count);
                try {
                    if (Integer.parseInt(s.toString()) > Integer.parseInt(studentItem.getMaxMarks())) {
                        Toast.makeText(ctx, "Maximum marks is " + studentItem.getMaxMarks(), Toast.LENGTH_LONG).show();
                        studentMarks.setText(text);
                        studentItem.setMarks(text);
                        marksList.set(pos, text);
                        Log.d("yes", "onTextChanged: marksList:" + marksList.toString());
                    } else {
                        studentItem.setMarks(s.toString());
                        marksList.set(pos, s.toString());
                        Log.d("yes", "onTextChanged: marksList:" + marksList.toString());
                        Log.d("yes", "onTextChanged: setMarks + getMarks: " + studentItem.getMarks());
                    }

                    Log.d("yes", "onTextChanged: getMarks: " + studentItem.getMarks() + " " + studentItemss.get(pos).getMarks());
                } catch (Exception e) {
                    studentItem.setMarks("");
                    marksList.set(pos, "");
                }
                studentItems = studentItemss;
                Log.d("yes", "onTextChanged: getMarks: " + studentItem.getMarks() + " " + studentItems.get(pos).getMarks());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }

    public class MyTag
    {
        String  position;
        String  marks;

        public MyTag()
        {
            position=null;
        }

        public MyTag(String position, String marks)
        {
            this.position=position;
            this.marks = marks;
        }

    }


}
