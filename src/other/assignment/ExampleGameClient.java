package other.assignment;

import java.util.SortedSet;

/**
 * ExampleGameClient.java
 * A sample client for the assignment handout.
 *
 * @author      Dean Hendrix (dh@auburn.edu)
 * @version     2018-03-22
 *
 */
public class ExampleGameClient {

   /** Drives execution. */
   public static void main(String[] args) {
      String path = "/Users/liguorui/Desktop/assignment/words_small.txt";
      WordSearchGame game = WordSearchGameFactory.createGame();
      game.loadLexicon(path);
      String[] strs =  {"E", "E", "C", "A",
                        "A", "L", "E", "P",
                        "H", "N", "B", "O",
                        "Q", "T", "T", "Y"};
      String[] strs1 = {"A", "B", "A", "C",
                        "A", "L", "E", "K",
                        "H", "N", "B", "O",
                        "Q", "T", "T", "Y"};
      game.setBoard(strs);
      System.out.println(game.getBoard());
      System.out.print("LENT is on the board at the following positions: ");
      System.out.println(game.isOnBoard("LENT"));
      System.out.print("POPE is not on the board: ");
      System.out.println(game.isOnBoard("POPE"));
      System.out.println("All words of length 5 or more: ");
      SortedSet<String> set = game.getAllValidWords(5);
      System.out.println(set);
      System.out.println(game.getScoreForWords(set, 3));
   }
}

/*

RUNTIME OUTPUT:

LENT is on the board at the following positions: [5, 6, 9, 13]
POPE is not on the board: []
All words of length 6 or more:
[ALEPOT, BENTHAL, PELEAN, TOECAP]

 */
