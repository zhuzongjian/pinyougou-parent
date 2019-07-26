package entity;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private Boolean success;
    private String message;

    @Override
    public String toString() {
        return "{\"ResonseResult\":{"
                + "\"success\":"
                + success
                + ",\"message\":\""
                + message + '\"'
                + "}}";
    }

    public ResponseResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
