/**This class represents the Stop the Clock lap type
 * special is the remaining time and it returns the remaining time* 0.2
 *
 * @author Gkevork Sogkogian
 * @version 2020.01.15*/

public class StopTheClock extends Lap {



    public int correctAnswer(boolean answer,int special) {
        double points;
        if(answer) {
            points = special * 0.2;
            return (int) points;
        }
        else
            return 0;
    }

}
