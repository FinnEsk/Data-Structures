import java.util.Random;

import javax.sound.sampled.SourceDataLine;

import java.util.ArrayList;

public class driver {
    
    

    public int dice_roll(){
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;

        return roll;
    }


    

    

    public static void main(String[] args) {
        

       


        // run 1 game


        
        String[] all_players = new String[]{"A","B","C","D"};
        ArrayList<String> current_players = new ArrayList<String>();
        
        Board brd3 = new Board();


        StringBuilder sbuild = new StringBuilder();
        sbuild.append("----------------------------------------------------------------------------------------------");
        sbuild.append("|Player in game  |Player A average  |Player B average  |Player C average  |Player D average  |\n");
        sbuild.append("----------------------------------------------------------------------------------------------");

        for(int i = 0; i < 4; i ++){
            current_players.add(all_players[i]);
            brd3.initialize_player(all_players[i]);
            for(int j = 0; j < 1000; j ++){
                
                
                brd3.run_one_game(current_players); 

                if(j % 100 == 0){
                    System.out.println("Game " + (j+1));
                    System.out.println(brd3.output_board());
                }

                brd3.board_reset(); 
            }
            //sbuild.append(brd3.output_stats());
            brd3.board_total_reset();
        }

        System.out.println(sbuild);
        
       

        

        


    }


    
}
