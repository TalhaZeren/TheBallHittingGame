
package com.mycompany.space_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fire{ 
    private int x;      // for Fire
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


public class GamePanel extends JPanel implements KeyListener,ActionListener{

    Timer timer = new Timer(5, this);       // Timer provided to moving the ball.
    
    private int pastTime = 0;
    private int spendFire = 0;
    
   private BufferedImage image;
   
   private ArrayList<Fire> fires = new ArrayList<Fire>();   // put the fires in here.
    
   private int fireDirY = 15;
   
   private int ballX = 0;
   
   private int ballDirX = 8;
   
   private int spaceWarShipX = 0;
   
   private int dirSpaceX = 20;
   
   public GamePanel(){
       
       try {
           image = ImageIO.read(new FileImageInputStream(new File("spikedShip.png")));      // Image will appear in screen;
       } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       setBackground(Color.BLACK);
         timer.start();
   }

   public boolean Checking(){
       
       for(Fire a : fires){
           
           if (new Rectangle(a.getX(),a.getY(),10,10).intersects(new Rectangle(ballX,0,20,20)) )
               return true;
       }
       return false;
   }
   
    @Override
    public void paint(Graphics g) {
        pastTime = pastTime + 5;
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        g.setColor(Color.WHITE);
        
        g.fillRect(ballX, 0, 20, 20); // Defined the ball object in method
        
        g.drawImage(image, spaceWarShipX, 520, image.getWidth()/ 7 , image.getHeight() / 7, this);
        
        for(Fire a: fires){
        
        if(a.getY() < 0 ){    
            fires.remove(fires);   
        }
    }
        
        g.setColor(Color.BLUE);
        for(Fire a : fires){
            
            g.fillRect(a.getX(), a.getY(), 10, 10);
            
        }
            if(Checking()){
                timer.stop();
                String message = "Congratulations, direct hit !"
                        + "\nSpent Bullets :" + spendFire
                        +  "\nSpent time : " + pastTime / 1000.0 + "Second";
                JOptionPane.showMessageDialog(this, message);
                System.exit(0);
            }
        
    }

    @Override
    public void repaint() {
        super.repaint(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
   
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
        int click = e.getKeyCode();
        
        if(click == KeyEvent.VK_LEFT){
            
            if(spaceWarShipX <= 0 ){
            spaceWarShipX = 0;}
            
            else{
            
            spaceWarShipX -= dirSpaceX;
        }
        
            
        }
        else if(click == KeyEvent.VK_RIGHT){
                
                if(spaceWarShipX >= 760){
                spaceWarShipX = 760 ;
               
                }
                else { 
                spaceWarShipX += dirSpaceX;
                }
         }
        
        else if(click == KeyEvent.VK_SPACE){
            
            fires.add(new Fire(spaceWarShipX + 23 ,523)); 
            spendFire++;        // Count bullets;
            
        }
        

    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        for (Fire a : fires){
            
            a.setY(a.getY() - fireDirY);
        }
        
        
        
        ballX += ballDirX;
        
       if(ballX >= 760){
          
           ballDirX = -ballDirX;
           
       }
      
       if(ballX <= 0 ){
           
           ballDirX = -ballDirX;
       }
        
       repaint();
        

    }
    
    
 
    
}
