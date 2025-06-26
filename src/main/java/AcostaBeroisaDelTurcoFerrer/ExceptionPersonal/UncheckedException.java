package AcostaBeroisaDelTurcoFerrer.ExceptionPersonal;

public class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String atributo;

    public UncheckedException(String message) {
        super(message);
    }
    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UncheckedException(String message, String atributo) {
        super(message);
        this.atributo = atributo;
    }
    public UncheckedException(String message, String atributo, Throwable cause) {
        super(message, cause);
        this.atributo = atributo;
    }


     public String getAtributo() {
        return atributo;
    }
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

}
