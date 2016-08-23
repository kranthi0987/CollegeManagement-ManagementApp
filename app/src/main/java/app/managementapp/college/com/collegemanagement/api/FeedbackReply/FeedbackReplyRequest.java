
package app.managementapp.college.com.collegemanagement.api.FeedbackReply;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class FeedbackReplyRequest implements Parcelable {

    public static final Creator<FeedbackReplyRequest> CREATOR = new Creator<FeedbackReplyRequest>() {
        @Override
        public FeedbackReplyRequest createFromParcel(Parcel in) {
            return new FeedbackReplyRequest(in);
        }

        @Override
        public FeedbackReplyRequest[] newArray(int size) {
            return new FeedbackReplyRequest[size];
        }
    };
    private Integer refID;
    private String reply;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public FeedbackReplyRequest() {

    }

    public FeedbackReplyRequest(Parcel in) {
        reply = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reply);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 
     * @return
     *     The refID
     */
    public Integer getRefID() {
        return refID;
    }

    /**
     * 
     * @param refID
     *     The RefID
     */
    public void setRefID(Integer refID) {
        this.refID = refID;
    }

    /**
     * 
     * @return
     *     The reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * 
     * @param reply
     *     The Reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
