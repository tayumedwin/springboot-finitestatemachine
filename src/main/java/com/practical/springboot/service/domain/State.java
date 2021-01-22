package com.practical.springboot.service.domain;

public enum State {
    UNALLOCATED ("Unallocated"),
    ALLOCATED ("Allocated"),
    STATEA ("StateA"),
    STATEB ("StateB"),
    COMPLETED ("Completed"),
    DELETED ("Deleted");

    private final String stateName;

    State(String stateName){
        this.stateName = stateName;
    }

    public String getStateName(){
        return this.stateName;
    }
}
