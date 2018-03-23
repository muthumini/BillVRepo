package lk.dialog.BillVerification.rest.resources;

/**
 * @author Sasini_08765
 */

public class CustomMessageResource {

	private String message;

	public CustomMessageResource(String message){
		this.message = message;
	}

	public CustomMessageResource(String message, String... args) {
		try {
			this.message = processMsg(message, args);
		} catch (Exception e) {
			this.message = e.getMessage();
		}
	}
	private String processMsg(String message, final String...args) throws Exception{
		int index = 0;
		while(message.contains("{}")){
			message = message.replaceFirst("\\{\\}", args[index++]);
		}
		return message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
