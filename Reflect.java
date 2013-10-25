

package reflect;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author sethschoenfeld
 */
public class Reflect 
{
    
    public static void println(Object o)
    {
        System.out.println("" + o);
    }

public static void showInterfaces(Class c)
{
    Class j[] = c.getInterfaces();
    for(Class x : j)println(x);   
}
    
public static void showMethods(Class c)
{
    Method[] m_s = c.getDeclaredMethods();
    for(Method m : m_s)println(m_s);
}
    
public static void showConstructors(Class c)
{
    Constructor con[] = c.getConstructors();
    println("The constructors for " + c + "are: ");
    for(Constructor c_s : con)println(c_s);
}
    
public static void showVariables(Class c)
{
    /**
    Field f[] = c.getDeclaredFields();
    try
    {
        int modNum = d.getModifiers();
            if(modNum & Modifier.PUBLIC == modNum)
            {
                
            }
        for(Field d : f)
        {
            
            {
                
            }       
 //                   Class.isPublic(modNum))println("public ");//forName?
 //           if(isPrivate(modNum))println("private "); //forName?
 //           if(isProtected(modNum))println("protected "); //forName?
            
        }
    }
    
}
* */
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    
    
    }
/**
    public boolean isPublic(int m)
    {
        return(m & Modifier.PUBLIC == m);
    }
   */ 

    public static ArrayList<String> fileName = new ArrayList<String>(); 
    public static void main(String args[]) throws ClassNotFoundException
    {
        GetOpt g = new GetOpt(args, "acmvCi");
        int c;
        boolean all = false,
                constructors = false, 
                methods = false, 
                variables = false, 
                constants = false, 
                interfaces = false;
        
        g.opterr(false);  // suppress display error messages
        Scanner inputStream = null;
        String t[] = g.getarg();           // get filename(s)
        while( ( c = g.getopt()) != -1)
        {
                  switch(c)
                  {
                     case 'a': all = true; break;
                     case 'c': constructors = true; break;  
                     case 'm': methods = true; break;
                     case 'v': variables = true; break;
                     case 'C': constants = true; break;
                     case 'i': interfaces = true; break;
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
              fileName.add(inputStream.nextLine());
            }
            Class clAss = fileName.getClass();
        
            if(constructors == true)
            {
                showConstructors(clAss);
            }
            if(methods == true)
            {
                showMethods(clAss);
                
            }
            if(variables == true)
            {
                showVariables(clAss);
            }
        }
    }
}

