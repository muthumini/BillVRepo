package lk.dialog.BillVerification.rest.resources;

public class DocumentIdResource {

    String documentId;

    public DocumentIdResource(String docId){
        this.documentId = docId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
