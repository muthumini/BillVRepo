package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class VoiceVerifyResource {
    private String operatorCode;
    private String eventType;

    private double noOfMinutesOfOperator;
    private double noOfMinutesOfDialog;

    private double voiceCostOfOperator;
    private double voiceCostOfDialog;

    private double numberOfCallsOfOperator;
    private double numberOfCallsOfDialog;

    public VoiceVerifyResource(){}


    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double getNoOfMinutesOfOperator() {
        return noOfMinutesOfOperator;
    }

    public void setNoOfMinutesOfOperator(double noOfMinutesOfOperator) {
        this.noOfMinutesOfOperator = noOfMinutesOfOperator;
    }

    public double getNoOfMinutesOfDialog() {
        return noOfMinutesOfDialog;
    }

    public void setNoOfMinutesOfDialog(double noOfMinutesOfDialog) {
        this.noOfMinutesOfDialog = noOfMinutesOfDialog;
    }

    public double getVoiceCostOfOperator() {
        return voiceCostOfOperator;
    }

    public void setVoiceCostOfOperator(double voiceCostOfOperator) {
        this.voiceCostOfOperator = voiceCostOfOperator;
    }

    public double getVoiceCostOfDialog() {
        return voiceCostOfDialog;
    }

    public void setVoiceCostOfDialog(double voiceCostOfDialog) {
        this.voiceCostOfDialog = voiceCostOfDialog;
    }

    public double getNumberOfCallsOfOperator() {
        return numberOfCallsOfOperator;
    }

    public void setNumberOfCallsOfOperator(double numberOfCallsOfOperator) {
        this.numberOfCallsOfOperator = numberOfCallsOfOperator;
    }

    public double getNumberOfCallsOfDialog() {
        return numberOfCallsOfDialog;
    }

    public void setNumberOfCallsOfDialog(double numberOfCallsOfDialog) {
        this.numberOfCallsOfDialog = numberOfCallsOfDialog;
    }
}
