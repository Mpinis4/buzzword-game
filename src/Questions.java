import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
/**This class parses the questions from a file and stores them temporary in the memory
* It is in charge of initializing the list with the questions, pull a question and reseting the question list
*
* @author Gkevork Sogkogian
* @version 2020.11.18
*/
public class Questions {
    /** Array that stores the questions */
    private ArrayList<Question> questionList;
    /**Used question array for a Game,it stores the index of the used question  */
    private ArrayList<Integer> usedQuestions;

/**   This constructor initializes the Question list and a list with the indexes of the used questions */
    public Questions(){
        questionList = new ArrayList<>();
        usedQuestions = new ArrayList<>();
    }
/** This method reads all the available questions from a "Questions.txt"
 * and stores them in an Arraylist , it checks the file parser, and the lenght of the splitted string
 * in case of errors on the file
 * The questions are stored in a format (Category|Question|answer1|..|answer4|correct answer)
 * and the get splitted using the regex "|"
 *
 * * @param questionReader is the file reader
 * * @param str stores the splited file
 *  */
    public void initialize(){
        BufferedReader questionReader;
        try{
            questionReader = new BufferedReader(new FileReader("Questions.txt"));
            String line = questionReader.readLine();
            String[] str;
            while(line != null && !line.isEmpty()){
                str = line.split("\\|");
                if (str.length == 7) {
                    questionList.add(new Question(str));
                }
                line = questionReader.readLine();
            }
            questionReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
/** This Method pulls a random question that has not been used, it shuffles the possible answers and add it to the used questions
  * ThreadLocalRandom is used in order get a random number
  *
  * @return an unused question
 */
    public Question getAQuestion(){
        int i;
        do {
            i = ThreadLocalRandom.current().nextInt(0, questionList.size() );
        }while (questionList.get(i).getUsed());
        usedQuestions.add(i);
        questionList.get(i).setUsed(true);
        questionList.get(i).shuffleAnswers();
        return questionList.get(i);
    }
/**This method resets all the questions so they can be used again in a new game  */
    public void resetQuestions(){
        if (!usedQuestions.isEmpty() ) {
            for (Integer usedQuestion : usedQuestions) {
                questionList.get(usedQuestion).setUsed(false);
            }
        }
    }
}
