package fr.dawan.project1.dto;

import java.io.Serializable;

public class ErrorLogDto implements Serializable {

    private int errorCode;
    private String message;
    private String path;

    public enum LogLevel { INFO, WARN, ERROR, DEBUG }
    private LogLevel level;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }
}
