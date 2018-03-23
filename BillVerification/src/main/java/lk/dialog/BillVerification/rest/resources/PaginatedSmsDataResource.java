package lk.dialog.BillVerification.rest.resources;

import java.util.List;

public class PaginatedSmsDataResource {
    private Long total;
    private List<SmsDataResource> smsData;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<SmsDataResource> getSmsData() {
        return smsData;
    }

    public void setSmsData(List<SmsDataResource> smsData) {
        this.smsData = smsData;
    }
}

