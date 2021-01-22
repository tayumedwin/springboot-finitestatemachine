package com.practical.springboot.service.fsm;

import com.practical.springboot.service.core.DefaultJobService;
import java.util.logging.Logger;


import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class StateMachineListener extends StateMachineListenerAdapter {
    private static final Logger LOG = Logger.getLogger(StateMachineListener.class.getName());

    @Override
    public void stateChanged(State from, State to) {
        LOG.info(() -> String.format("Transitioned from %s to %s%n", from == null ? "none" : from.getId(), to.getId()));
    }
}
