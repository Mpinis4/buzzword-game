


/**This Class is part of the game BUZZ!Quiz world.
 *<b>It is initializes used to initialize and store every player and his/her score.</b>
 *Every object created from this class represents a player of the game.
 *Also has methods which help the program.
 *
 * @author Charalampos Xanthopoulos
 * @version 2020.11.17
 *
 *
 */

public class Player {

    private final String name;
    private int points;
    private int wins;
    private int highscore;
    private int correctAnswers;

    /**
     * This constructor sets Players name which is the only way to set it and initialize his\her points to 0.
     *
     * @param name a {@code String} which contains the players name given from the method startingGame of the Class Game.
     */

    public Player(String name) {
        this.name = name;
        points = 0;
        wins = 0;
        highscore = 0;
    }

    /**
     * This Method returns the points of the Object-player.
     *
     * @return points is the points of the player
     */

    public int getPoints() {
        return points;
    }

    /**
     * This Method returns the name of the player.
     *
     * @return name of the player.
     */

    public String getName() {
        return name;
    }
    public int getHighscore(){return highscore;}
    public int getWins(){return wins;}

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * This Method is used to add a certain amount of points to the players score
     *
     * @param addedPoints {@code int} is the points added to player.
     */

    public void addPoints(int addedPoints) {
        points += addedPoints;
    }

    public void addCorrectAnswers() {
        correctAnswers += 1;
    }

    public void resetCorrectAnswers() {
        correctAnswers = 0;
    }

    public void setWins(int wins){
        this.wins = wins;
    }
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
    public void increaseWins(){
        wins++;
    }
}
