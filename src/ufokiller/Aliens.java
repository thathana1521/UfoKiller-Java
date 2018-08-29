
package ufokiller;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Aliens {
    public static int numberOfAliens=30;
    public static int aliensKilled=0;
    public boolean won=true;
    public boolean newStayTime = true;
    public double stayTime = 2000;
    public JTextField score;
    public int currentScore = 0;
    public JLabel[] aliens;
    
    public Aliens(JPanel windowPanel, JPanel northPanel, int numOfAliens, double stayTime, int bestScore, TxtFile file) throws InterruptedException, FileNotFoundException, MalformedURLException{
    
    score = new JTextField("Score "+ currentScore);
    score.setPreferredSize(new Dimension ( 100, 25));
    score.setEditable(false);
    northPanel.add(score);
    
    aliens= createLabels(windowPanel);    
    
    randomAliens(aliens, windowPanel, numOfAliens, stayTime, bestScore, file);
}

    //Show randomly aliens
    public void randomAliens (JLabel[] alien, JPanel panel, int numOfAliens, double stayTime, int bestScore ,TxtFile file) throws InterruptedException, FileNotFoundException, MalformedURLException  {
        int i=0;
        int randomNum;
        
        //30 ufos to show.           
        while (i<numberOfAliens){
            clearAliens(alien);           
            randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            alien[randomNum].setVisible(true);           
            
            System.out.println("Number "+ randomNum);
            // if currentScore mod 50 = 0 reduce stayTime by 20% 
            System.out.println("Score = " +currentScore);
            
            if (currentScore != 0){
                if (currentScore%50 == 0  && newStayTime){
                    stayTime=stayTime*0.8;
                    //newStayTime = true every time the user clicks on an alien. To prevent multiple reduces.
                    newStayTime=false;
                }
            }
            System.out.println("StayTime = "+ (int)stayTime);
            TimeUnit.MILLISECONDS.sleep((int) stayTime); 
            
            //if there are no more chances to win this game. RemainingAliens < AliensToKill           
            if (i + numOfAliens - aliensKilled > numberOfAliens-1){
                gameOver();
                won = false;
                break;
            }
            i++;
        }
        clearAliens(alien);
        if (won) {
            if( (int)currentScore > bestScore){

                if (bestScore!=0){
                    System.out.println("New Best Score! ");
                    congratsWindow();
                }
                bestScore = (int)currentScore;
                file.updateBestScore(bestScore);
            } 
            else {
                wonGame();
            }
        }       
        
    }
    
    //Create 3 JLabels ( EAST, WEST, NORTH ) to show aliens as icons, add them on Panel
    public JLabel[] createLabels(JPanel panel){
        JLabel[] labels=new JLabel[3];
        
        for (int i=0;i<3;i++){
            labels[i]=new JLabel();
            ImageIcon alienIcon = new ImageIcon(getClass().getResource("alien.png"));
                Image alienImage = alienIcon.getImage();
                Image alienImg = alienImage.getScaledInstance(80,80,Image.SCALE_SMOOTH);
                alienIcon= new ImageIcon(alienImg);
                labels[i] = new JLabel(alienIcon);
                //labels[i].setName(""+i);
                labels[i].addMouseListener(new mouseListener());
                labels[i].setVisible(false);
        }        
        
        panel.add(labels[0],BorderLayout.WEST);
        panel.add(labels[1],BorderLayout.NORTH);
        panel.add(labels[2],BorderLayout.EAST);        
        
        return labels;
    }
    
    //Clear all aliens' Icons. Called when a new alien apears or one has hust got killed
    private void clearAliens(JLabel[] aliens) {        
        for (int i = 0; i<3; i++) {
            aliens[i].setVisible(false);
        }
    }
    
    public class mouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            for(int i = 0; i<aliens.length; i++){
                if(e.getSource()== aliens[i]){
                    
                    if( aliens[i].isVisible() ){
                        aliens[i].setVisible(false);
                        currentScore = currentScore + 25;
                        aliensKilled++;
                        score.setText("Score " + currentScore);                      
                        newStayTime=true;
                    }
                }
            }            
        }        
    }
    
    //Congratulations Window appears when the user breaks past record
    public void congratsWindow() {
            JOptionPane.showMessageDialog(null, "Congratulations you achieved a new bestScore!","Congrats", JOptionPane.INFORMATION_MESSAGE);
    }    
    //Game Over Window appears when the user loses.
    public void gameOver() {
            JOptionPane.showMessageDialog(null, "Game over! You did not killed enough aliens","Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
    //Won the game without a new record
    public void wonGame() {
            JOptionPane.showMessageDialog(null, "You win! Well done.","Well done!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
