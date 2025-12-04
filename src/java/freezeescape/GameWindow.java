package freezeescape;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        setTitle("Freeze Scape");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new GameUI());

        setVisible(true);
    }

}
