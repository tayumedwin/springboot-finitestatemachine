package com.practical.springboot.service.domain;

public enum Status {
    NEW ("NEW"),
    ACTIVE ("ACTIVE"),
    INACTIVE ("INACTIVE");

    private final String status;

    Status(String status){
        this.status = status;
    }

    public String getStatusName(){
        return this.status;
    }
}
