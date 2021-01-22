package com.practical.springboot.service.fsm;

import com.practical.springboot.service.config.StateMachineConfig;
import com.practical.springboot.service.core.DefaultJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccessor;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Collection;
import java.util.UUID;

public class DefaultStateMachine implements StateMachine {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultStateMachine.class);

    private final StateMachineConfig stateMachineConfig = new StateMachineConfig();

    @Override
    public State getInitialState() {
        return null;
    }

    @Override
    public ExtendedState getExtendedState() {
        return null;
    }

    @Override
    public StateMachineAccessor getStateMachineAccessor() {
        return null;
    }

    @Override
    public void setStateMachineError(Exception e) {

    }

    @Override
    public boolean hasStateMachineError() {
        return false;
    }

    @Override
    public UUID getUuid() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void start() {
        LOG.info("start");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean sendEvent(Message message) {
        return false;
    }

    @Override
    public boolean sendEvent(Object o) {
        return false;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public Collection<State> getStates() {
        return null;
    }

    @Override
    public Collection<Transition> getTransitions() {
        return null;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public void addStateListener(StateMachineListener stateMachineListener) {

    }

    @Override
    public void removeStateListener(StateMachineListener stateMachineListener) {

    }
}
