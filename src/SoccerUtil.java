public class SoccerUtil {
    private SoccerUtil(){
        //do not initialize...
    }
    public static ObjectInfo getCurrentGoal(Memory memory, char side){
        if (side == Constants.LEFT){
            return memory.getObject(Constants.GOAL_RIGHT);
        } else {
            return memory.getObject(Constants.GOAL_LEFT);
        }
    }

    public static boolean areAllTrue(boolean[] array)
    {
        for(boolean b : array){
            if(!b) {
                return false;
            }
        }
        return true;
    }
}
