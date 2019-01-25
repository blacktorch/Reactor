public class PlayView {
    private Memory memory;
    private String team;
    private char side;

    public PlayView(Memory memory, String team, char side){
        this.memory = memory;
        this.team = team;
        this.side = side;
    }

    public boolean canSeeBall(){
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (ball == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean hasBall(){
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (ball != null){
            if (ball.distance > 1){
                return false;
            } else {
                return true;
            }
        }
       return false;
    }

    public boolean canSeeGoal(){
        if (SoccerUtil.getOpponentsGoal(memory, side) == null){
            return false;
        } else {
            return true;
        }
    }


    public boolean canSeeTeamMate() {
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);
        if (player != null && player.getTeamName().equals(team)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean teamMateHasBall(){
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (player != null && ball != null && player.getTeamName().equals(team)) {
            if ((player.distance - ball.distance) >= -4 && (player.distance - ball.distance) <= 4){
                return true;
            }
        }
        return false;
    }

    public boolean farFromGoal(){
        ObjectInfo goal = SoccerUtil.getOpponentsGoal(memory, side);
        if ( goal != null && goal.distance >= 20){
            return true;
        } else {
            return false;
        }
    }

    public boolean facingMyGoal(){
        ObjectInfo myGoal = SoccerUtil.getMyGoal(memory, side);
        if (myGoal != null){
            return true;
        } else {
            return false;
        }
    }

    public enum Environments {
        BALL_NOT_VISIBLE,
        CAN_SEE_BALL,
        CAN_SEE_GOAL,
        CAN_SEE_TEAM_MATE,
        FAR_FROM_GOAL,
        HAS_BALL,
        TEAM_MATE_HAS_BALL,
        FACING_MY_GOAL
    }
}
