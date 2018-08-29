
package ufokiller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Formatter;
import java.util.Scanner;

public class TxtFile {
    
    public static Formatter y,x;
    
    public Scanner scanner;
    public String[] arguments;
    
    public TxtFile() {
        arguments= new String[3];
    }
    
    public void openFile() throws FileNotFoundException, MalformedURLException, UnsupportedEncodingException {
        Boolean fileFound = true;
        //If file doesnt exist create it and then create the scannerFile
        while(fileFound) {
            try {
                scanner = new Scanner(new File("txtFile.txt"));
                fileFound= false;
            }
            catch(Exception e) {
                System.out.println("File not found.");                
                createTxtFile();
            }
        }
    }
    
    public String[] readFile() {
                       
        while(scanner.hasNext()) {                        
            arguments[0] = scanner.next(); //args[0] = numOfAliens            
            arguments[1] = scanner.next(); //args[1] = stayTime
            arguments[2] = scanner.next();//args[2] = bestScore                 
        }
        return arguments;   
    }
    
    public static void createTxtFile() throws MalformedURLException, FileNotFoundException, UnsupportedEncodingException{        
                
        try{
            x = new Formatter("txtFile.txt");
            System.out.println("txtFile Created.");
        }
        catch (IOException e){
            e.printStackTrace();
        }    
        addRecordsToFile();
        x.close();                    
    }
    
    //First string = stayTime, Second string = numberOfAliens, Third string = maxScore
    public static void addRecordsToFile() {
        x.format("%s%s%s", "10 ", "2000 ", "0 ");
    }
    
    public static void updateBestScore(int bestScore) throws FileNotFoundException {
        try{
    		
            File file = new File("txtFile.txt");
            if(file.delete()){
                    System.out.println(file.getName() + " is deleted!");
            }else{
                    System.out.println("Delete operation is failed.");
            }
    	   
    	}catch(Exception e){
    		
            e.printStackTrace();
    		
    	}
        try{
            x = new Formatter("txtFile.txt");
            System.out.println("txtFile Created.");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Score "+bestScore);
        x.format("%s%s%s", "10 ", "2000 ", ""+bestScore);
        x.close();                
    }
    
    public void closeScannerFile(){
        scanner.close();
    }
}
