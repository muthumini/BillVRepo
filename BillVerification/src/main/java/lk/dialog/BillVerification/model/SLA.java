package lk.dialog.BillVerification.model;

import javax.persistence.*;

/**
 * @author Sasini_08765
 */

@Entity
@Table(name = "User_SLA")
public class SLA {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SLA_Type")
    String slaType;
    @Column(name = "No_of_Holidays")
    int noOfHolidays;
    @Column(name = "After5pm")
    boolean after5pm;
    @Column(name = "SLA_Achieved")
    boolean slaAchieved;

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

    public boolean getSlaAchieved() {
        return slaAchieved;
    }

    public void setSlaAchieved(boolean slaAchieved) {
        this.slaAchieved = slaAchieved;
    }
}
