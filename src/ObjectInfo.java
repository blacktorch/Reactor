//
//	File:			ObjectInfo.java
//	Author:		Krzysztof Langner
//	Date:			1997/04/28

//  Modified by:  Paul Marlow, Amir Ghavam, Yoga Selvaraj
//  Course:       Software Agents
//  Date Due:     November 30, 2000

//  Modified by:  Edgar Acosta
//  Date:         March 4, 2008

//***************************************************************************
//
//	This is base class for different classese with visual information
//	about objects
//
//***************************************************************************

class ObjectInfo
{
  public String type;
  public float distance;
  public float direction;
  public float distChange;
  public float dirChange;

  //===========================================================================
  // Initialization member functions
  public ObjectInfo(String type)
  {
    this.type = type;
  }

  public float getDistance()
  {
    return distance;
  }

  public float getDirection()
  {
    return direction;
  }

  public float getDistChange()
  {
    return distChange;
  }

  public float getDirChange()
  {
    return dirChange;
  }

  public String getType()
  {
    return type;
  }
}


//***************************************************************************
//
//	This class holds visual information about player
//
//***************************************************************************
class PlayerInfo extends ObjectInfo
{
  private String teamName = "";
  private int uniformName = 0;        // recognise 0 as not being able to see number
  float bodyDir;
  float headDir;
  private boolean goalie = false;

  //===========================================================================
  // Initialization member functions
  public PlayerInfo()
  {
    super("player");
  }

  public PlayerInfo(String team, int number, boolean is_goalie)
  {
    super("player");
    teamName = team;
    uniformName = number;
    goalie = is_goalie;
    bodyDir = 0;
    headDir = 0;
  }

  public PlayerInfo(String team, int number, float bodyDir, float headDir)
  {
    super("player");
    teamName = team;
    uniformName = number;
    this.bodyDir = bodyDir;
    this.headDir = headDir;
  }

  public String getTeamName()
  {
    return teamName;
  }

  public void setGoalie(boolean goalie)
  {
    this.goalie = goalie;
  }

  public boolean isGoalie()
  {
    return goalie;
  }

  public int getTeamNumber()
  {
    return uniformName;
  }
}


//***************************************************************************
//
//	This class holds visual information about goal
//
//***************************************************************************
class GoalInfo extends ObjectInfo
{
  private char side;
  //===========================================================================
  // Initialization member functions
  public GoalInfo()
  {
    super("goal");
    side = ' ';
  }

  public GoalInfo(char side)
  {
    super("goal " + side);
    this.side = side;
  }

  public char getSide()
  {
    return side;
  }
}


//***************************************************************************
//
//	This class holds visual information about ball
//
//***************************************************************************
class BallInfo extends ObjectInfo
{
  //===========================================================================
  // Initialization member functions
  public BallInfo()
  {
    super(Constants.BALL);
  }
}


//***************************************************************************
//
//	This class holds visual information about flag
//
//***************************************************************************
class FlagInfo extends ObjectInfo
{
  char m_type;  // p|g
  char m_pos1;  // t|b|l|c|r
  char m_pos2;  // l|r|t|c|b
  int m_num;    // 0|10|20|30|40|50
  boolean m_out;

  //===========================================================================
  // Initialization member functions
  public FlagInfo()
  {
    super(Constants.FLAG);
    m_type = ' ';
    m_pos1 = ' ';
    m_pos2 = ' ';
    m_num = 0;
    m_out = false;
  }

  public FlagInfo(String flagType, char type, char pos1, char pos2,
                  int num, boolean out)
  {
    super(flagType);
    m_type = type;
    m_pos1 = pos1;
    m_pos2 = pos2;
    m_num = num;
    m_out = out;
  }

  public FlagInfo(char type, char pos1, char pos2, int num, boolean out)
  {
    super(Constants.FLAG);
    m_type = type;
    m_pos1 = pos1;
    m_pos2 = pos2;
    m_num = num;
    m_out = out;
  }
}


//***************************************************************************
//
//	This class holds visual information about line
//
//***************************************************************************
class LineInfo extends ObjectInfo
{
  char m_kind;  // l|r|t|b

  //===========================================================================
  // Initialization member functions
  public LineInfo()
  {
    super(Constants.LINE);
  }

  public LineInfo(char kind)
  {
    super(Constants.LINE);
    m_kind = kind;
  }
}