/*
 * Created by Seth Schoenfeld
 * Purpose is to count characters, words or lines
 * in a file given as second argument to WC on command line.
 * First argument is either -c, -w or -l. Count of characters
 * words or lines is processed depending on 
 * corresponding first command line arguement. 
 * 2/18/2012
 * last modified 2/18/2012
 * sschoen1@mail.ccsf.edu
 */

package wc;

import java.io.*;
import java.util.*;
public class WC
{
    public static void main(String[] args) throws FileNotFoundException
    {
        
        // check to see that there are exactly 2 arguments. 
        
        if(args.length != 2)
        {    
            System.err.println("Must be exactly two arguments");
            System.exit(1);
        }
            
        File f1 = new File(args[2]);
        Scanner fStream = new Scanner("f1");
            
        if(!(f1.isFile()) || !(f1.canRead()))  
        {    
            System.err.println(f1 + "is not a file"
                    + " or is not readable");
            System.exit(1);
        }
        else
        {    
            if (args[1].equals("-l"))   
            {
                
                // count lines in file
                int lineSum = 0;
                while (fStream.hasNextLine())   
                {
                    
                    lineSum ++; // count the lines
                    
                }
                System.out.println("File: " + f1 + " contains "
                        + lineSum + "lines");
                
            }
        
            else if(args[1].equals("-c"))
            {
                // count characters in file
                int charTotal = 0;
                while(fStream.hasNextByte())
                {
                    charTotal++;
                }
                System.out.println("File: " + f1 + " contains " + charTotal
                        + " characters");
          
            }
            else if (args[1].equals("-w"))
            {
                // count words in file
                int wordTotal = 0;
                while(fStream.hasNext())
                {
                    wordTotal++;

                }
                System.out.println("File: " + f1 + " contains " + wordTotal
                        + "words");
            }
            fStream.close();
            
        } // end of else-block file processing
    } // end of main method
} // end of class
