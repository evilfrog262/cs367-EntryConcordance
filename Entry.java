///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             Entry.java
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

/**
 * Each entry object contains a String and a list of Integers.  These
 * represent line numbers where that word appears.
 * 
 * @author Will Kraus and Kristin Cox
 */
public class Entry implements Comparable<Entry> {
	private String word;	//keyword of entry
	private ArrayList<Integer> list;	//list of line numbers

	/**
	 * Constructs an Entry object containing the given word and an empty
	 * List of Strings.
	 * 
	 * @param: String word to be added to the Entry
	 */
	public Entry(String word) {
		this.word = word.toLowerCase();
		list = new ArrayList<Integer>();
	}

	/**
	 * Implements comparable interface by calling the compareTo() method
	 * for Strings.
	 * 
	 * @param: Entry whose word will be compared to this one
	 * @return: 1 if greater than, -1 if less than, 0 if equal
	 */
	public int compareTo(Entry other) {
		return ((this.word).compareTo(other.word));
	}

	/**
	 * Test if two Entries are equal by comparing their words
	 * 
	 * @param: true if equal, false otherwise
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		Entry that = null;
		if (other instanceof Entry) {
			that = (Entry)other;
		}
		return (this.word).equals(that.word);
	}

	/**
	 * Accessor for the Entry's word
	 * 
	 * @return: String word of the given Entry
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Accessor for the Entry's List of line numbers
	 * 
	 * @return: List of line numbers for the given entry
	 */
	public ArrayList<Integer> getLines() {
		return this.list;
	}
	
	/**
	 * Turns the given entry into a String containing its word
	 * and the list of all line numbers where it appears separated
	 * by commas.
	 * 
	 * @return: String representing entry
	 */
	public String toString() {
		//String containing its word and the list of all line numbers where it 
		//appears separated by commas
		String str = this.word + "(" + this.list.size() + "): ";
		for (int i = 0; i < this.list.size() - 1; i++) {
			str += this.list.get(i) + ", ";
		}
		str += this.list.get(this.list.size() - 1);
		return str;
	}
}
