
package ufokiller;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.swing.*;

public class Game extends JFrame{        
    
    public JPanel windowPanel = new JPanel();
    public JPanel northPanel = new JPanel();
    public JPanel southPanel = new JPanel();
    public int currentScore = 0;
    
    private JLabel killer;
    
    public Game(int numOfAliens, double stayTime, int bestScore, TxtFile file) throws InterruptedException, FileNotFoundException, MalformedURLException {
        super("Ufo Killer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
              
        setVisible(true);
        setResizable(false);
        
        windowPanel.setLayout(new BorderLayout());
        windowPanel.setPreferredSize(new Dimension(500, 500));
        
       
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setLayout(new BorderLayout());      
                            
        ImageIcon killerIcon = new ImageIcon(getClass().getResource("killer.png"));
        Image killerImage = killerIcon.getImage();
        Image killImg = killerImage.getScaledInstance(150,180,Image.SCALE_SMOOTH);
        killerIcon= new ImageIcon(killImg);
        killer = new JLabel(killerIcon);
        southPanel.add(killer, BorderLayout.SOUTH);      
                
        windowPanel.add(northPanel, BorderLayout.NORTH);
        windowPanel.add(southPanel);
        
        add(windowPanel);
        
        Aliens aliens = new Aliens(southPanel, northPanel, numOfAliens, stayTime, bestScore, file);       
             
    }      
    
}
