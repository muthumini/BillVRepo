package lk.dialog.BillVerification.rest.resources;

import java.util.List;

public class PaginatedVoiceDataResource {
    private Long total;
    private List<VoiceDataResource> voiceData;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<VoiceDataResource> getVoiceData() {
        return voiceData;
    }

    public void setVoiceData(List<VoiceDataResource> voiceData) {
        this.voiceData = voiceData;
    }
}
