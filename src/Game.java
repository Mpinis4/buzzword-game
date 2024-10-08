import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * <b>This Class is in control of the Game</b>
 *It is in charge of advancing the game when that is necessery
 * it picks a Lap type, pulls a question from the question list
 * Keeps track of the players points
 * and it reset's the
 *
 * @author Charalampos Xanthopoulos, Gkevork Sogkogian
 * @version 2020.01.15
 */

public class Game {
    private int lapNumber;
    private int questionNumber;
    private int currentQuestion, currentLap;
    private LapType lapType;
    //Random r =new Random();
    private Questions newSet;
    private Question eachQuestion;
    private ArrayList<Player> players;
    private Leaderboard leaderboard;

    public Game() {
        newSet = new Questions();
        players = new ArrayList<>();
        currentQuestion = 0;
        currentLap = 0;
        lapNumber = 2;
        questionNumber = 2;
        newSet.initialize();
        newLapType();
        leaderboard = new Leaderboard();
    }

    /**
     * This function chooses randomly the round type
     * sets a bound based on the player number so that the 2 player types don't get picked
     */
    private void newLapType() {
        int bound = 5;
        currentLap++;
        currentQuestion = 0;
        if (players.size() == 1) {
            bound = 3;
        }
        switch (new Random().nextInt(bound -1 ) +1 ) {
            case 1:
                lapType = LapType.CORRECT_ANSWER;
                break;
            case 2:
                lapType = LapType.BET;
                break;
            case 3:
                lapType = LapType.STOP_THE_CLOCK;
                break;
            case 4:
                lapType = LapType.THERMOMETER;
                break;
            case 5:
                lapType = LapType.QUICK_ANSWER;
                break;
        }
    }

    /**
     * This function is in charge of advancing the game
     * it checks if the conditions to advance at the next round are met
     * it chooses the next question, it calls the newLapType method to change the round type
     * there is a special condition for the Thermometer type since the win condition is met when a player reaches 5 right answers
     *
     * @return 0 in case there are no changes , 1 if there is a new round type , 2 if the game has reached the end
     */
    public int advanceGame(){
        int requiredAction = -1;
        if (currentQuestion == 0 && currentLap == 0){
            requiredAction = 1;
            newLapType();
            eachQuestion = newSet.getAQuestion();
            currentQuestion++;
        }
        else if (lapType == LapType.THERMOMETER && players.size() == 2) {
            if (players.get(0).getCorrectAnswers() == 5) {
                Player p = players.get(0);
                p.addPoints(5000);
                p.resetCorrectAnswers();
                players.set(0,p);
                requiredAction = 1;
                newLapType();
                eachQuestion = newSet.getAQuestion();
                currentQuestion++;
            }else if (players.get(1).getCorrectAnswers() == 5){
                Player p = players.get(1);
                p.addPoints(5000);
                p.resetCorrectAnswers();
                players.set(1,p);
                requiredAction = 1;
                newLapType();
                eachQuestion = newSet.getAQuestion();
                currentQuestion++;
            }
            else{
                requiredAction = 0;
                eachQuestion = newSet.getAQuestion();
                currentQuestion++;
            }
        }
        else if (currentQuestion< questionNumber && currentLap <= lapNumber){
            requiredAction = 0;
            eachQuestion = newSet.getAQuestion();
            currentQuestion++;
        }
        else if (currentQuestion == questionNumber && currentLap< lapNumber){
            requiredAction = 1;
            newLapType();
            eachQuestion = newSet.getAQuestion();
            currentQuestion++;
        }
        else if (currentQuestion == questionNumber && currentLap == lapNumber){
            requiredAction = 2;
            updateLeaderboard();
            leaderboard.updateFile();
        }

        return requiredAction;
    }


    /**
     * This functions counts the correct answers of the player for the Thermometer rounds
     */
    public void increaseCorrectAnswers(int i) {
        players.get(i).addCorrectAnswers();
    }

    /**This functions adds the players to the players class */
    public void addPlayers(ArrayList<String> names) {
        for (String readNames : names) {
            players.add(new Player(readNames));
        }
    }
    /**This function resets the game varieables so a new game can begin*/
    public void resetGame() {
        newSet.resetQuestions();
        currentLap = 0;
        currentQuestion = 0;
        players.clear();
    }

    public Question getQuestion() {
        return eachQuestion;
    }
    public LapType getLapType() {
        return lapType;
    }

    /**This function returns the points of a player given his name*/
    public int getPoints(String name) {
        int points=0;
        for (Player p : players) {
            if (p.getName().equals(name)) {
                points = p.getPoints();
                break;
            }
        }
        return  points;
    }


    /**
     * This Function gets the result of the answer and the player it add's or subtracts the points to the player depending the lap type
     * and updates the points total
     *
     * @return the points gained or lost in this round
     */
    public int giveAnswer(boolean answerResult, int playersIndex , int special) {
        int gainedPoints = 0;
        Player p = players.get(playersIndex);
        switch (lapType) {
            case CORRECT_ANSWER:
                gainedPoints = new CorrectAnswer().correctAnswer(answerResult, special);
                p.addPoints(gainedPoints);
                players.set(playersIndex,p);
                break;
                case BET:
                    gainedPoints = new Bet().correctAnswer(answerResult, special);
                    p.addPoints(gainedPoints);
                    players.set(playersIndex,p);
                    break;
                case STOP_THE_CLOCK:
                    gainedPoints = new StopTheClock().correctAnswer(answerResult, special);
                    p.addPoints(gainedPoints);
                    players.set(playersIndex,p);
                    break;
                    case QUICK_ANSWER:
                    gainedPoints = new QuickAnswer().correctAnswer(answerResult, special);
                    p.addPoints(gainedPoints);
                    players.set(playersIndex,p);
                    break;
                case THERMOMETER:
                    increaseCorrectAnswers(playersIndex);
                    break;
            }
        return gainedPoints;
    }
    /**this is used to update the leaderboard
     * if there are 2 players playing it updates the win records and then moves to the score update
     * */
    private void updateLeaderboard(){
        if(players.size() == 2) {
            if (players.get(0).getPoints() > players.get(1).getPoints()) {
                leaderboard.winUpdate(players.get(0));
            }
            else{
                leaderboard.winUpdate(players.get(1));
            }
        }
        for(Player p : players){
            leaderboard.scoreUpdate(p);
        }
    }

    /**Setters are used in test class*/
    public void setCurrentQuestion(int currentQuestion){
        this.currentQuestion = currentQuestion;
    }
    public void setCurrentLap(int currentLap){
        this.currentLap = currentLap;
    }
    public void setLapType(LapType lapType){
        this.lapType = lapType;
    }

    public ArrayList<Player> getLeaderboard(){return leaderboard.getPlayerList();}
}

