package bo.edu.ucb.todoappbackend.dto;

public class ResponseDto { // respuesta genérica a cualquier invocación que se haga
    private String code;
    private Object result;
    private String message;

    // Se borra el constructor por defecto, porque así se obliga al programador a utilizar
    // únicamente los constructores definidos posteriormente.
    // public ResponseDto() {}

    /** Utilizar este constructor cuando no hay error.
     * @param result
     */
    public ResponseDto(Object result) {
        this.code = "TODO-0000";
        this.result = result;
    }

    /** Utilizar este constructor cuando hay error.
     * Agregar el código de error y el mensaje de error.
     * Procurar que el código de error sea único.
     * @param code
     * @param message
     */
    public ResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
