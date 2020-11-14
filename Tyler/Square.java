import java.awt.*;
public class Square{
    public int x,y,lives;
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        lives = 3;
    }
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(10 + x * 100,10 + y * 100,80,80);
    }
    public void paintLives(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(lives),50 + x * 100,50 + y * 100);
    }
}