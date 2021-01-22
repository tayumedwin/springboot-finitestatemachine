package com.practical.springboot.service.domain;

public enum Event {
    EVENT_A ("A"),
    EVENT_B ("B"),
    EVENT_C ("C"),
    EVENT_D ("D"),
    EVENT_E ("E"),
    EVENT_F ("F");

    private final String eventName;

    Event(String eventName){
        this.eventName = eventName;
    }

    public String getEventName(){
        return this.eventName;
    }
}
