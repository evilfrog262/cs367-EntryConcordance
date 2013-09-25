///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  ConcordanceGenerator.java
// File:             BSTBasicMap.java
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

import java.util.List;
import java.util.*;

/**
 * Creates a Binary Search Tree using a BasicMapADT interface where each
 * node holds both a key and a value.  The tree is organized by key.
 * 
 * @author Will Kraus and Kristin Cox
 */
public class BSTBasicMap<K extends Comparable<K>, V> 
                                              implements BasicMapADT<K, V> {
    private BSTnode<K, V> root;  // the root node
    private int numItems;        // the number of items in the basic map
    
    /**
     * Zero-argument constructor
     */
    public BSTBasicMap(){
    	this.root = null;
    	this.numItems = 0;
    }
   
    /**
     * Adds the given (key, value) pair to the Basic Map if the key is not 
     * already in the Basic Map. If the key is already in the Basic Map, a 
     * DuplicateKeyException is thrown. 
     *
     * @param key: the key to insert into the Basic Map
     * @param value: the value associated with the key
     */
    public void put(K key, V value) throws DuplicateException {
        if (numItems == 0) {
        	root = new BSTnode<K,V> (key, value);
        }
        else {
        	insert(root, key, value);
        }
        this.numItems++;

    }

    /**
     * Removes the (key, value) pair from the Basic Map that corresponds 
     * with the given key. If the key is in the Basic Map, the (key, value) 
     * pair is deleted and true is returned. If the key is not in the Basic 
     * Map, the Basic Map is unchanged and false is returned. 
     *
     * @param key: the key for the (key,value) pair to delete from the Basic Map
     * @return: true if the removal is successful, false otherwise
     */
    public boolean remove(K key) {
    	BSTnode<K,V> removed = delete(root, key);
    	if (removed != null) {
    		this.numItems--;
    		return true;
    	}
    	return false;
    }


    /**
     * Searches for the given key in the Basic Map and returns the value 
     * associated with it. If the key is not in the Basic Map, null is returned. 
     *
     * @param key: the key to search for
     * @return: the value from the Basic Map corresponding to the key, if 
     * the key is in the Basic Map; null if the key is not in the Basic Map 
     */
    public V get(K key) {
  
    	return WalkOnTheBeach(key).getValue();
    }

    /**
     * Returns true if and only if the given key is in the Basic Map.  
     *
     * @param key: the key to search for
     * @return: true if and only if the given key is in the Basic Map 
     */
    public boolean containsKey(K key) {

    	return lookup(root, key);
    }

    /**
     * Returns true if and only if the given value is in the Basic Map. 
     * More specifically, returns true if and only if there is at least 
     * one key k in the Basic Map whose associated value is v such that 
     * value.equals(v)   
     *
     * @param value: the value to search for
     * @return: true if and only if the given value is in the Basic Map 
     */
    public boolean containsValue(V value) {
        
        return lookupValue(root, value);
    }

    /**
     * Returns the number of (key,value) pairs in the Basic Map.
     *
     * @return: the number of (key,value) pairs in the Basic Map.
     */
    public int size() {
       
        return this.numItems;
    }

    /**
     * Returns true if and only if the Basic Map is empty.
     *
     * @return: true if the Basic Map is empty, false otherwise
     */
    public boolean isEmpty() {
      
        return this.size() == 0;
    }

    /**
     * Returns a list of the keys in the Basic Map in order from smallest
     * to largest.  
     * 
     * @return: a list of the keys in the Basic Map in order
     */
    public List<K> keys() {
   
    	return keyList(root);
    }

    /**
     * Returns a list of the values in the Basic Map in order of the associated 
     * keys from smallest to largest.  
     * 
     * @return: a list of the values in the Basic Map in key order
     */
    public List<V> values() {
        
        return valueList(root);
    }

    /**
     * Returns the total path length. The total path length is the sum of the 
     * lengths of the paths to each (key, value) pair. 
     * 
     * @return: the total path length
     */
    public int totalPathLength() {
        
        return pathLength(root);
    }

    /**
     * Recursively searches the subtree of node n for a node containing the 
     * given key. Returns true if and only if it is found. 
     * 
     * @param: BSTnode n as root of the subtree to search
     * @param: K key to search for
     * @return: true if the key is found, false otherwise
     */
    private boolean lookup(BSTnode<K, V> n, K key) {
    	if (n == null) {
    		return false;
    	}

    	if (n.getKey().equals(key)) {
    		return true;
    	}

    	if (key.compareTo(n.getKey()) < 0) {
    		// key < this node's key; look in left subtree
            return lookup(n.getLeft(), key);
        }
        
        else {
            // key > this node's key; look in right subtree
            return lookup(n.getRight(), key);
        }
    }
    
    /**
     * Iteratively traverses the tree by key order until either a
     * node with the given key is found or it reaches the end.
     * 
     * @param: K key to search for
     * @return: BSTnode containing given key or null if not found
     */
   private BSTnode<K,V> WalkOnTheBeach(K key){
	   //current node being checked
	   BSTnode<K, V> check = root;
	   while(!check.getKey().equals(key)){	   
		   if(check.getKey().compareTo(key) < 0){
			   check = check.getRight();
		   }
		   else{
			   check = check.getLeft();
		   }		  
	   }
	   return check;
   }
   
   /**
    * Recursively searches the subtree of n for the given key, deletes it if found,
    * and adjusts the subtree accordingly 
    * 
    * @param: BSTnode n as root of the subtree to search
    * @param: K key to search for
    * @return: BSTnode that was deleted
    */
   private BSTnode<K, V> delete(BSTnode<K, V> n, K key) {
	    if (n == null) {
	        return null;
	    }
	    
	    if (key.equals(n.getKey())) {
	        // n is the node to be removed
	        if (n.getLeft() == null && n.getRight() == null) {
	            return null;
	        }
	        if (n.getLeft() == null) {
	            return n.getRight();
	        }
	        if (n.getRight() == null) {
	            return n.getLeft();
	        }
	       
	        // if we get here, then n has 2 children
	        K smallVal = smallest(n.getRight());
	        n.setKey(smallVal);
	        n.setRight( delete(n.getRight(), smallVal) );
	        return n; 
	    }
	    
	    else if (key.compareTo(n.getKey()) < 0) {
	        n.setLeft( delete(n.getLeft(), key) );
	        return n;
	    }
	    
	    else {
	        n.setRight( delete(n.getRight(), key) );
	        return n;
	    }
	}
   
   /**
    * Traverses the subtree of n as far left as possible and returns the key 
    * located there, which is the smallest value. 
    * 
    * @param: BSTnode n as root of the subtree to search
    * @return: K smallest key
    */
   private K smallest(BSTnode<K, V> n){
	   while(n.getLeft() != null){
		   n = n.getLeft();
	   }
	   return n.getKey();
   }
   
   /**
    * Recursively searches the subtree of node n for the appropriate
    * insert location and then inserts the new node.
    * 
    * @param: BSTnode n as root of the subtree to search
    * @param: K key to be added to the new node
    * @param: V value to be added to the new node
    * @return: BSTnode root
    */
   private BSTnode<K, V> insert(BSTnode<K, V> n, K key, V value) 
   	throws DuplicateException {
	    if (n == null) {
	    	return new BSTnode<K, V>(key, value);
	    }
	     
	    if (n.getKey().equals(key)) {
	        throw new DuplicateException();
	    }
	    
	    if (key.compareTo(n.getKey()) < 0) {
	        // add k to the left subtree
	        n.setLeft( insert(n.getLeft(), key, value) );
	        return n;
	    }
	    
	    else {
	        // add k to the right subtree
	        n.setRight( insert(n.getRight(), key, value) );
	        return n;
	    }
	}
   
   /**
    * Recursively searches the subtree of node n for a node containing the 
    * given value. Returns true if and only if it is found. 
    * 
    * @param: BSTnode n as root of the subtree to search
    * @param: V value to search for
    * @return: true if the key is found, false otherwise
    */
   private boolean lookupValue(BSTnode<K,V> n, V value) {
	   if (n == null) {
		   return false;
	   }
	   if (n.getValue().equals(value)) {
		   return true;
	   }
	   //initially false, true if node with value is found
	   boolean found = false;
	   if (n.getLeft() != null) {
		   found = lookupValue(n.getLeft(), value);
	   }
	   if (!found) {
		   found = lookupValue(n.getRight(),value);
	   }	   
	   return found;
   }
   
   /**
    * Recursively performs an in-order traversal of the subtree of node n 
    * in order to calculate the path length.
    * 
    * @param: BSTnode n as root of the subtree to traverse
    * @return: int path length
    */
   private int pathLength(BSTnode<K,V> n) {
	   //counts total path length
	   int count = 0;
	   if (n == null) {
		   return 0;
	   }
	   //in order traversal
	   count += pathLength(n.getLeft());
	   count += getDepth(n.getKey());
	   count += pathLength(n.getRight());
	   return count;	   
   }
   
   /**
    * Given a certain key, iteratively calculates the depth of the
    * node containing that key.
    * 
    * @param: K key to search for
    * @return: int depth of node containing given key
    */
   private int getDepth(K key) {
	   //current node being checked
	   BSTnode<K, V> check = root;
	   //counts the total depth
	   int count = 1;
	   while(check != null && !check.getKey().equals(key)){
		   //go right if current is less than key
		   if(check.getKey().compareTo(key) < 0){
			   check = check.getRight();
		   }
		   else{
			   //go left if current is greater than key
			   check = check.getLeft();
		   }
		   count++;
	   }
	   return count;
   }
   
   /**
    * Recursively performs an in-order traversal of the subtree of node n 
    * in order to create a list of all keys in the tree in order.
    * 
    * @param: BSTnode n as root of the subtree to traverse
    * @return: List of keys in the tree in order
    */
   private List<K> keyList(BSTnode<K,V> n) {
	   //list of keys in order
	   List<K> listOfKeys = new ArrayList<K>();
	   if (n == null) {
		   return new ArrayList<K>();
	   }
	   listOfKeys.addAll(keyList(n.getLeft()));
	   listOfKeys.add(n.getKey());
	   listOfKeys.addAll(keyList(n.getRight()));
	   return listOfKeys;
   }
   
   /**
    * Recursively performs an in-order traversal of the subtree of node n 
    * in order to create a list of all values in the tree in key order.
    * 
    * @param: BSTnode n as root of the subtree to traverse
    * @return: List of values in the tree in key order
    */
   private List<V> valueList(BSTnode<K,V> n) {
	   //list of values in key order
	   List<V> listOfValues = new ArrayList<V>();
	   if (n == null) {
		   return new ArrayList<V>();
	   }
	   listOfValues.addAll(valueList(n.getLeft()));
	   listOfValues.add(n.getValue());
	   listOfValues.addAll(valueList(n.getRight()));
	   return listOfValues;
   }
}
