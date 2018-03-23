package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class PasswordResetResource {
    private String oldPwd;
    private String newPwd;

    public PasswordResetResource() {}

    public PasswordResetResource(String oldPassword, String newPassword) {
        this.oldPwd = oldPassword;
        this.newPwd = newPassword;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
