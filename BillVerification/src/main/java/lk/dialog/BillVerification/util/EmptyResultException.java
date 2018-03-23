package lk.dialog.BillVerification.util;

public class EmptyResultException extends RuntimeException {

    public EmptyResultException(String message, Object...args){
        super((args.length > 0) ? EmptyResultException.generateMessage(message, args) : message);
    }
    public EmptyResultException(String message, Throwable cause, Object...args){
        super((args.length > 0) ? EmptyResultException.generateMessage(message, args) : message, cause);

    }
    public EmptyResultException(Throwable cause){
        super(cause);
    }

    public EmptyResultException(String message){
        super(message);
    }

    private static String generateMessage(String message, Object...args){
        int index = 0;
        while(message.contains("{}") && index < args.length){
            message = message.replaceFirst("\\{\\}", String.valueOf(args[index++]));
        }
        return message;
    }

}

