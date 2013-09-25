///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             DuplicateException.java
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

/**
 * This checked exception gets thrown when attempting to add a duplicate
 * item to the tree.
 * 
 * @author Will Kraus and Kristin Cox
 */

public class DuplicateException extends Exception  {
	
	/**
	 * Constructs a Duplicate Exception
	 * 
	 */
	public DuplicateException() {
		super();
	}
	
	/**
	 * Constructs a Duplicate Exception with a message.
	 * 
	 */
	public DuplicateException(String msg) {
		super(msg);
	}

}
