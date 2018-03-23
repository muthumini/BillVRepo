package lk.dialog.BillVerification.rest.resources;

import lk.dialog.BillVerification.model.SLA;

public class SLAResource {

    private Long id;
    private String slaType;
    private int noOfHolidays;
    private boolean after5pm;
    private boolean slaAchieved;

    public SLAResource(){}

    public SLAResource(SLA sla){
        this.id = sla.getId();
        this.slaType = sla.getSlaType();
        this.noOfHolidays = sla.getNoOfHolidays();
        this.after5pm = sla.getAfter5pm();
        this.slaAchieved = sla.getSlaAchieved();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlaType() {
        return slaType;
    }

    public void setSlaType(String slaType) {
        this.slaType = slaType;
    }

    public int getNoOfHolidays() {
        return noOfHolidays;
    }

    public void setNoOfHolidays(int noOfHolidays) {
        this.noOfHolidays = noOfHolidays;
    }

    public boolean getAfter5pm() {
        return after5pm;
    }

    public void setAfter5pm(boolean after5pm) {
        this.after5pm = after5pm;
    }


    public SLA toSLA() {
        return toSLA(new SLA());
    }

    public SLA toSLA(SLA sla) {
        sla.setId(id);
        sla.setSlaType(slaType);
        sla.setAfter5pm(after5pm);
        sla.setNoOfHolidays(noOfHolidays);
        sla.setSlaAchieved(slaAchieved);

        return sla;
    }

}
