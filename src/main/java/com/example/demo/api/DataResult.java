package com.example.demo.api;

public class DataResult<T>  {

    private T data;
    public DataResult(T data, boolean success, String message) {
        this.data = data;
    }

    public DataResult(T data ) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }





}