package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class CustomVoiceStatusResource {

    private String status;
    private double timeDiff, costDiff, noOfCallsDiff, timePercentage, costPercentage, callsDiffPercentage;


    public CustomVoiceStatusResource(String status, double timeDiff, double costDiff, double noOfCallsDiff,
                                     double timePercentage, double costPercentage, double callsDiffPercentage){

        this.status = status;
        this.timeDiff = timeDiff;
        this.costDiff = costDiff;
        this.noOfCallsDiff = noOfCallsDiff;
        this.timePercentage = timePercentage;
        this.costPercentage = costPercentage;
        this.callsDiffPercentage = callsDiffPercentage;
    }

    public CustomVoiceStatusResource(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(double timeDiff) {
        this.timeDiff = timeDiff;
    }

    public double getCostDiff() {
        return costDiff;
    }

    public void setCostDiff(double costDiff) {
        this.costDiff = costDiff;
    }

    public double getNoOfCallsDiff() {
        return noOfCallsDiff;
    }

    public void setNoOfCallsDiff(double noOfCallsDiff) {
        this.noOfCallsDiff = noOfCallsDiff;
    }

    public double getTimePercentage() {
        return timePercentage;
    }

    public void setTimePercentage(double timePercentage) {
        this.timePercentage = timePercentage;
    }

    public double getCostPercentage() {
        return costPercentage;
    }

    public void setCostPercentage(double costPercentage) {
        this.costPercentage = costPercentage;
    }

    public double getCallsDiffPercentage() {
        return callsDiffPercentage;
    }

    public void setCallsDiffPercentage(double callsDiffPercentage) {
        this.callsDiffPercentage = callsDiffPercentage;
    }
}
