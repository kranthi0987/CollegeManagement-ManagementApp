
package app.managementapp.college.com.collegemanagement.api.FeedbackReply;

import java.util.HashMap;
import java.util.Map;
public class FeedbackReplyRequest {

    private Integer refID;
    private String reply;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
