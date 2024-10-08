import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;



public class QuestionTest extends TestCase  {
    public void testQuestion(){
        String[] a ={"Math","5+5=?" ,"2" ,"5", "10", "7", "10"};
        Question b = new Question(a);
        assertEquals(true,b.isCorrect("10"));
        assertEquals(false,b.isCorrect("5"));


    }
}
