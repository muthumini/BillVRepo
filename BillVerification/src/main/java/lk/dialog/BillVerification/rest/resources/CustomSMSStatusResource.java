package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class CustomSMSStatusResource {

    private String status;
    private double smsPercentage, costPercentage;
    private double smsDiff, costDiff;

    public CustomSMSStatusResource(){ }

    public CustomSMSStatusResource(String status, double smsPercentage, double costPercentage, double smsDiff, double costDiff) {
        this.status = status;
        this.smsPercentage = smsPercentage;
        this.costPercentage = costPercentage;
        this.smsDiff = smsDiff;
        this.costDiff = costDiff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSmsPercentage() {
        return smsPercentage;
    }

    public void setSmsPercentage(double smsPercentage) {
        this.smsPercentage = smsPercentage;
    }

    public double getCostPercentage() {
        return costPercentage;
    }

    public void setCostPercentage(double costPercentage) {
        this.costPercentage = costPercentage;
    }

    public double getSmsDiff() {
        return smsDiff;
    }

    public void setSmsDiff(double smsDiff) {
        this.smsDiff = smsDiff;
    }

    public double getCostDiff() {
        return costDiff;
    }

    public void setCostDiff(double costDiff) {
        this.costDiff = costDiff;
    }
}
