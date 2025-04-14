package vn.tayjava.controller.response;

public class ResponseError extends ResponseData<Object> {

    public ResponseError(int status, String message) {
        super(status, message);
    }
}
