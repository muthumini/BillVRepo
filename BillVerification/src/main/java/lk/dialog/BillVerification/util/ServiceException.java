package lk.dialog.BillVerification.util;

/**
 * @author Sasini_08765
 */

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ServiceException(String message, Object...args){
		super((args.length > 0) ? ServiceException.generateMessage(message, args) : message);
	}
	public ServiceException(String message, Throwable cause, Object...args){
		super((args.length > 0) ? ServiceException.generateMessage(message, args) : message, cause);

	}
	public ServiceException(Throwable cause){
		super(cause);
	}
	
	private static String generateMessage(String message, Object...args){
		int index = 0;
		while(message.contains("{}") && index < args.length){
			message = message.replaceFirst("\\{\\}", String.valueOf(args[index++]));
		}
		return message;
	}
	
}
