import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame{
    public static Panel panel;
    public static Dimension screenSize;
    public Frame(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(750,750);
        setTitle("The Game Thing");
        panel = new Panel();
        panel.setBackground(Color.BLACK);
        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
