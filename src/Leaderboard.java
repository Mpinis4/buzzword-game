import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**This class implements the leaderboard of the game
 * it keeps record of each players wins and highscore in a file
 *
 * @author Gkevork Sogkogian
 * */

public class Leaderboard {
    private ArrayList<Player> playerList;

    public Leaderboard(){
        playerList = new ArrayList<>();
        BufferedReader lbReader;
        try{
            lbReader = new BufferedReader(new FileReader("Leaderboard.txt"));//
            String line = lbReader.readLine();
            String[] str;
            int i = 0;
            while(line != null && !line.isEmpty()){
                str = line.split("\\|");
                if (str.length == 3) {
                    playerList.add(new Player(str[0]));
                    playerList.get(i).setWins(Integer.parseInt(str[1]));
                    playerList.get(i).setHighscore(Integer.parseInt(str[2]));
                }
                line = lbReader.readLine();
            }
            lbReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**This function adds a win to a player
     * it adds the player to the list if an entry doesnt exist
     * */
    public void winUpdate(Player p){
        int index = findPlayer(p.getName());

        if(index == -1 ){
            p.increaseWins();
            playerList.add(p);
        }
        else{
            p.increaseWins();
            playerList.set(index,p);
        }
    }
    /**This functions updates the highscore of a player if it is greater than the current highscore
     *it adds the player to the list if an entry doesnt exist
     * */
    public void scoreUpdate(Player p ){
        int index = findPlayer(p.getName());
        if(index == -1 ){
            playerList.add(p);
        }
        else{
            if(playerList.get(index).getHighscore() < p.getHighscore()){
                playerList.set(index,p);
            }
        }
    }


    /**This functions searches if a player exists in the current leaderboard
     * @return -1 in case no match is found
     * @return  i the matching index*/
    private int findPlayer(String name){
        int index= -1;
        for(int i =0;i<playerList.size();i++){
            if(playerList.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
    }

    /**This function updates the file with the records
     * when it starts righting in the file all previous entries get deleted
     * it records the Name the Wins and the Highscore of a player
     */
    public void updateFile(){
        try {
            PrintWriter writer = new PrintWriter("Leaderboard.txt");
            for(Player p : playerList){
                writer.println(p.getName() +"|" +p.getWins()+"|"+p.getHighscore());
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Player> getPlayerList(){return playerList;}
}
