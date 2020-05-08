package in.neuw.example.utils.constants;

import in.neuw.example.utils.constants.SystemType;
import org.springframework.http.HttpStatus;

public enum ErrorCodes {

    BAD_REQUEST(400, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED),
    NOT_FOUND(404, HttpStatus.NOT_FOUND),
    CONFLICT(409, HttpStatus.CONFLICT),
    UNPROCESSABLE_ENTITY(422, HttpStatus.UNPROCESSABLE_ENTITY),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private HttpStatus status;
    private SystemType systemType = SystemType.APIS;

    ErrorCodes(int code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    ErrorCodes(int code, HttpStatus status, SystemType systemType) {
        this.code = status.value();
        this.status = status;
        this.systemType = systemType;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public SystemType getSystemType() {
        return systemType;
    }
}
