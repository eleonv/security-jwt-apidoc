package com.example.security04.entity;

public class ResponseApi<T> {
    private String status;  // 1:success 0:error
    //private String code;    // caso de error: codigo de error
    private String message; // caso de error: mensaje de error
    private T data;         // caso de exito: datos

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
