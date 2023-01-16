package mx.com.mundodafne.ssh.almacen.app.exceptions;

public class BusinessException {
    private String message;
    private Throwable throwable;
    public BusinessException(String msg, Throwable throwable){
        this.message = msg;
        this.throwable = throwable;
    }

    public BusinessException(String msg){
        this.message = msg;
    }
}
