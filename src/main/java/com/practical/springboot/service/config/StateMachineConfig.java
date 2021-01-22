package com.practical.springboot.service.config;

import com.practical.springboot.service.domain.Event;
import com.practical.springboot.service.domain.State;
import com.practical.springboot.service.fsm.StateMachineListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {

        states
                .withStates()
                .initial(State.UNALLOCATED.getStateName())
                .end(State.COMPLETED.getStateName())
                .states(new HashSet<String>(
                        Arrays.asList(State.UNALLOCATED.getStateName(),
                                State.ALLOCATED.getStateName(),
                                State.STATEA.getStateName(),
                                State.STATEB.getStateName(),
                                State.COMPLETED.getStateName(),
                                State.DELETED.getStateName()))
                );

    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {

        transitions.withExternal()
                .source(State.UNALLOCATED.getStateName()).target(State.ALLOCATED.getStateName()).event(Event.EVENT_A.getEventName()).and()
                .withExternal()
                .source(State.ALLOCATED.getStateName()).target(State.STATEA.getStateName()).event(Event.EVENT_B.getEventName()).and()
                .withExternal()
                .source(State.STATEA.getStateName()).target(State.STATEB.getStateName()).event(Event.EVENT_C.getEventName());
    }
}
