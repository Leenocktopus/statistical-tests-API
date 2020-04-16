package randombits.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

/**
 * @author Alexey Raichev
 * @since 15.04.2020
 * Main focus of this class is providing a convenient data structure for errors
 * that have to be converted to JSON.
 */
public class ErrorEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private
    ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorEntity(ZonedDateTime dateTime, int status, String error, String message, String path) {
        this.setTimestamp(dateTime);
        this.setStatus(status);
        this.setError(error);
        this.setMessage(message);
        this.setPath(path);
    }

    public ErrorEntity() {
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
