public class Action {

    String team;

    public Action(String team) {
        this.team = team;
    }

    public void lookAround(SendCommand actor, Memory memory) {
        // If you don't know where is ball then find it
        actor.turn(40);
        memory.waitForNewInfo();
    }

    public void dashTowardsBall(SendCommand actor, Memory memory) {
        ObjectInfo ball = memory.getObject(Constants.BALL);
        PlayerInfo player = (PlayerInfo)memory.getObject(Constants.PLAYER);
        if (ball.direction != 0) {
            actor.turn(ball.direction);
        } else {
            if (!(player != null && player.getTeamName().equals(team) && player.distance <= 6)){
                actor.dash(10 * ball.distance);
            }
        }
    }

    public void kickTowardsGoal(SendCommand actor, Memory memory, char side) {
        actor.kick(100, PlayView.getCurrentGoal(memory, side).direction);
    }

    public void passBall(SendCommand actor, Memory memory) {
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);

        if (player.direction != 0) {
            actor.turn(player.direction);
        } else {
            actor.kick(5 * player.distance, player.direction);
        }

    }

    public void dashTowardsGoal(SendCommand actor, Memory memory, char side){
        ObjectInfo goal = PlayView.getCurrentGoal(memory, side);
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (goal.direction != 0){
            actor.turn(goal.direction);
        } else {
            if (PlayView.hasBall(memory)){
                actor.kick(20, goal.direction);
            } else if (ball != null){
                dashTowardsBall(actor,memory);
            }
        }
    }

}
