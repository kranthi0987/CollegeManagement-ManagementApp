
package app.managementapp.college.com.collegemanagement.api.FeePaymentDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("BankName")
    @Expose
    private Object bankName;
    @SerializedName("FeeName")
    @Expose
    private String feeName;
    @SerializedName("PaidAmount")
    @Expose
    private Integer paidAmount;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("PaymentModeWithDocNo")
    @Expose
    private String paymentModeWithDocNo;
    @SerializedName("Receipt")
    @Expose
    private String receipt;
    @SerializedName("RefundAmount")
    @Expose
    private Integer refundAmount;
    @SerializedName("Sem")
    @Expose
    private String sem;

    /**
     * 
     * @return
     *     The bankName
     */
    public Object getBankName() {
        return bankName;
    }

    /**
     * 
     * @param bankName
     *     The BankName
     */
    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    /**
     * 
     * @return
     *     The feeName
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 
     * @param feeName
     *     The FeeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 
     * @return
     *     The paidAmount
     */
    public Integer getPaidAmount() {
        return paidAmount;
    }

    /**
     * 
     * @param paidAmount
     *     The PaidAmount
     */
    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    /**
     * 
     * @return
     *     The paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * 
     * @param paymentDate
     *     The PaymentDate
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 
     * @return
     *     The paymentModeWithDocNo
     */
    public String getPaymentModeWithDocNo() {
        return paymentModeWithDocNo;
    }

    /**
     * 
     * @param paymentModeWithDocNo
     *     The PaymentModeWithDocNo
     */
    public void setPaymentModeWithDocNo(String paymentModeWithDocNo) {
        this.paymentModeWithDocNo = paymentModeWithDocNo;
    }

    /**
     * 
     * @return
     *     The receipt
     */
    public String getReceipt() {
        return receipt;
    }

    /**
     * 
     * @param receipt
     *     The Receipt
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * 
     * @return
     *     The refundAmount
     */
    public Integer getRefundAmount() {
        return refundAmount;
    }

    /**
     * 
     * @param refundAmount
     *     The RefundAmount
     */
    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 
     * @return
     *     The sem
     */
    public String getSem() {
        return sem;
    }

    /**
     * 
     * @param sem
     *     The Sem
     */
    public void setSem(String sem) {
        this.sem = sem;
    }

}
