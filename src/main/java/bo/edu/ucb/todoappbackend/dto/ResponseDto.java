package bo.edu.ucb.todoappbackend.dto;

public class ResponseDto<T> { // respuesta genérica para cualquier invocación que se haga
    private String code;
    private T response;
    private String errorMessage;

    // Se borra el constructor por defecto, porque así se obliga al programador a utilizar
    // únicamente los constructores definidos posteriormente.
    // public ResponseDto() {}

    /** Utilizar este constructor cuando no hay error.
     * @param response
     */
    public ResponseDto(T response) {
        this.code = "TODO-0000";
        this.response = response;
    }

    /** Utilizar este constructor cuando hay error.
     * Agregar el código de error y el mensaje de error.
     * Procurar que el código de error sea único.
     * @param code
     * @param errorMessage
     */
    public ResponseDto(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", response=" + response +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
