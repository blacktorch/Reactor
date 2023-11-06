//
//	File:			Brain.java
//	Author:		Krzysztof Langner
//	Date:			1997/04/28
//
//    Modified by:	Paul Marlow

//    Modified by:      Edgar Acosta
//    Date:             March 4, 2008

import java.lang.Math;
import java.util.List;
import java.util.regex.*;

class Brain extends Thread implements SensorInput {

    //===========================================================================
    // Private members
    private SendCommand reactor;          // robot which is controled by this brain
    private Memory memory;                // place where all information is stored
    private char side;
    volatile private boolean timeOver;
    private String playMode;
    private int number;
    private String team;
    private Action action;
    private List<Behavior> behaviors;

    //---------------------------------------------------------------------------
    // This constructor:
    // - stores connection to reactor
    // - starts thread for this object
    public Brain(SendCommand reactor, String team, char side, int number, String playMode, List<Behavior> behaviors) {
        timeOver = false;
        this.reactor = reactor;
        memory = new Memory();
        this.team = team;
        this.side = side;
        this.number = number;
        this.playMode = playMode;
        action = new Action(this.reactor,this.memory,this.team,this.side);
        this.behaviors = behaviors;
        start();
    }


    public void run() {
        // first put it somewhere on my side
        if (Pattern.matches("^before_kick_off.*", playMode))
            reactor.move(-Math.random() * 52.5, 34 - Math.random() * 68.0);

        while (!timeOver) {

            for (Behavior behavior : behaviors){
                if (behavior.isInCurrentEnvironment(memory, team, side)){
                    behavior.performAction(action);
                }
            }

            // sleep one step to ensure that we will not send
            // two commands in one cycle.
            try {
                Thread.sleep(2 * SoccerParams.simulator_step);
            } catch (Exception e) {
            }
        }
        reactor.bye();
    }


    //===========================================================================
    // Here are suporting functions for implement logic


    //===========================================================================
    // Implementation of SensorInput Interface

    //---------------------------------------------------------------------------
    // This function sends see information
    public void see(VisualInfo info) {
        memory.store(info);
    }


    //---------------------------------------------------------------------------
    // This function receives hear information from player
    public void hear(int time, int direction, String message) {
    }

    //---------------------------------------------------------------------------
    // This function receives hear information from referee
    public void hear(int time, String message) {
        if (message.compareTo("time_over") == 0)
            timeOver = true;

    }

}
