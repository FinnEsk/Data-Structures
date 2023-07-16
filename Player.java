import java.util.ArrayList;

public class Player {
    
    private String name;
    private int points;
    private int position;
    private int moves;
    private int total_moves;
    private int total_wins;
    private boolean winner;
    

    public Player(String name){
        this.points = 0;
        this.name = name;
        this.position = 0;
        this.winner = false;
        this.total_moves = 0;
        this.total_wins = 0;
    }

    public void set_points(int num){
        this.points += num;
    }

    public int get_points(){
        return this.points;
    }

    public void set_name(String name){
        if(name.length() <= 1){
            this.name = name;
        }
    }

    public String get_name(){
        return this.name;
    }

    public void set_position(int num){
        this.position = num;
    }

    public int get_position()
    {
        return this.position;
    }

    public int get_moves(){
        return this.moves;
    }

    public void set_moves(int num){
        this.moves = get_moves() + num;
    }

   public int get_total_moves(){
    return this.total_moves;
   }

   public void increment_total_moves(int num){
    this.total_moves += num;
   }

   public int get_total_wins(){
    return this.total_wins;
   }

   public void increment_total_wins(){
    this.total_wins += 1;
   }


   public void player_reset(){
    this.points = 0;
    this.position = 0;
    this.winner = false;
   }

   public void player_total_reset(){
    this.points = 0;
    this.position = 0;
    this.winner = false;
    this.total_moves = 0;
    this.total_wins = 0;
   }
   

    public boolean get_winner(){
        return this.winner;
    }


    public void change_position(int num){
        
        // if the move forward doesn't put the player at the 'end'
        if(this.position + num <= 26){
            this.set_position(get_position() + num);
        }
        // if they are at the end and they have less than 44 points
        else if(this.position + num >= 26 && this.points < 44){
            this.set_position(0);
        }
        // if they are at the end position and they have at least 44 points
        else if(this.position + num >= 26 && this.points >= 44){
            this.set_position(26);
            this.winner = true;
        }
        else if(this.position + num < 0){
            this.set_position(0);
        }

        this.set_moves(num);
    }
}
