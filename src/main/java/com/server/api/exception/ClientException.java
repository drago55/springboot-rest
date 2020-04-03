package com.server.api.exception;

public class ClientException extends RuntimeException {

    private ApiError apiError;

    public ClientException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }

    public ClientException(String message, ApiError apiError) {
        super(apiError.getMessage() + " " + message);
        this.apiError = apiError;
    }

    public ClientException(String message, Throwable cause, ApiError apiError) {
        super(apiError.getMessage() + " " + message, cause);
        this.apiError = apiError;
    }

    public ClientException(Throwable cause, ApiError apiError) {
        super(apiError.getMessage(), cause);
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
