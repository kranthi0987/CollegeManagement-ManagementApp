package app.managementapp.college.com.collegemanagement.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by new on 5/17/2016.
 */
public class ErrorToaster {
    public boolean toastError(Exception error, Context ctx) {
        if(error != null) {
            Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_LONG).show();
//            error = null;
            return true;
        }
        return false;
    }
}
