package other.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;   

/**
 * Scans in file and creates a word search game.
 *
 * @author Phil Burchfield (pzb0024@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-22
 * 
 */
public class BoggleWordSearch implements WordSearchGame {

   private TreeSet<String> lexicon; 
   private List<Integer> path;
   private List<Integer> actualPath;
   private int length;
   private String[][] board;
   private Boolean[][]visited;
   private SortedSet<String> vaildWords;
   private int minLength;
   public boolean lexiconLoaded;
   
   
   /**
    * Constructor for BoggleWordSearchClass.
    */
   
   
   public BoggleWordSearch() {
      lexicon = new TreeSet<String>();
      path = new ArrayList<Integer>();
      vaildWords = new TreeSet<String>();
      actualPath = new ArrayList<Integer>();
   }
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
    
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("File Not Loaded");
      }
      Scanner scanFile;
      Scanner lineScanner;
      String line;
      try {
         scanFile = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (scanFile.hasNext()) {
            line = scanFile.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(" ");
            while (lineScanner.hasNext()) {
               lexicon.add(lineScanner.next().toLowerCase());
            }
         
         }
      } 
      catch (Exception e) {
         throw new IllegalArgumentException("File Not Loaded");
      }
   
      lexiconLoaded = true;
      //System.out.println(lexiconLoaded);
   }      
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
    
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException("Incorrect File Type");
      }
      
      double dimension = Math.sqrt(letterArray.length);
   
      if (dimension % 1 > 0) {
         throw new IllegalArgumentException("Incorrect Dimension");
      }
      
      else {
         length = (int) dimension;
         board = new String[length][length];
         visited = new Boolean[length][length];
         int count = 0;
         for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
               visited[i][j] = false;
               board[i][j] = letterArray[count].toLowerCase();
               count++;
            }
         }
      }
   }  
       
   /**
    * Creates a String representation of the board, suitable for printing to
    * standard out. Note that this method can always be called since
    * implementing classes should have a default board.
    */
    
   public String getBoard() {
      String result = "";
      for (String[] arrayOfStrings: board) {
         for (String string: arrayOfStrings) {
            result += string;
         }
      }      
      return result;    
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
    
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
      minLength = minimumWordLength;
      vaildWords.clear();
      //System.out.println(lexiconLoaded);
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load Lexicon");
      }
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Invalid Word Length");
      }
      
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            findWords(board[i][j], i, j);
         }
      }
      return vaildWords;
   }   
   
  /**
   * locates word for getAllValidWords().
   * @param word is the word.
   * @param x is the x value of the word.
   * @param y is the y value of the word.
   */
   public void findWords(String word, int x, int y) {
   
      if (isValidPrefix(word) == false) {
         return;
      }
   
      visited[x][y] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         vaildWords.add(word.toUpperCase());
      }
   
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((x + i) <= ((int) length - 1)
               && (y + j) <= ((int) length - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !visited[x + i][y + j]) {
               visited[x + i][y + j] = true;
               findWords(word + board[x + i][y + j], x + i, y + j);
               visited[x + i][y + j] = false;
            }
         }
      }
      visited[x][y] = false;
   }

      
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load lexicon");
      }
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Invalid Word Length");
      }
   
      int score = 0;
   
      for (String word: words) {
         int size = word.length();
         score += 1 + (size - minimumWordLength);
      }
   
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
    
   public boolean isValidWord(String wordToCheck) {
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load Lexicon Not Called");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid Word");
      } 
      return lexicon.contains(wordToCheck.toUpperCase());
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
    
   public boolean isValidPrefix(String prefixToCheck) {
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load Lexicon Not Called");
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("Invalid Word");
      } 
      
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
    
   public List<Integer> isOnBoard(String wordToCheck) {
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load Lexicon Not Called");
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid Word");
      } 
      
      path.clear();
      actualPath.clear();
   
      for (int i = 0; i < (int) length; i++) {
         for (int j = 0; j < length; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               int value = j + (i * length);
               path.add(value);
               recursion(wordToCheck, new StringBuilder (board[i][j]), i, j);
               if (!actualPath.isEmpty()) {
                  return actualPath;
               }
               path.clear();
               actualPath.clear();
            }
         }
      }
      return path;
   }
   
  /**
   * Recursion for isOnBoard().
   * @param wordToCheck is the word to check.
   * @param current is the current word.
   * @param x is the current x value.
   * @param y is the current y value.
   */
   
   public void recursion(String wordToCheck, StringBuilder current, int x, int y) {
   
      visited[x][y] = true;
      if (!(isValidPrefix(current.toString()))) {
         return;
      }
      if (current.toString().toUpperCase().equals(wordToCheck.toUpperCase())) {
         ArrayList<Integer> actualPath = new ArrayList<Integer>(path);
         return;
      }
      // get neighbors
      ArrayList<IndexLocation> neighbors = new ArrayList<IndexLocation>();
      for (int i = -1; i <=1; i++) {
         for (int j = -1; j <= 1; j++) {
         
            IndexLocation in = new IndexLocation(x + i, y + j);
            if(!(i == 0 && j == 0)){
               if (in.x >= 0 && in.x < length - 1 && in.y >= 0 && in.y < length - 1) {
                  visited[x + i][y + j] = true;
                  neighbors.add(in);
                  current.append(board[x + i][y + j]); 
                  recursion(wordToCheck, current, x + i, y + j);
               }  
            }
           
         }
      }
      for (IndexLocation neighbor : neighbors) {
         if (wordToCheck.toUpperCase().equals(current.toString())) {
            return;
         }
         if ((neighbor.x >= 0 && neighbor.x < length && neighbor.y >= 0 && neighbor.y < length)) {
            path.add(neighbor.x * length + neighbor.y);
         }
      }
      
     
      current.deleteCharAt(current.length() - 1);
      path.remove(path.size() - 1);
      visited[x][y] = false;
      return;
   }
   
   private class IndexLocation extends BoggleWordSearch {
      int x;
      int y;
   
      public IndexLocation(int x, int y) {
         this.x = x;
         this.y = y;
      }
   }

}
