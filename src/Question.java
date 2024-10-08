import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
/**This class represents a question, each question has a list with the question and the possible answers
 * a correct answer stored seperetly , the category, and a boolean that marks then when they get used during a game
*
* @author Gkevork Sogkogian
* @version 2020.11.18
 */


public class Question {

    private List<String> question;
    private String correctAnswer;
    private boolean used;
    private boolean hasPic;
    private String category;
    private String picLocation;

/**This constructor takes a question with the possible answers and puts them in a list
* while it seperates the correct answer and the category to different variables
* The index [0] is the question , [1-4] are the possible answers and [5] is the picture location in case there is a picture
*
* @param question a{@code String[]}  contains the Category, the question, and the possible answers
 */
    public Question(String[] question){
        category = question[0];
        correctAnswer = question[6];
        this.question = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            this.question.add(question[i]);
        }
        if(question.length == 7){
            hasPic = true;
            picLocation=question[6];
        }
        used = false;
    }
    /** Getters for the question */
    public String getCorrectAnswer(){return correctAnswer;}
    public String getCategory(){
        return category;
    }
    public List<String> getQuestion(){
        return question;
    }
    public boolean getUsed(){
        return used;
    }
    public boolean getHasPic(){return hasPic;}
    public String getPicLocation(){return picLocation;}

    /** Changes the state of a question to either used or not used
   *  @param a is the new state of the question  */
    public void setUsed(boolean a){
        used = a;
    }

    // boolean function that compares the given answer with the correct answer
    public boolean isCorrect(String answer){
        return correctAnswer.equals(answer);
    }


    /** Shuffles the possible answers to avoid the appearance in the same order
     * Uses a randomly generated int to perform swaps in the List */
    public void shuffleAnswers(){
        int rand= ThreadLocalRandom.current().nextInt(1,question.size() );
        for(int i=1;i<= 4;i++){
            Collections.swap(question , question.size()-i , rand);
            rand = ThreadLocalRandom.current().nextInt(1,question.size());
        }
    }



}

