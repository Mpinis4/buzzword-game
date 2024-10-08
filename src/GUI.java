/**This is the graphical user interface
 * @author Charalampos Xantopoulos
 * @date 15/1/2021
 */


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class GUI extends JFrame {
    private ArrayList<Player> Leaderboard;
    private ImageIcon icon;
    private JButton p1,p2,stat,controls;
    private JLabel label0,label1,label2,lblTime,lblImage,lblButtons;
    private JPanel startScreen;
    private JPanel StatControl;
    private JPanel actionPanel;
    private JPanel Pic;
    private JTextField name1,name2;
    private Question eachQuestion;
    Game aGame=new Game();
    private JButton bet1,bet2,bet3,bet4;
    private JButton answer1,answer2,answer3,answer4;
    private JButton Buzzer1,Buzzer2;
    ArrayList<String> names=new ArrayList<>();
    private int special;
    List<String> question;
    private Timer timer;
    private long startTime;
    private long duration=30000;
    private long clockTime;
    private int playerPlaying;
    private int playerRemain;
    private int[] scores={0,0};
    private int firstAnswer;

    GUI(){

        setTitle("Let's play Quizmo");
        setSize(900,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);


        label0 = new JLabel();
        label1=new JLabel();
        label2=new JLabel();
        lblTime=new JLabel();
        lblTime.setVisible(false);
        lblTime.setOpaque(true);
        lblImage=new JLabel();
        lblImage.setVisible(false);


        Buzzer1=new JButton("Player1");
        Buzzer2=new JButton("Player2");
        Buzzer1.setVisible(false);
        Buzzer2.setVisible(false);
        Buzzer2.setMnemonic(KeyEvent.VK_A);
        Buzzer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstAnswer=0;
                JOptionPane.showMessageDialog(null,names.get(firstAnswer)+" answered first",
                        "First to answer",JOptionPane.INFORMATION_MESSAGE);
                playerPlaying=1;
                Buzzer1.setEnabled(false);
                Buzzer2.setEnabled(false);
                AskQuestion();
            }
        });
        Buzzer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstAnswer=1;
                JOptionPane.showMessageDialog(null,names.get(firstAnswer)+" answered first",
                        "First to answer",JOptionPane.INFORMATION_MESSAGE);
                playerPlaying=2;
                Buzzer1.setEnabled(false);
                Buzzer2.setEnabled(false);
            }
        });

        answer1=new JButton();
        answer2=new JButton();
        answer3=new JButton();
        answer4=new JButton();
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
        answer1.setMnemonic(KeyEvent.VK_1);
        answer2.setMnemonic(KeyEvent.VK_2);
        answer3.setMnemonic(KeyEvent.VK_3);
        answer4.setMnemonic(KeyEvent.VK_4);
        answer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("answer1 given");
                GetAnswer(question.get(1));
            }
        });
        answer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("answer2 given");
                GetAnswer(question.get(2));
            }
        });
        answer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("answer3 given");
                GetAnswer(question.get(3));
            }
        });
        answer4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("answer4 given");
                GetAnswer(question.get(4));
            }
        });


        name1= new JTextField(20);
        name1.setVisible(false);
        name1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                names.add(name1.getText());
                aGame.addPlayers(names);
                name1.setVisible(false);
                aGame.advanceGame();
                LapPlay();

            }
        });
        name2=new JTextField(20);
        name2.setVisible(false);
        name2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                names.add((name2.getText()));
                name2.setVisible(false);
                label0.setText("Give 2nds player name");
                name1.setVisible(true);
            }
        });



        //bet buttons
        bet1=new JButton("250");
        bet2=new JButton("500");
        bet3=new JButton("750");
        bet4=new JButton("1000");
        bet1.setVisible(false);
        bet2.setVisible(false);
        bet3.setVisible(false);
        bet4.setVisible(false);
        bet1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setVisible(false);
                bet1.setVisible(false);
                bet2.setVisible(false);
                bet3.setVisible(false);
                bet4.setVisible(false);
                setSpecial(250);
                AskQuestion();
            }
        });
        bet2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setVisible(false);
                bet1.setVisible(false);
                bet2.setVisible(false);
                bet3.setVisible(false);
                bet4.setVisible(false);
                setSpecial(500);
                AskQuestion();

            }
        });
        bet3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setVisible(false);
                bet1.setVisible(false);
                bet2.setVisible(false);
                bet3.setVisible(false);
                bet4.setVisible(false);
                setSpecial(750);
                AskQuestion();
            }
        });
        bet4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setVisible(false);
                bet1.setVisible(false);
                bet2.setVisible(false);
                bet3.setVisible(false);
                bet4.setVisible(false);
                setSpecial(1000);
                AskQuestion();
            }
        });



        //Panels
        startScreen=new JPanel();
        startScreen.setLayout(new FlowLayout(FlowLayout.CENTER));
        startScreen.setBackground(Color.BLACK);
        add(startScreen,BorderLayout.PAGE_START);

        Pic=new JPanel();
        add(Pic,BorderLayout.WEST);

        StatControl= new JPanel();
        add(StatControl,BorderLayout.CENTER);

        actionPanel=new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(actionPanel,BorderLayout.PAGE_END);

        //1 player button
        p1=new JButton("1 Player");
        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setEnabled(false);
                p2.setEnabled(false);
                label0.setText("Give players name");
                name1.setVisible(true);
            }
        });

        //two players button
        p2=new JButton("Player vs Player");
        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setEnabled(false);
                p2.setEnabled(false);
                label0.setText("Give 1st players name");
                name2.setVisible(true);
                }
            });

        //statistics button
        stat=new JButton("Statistics");
        stat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Leaderboard = new ArrayList<>();
                Leaderboard = aGame.getLeaderboard();
                String name = "";
                boolean found=false;
                while (!name.equals("exit")){
                    name=JOptionPane.showInputDialog(null,
                        "Give name you want to see statistics of(type exit to close)",
                        "Statistics", JOptionPane.INFORMATION_MESSAGE);
                    for(Player player:Leaderboard){
                        if(player.getName().equals(name)){
                            JOptionPane.showMessageDialog(null,"Player: "+player.getName()+
                                    "Wins: "+player.getWins()+"High-score: "+player.getHighscore());
                            found=true;
                        }
                    }
                    if(!found){
                        JOptionPane.showMessageDialog(null,"Sorry player now found");
                    }
                }
            }
        });

        //player controls button
        controls=new JButton("view controls");
        controls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Player 1 : click Buzzer1 =Buzzer click 1)='first answer' click 2)='second answer' click 3)='third answer' click 4)='fourth answer"+
                        "\n Player 2 : ALT+A =Buzzer ALT+1='first answer' ALT+2='second answer' ALT+3='third answer' ALT+4='fourth answer","Controls",JOptionPane.INFORMATION_MESSAGE);
            }
        });


        //adding buttons labels etc.
        startScreen.add(p1);
        startScreen.add(p2);
        startScreen.add(stat);
        startScreen.add(controls);
        startScreen.add(lblTime);


        StatControl.add(label0);
        StatControl.add(label1);


        actionPanel.add(answer1);
        actionPanel.add(answer2);
        actionPanel.add(answer3);
        actionPanel.add(answer4);

        actionPanel.add(Buzzer1);
        actionPanel.add(Buzzer2);

        actionPanel.add(name1);
        actionPanel.add(name2);

        actionPanel.add(label2);
        actionPanel.add(bet1);
        actionPanel.add(bet2);
        actionPanel.add(bet3);
        actionPanel.add(bet4);


        setVisible(true);
    }
    /**
     *This method initializes a new timer its time and stops when the time is up
     */
    public void StartTimer(){
        lblTime.setVisible(true);
        clockTime=0;
        startTime=-1;
        AskQuestion();
        timer=new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime<0){
                    startTime=System.currentTimeMillis();
                }
                long now=System.currentTimeMillis();
                clockTime=now-startTime;
                if(clockTime>=duration){
                    clockTime=duration;
                    timer.stop();
                    JOptionPane.showMessageDialog(null,"Time's up","Sorry",JOptionPane.ERROR_MESSAGE);
                    lblTime.setVisible(false);
                    GetAnswer("");
                }
                SimpleDateFormat sdf =new SimpleDateFormat("mm:ss:SSS");
                lblTime.setText(sdf.format(duration-clockTime));
            }
        });
        timer.start();
    }
    /**
     *This method stops the timer and saves the time left
     */
    public void StopTimer(){
        timer.stop();
        setSpecial((int)clockTime);
    }

    /**
     *This method takes a question given from class Game
     */
    public void getEachQuestion () {
        eachQuestion=aGame.getQuestion();
        question=eachQuestion.getQuestion();
    }


    /**
     * This method resets the question and the lap when needed
     * and the round
     */
    public void LapPlay(){
        playerRemain=names.size()-1;
        playerPlaying=1;
        getEachQuestion();
        switch (aGame.getLapType()){
            case CORRECT_ANSWER:
                JOptionPane.showMessageDialog(null,"Lap type Correct Answer","LaType",JOptionPane.INFORMATION_MESSAGE);
                AskQuestion();
                break;
            case BET:
                JOptionPane.showMessageDialog(null,"Lap type Bet","LaType",JOptionPane.INFORMATION_MESSAGE);
                giveBet();
                break;
            case THERMOMETER:
                JOptionPane.showMessageDialog(null,"Lap type Thermometer","LaType",JOptionPane.INFORMATION_MESSAGE);
                AskQuestion();
                break;
            case QUICK_ANSWER:
                JOptionPane.showMessageDialog(null,"Lap type Quick Answer","LaType",JOptionPane.INFORMATION_MESSAGE);
                QuickAnswerShowQuestion();
                break;
            case STOP_THE_CLOCK:
                JOptionPane.showMessageDialog(null,"Lap type Stop The Clock","LaType",JOptionPane.INFORMATION_MESSAGE);
                StartTimer();
                break;
        }
    }

    /**
     * addPic is a method witch prints a Picture if there is one for the question
     */
    public void addPic() {
        String image =eachQuestion.getPicLocation();
        URL url=getClass().getResource(image);
        if(url!=null){
            icon= new ImageIcon(url);
        }
        lblImage.setIcon(icon);
        lblImage.setVisible(true);

    }

    /**
     * This methods prints a question for the user also checks if the question has an image and calls
     * the Method addPic
     */
    public void AskQuestion(){
        //question=eachQuestion.getQuestion();
        if(eachQuestion.getHasPic()){
            addPic();
        }
        label0.setText(names.get(playerPlaying-1));
        label1.setText(question.get(0));
        answer1.setText("1) "+question.get(1));
        answer2.setText("2) "+question.get(2));
        answer3.setText("3) "+question.get(3));
        answer4.setText("4) "+question.get(4));
        label0.setVisible(true);
        label1.setVisible(true);
        answer1.setVisible(true);
        answer2.setVisible(true);
        answer3.setVisible(true);
        answer4.setVisible(true);
    }

    /**
     * This method Prints the question with the possible answers when needed
     * Stops the clocks with StopTimer when we are playing STOP_THE_CLOCK
     * stores the scores of its player
     * and runs the Method advanceGame of the class game witch determines the actions of the game
     * Checks if their are an players remaining to be asked a question and re-ask the question
     * sets the score ands calls ScoreUpdate to print it after its round
     * @param answerGiven is the answer given by the player
     */
    public void GetAnswer(String answerGiven){
        if(aGame.getLapType()==LapType.STOP_THE_CLOCK) {
            StopTimer();
        }
        //adds the points
        if(aGame.getLapType()!=LapType.QUICK_ANSWER) {
            scores[playerPlaying-1]+=aGame.giveAnswer(eachQuestion.isCorrect(answerGiven),
                    playerPlaying-1, special);
        }
        else{
            if(firstAnswer==playerPlaying-1) {
                scores[firstAnswer] += aGame.giveAnswer(eachQuestion.isCorrect(answerGiven),
                        firstAnswer, 0);
                if(playerPlaying==1){
                    playerPlaying=2;
                    AskQuestion();
                    return;
                }
                else if(playerPlaying==2){
                    playerPlaying=1;
                    AskQuestion();
                    return;
                }
            }
        }
        int advance;
        advance=aGame.advanceGame();
        if(advance==0){
            if(playerRemain==1 && aGame.getLapType()!=LapType.QUICK_ANSWER)
            {
                playerRemain--;
                playerPlaying++;
                if(aGame.getLapType()==LapType.BET)
                {
                    giveBet();
                    return;
                }
                else if(aGame.getLapType()==LapType.STOP_THE_CLOCK){
                    StartTimer();
                    return;
                }
                else{
                    AskQuestion();
                    return;
                }
            }
            else{
                if(aGame.getLapType()==LapType.QUICK_ANSWER)
                {
                    QuickAnswerShowQuestion();
                }
                else {
                    ScoreUpdate();
                    LapPlay();
                    return;
                }
            }
        }
        else{
            if (advance==1){
                bet1.setVisible(false);
                bet2.setVisible(false);
                bet3.setVisible(false);
                bet4.setVisible(false);
                lblTime.setVisible(false);
                lblButtons.setVisible(false);
                ScoreUpdate();
                LapPlay();
            }
            else{
                if (advance==2){
                    System.out.println("end game");
                    JOptionPane.showMessageDialog(null,"End of the game","Hoped you had fun",JOptionPane.INFORMATION_MESSAGE);
                    ScoreUpdate();
                    System.exit(0);
                }
            }
        }
    }
    /**
     * This method changes the scores when asked
     */
    private void ScoreUpdate(){
        JOptionPane.showMessageDialog(null,names.get(0)+"score is: "+scores[0],
                "Score",JOptionPane.INFORMATION_MESSAGE);
        if(names.size()>1) {
            JOptionPane.showMessageDialog(null,names.get(1)+"score is: "+scores[1],
                    "Score",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * special is the bet the time remain from Stop the clock everything that differs in the lapTypes
     */
    private void setSpecial(int i){ special=i; }

    /**
     * This Method sets the buzzers for the Quick Answer LapType
     */
    private void QuickAnswerShowQuestion(){
        Buzzer1.setEnabled(true);
        Buzzer2.setEnabled(true);
        label1.setVisible(false);
        lblImage.setVisible(false);
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
        Buzzer1.setVisible(true);
        Buzzer2.setVisible(true);
        label1.setText(question.get(0));
        label1.setVisible(true);


    }

    /**
     * This Method sets the bets for the LapType Bet
     */
    public void giveBet(){
        label1.setVisible(false);
        lblImage.setVisible(false);
        answer1.setVisible(false);
        answer2.setVisible(false);
        answer3.setVisible(false);
        answer4.setVisible(false);
        label2.setText("Give bet "+names.get(playerPlaying-1)+": ");
        label0.setText("Category : "+ eachQuestion.getCategory());
        label2.setVisible(true);
        bet1.setVisible(true);
        bet2.setVisible(true);
        bet3.setVisible(true);
        bet4.setVisible(true);
    }

}
