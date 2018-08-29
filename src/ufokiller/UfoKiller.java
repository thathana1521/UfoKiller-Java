package ufokiller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Formatter;

public class UfoKiller{
    
    public static Formatter x;
    public static String[] arguments;
    public static int numOfAliens;
    public static double stayTime;
    public static int bestScore;
    public static TxtFile file;
    
    public static void main(String[] args) throws InterruptedException, MalformedURLException, FileNotFoundException, UnsupportedEncodingException {
        
        file = new TxtFile();
        //args = new String[3];
        file.openFile();
        arguments=file.readFile();
        file.closeScannerFile();       
                
        numOfAliens = Integer.parseInt(arguments[0]);
        stayTime = Double.parseDouble(arguments[1]);
        bestScore = Integer.parseInt(arguments[2]);               
        
        Game game = new Game(numOfAliens, stayTime, bestScore, file);
        
    }     
}
    

