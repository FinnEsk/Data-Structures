import java.util.ArrayList;

public class BoardSpace {
   
    private ArrayList<Player> players_on_space = new ArrayList<Player>();
    private int space_num;


    public BoardSpace(int num){
        this.space_num = num;
    }

    public int get_space_num(){
        return this.space_num;
    }

    public void add_player_on_space(Player player){
        this.players_on_space.add(player);
    }

    public void remove_player_on_space(Player player){
        this.players_on_space.remove(player);
    }

    public ArrayList<Player> get_players_on_space(){
        return this.players_on_space;
    }

    public String get_players_on_space_str(){
        String str = "";
        if(players_on_space.size() == 0){
            return "    ";
        }
        else{
            for(Player player : this.players_on_space){
                str += player.get_name();
            }
            return str;
        }
    }

    
    public String toString(){
        return Integer.toString(this.space_num);
    }
}
