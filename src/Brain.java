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
        playView = new PlayView(this.memory, this.team, this.side);
        this.behaviors = behaviors;
        start();
    }


    //---------------------------------------------------------------------------
    // This is main brain function used to make decision
    // In each cycle we decide which command to issue based on
    // current situation. the rules are:
    //
    //	1. If you don't know where is ball then turn right and wait for new info
    //
    //	2. If ball is too far to kick it then
    //		2.1. If we are directed towards the ball then go to the ball
    //		2.2. else turn to the ball
    //
    //	3. If we dont know where is opponent goal then turn wait 
    //				and wait for new info
    //
    //	4. Kick ball
    //
    //	To ensure that we don't send commands to often after each cycle
    //	we waits one simulator steps. (This of course should be done better)

    // ***************  Improvements ******************
    // Allways know where the goal is.
    // Move to a place on my side on a kick_off
    // ************************************************

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



    //===========================================================================
    // Private members
    private SendCommand reactor;            // robot which is controled by this brain
    private Memory memory;                // place where all information is stored
    private char side;
    volatile private boolean timeOver;
    private String playMode;
    private int number;
    private String team;
    private Action action;
    private PlayView playView;
    private List<Behavior> behaviors;

}
