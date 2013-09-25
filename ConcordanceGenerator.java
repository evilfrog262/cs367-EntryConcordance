///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            ConcordanceGenerator
// Files:            BSTBasicMap.java, Entry.java, DuplicateException.java
// Semester:         Spring 2012
//
// Author:           Will Kraus
// CS Login:         kraus
// Lecturer's Name:  Beck Hasti
// Lab Section:      n/a
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Kristin Cox
// CS Login:         kcox
// Lecturer's Name:  Beck Hasti
// Lab Section:      n/a
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * Parses the words from a text file and uses them to create a concordance.
 * 
 * @author Will Kraus and Kristin Cox
 */
public class ConcordanceGenerator {
    /**
     * The main method generates a concordance as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine how input and 
     * output is done:
     * <ul>
     *   <li>if there is one command-line argument, then it is treated as the
     *   name of the file from which to get input and output is sent to the
     *   console</li>
     *   <li>if there are two command-line arguments, then the first is the name
     *   of the file from which to get the input and the second is the name of 
     *   the file to which to sent the output</li>
     * </ul>
     */
    public static void main(String[] args) {
        Scanner in = null;         // for input
        PrintStream out = null;    // for output
        BasicMapADT<String, Entry> concord = new BSTBasicMap<String, Entry>();  
                                   // the concordance
        File inFile = null;			//store input file
        File outFile = null;		//store output file

        // Set up where to send input and output

        switch (args.length) {

        case 1:
        	try {
        		inFile = new File(args[0]);     	 
        		in = new Scanner(inFile);
        	} catch (FileNotFoundException ex) {
        		System.out.println("file not found");
        		System.exit(0);
        	}
        	out = System.out;
        	break;

        case 2: 
        	try { 
        		inFile = new File(args[0]);     	 
        		in = new Scanner(inFile); 
        		outFile = new File(args[1]);
        		out = new PrintStream(outFile);
        	} catch (FileNotFoundException ex) {
        		System.out.println("File not found");
        		System.exit(0);
        	}

            break;

        default:
            System.err.println("Invalid command-line arguments");
            System.exit(0);
        }

        // Process the input file line by line
        // Note: the code below just prints out the words contained in each
        // line.  You will need to replace that code with code to generate
        // the concordance.
              
        int lineNum = 1;	 //keeps track of current line number
        while (in.hasNext()) {
            String line = in.nextLine();
            List<String> words = parseLine(line);

            ////////////////////////////////////////
            // replace the code below with your code
            for (String word : words) {
                Entry myEntry = new Entry(word); 
            	try {
            		concord.put(word.toLowerCase(), myEntry);
            		myEntry.getLines().add(lineNum);
            	} catch (DuplicateException ex) {
            		concord.get(word.toLowerCase()).getLines().add(lineNum);
            	}
            }
            lineNum++;
            ////////////////////////////////////////

        } // end while

        // Print out the concordance
        Iterator<Entry> iter = concord.values().iterator();
        while (iter.hasNext())
            out.println(iter.next().toString());

        // Print out the statistics
        int keys = concord.size();
        int pathLength = concord.totalPathLength();
        out.print("# keys: " + keys + "  total path length: " + pathLength);
        double avg = pathLength;
        out.println("  avg path length: " + ((keys  == 0)? 0 :avg/keys));
        
        out.close();
    } 
    
    /**
     * Parses the given line into an array of words.
     * DO NOT CHANGE THIS METHOD.
     */
    private static List<String> parseLine(String line) {
        String[] tokens = line.split("[ ]+");
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {  // for each word
            
            // find index of first digit/letter
              boolean done = false; 
              int first = 0;
            String word = tokens[i];
            while (first < word.length() && !done) {
                if (Character.isDigit(word.charAt(first)) ||
                    Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;
            }
            
            // find index of last digit/letter
            int last = word.length()-1;
            done = false;
            while (last > first && !done) {
                if (Character.isDigit(word.charAt(last)) ||
                        Character.isLetter(word.charAt(last)))
                        done = true;
                    else last--;
            }
            
            // trim from beginning and end of string so that is starts and
            // ends with a letter or digit
            word = word.substring(first, last+1);
  
            // make sure there is at least one letter in the word
            done = false;
            first = 0;
            while (first < word.length() && !done)
                if (Character.isLetter(word.charAt(first)))
                    done = true;
                else first++;           
            if (done)
                words.add(word);
        }
        
        return words;
    }
}
