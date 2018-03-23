package lk.dialog.BillVerification.service;

import lk.dialog.BillVerification.model.Invoice;
import lk.dialog.BillVerification.util.ServiceException;

public class SLAService {
    public boolean calculateSLA(Invoice invoice) {
        boolean slaAchieved = false;
        long diff = invoice.getVerified().getTime() - invoice.getReceived().getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        try {
            if (!invoice.getSla().getAfter5pm()) {
                if (invoice.getSla().getSlaType().equals("normal")) {
                    if (invoice.getSla().getNoOfHolidays() == 0) {
                        if (diffDays <= 5) {
                            slaAchieved = true;
                        }
                    } else {
                        if (diffDays <= 5 + invoice.getSla().getNoOfHolidays()) {
                            slaAchieved = true;
                        }
                    }
                } else if (invoice.getSla().getSlaType().equals("urgent")) {
                    if (invoice.getSla().getNoOfHolidays() == 0) {
                        if (diffDays <= 1) {
                            slaAchieved = true;
                        }
                    } else {
                        if (diffDays <= 1 + invoice.getSla().getNoOfHolidays()) {
                            slaAchieved = true;
                        }
                    }
                }
            } else {
                if (invoice.getSla().getSlaType().equals("normal")) {
                    if (invoice.getSla().getNoOfHolidays() == 0) {
                        if ((diffDays - 1) <= 5) {
                            slaAchieved = true;
                        }
                    } else {
                        if ((diffDays - 1) <= 5 + invoice.getSla().getNoOfHolidays()) {
                            slaAchieved = true;
                        }
                    }
                } else if (invoice.getSla().getSlaType().equals("urgent")) {
                    if (invoice.getSla().getNoOfHolidays() == 0) {
                        if ((diffDays - 1) <= 1) {
                            slaAchieved = true;
                        }
                    } else {
                        if ((diffDays - 1) <= 1 + invoice.getSla().getNoOfHolidays()) {
                            slaAchieved = true;
                        }
                    }
                }
            }
            return slaAchieved;
        } catch (Exception e) {
            throw new ServiceException("SLA calculation failed");
        }
    }
}

