
package com.mycompany.space_game;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class MainScreen extends JFrame{

    public MainScreen(String title) throws HeadlessException {      // Game Title;
        super(title);
    }
    
    
    public static void main(String[] args){
        
        MainScreen screen = new MainScreen("War Game");
        
        screen.setResizable(false);         // Resizable prevented.
        screen.setFocusable(false);  // if this method be false, preventing the game to focus the screen;
         
        screen.setSize(800, 600);
        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Default Closed Method.
        
        GamePanel gamePanel = new GamePanel();      // Created The GamePanel Class Reference;
        
        gamePanel.requestFocus();
        
        // Method Order is important!
        
        gamePanel.addKeyListener(gamePanel);    // GamePanel implemented KayListener and ActionListener; (Look at GamePanel Class)
        
        gamePanel.setFocusable(true);   // Game Focused the gamePanel when write 'true';
        
        gamePanel.setFocusTraversalKeysEnabled(false);
        
        screen.add(gamePanel);  // Game Commad added the inside screen;
        
        screen.setVisible(true);  // Opened the GameScreen;
        
        
        
    }
    
}
