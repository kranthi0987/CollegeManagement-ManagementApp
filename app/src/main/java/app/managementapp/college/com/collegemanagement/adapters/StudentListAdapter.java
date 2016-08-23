package app.managementapp.college.com.collegemanagement.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.model.MenuItem;
import app.managementapp.college.com.collegemanagement.model.StudentItem;

/**
 * Created by new on 4/13/2016.
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder>{


    private List<StudentItem> studentItems = new ArrayList<>();
    private Context ctx;
    private ViewGroup parent;
    boolean saved = true;
    boolean warnOneDone = true;

    public StudentListAdapter(Context ctx, List<StudentItem> studentItems) {
        this.ctx = ctx;
        this.studentItems = studentItems;
    }
    public List<StudentItem> getStudentList(){
        return studentItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(ctx).inflate(R.layout.row_attandance_student_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.toggle.setChecked(studentItem.getPresent());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentItem studentItem = studentItems.get(position);
        holder.studentRoll.setText(studentItem.getCode());
        holder.studentName.setText(studentItem.getFullName());
//        holder.studentId.setText((position + 1) + "");
        holder.toggle.setTextOn(studentItem.getFullName().substring(0,1));
        holder.toggle.setTextOff(studentItem.getFullName().substring(0,1));
        holder.toggle.setChecked(studentItem.getPresent());
//        if(studentItem.getPresent()) {
//            holder.present.setVisibility(View.VISIBLE);
//            holder.abscent.setVisibility(View.INVISIBLE);
//        } else {
//            holder.present.setVisibility(View.INVISIBLE);
//            holder.abscent.setVisibility(View.VISIBLE);
//        }
        holder.studentCont.setOnClickListener(new StudentOnClickListener(position));
        holder.itemView.setTag(studentItems.get(position));
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
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

    public class ViewHolder extends RecyclerView.ViewHolder {//  implements View.OnClickListener {
        public MaterialRippleLayout studentCont;
//        public TextView studentId;
        public TextView studentName;
        public TextView studentRoll;
//        public ImageView present;
//        public ImageView abscent;
        public ToggleButton toggle;

        public ViewHolder(View itemView) {
            super(itemView);

            studentCont = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_parent);
//            studentId = (TextView) itemView.findViewById(R.id.studentId);
            studentName = (TextView) itemView.findViewById(R.id.studentName);
            studentRoll = (TextView) itemView.findViewById(R.id.studentRoll);
            toggle = (ToggleButton) itemView.findViewById(R.id.toggle);
//            present = (ImageView) itemView.findViewById(R.id.present);
//            abscent = (ImageView) itemView.findViewById(R.id.abscent);
        }

       /* @Override
        public void onClick(View v) {
            Log.d("yyyy", "onClick: clickeddd" );
            icon.setImageResource(R.drawable.ic_abscent);
        }*/
    }

    public class  StudentOnClickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        private StudentItem studentItem;
        private int position;
        public StudentOnClickListener(int position){
            this.studentItem = studentItems.get(position);
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            setSaved(false);
            Log.d(DEBUG_TAG, "onClick: StudentOnClickListener ");
//            ImageView toggle = (ImageView) v.findViewById(R.id.icon);
            ToggleButton toggle = (ToggleButton) v.findViewById(R.id.toggle);

//            ImageView present = (ImageView) v.findViewById(R.id.present);
//            ImageView abscent = (ImageView) v.findViewById(R.id.abscent);
//            if(studentItem.getPresent()) {
//                present.setVisibility(View.VISIBLE);
//                abscent.setVisibility(View.INVISIBLE);
//            } else {
//                present.setVisibility(View.INVISIBLE);
//                abscent.setVisibility(View.VISIBLE);
//            }
            toggle.setChecked(!studentItem.getPresent());
            studentItem.setPresent(!studentItem.getPresent());
//            studentItems.add(position, studentItem);
        }
    };
}
