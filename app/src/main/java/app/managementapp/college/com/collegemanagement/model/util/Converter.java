package app.managementapp.college.com.collegemanagement.model.util;

import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.model.ExternalExamItem;
import app.managementapp.college.com.collegemanagement.model.Faculty;
import app.managementapp.college.com.collegemanagement.model.InternalExamItem;
import app.managementapp.college.com.collegemanagement.model.InternalInvigilationItem;

/**
 * Created by new on 4/21/2016.
 */
public class Converter {

    private static final String DEBUG_TAG = "Converter";

    DateFormat keyFormatter = new SimpleDateFormat("dd-MM-yyyy");
    public Map<Date, List<InternalInvigilationItem>> convertInternalInvigilationItems(String json) {

        Map<Date, List<InternalInvigilationItem>> finalData = new LinkedHashMap<Date, List<InternalInvigilationItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(json);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                String s = data.getString("ExamDate").substring(6, data.getString("ExamDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                List<InternalInvigilationItem> InternalInvigilationItemList = new ArrayList<>();
                if(finalData.size() == 0 && !finalData.containsKey(d)) {
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(d, InternalInvigilationItemList);
                } else if(finalData.containsKey(d)){
                    InternalInvigilationItemList = finalData.get(d);
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(d, InternalInvigilationItemList);
                }
                Log.d(DEBUG_TAG, "dates: " + d);
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        return finalData;
    }
    public Map<String, List<InternalInvigilationItem>> convertInternalInvigilationItemsString(String json) {

        Map<String, List<InternalInvigilationItem>> finalData = new LinkedHashMap<String, List<InternalInvigilationItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(json);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                String s = data.getString("ExamDate").substring(6, data.getString("ExamDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String month = "" + (cal.get(Calendar.MONTH));
                String ds = keyFormatter.format(cal.getTime());
                List<InternalInvigilationItem> InternalInvigilationItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                } else if(!finalData.containsKey(ds)){
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                }
                else if(finalData.containsKey(ds)){
                    InternalInvigilationItemList = finalData.get(ds);
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                }
                Log.d(DEBUG_TAG, "dates: " + d + "  ---- " + ds);
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        return finalData;
    }

    public Map<String, List<InternalInvigilationItem>> convertInvigilationSingleItemsString(String json) {

        Map<String, List<InternalInvigilationItem>> finalData = new LinkedHashMap<String, List<InternalInvigilationItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(json);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String ds = "0";
                List<InternalInvigilationItem> InternalInvigilationItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                } else if(!finalData.containsKey(ds)){
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                }
                else if(finalData.containsKey(ds)){
                    InternalInvigilationItemList = finalData.get(ds);
                    InternalInvigilationItemList.add(convertInternalInvigilationItem(data));
                    finalData.put(ds, InternalInvigilationItemList);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        return finalData;
    }

    public InternalInvigilationItem convertInternalInvigilationItem(JSONObject item) {
        InternalInvigilationItem internalInvigilationItem = new InternalInvigilationItem();
        Date d = new Date();
        String intentClassDate = "";
        String intentStartTime = "";
        String intentEndTime = "";
        try {
            String s = "";
            if (item.getString("EndDate").indexOf("+") > 0) {
                s = item.getString("EndDate").substring(6, item.getString("EndDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                d = new Date(l);
                Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + item.getString("EndDate") + "----"+ s);
                intentEndTime = getInFormat(d,
                        new SimpleDateFormat("HH:mm:ss"));
            }
            if (item.getString("StartDate").indexOf("+") > 0) {
                s = item.getString("StartDate").substring(6, item.getString("StartDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                d = new Date(l);
                intentStartTime = getInFormat(d,
                        new SimpleDateFormat("HH:mm:ss"));
            }
            if (item.getString("ClassDate").indexOf("+") > 0) {
                s = item.getString("ClassDate").substring(6, item.getString("ClassDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                d = new Date(l);
                intentClassDate = getInFormat(d,
                        new SimpleDateFormat("dd/MM/yyyy"));
            }


            s = item.getString("ExamDate").substring(6, item.getString("ExamDate").length() - 2);
            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
            long l = Long.parseLong(s);
            d = new Date(l);
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        try {
            internalInvigilationItem = new InternalInvigilationItem(d, item.getString("ExamName"), item.getString("RoomCode"), item.getString("RoomDescription"),
                    item.getString("RoomID"), item.getString("Session"), item.getString("SessionID"), item.getString("Subjects"),
                    intentClassDate, intentStartTime, intentEndTime
            );
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        return internalInvigilationItem;
    }


    public String invigilationDetailstoJson(List<InternalInvigilationItem> duties) {
        Iterator<InternalInvigilationItem> it = duties.iterator();
        String send = "{'DataList':[";
        boolean firstIt = false;
        while (it.hasNext()){
            if(firstIt) send += ",";
            firstIt = true;
            InternalInvigilationItem internalInvigilationItem = it.next();
            Log.d("yes", "coverter: " + internalInvigilationItem.getExamName());
            send += "{'ExamDate':'" + internalInvigilationItem.getExamDate();
            send += "','ExamName':'" + internalInvigilationItem.getExamName();
            send += "','RoomCode':'" + internalInvigilationItem.getRoomCode();
            send += "','RoomDescription':'" + internalInvigilationItem.getRoomDescription();
            send += "','RoomID':'" + internalInvigilationItem.getRoomID();
            send += "','Session':'" + internalInvigilationItem.getSession();
            send += "','SessionID':'" + internalInvigilationItem.getSessionID();
            send += "','Subjects':'" + internalInvigilationItem.getSubjects() + "'}";
        }
        send += "]}";
        return send;
    }

    public Map<String, List<ExternalExamItem>> convertExternalExamsItemsString(String result) {

        Map<String, List<ExternalExamItem>> finalData = new LinkedHashMap<String, List<ExternalExamItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {


                JSONObject data = (JSONObject) dataList.get(i);
                Log.d(DEBUG_TAG, "convertExternalExamsItemsString: yesss " + data);
                String s = data.getString("ScheduleDate").substring(6, data.getString("ScheduleDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String month = "" + (cal.get(Calendar.MONTH));
                String ds = keyFormatter.format(cal.getTime());

                List<ExternalExamItem> ExternalExamItem = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    ExternalExamItem.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItem);
                } else if(!finalData.containsKey(ds)){
                    ExternalExamItem.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItem);
                }
                else if(finalData.containsKey(ds)){
                    ExternalExamItem = finalData.get(ds);
                    ExternalExamItem.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItem);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        Log.d(DEBUG_TAG, "convertInternalInvigilationItems: finalData " + finalData);
        return finalData;
    }

    private ExternalExamItem convertExternalExamItem(JSONObject item) {
        ExternalExamItem externalExamItem = new ExternalExamItem();
        String EndTime = "";
        Date ScheduleDate = new Date();
        String StartTime = "";
        String s = "";
        long l;
        try {
            if(item.getString("EndTime").length() > 6){
                s = item.getString("EndTime").substring(6, item.getString("EndTime").length() - 2);
                if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
                l = Long.parseLong(s);
                EndTime = new Date(l).getHours() + ":" + new Date(l).getMinutes() ;
                Log.d(DEBUG_TAG, "convertExternalExamItem: " + EndTime);
            } else{
                EndTime = item.getString("EndTime");
                Log.d(DEBUG_TAG, "convertExternalExamItem: else " + EndTime);
            }

            if(item.getString("StartTime").length() > 6) {
                s = item.getString("StartTime").substring(6, item.getString("StartTime").length() - 2);
                if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
                l = Long.parseLong(s);
                StartTime = new Date(l).getHours() + ":" + new Date(l).getMinutes();
                Log.d(DEBUG_TAG, "convertExternalExamItem: " + StartTime);
            } else {
                StartTime = item.getString("StartTime");
            }

            s = item.getString("ScheduleDate").substring(6, item.getString("ScheduleDate").length() - 2);
            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
            l = Long.parseLong(s);
            ScheduleDate = new Date(l);

        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        try {
            Log.d(DEBUG_TAG, "convertExternalExamItem: yesss " + EndTime + " ------ " + StartTime );
            externalExamItem = new ExternalExamItem(EndTime, item.getString("ExamID"), item.getString("ExamName"), ScheduleDate, item.getString("Section"), item.getString("Semester"), StartTime, item.getString("SubjectCode"), item.getString("SubjectName") );
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        return externalExamItem;
    }

    public String externalExamItemstoJson(List<ExternalExamItem> duties) {

        Iterator<ExternalExamItem> it = duties.iterator();
        String send = "{'DataList':[";
        boolean firstIt = false;
        while (it.hasNext()){
            if(firstIt) send += ",";
            firstIt = true;
            ExternalExamItem externalExamItem = it.next();
            Log.d("yes", "coverter: " + externalExamItem.getExamName());
            send += "{'EndTime':'" + externalExamItem.getEndTime();
            send += "','ExamID':'" + externalExamItem.getExamID();
            send += "','ExamName':'" + externalExamItem.getExamName();
            send += "','ScheduleDate':'" + externalExamItem.getScheduleDate();
            send += "','Section':'" + externalExamItem.getSection();
            send += "','Semester':'" + externalExamItem.getSemester();
            send += "','StartTime':'" + externalExamItem.getStartTime();
            send += "','SubjectCode':'" + externalExamItem.getSubjectCode();
            send += "','SubjectName':'" + externalExamItem.getSubjectName() + "'}";
        }
        send += "]}";

        Log.d(DEBUG_TAG, "externalExamItemstoJson: " + send);
        return send;
    }

    public Map<String, List<ExternalExamItem>> convertExternalExamSingleItemsString(String extrs) {

        Map<String, List<ExternalExamItem>> finalData = new LinkedHashMap<String, List<ExternalExamItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(extrs);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String ds = "0";
                List<ExternalExamItem> ExternalExamItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    ExternalExamItemList.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItemList);
                } else if(!finalData.containsKey(ds)){
                    ExternalExamItemList.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItemList);
                }
                else if(finalData.containsKey(ds)){
                    ExternalExamItemList = finalData.get(ds);
                    ExternalExamItemList.add(convertExternalExamItem(data));
                    finalData.put(ds, ExternalExamItemList);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertExternalExamSingleItemsString: " + e);
        }
        return finalData;
    }

    public Map<String, List<InternalExamItem>> convertInternalExamsItemsString(String result) {

        Map<String, List<InternalExamItem>> finalData = new LinkedHashMap<String, List<InternalExamItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {


                JSONObject data = (JSONObject) dataList.get(i);
                Log.d(DEBUG_TAG, "convertInternalExamsItemsString: yesss " + data);
                String s = data.getString("ScheduleDate").substring(6, data.getString("ScheduleDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String month = "" + (cal.get(Calendar.MONTH));
                String ds = keyFormatter.format(cal.getTime());

                List<InternalExamItem> InternalExamItem = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    InternalExamItem.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItem);
                } else if(!finalData.containsKey(ds)){
                    InternalExamItem.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItem);
                }
                else if(finalData.containsKey(ds)){
                    InternalExamItem = finalData.get(ds);
                    InternalExamItem.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItem);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalExamsItemsString: " + e);
        }
        Log.d(DEBUG_TAG, "convertInternalExamsItemsString: finalData " + finalData);
        return finalData;
    }

    private InternalExamItem convertInternalExamItem(JSONObject item) {
        InternalExamItem internalExamItem = new InternalExamItem();
        String EndTime = "";
        Date ScheduleDate = new Date();
        String StartTime = "";
        String s = "";
        long l;
        try {
            if(item.getString("EndTime").length() > 6) {
                s = item.getString("EndTime").substring(6, item.getString("EndTime").length() - 2);
                if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
                l = Long.parseLong(s);
                EndTime = new Date(l).getHours() + ":" + new Date(l).getMinutes() ;
            } else {
                EndTime = item.getString("EndTime");
            }
            if(item.getString("StartTime").length() > 6) {
                s = item.getString("StartTime").substring(6, item.getString("StartTime").length() - 2);
                if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
                l = Long.parseLong(s);
                StartTime = new Date(l).getHours() + ":" + new Date(l).getMinutes();
            } else {
                StartTime = item.getString("StartTime");
            }
            s = item.getString("ScheduleDate").substring(6, item.getString("ScheduleDate").length() - 2);
            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
            l = Long.parseLong(s);
            ScheduleDate = new Date(l);


        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        try {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: yesss " + EndTime + " ------ " + StartTime );
            internalExamItem = new InternalExamItem(EndTime, item.getString("ExamID"), item.getString("ExamName"),
                    ScheduleDate, item.getString("Section"), item.getString("Semester"), StartTime,
                    item.getString("SubjectCode"), item.getString("SubjectName")
            );
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        return internalExamItem;
    }

    public String internalExamItemstoJson(List<InternalExamItem> duties) {

        Iterator<InternalExamItem> it = duties.iterator();
        String send = "{'DataList':[";
        boolean firstIt = false;
        while (it.hasNext()){
            if(firstIt) send += ",";
            firstIt = true;
            InternalExamItem internalExamItem = it.next();
            Log.d("yes", "coverter: " + internalExamItem.getExamName());
            send += "{'EndTime':'" + internalExamItem.getEndTime();
            send += "','ExamID':'" + internalExamItem.getExamID();
            send += "','ExamName':'" + internalExamItem.getExamName();
            send += "','ScheduleDate':'" + internalExamItem.getScheduleDate();
            send += "','Section':'" + internalExamItem.getSection();
            send += "','Semester':'" + internalExamItem.getSemester();
            send += "','StartTime':'" + internalExamItem.getStartTime();
            send += "','SubjectCode':'" + internalExamItem.getSubjectCode();
            send += "','SubjectName':'" + internalExamItem.getSubjectName() + "'}";
        }
        send += "]}";

        Log.d(DEBUG_TAG, "internalExamItemstoJson: " + send);
        return send;
    }

    public Map<String, List<InternalExamItem>> convertInternalExamSingleItemsString(String extrs) {

        Map<String, List<InternalExamItem>> finalData = new LinkedHashMap<String, List<InternalExamItem>>();;
        try {
            JSONObject resultJSON = new JSONObject(extrs);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String ds = "0";
                List<InternalExamItem> InternalExamItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    InternalExamItemList.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItemList);
                } else if(!finalData.containsKey(ds)){
                    InternalExamItemList.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItemList);
                }
                else if(finalData.containsKey(ds)){
                    InternalExamItemList = finalData.get(ds);
                    InternalExamItemList.add(convertInternalExamItem(data));
                    finalData.put(ds, InternalExamItemList);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalExamSingleItemsString: " + e);
        }
        return finalData;
    }


    public Map<String, List<ClassData>> convertClassDataItemsString(String result) {

        Map<String, List<ClassData>> finalData = new LinkedHashMap<String, List<ClassData>>();;
        String ds ="";
        try {
            JSONObject resultJSON = new JSONObject(result).getJSONObject("GetClassScheduleResult");
            Log.d(DEBUG_TAG, "convertClassDataItemsString: " + resultJSON);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {


                JSONObject data = (JSONObject) dataList.get(i);
                Log.d(DEBUG_TAG, "convertClassDataItemsString: yesss " + data);
                String s = data.getString("ClassDate").substring(6, data.getString("ClassDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String month = "" + (cal.get(Calendar.MONTH) + 1);
                Log.d(DEBUG_TAG, "convertClassDataItemsString: " + d + " -- " +  d.getMonth() + " ----- " + cal + " ------ " + cal.get(Calendar.MONTH));
                ds = keyFormatter.format(cal.getTime());

                List<ClassData> classDatas = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    classDatas.add(convertClassDataItem(data));
                    finalData.put(ds, classDatas);
                } else if(!finalData.containsKey(ds)){
                    classDatas.add(convertClassDataItem(data));
                    finalData.put(ds, classDatas);
                } else if(finalData.containsKey(ds)){
                    classDatas = finalData.get(ds);
                    classDatas.add(convertClassDataItem(data));
                    finalData.put(ds, classDatas);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        Log.d(DEBUG_TAG, "convertInternalInvigilationItems: finalData " + finalData.get(ds));
        return finalData;
    }

    private String getInFormat(Date date, DateFormat  formatter) {
        try{
            return formatter.format(date);
        } catch (Exception e){
            Log.d(DEBUG_TAG, "getInFormat: " + e);
        }
        return null;
    }

    private ClassData convertClassDataItem(JSONObject item) {
        ClassData classData = new ClassData();
        Date EndTime = new Date();
        Date ScheduleDate = new Date();
        Date StartTime = new Date();
        String intentClassDate = "";
        String intentStartTime = "";
        String intentEndTime = "";

        try {
//            String s = item.getString("EndTime").substring(6, item.getString("EndTime").length() - 2);
//            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
////            long l = Long.parseLong(s);
////            EndTime = new Date(l);
//
//            s = item.getString("StartTime").substring(6, item.getString("StartTime").length() - 2);
//            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
////            l = Long.parseLong(s);
////            StartTime = new Date(l);
//
//            s = item.getString("ScheduleDate").substring(6, item.getString("ScheduleDate").length() - 2);
//            if (s.indexOf("+") > 0) s = s.substring(0, s.indexOf("+"));
////            l = Long.parseLong(s);
////            ScheduleDate = new Date(l);
            String s = "";
            if (item.getString("EndDate").indexOf("+") > 0) {
                s = item.getString("EndDate").substring(6, item.getString("EndDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                Log.d(DEBUG_TAG, "convertInternalInvigilationItem: " + item.getString("EndDate") + "----"+ s);
                intentEndTime = getInFormat(d,
                        new SimpleDateFormat("HH:mm:ss"));
            }
            if (item.getString("StartDate").indexOf("+") > 0) {
                s = item.getString("StartDate").substring(6, item.getString("StartDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                intentStartTime = getInFormat(d,
                        new SimpleDateFormat("HH:mm:ss"));
            }
            if (item.getString("ClassDate").indexOf("+") > 0) {
                s = item.getString("ClassDate").substring(6, item.getString("ClassDate").length() - 2);
                if(s.indexOf("+") > 0) s = s.substring(0,s.indexOf("+"));
                long l = Long.parseLong(s);
                Date d = new Date(l);
                intentClassDate = getInFormat(d,
                        new SimpleDateFormat("dd/MM/yyyy"));
            }
            Log.d(DEBUG_TAG, "convertClassDataItem: " + intentClassDate + "---" + intentStartTime + "---" + intentEndTime);

        }
        catch (Exception e) {
            Log.e(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        try {
            Log.d(DEBUG_TAG, "convertExternalExamItem: yesss " + EndTime + " ------ " + StartTime );
            classData = new ClassData(
                    item.getString("AdTeacherID"), item.getString("ClassDate"), item.getString("ClassSchedID"), item.getString("Course"),
                    item.getString("EndDate"), item.getString("EndTime"), item.getString("Place"), item.getString("Section"),
                    item.getString("StartDate"), item.getString("StartTime"), item.getString("Title"),
                    item.getString("ClassDate"), intentStartTime, intentEndTime);
        } catch (Exception e) {
            Log.e(DEBUG_TAG, "convertInternalInvigilationItem: " + e);
        }
        Log.e(DEBUG_TAG, "success: " + classData);
        return classData;
    }


    public String internalClassDataItemstoJson(List<ClassData> duties) {

        Iterator<ClassData> it = duties.iterator();
        String send = "{'DataList':[";
        boolean firstIt = false;
        while (it.hasNext()){
            if(firstIt) send += ",";
            firstIt = true;
            ClassData classDataItem = it.next();
            Log.d("yes", "AdTeacherID: " + classDataItem.getAdTeacherID());
            send += "{'AdTeacherID':'" + classDataItem.getAdTeacherID();
            send += "','ClassDate':'" + classDataItem.getClassDate();
            send += "','ClassSchedID':'" + classDataItem.getClassSchedID();
            send += "','Course':'" + classDataItem.getCourse();
            send += "','EndDate':'" + classDataItem.getEndDate();
            send += "','EndTime':'" + classDataItem.getEndTime();
            send += "','Place':'" + classDataItem.getPlace();
            send += "','Section':'" + classDataItem.getSection();
            send += "','StartDate':'" + classDataItem.getStartDate();
            send += "','StartTime':'" + classDataItem.getStartTime();
            send += "','intentClassDate':'" + classDataItem.getIntentClassDate();
            send += "','intentStartTime':'" + classDataItem.getIntentStartTime();
            send += "','intentEndTime':'" + classDataItem.getIntentEndTime();
            send += "','Title':'" + classDataItem.getTitle() + "'}";
        }
        send += "]}";

        Log.d(DEBUG_TAG, "internalClassDataItemstoJson: " + send);
        return send;
    }

    public Map<String, List<ClassData>> convertClassDataSingleItemsString(String extrs) {

        Map<String, List<ClassData>> finalData = new LinkedHashMap<String, List<ClassData>>();;
        try {
            JSONObject resultJSON = new JSONObject(extrs);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String ds = "0";
                List<ClassData> ClassDataItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                } else if(!finalData.containsKey(ds)){
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                }
                else if(finalData.containsKey(ds)){
                    ClassDataItemList = finalData.get(ds);
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertExternalExamSingleItemsString: " + e);
        }
        return finalData;
    }


    public String[] getApproverArray(JSONArray dataList) {
        String[] str;
        str = new String[dataList.length()];
        try {
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                str[i] = data.getString("FirstName") + ", " + data.getString("Designation") + ", " +
                        data.getString("Department");
            }
        } catch (Exception e) {

        }
        return str;
    }


    public String[] getLeaveTypeArray(JSONArray dataList) {
        String[] str;
        str = new String[dataList.length()];
        try {
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                str[i] = data.getString("LeaveName") + ", Available:" + data.getString("AvailableLeaves");
            }
        } catch (Exception e) {

        }
        return str;
    }



    public Map<String, List<ClassData>> convertClassLeaveApplyItemsString(String extrs) {

        Map<String, List<ClassData>> finalData = new LinkedHashMap<String, List<ClassData>>();;
        try {
            JSONObject resultJSON = new JSONObject(extrs);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String ds = "0";
                List<ClassData> ClassDataItemList = new ArrayList<>();
                if(finalData.size() == 0 ) {
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                } else if(!finalData.containsKey(ds)){
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                }
                else if(finalData.containsKey(ds)){
                    ClassDataItemList = finalData.get(ds);
                    ClassDataItemList.add(convertClassDataItem(data));
                    finalData.put(ds, ClassDataItemList);
                }
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertExternalExamSingleItemsString: " + e);
        }
        return finalData;
    }

    public String getFromJSon(JSONObject jsonObject, String data){
        String temp = "";
        try {
            temp = jsonObject.getString(data);
            if(temp == "null") return "N/A";
            return temp;
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "getFromJSon: this is not present -->" + temp);
        }

        return null;
    }

    public List<Faculty> convertFacultyItemsString(String result) {

        List<Faculty> finalData = new ArrayList<Faculty>();
        try {
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String Code =  getFromJSon(data, "Code");
                String Department =  getFromJSon(data, "Department");
                String DepartmentID =  getFromJSon(data, "DepartmentID");
                String Designation =  getFromJSon(data, "Designation");
                String DesignationID =  getFromJSon(data, "DesignationID");
                String Email =  getFromJSon(data, "Email");
                String FirstName =  getFromJSon(data, "FirstName");
                String ID =  getFromJSon(data, "ID");
                String LastName =  getFromJSon(data, "LastName");
                String MGUID =  getFromJSon(data, "MGUID");
                String MiddleName =  getFromJSon(data, "MiddleName");
                String Phone =  getFromJSon(data, "Phone");
                finalData.add(new Faculty(Code, Department, DepartmentID, Designation, DesignationID, Email, FirstName, ID, LastName, MGUID, MiddleName, Phone));
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertInternalInvigilationItems: " + e);
        }
        Log.d(DEBUG_TAG, "convertInternalInvigilationItems: finalData " + finalData);
        return finalData;
    }

    public String[] convertFacultyArrayString(String result) {

        String[] str;
        try {
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            str = new String[dataList.length()];
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);

                String Code =  getFromJSon(data, "Code");
                String Department =  getFromJSon(data, "Department");
                String DepartmentID =  getFromJSon(data, "DepartmentID");
                String Designation =  getFromJSon(data, "Designation");
                String DesignationID =  getFromJSon(data, "DesignationID");
                String Email =  getFromJSon(data, "Email");
                String FirstName =  getFromJSon(data, "FirstName");
                String ID =  getFromJSon(data, "ID");
                String LastName =  getFromJSon(data, "LastName");
                String MGUID =  getFromJSon(data, "MGUID");
                String MiddleName =  getFromJSon(data, "MiddleName");
                String Phone =  getFromJSon(data, "Phone");
                str[i] = FirstName;
            }
            return str;
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "convertFacultyArrayString: " + e);
        }
        return null;
    }

}

