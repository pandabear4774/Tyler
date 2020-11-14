import java.awt.*;
public class Character{
    public Position position;
    private Color color;
    public boolean dead;
    public Character(int x, int y, Color c, int index){
        position = new Position(x,y);
        color = c;
        dead = false;
    }
    public void paint(Graphics g){
        
        if(!dead){
           g.setColor(color);
           g.fillRect(25+position.x*100,25+position.y*100,50,50); 
        }
    }
}