import java.util.List;

public class Behavior {

    private List<PlayView.Environments> environments;
    private Action.Actions action;

    public Behavior(List<PlayView.Environments> environments, Action.Actions action){
        this.environments = environments;
        this.action = action;
    }

    public List<PlayView.Environments> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<PlayView.Environments> environments) {
        this.environments = environments;
    }

    public Action.Actions getAction() {
        return action;
    }

    public void setAction(Action.Actions action) {
        this.action = action;
    }

    public boolean isInCurrentEnvironment(Memory memory, String team, char side){
        PlayView playView = new PlayView(memory, team, side);
        boolean[] environs = new boolean[environments.size()];
        int i = 0;

        for(PlayView.Environments environment : environments){
            switch (environment){
                case HAS_BALL:
                    environs[i] = playView.hasBall();
                    break;
                case CAN_SEE_BALL:
                    environs[i] = playView.canSeeBall();
                    break;
                case FAR_FROM_GOAL:
                    environs[i] = playView.farFromGoal();
                    break;
                case BALL_NOT_VISIBLE:
                    environs[i] = !playView.canSeeBall();
                    break;
                case CAN_SEE_GOAL:
                    environs[i] = playView.canSeeGoal();
                    break;
                case CAN_SEE_TEAM_MATE:
                    environs[i] = playView.canSeeTeamMate();
                    break;
                case TEAM_MATE_HAS_BALL:
                    environs[i] = playView.teamMateHasBall();
                    break;
                case FACING_MY_GOAL:
                    environs[i] = playView.facingMyGoal();
                    break;
            }
            i++;
        }

        return SoccerUtil.areAllTrue(environs);
    }

    public void performAction(Action todo){

        switch (action){
            case PASS_BALL:
                todo.passBall();
                break;
            case LOOK_AROUND:
                todo.lookAround();
                break;
            case DASH_TOWARDS_BALL:
                todo.dashTowardsBall();
                break;
            case DASH_TOWARDS_GOAL:
                todo.dashTowardsGoal();
                break;
            case KICK_TOWARDS_GOAL:
                todo.kickTowardsGoal();
                break;
            case DO_NOTHING:
                break;
        }

    }
}
