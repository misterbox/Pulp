package com.theskyegriffin.pulp.ynab;

public class ResponseData<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
