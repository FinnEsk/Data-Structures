import java.util.ArrayList;
import java.util.Random;



public class Board {
    
    // this array holds all the standard board values including a '0' for start and '10000' for the end
    static int[] standard_board_arr = {0, 5, 10, 8, 10, 7, 5, 9, 10, 6, 7, 10, 6, 5, 8, 9, 5, 10, 5, 9, 6, 8, 7, 10, 6, 8, 0};
    
    private DoublyLinkedList<BoardSpace> brd_list = new DoublyLinkedList<BoardSpace>();
    private ArrayList<Player> players = new ArrayList<Player>();

    private int winner_index;



    // board contructor
    public Board(){
        
        // for loop that adds all the standard board values to the new board
        for(int i = 0; i<standard_board_arr.length; i++){
            brd_list.addLast(new BoardSpace(standard_board_arr[i]));
        }
    }


    public int dice_roll(){
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;

        return roll;
    }

    public Player get_player(String n){

        for(Player p : this.players){
            if (n.equals(p.get_name())){
                return p;
            }
        }

        return null;
    }
    public void initialize_player(String name){
        Player player = new Player(name);
        players.add(player);
        brd_list.get_value_at(0).add_player_on_space(player);
    }

    public void board_reset(){

        for(Player p: players){
            BoardSpace p_position = this.brd_list.get_value_at(p.get_position());
            p_position.remove_player_on_space(p);

            p.player_reset();
            brd_list.get_value_at(0).add_player_on_space(p);

        }

    }

    public void board_total_reset(){
        for(Player p: players){
            BoardSpace p_position = this.brd_list.get_value_at(p.get_position());
            p_position.remove_player_on_space(p);

            p.player_total_reset();
            brd_list.get_value_at(0).add_player_on_space(p);

        }
    }


    public void move_player(Player player, int roll){

        BoardSpace player_original_position = brd_list.get_value_at(player.get_position());
        // change the player's position and points in the player object
        player.change_position(roll);
        

        // now get the new position of the player
        BoardSpace player_new_position = brd_list.get_value_at(player.get_position());
        player.set_points(player_new_position.get_space_num());

        // add the new player to the board space object
        player_new_position.add_player_on_space(player);

        // now if there are other players already on the boardspace they are moved back
        if(player_new_position.get_players_on_space().size() > 1 && player_new_position.get_space_num() != 0){
            for(int i = 0; i < player_new_position.get_players_on_space().size() - 1; i ++){
                Player previous_player = player_new_position.get_players_on_space().get(i);
                // move the previous player back 7 in the player object
                previous_player.change_position(-7);

                // move the previous players location in the boardspace
                brd_list.get_value_at(previous_player.get_position()).add_player_on_space(previous_player);

                player_new_position.remove_player_on_space(previous_player);
            }
        }

        // remove the player whos turn it is from their original space
        player_original_position.remove_player_on_space(player);

    }
    


    public boolean winner_tester(){

        for(int i = 0; i < this.players.size(); i ++){
            Player p  = this.players.get(i);
            if (p.get_winner() == true){
                this.winner_index = i;
                return true;
            }
        }

        return false;
    }

    public String output_stats(){

        StringBuilder sbi = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String playernameslist = "";
        String stats1 = "";
        
        for(Player s : this.players){
            playernameslist += s.get_name();
        }
            System.out.println("|Player in game  |Player A average  |Player B average  |Player C average  |Player D average  |");
            
            sb.append(String.format("|%16s",playernameslist));
        for(int i = 0; i < 4; i++){    

            sbi.append(Integer.toString((this.players.get(i).get_total_moves()/1000)));
            stats1 += "/";
            stats1 += Integer.toString(players.get(i).get_total_wins()/100);
            stats1 += "%";

            if(i <= players.size()){
                sb.append(String.format("|%18s",stats1));
            }
            else{
                sb.append("|                  ");
            }
        }

        sb.append("|\n");
        sb.append("----------------------------------------------------------------------------------------------");
        return sb.toString();
    }


    //play one game
    public void run_one_game(ArrayList<String> new_players){
        
        // initialize the players
        

        while(winner_tester() == false){
            for(Player p : this.players){
                move_player(p, dice_roll());
            }
        }
        
        this.players.get(winner_index).increment_total_wins();

        for(Player p : this.players){
            p.increment_total_moves(p.get_moves());
        }
        
    }

    








    
    public String output_board(){
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < 87; i ++){
            sb.append("-");
        }
        sb.append("\n");
        sb.append("| " + "Start " + this.brd_list.get_value_at(0).get_players_on_space_str() + "|");
        for(int i = 1; i <= 9; i ++){
            sb.append(" " + this.brd_list.get_value_at(i).get_space_num() + this.brd_list.get_value_at(i).get_players_on_space_str() + " |" );
        }
        sb.append("\n");
        for(int i = 0; i < 87; i ++){
            sb.append("-");
        }
        sb.append("\n           |");
        for(int i = 10; i <= 16; i ++){
            sb.append(" " + this.brd_list.get_value_at(i).get_space_num() + this.brd_list.get_value_at(i).get_players_on_space_str() + " |" );
        }
        sb.append("\n           ");
        for(int i = 0; i < 85; i ++){
            sb.append("-");
        }
        
        sb.append("\n           |");
        for(int i = 17; i <= 25; i ++){
            sb.append(" " + this.brd_list.get_value_at(i).get_space_num() + this.brd_list.get_value_at(i).get_players_on_space_str() + " |" );
        }
        sb.append(" End" + this.brd_list.get_value_at(26).get_players_on_space_str() + " |");

        sb.append("\n           ");
        for(int i = 0; i < 85; i ++){
            sb.append("-");
        }

        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.brd_list.toString());

        return sb.toString();
    }


    

    


    
    
}
