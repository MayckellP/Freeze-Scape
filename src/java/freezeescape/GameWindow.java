package freezeescape;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        setTitle("Freeze Scape");
        setSize(640, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // -- Main Layout
        setLayout(new BorderLayout());

        // -- Map panel
        GameUI gamePanel = new GameUI();
        GameEngine engine = new GameEngine(gamePanel);

        // -- Top Panel with Button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton startButton = new JButton("Start Game");

        startButton.addActionListener(e -> {
           System.out.println("Game started");
           engine.start();

        });

        topPanel.add(startButton);

        // -- Window
        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);

        //add(new GameUI());

        setVisible(true);
    }

}
