public class PlayView {
    private PlayView(){
        //
    }

    public static boolean canSeeBall(Memory memory){
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (ball == null){
            return false;
        } else {
            return true;
        }
    }

    public static boolean hasBall(Memory memory){
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (ball.distance > 1){
            return false;
        } else {
            return true;
        }
    }

    public static boolean canSeeGoal(Memory memory, char side){
        if (getCurrentGoal(memory, side) == null){
            return false;
        } else {
            return true;
        }
    }

    public static ObjectInfo getCurrentGoal(Memory memory, char side){
        if (side == Constants.LEFT){
            return memory.getObject(Constants.GOAL_RIGHT);
        } else {
            return memory.getObject(Constants.GOAL_LEFT);
        }
    }

    public static boolean canSeeTeamMate(Memory memory, String team) {
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);
        if (player != null && player.getTeamName().equals(team)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean teamMateHasBall(Memory memory, String team){
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (player != null && ball != null && player.getTeamName().equals(team)) {
            if ((player.distance - ball.distance) >= -5 && (player.distance - ball.distance) <= 5){
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public static boolean farFromGoal(Memory memory, char side){
        ObjectInfo goal = getCurrentGoal(memory, side);
        if ( goal != null && goal.distance >= 28){
            return true;
        } else {
            return false;
        }
    }
}
