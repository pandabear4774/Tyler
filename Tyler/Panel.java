import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.*;
public class Panel extends JPanel implements ActionListener{
    public static Character[] characters = new Character[6];
    public static Square[][] board = new Square[7][7];
    public Random random = new Random();
    public Timer t = new Timer(1000,this);
    int iteration = 0;
    int redWins = 0;
    int greenWins = 0; 
    int tie = 0;
    public Panel(){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j ++){
                board[i][j] = new Square(i,j);
            }
        }
        reset();
    }
    public void reset(){
        characters[0] = new Character(0,0,Color.RED,0);
        characters[1] = new Character(3,0,Color.RED,1);
        characters[2] = new Character(6,0,Color.RED,2);
        characters[3] = new Character(0,6,Color.GREEN,3);
        characters[4] = new Character(3,6,Color.GREEN,4);
        characters[5] = new Character(6,6,Color.GREEN,5);
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j ++){
                board[i][j].lives = 3;
            }
        }
        t.start();
    }
    public void actionPerformed(ActionEvent ev){
        
        
        if(!checkDead()){
            moveGreen();
            moveRed();//moves red
            changeBoard();
            repaint();
        } else {
            t.stop();
            int winner = checkAlive();
            if(winner >= 3){
                //System.out.println("Bottom or Green WINS");
                greenWins++;
            } else if(winner >= 0){
                //System.out.println("Top or Red WINS");
                redWins++;
                
            } else {
                //System.out.println("NO WINNER");
                tie++;
            }
            iteration++;
            System.out.println(iteration);
            if(iteration < 200){
                reset();
            } else{
                System.out.println("Green Wins: " + greenWins);
                System.out.println("Red   Wins: " + redWins);
                System.out.println("Tie  Games: " + tie);
            }
        }
        
    }
    public boolean checkDead(){
        int counter = 0;
        for(int i = 0; i < 3; i++){
            if(!characters[i].dead){
                counter++;
            }
        }
        if(counter == 0){
            return true;
        }
        counter = 0;
        for(int i = 3; i < 6; i++){
            if(!characters[i].dead){
                counter++;
            }
        }
        if(counter == 0){
            return true;
        }
        return false;
    }
    public int checkAlive(){
        for(int i = 0; i < 6; i++){
            if(!characters[i].dead){
                return i;
            }
        }
        return -1;
    }
    public void moveRed(){
        ArrayList<String> moves = new ArrayList<String>();
        for(int i = 0; i < 3; i++){
            moves.clear();
            if(characters[i].dead){
                continue;
            }
            String nextMove;
            if(canStandStill(characters[i].position.x,characters[i].position.y)){
                //moves.add("Jump");
                nextMove = "Jump";
            } else {
                    if(canMoveNorth(characters[i].position.x,characters[i].position.y)){
                    moves.add("North");
                }
                if(canMoveEast(characters[i].position.x,characters[i].position.y)){
                    moves.add("East");
                }
                if(canMoveSouth(characters[i].position.x,characters[i].position.y)){
                    moves.add("South");
                }
                if(canMoveWest(characters[i].position.x,characters[i].position.y)){
                    moves.add("West");
                }
                
                
                if(moves.size() > 0)
                    nextMove = moves.get(random.nextInt(moves.size()));
                else {
                    nextMove = "Jump";
                    characters[i].dead = true;
                }
                switch(nextMove){
                    case "North":
                        characters[i].position.y--;
                        break;
                    case "East":
                        characters[i].position.x++;
                        break;
                    case "South":
                        characters[i].position.y++;
                        break;
                    case "West":
                        characters[i].position.x--;
                        break;
                    case "Jump":
                        
                        break;
                    default:
                        System.out.println("NOT POSSIBLE MOVE");
                        break;
                }
            }
        }
    }
    public void moveGreen(){
        ArrayList<String> moves = new ArrayList<String>();
        for(int i = 3; i < 6; i++){
            moves.clear();
            if(characters[i].dead){
                continue;
            }
            String nextMove;
            if(canStandStill(characters[i].position.x,characters[i].position.y)){
                //moves.add("Jump");
                nextMove = "Jump";
            } else {
                    if(canMoveNorth(characters[i].position.x,characters[i].position.y)){
                    moves.add("North");
                }
                if(canMoveEast(characters[i].position.x,characters[i].position.y)){
                    moves.add("East");
                }
                if(canMoveSouth(characters[i].position.x,characters[i].position.y)){
                    moves.add("South");
                }
                if(canMoveWest(characters[i].position.x,characters[i].position.y)){
                    moves.add("West");
                }
                
                
                if(moves.size() > 0)
                    nextMove = moves.get(random.nextInt(moves.size()));
                else {
                    nextMove = "Jump";
                    characters[i].dead = true;
                }
                switch(nextMove){
                    case "North":
                        characters[i].position.y--;
                        break;
                    case "East":
                        characters[i].position.x++;
                        break;
                    case "South":
                        characters[i].position.y++;
                        break;
                    case "West":
                        characters[i].position.x--;
                        break;
                    case "Jump":
                        
                        break;
                    default:
                        System.out.println("NOT POSSIBLE MOVE");
                        break;
                }
            }
            
        }
    }
    public int[] checkPath(int x, int y, int direction){
        int[]d = new int[4];
        if(direction != 0 && canMoveNorth(x,y)){
            d[0] = board[x][y].lives;
        }
        if(direction != 1 && canMoveEast(x,y)){
            d[1] = board[x][y].lives;
        }
        if(direction != 2 && canMoveSouth(x,y)){
            d[2] = board[x][y].lives;
        }
        if(direction != 3 && canMoveWest(x,y)){
            d[3] = board[x][y].lives;
        }
        return d;
    }
    public void changeBoard(){
        for(int i = 0; i < 6; i++){
            if(!characters[i].dead){
                board[characters[i].position.x][characters[i].position.y].lives--;
            }
        }
        for(int i = 0; i < 6; i++){
            if(!characters[i].dead){
                if(board[characters[i].position.x][characters[i].position.y].lives <= 0){
                    characters[i].dead = true;
                }
            }
        }
    }
    public void randomMove(int i){
            ArrayList<String> moves = new ArrayList<String>();
            moves.clear();
            if(characters[i].dead){
                return;
            }
            if(canMoveNorth(characters[i].position.x,characters[i].position.y)){
                moves.add("North");
            }
            if(canMoveEast(characters[i].position.x,characters[i].position.y)){
                moves.add("East");
            }
            if(canMoveSouth(characters[i].position.x,characters[i].position.y)){
                moves.add("South");
            }
            if(canMoveWest(characters[i].position.x,characters[i].position.y)){
                moves.add("West");
            }
            if(canStandStill(characters[i].position.x,characters[i].position.y)){
                moves.add("Jump");
            }
            String nextMove;
            if(moves.size() > 0)
                nextMove = moves.get(random.nextInt(moves.size()));
            else {
                nextMove = "Jump";
                characters[i].dead = true;
            }
            switch(nextMove){
                case "North":
                    characters[i].position.y--;
                    board[characters[i].position.x][characters[i].position.y].lives--;
                    break;
                case "East":
                    characters[i].position.x++;
                    board[characters[i].position.x][characters[i].position.y].lives--;
                    break;
                case "South":
                    characters[i].position.y++;
                    board[characters[i].position.x][characters[i].position.y].lives--;
                    break;
                case "West":
                    characters[i].position.x--;
                    board[characters[i].position.x][characters[i].position.y].lives--;
                    break;
                case "Jump":
                    board[characters[i].position.x][characters[i].position.y].lives--;
                    break;
                default:
                    System.out.println("NOT POSSIBLE MOVE");
                    break;
            }
    }
    public boolean canMoveNorth(int x, int y){
        if(y-1 >= 0 && board[x][y-1].lives > 1){
            return true;
        }
        return false;
    }
    public boolean canMoveEast(int x, int y){
        if(x+1 <= 6 && board[x+1][y].lives > 1){
            return true;
        }
        return false;
    }
    public boolean canMoveSouth(int x, int y){
        if(y+1 <= 6 && board[x][y+1].lives > 1){
            return true;
        }
        return false;
    }
    public boolean canMoveWest(int x, int y){
        if(x-1 >= 0 && board[x-1][y].lives > 1){
            return true;
        }
        return false;
    }
    public boolean canStandStill(int x, int y){
        if(board[x][y].lives > 1){
            return true;
        }
        return false;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j].paint(g);
            }
        }
        for(int i = 0; i < 6; i++){
            characters[i].paint(g);
        }
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j].paintLives(g);
            }
        }
    }
}