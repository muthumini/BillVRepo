package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class SmsVerifyResource {

    private String eventType;
    private String operatorCode;
    private long noOfSmsOfOperator;
    private double smsCostOfOperator;
    private long noOfSmsOfDialog;
    private double smsCostOfDialog;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public long getNoOfSmsOfOperator() {
        return noOfSmsOfOperator;
    }

    public void setNoOfSmsOfOperator(long noOfSmsOfOperator) {
        this.noOfSmsOfOperator = noOfSmsOfOperator;
    }

    public double getSmsCostOfOperator() {
        return smsCostOfOperator;
    }

    public void setSmsCostOfOperator(double smsCostOfOperator) {
        this.smsCostOfOperator = smsCostOfOperator;
    }

    public long getNoOfSmsOfDialog() {
        return noOfSmsOfDialog;
    }

    public void setNoOfSmsOfDialog(long noOfSmsOfDialog) {
        this.noOfSmsOfDialog = noOfSmsOfDialog;
    }

    public double getSmsCostOfDialog() {
        return smsCostOfDialog;
    }

    public void setSmsCostOfDialog(double smsCostOfDialog) {
        this.smsCostOfDialog = smsCostOfDialog;
    }
}
