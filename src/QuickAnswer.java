/**This class represents the Quick Answer lap type, special marks if the player answered first or second
 *
 * @author Gkevork Sogkogian
 * @version 2020.01.15
 * */
public class QuickAnswer extends Lap {

    public int correctAnswer(boolean answer,int special){
        if(answer){
            if(special == 0)
                return 1000;
            else
                return 500;
        }else
            return 0;
    }

}
