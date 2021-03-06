package com.example.backend;


import com.example.backend.p2p.event.P2PStartEvent;
import com.example.backend.p2p.state.P2PState;
import com.example.backend.p2p.view.P2PView;
import com.example.backend.simulator.EventQueue;
import com.example.backend.simulator.Simulator;
import com.example.backend.simulator.StopEvent;

import java.nio.file.Paths;

public class Simulation {
    public static void main(String[] args) {
        int nrOfNodes = 5;
        double TIME_SIM_STOP = 0.5;
        long TIME_SEED = 1234; 
        double ARRIVAL_SPEED = 32;
        System.out.println(Paths.get("simulations").toString());
        P2PView view = new P2PView(Paths.get("simulations").toString(),nrOfNodes);
        EventQueue eventQueue = new EventQueue();
        P2PState state = new P2PState(ARRIVAL_SPEED, TIME_SEED, nrOfNodes,eventQueue, TIME_SIM_STOP);

        //Create and add events
        eventQueue.addEvent(new P2PStartEvent(state));
        eventQueue.addEvent(new StopEvent(state, TIME_SIM_STOP));
        
        //Add Observer
        state.addObserver(view);

        //Run Simulator
        new Simulator(state, eventQueue).run();

    }
}