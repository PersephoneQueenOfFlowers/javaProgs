/**
* sschoen1@mail.ccsf.edu
* input: command line args + filenames
* process: args i for invert text bottom to top
* -n to number all lines
* -r to reverse list all lines backward
* output file lines inverted, reversed and/or numbered
* per arguments passed on command line
* no options means just listing
* last modified 5/17/13
*/

import java.util.*;
import java.io.*;
import java.util.Scanner;

class List
{

    public static String reverse(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        return (sb.reverse().toString());
    }

    public static ArrayList<String> x = new ArrayList<String>(); 
    public static void main(String args[])
    {
        GetOpt g = new GetOpt(args, "inr");
        int c;
        boolean reversing = false, inverting = false, numbering = false;
        
        g.opterr(false);  // suppress display error messages
        Scanner inputStream = null;
        String t[] = g.getarg();           // get filename(s)
        while( ( c = g.getopt()) != -1)
        {
                  switch(c)
                  {
                     case 'i': inverting = true; break;
                     case 'n': numbering = true; break;  
                     case 'r': reversing = true; break;
                     case '?': System.out.println("Wrong option:"+ g.optopt());
                        System.exit(1);
                  }
        }
        for(String s: t)
        {
            try
            {
                inputStream = new Scanner(new FileInputStream(s));
            }
            catch(FileNotFoundException e)
            {
                System.out.println("File " + s + " was not found.");
                System.out.println("or could not be found.");
            }
            while(inputStream.hasNext())
            {
              x.add(inputStream.nextLine());
            }
               if(reversing == true
               && numbering == true
               && inverting == true) // options i, n & r together
               {   
                   int lineNum = 1;
                   for(int j = x.size()-1; j >= 0; j--)
                   {
                       System.out.printf("%5d ", lineNum);
                        String x2 = reverse(x.get(j));
                       System.out.println(x2);
                       lineNum++;
                   }
               }
               else if(inverting == true
                     && numbering == true
                     && reversing == false) // options i & n together
               {
                   int lineNum = 1;
                   for(int j = x.size()-1; j >= 0; j--)
                   {
                       System.out.printf("%5d", lineNum);
                       System.out.println(" "+ x.get(j));
                       lineNum++;
  
                   }
               }
               else if(numbering == true
                    && reversing == true
                    && inverting == false) // options n & r together  
               {
                   int lineNum = 1;
                   for(int j = 0; j < x.size(); j++)
                   {
                       System.out.printf("%5d" , lineNum);
                       System.out.println(" " + reverse("" + x.get(j)));
                       lineNum++;
                   }
               }
               else if(numbering == false
                    && reversing == true
                    && inverting == true) // options i & r together
               {
                   for(int j = x.size()-1; j>=0; j--)
                   {
                       System.out.println(" " + reverse("" + x.get(j)));                                     
                   }
               }
             else if(reversing == true 
                  && inverting == false 
                  && numbering == false) //option r only
             {
                 for(int j =0;j < x.size(); j++)
                 {  
                      System.out.println(" " + reverse("" + x.get(j)));
                 }    
             }
             else if(inverting == true 
                  && reversing == false 
                  && numbering == false) // option i only
             {
                 for(int j = x.size()-1; j >= 0; j--)System.out.println(x.get(j));

             }
             else if(numbering == true 
                  && reversing == false 
                  && inverting == false) // option n only
             {
                 int lineNum = 1;
                 for(int j = 0; j < x.size(); j++)
                 {
                       System.out.printf("%5d ", lineNum);
                       System.out.println(x.get(j));
                       lineNum++;
                 }
             }
               else{for(int k = 0; k < x.size(); k++)System.out.println(x.get(k));}
          } // end for loop 

       }
    
    }    



