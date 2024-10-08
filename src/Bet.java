
/**This class implements the Bet round type
 * it gives the bet amount if the answer is correct
 * and -1* bet if its wrong
 *
 *@author Gkevork Sogkogian
 *@version 2020.11.18
 * */
public class Bet extends Lap {

    public int correctAnswer(boolean answer,int special) {

        if(answer){
            return   special;
        }
        else {
            return -1 * special;
        }
    }

}
