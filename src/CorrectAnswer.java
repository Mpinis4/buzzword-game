/*This class represents the correct answer lap type
*@author Gkevork Sogkogian
* @version 2020.11.18 */
public class CorrectAnswer extends Lap {

    public int correctAnswer(boolean answer,int special) {
        if(answer)
            return 1000;
        else
            return 0;

    }


}
