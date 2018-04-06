package other.assignment;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Gary on 4/2/18.
 */
public class GameEngine implements WordSearchGame{
    char[][] board;
    String boardString;
    SortedSet<String> dictionary;


    public GameEngine(){
        dictionary = new TreeSet<>();
    }
    @Override
    public void loadLexicon(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Incorrect entry");
        }
        Scanner fileScan;
        String word;
        try {
            fileScan = new Scanner(new FileReader(fileName));
            while (fileScan.hasNext()) {
                word = fileScan.nextLine();
                if (word != null && word.length() > 0)
                    dictionary.add(word.toUpperCase());
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Incorrect entry");
        }
    }

    @Override
    public void setBoard(String[] letterArray) {
        int num = (int)Math.sqrt(letterArray.length);
        board = new char[num][num];
        for (int i = 0; i < letterArray.length; i++) {
            int row = i / num;
            int col = i % num;
            board[row][col] = letterArray[i].charAt(0);
        }
    }

    @Override
    public String getBoard() {
        if (board == null)  return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    @Override
    public SortedSet<String> getAllValidWords(int minimumWordLength) {
        SortedSet<String> result = new TreeSet<>();
        for (String word: dictionary) {
            if (word.length() >= minimumWordLength){
                List<Integer> path = isOnBoard(word);
                if (path != null && path.size() > 0)
                    result.add(word);
            }
        }
        return result;
    }

    @Override
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        int result = 0;
        for (String word: words) {
            result += word.length() - minimumWordLength + 1;
        }
        return result;
    }

    @Override
    public boolean isValidWord(String wordToCheck) {
        return dictionary.contains(wordToCheck);
    }

    @Override
    public boolean isValidPrefix(String prefixToCheck) {
        for (String word: dictionary) {
            if (word.startsWith(prefixToCheck)) return true;
        }
        return false;
    }


    private List<List<Integer>> result;
    @Override
    public List<Integer> isOnBoard(String wordToCheck) {
        result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                helper(i, j, 0, wordToCheck, new ArrayList<>());
            }
        }
        return result.size() == 0 ? new ArrayList<>() : result.get(0);
    }
    private void helper(int i, int j, int cur, String word, ArrayList<Integer> list) {
        if (i < 0 || j < 0 || i >= board.length || j >= board.length || board[i][j] == '*' || result.size() > 0)
            return;
        if (cur == word.length() && list.size() == word.length()){
            result.add(new ArrayList<>(list));
            return;
        }
        char old = board[i][j];
        if (word.charAt(cur) == old){
            board[i][j] = '*';
            list.add(i * board.length + j);
            // 上，下，左，右，左上，右上，左下，右下
            int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
            int[] dy = { 0, 0,-1, 1, -1,  1,-1, 1};
            for (int k = 0; k < 8; k++) {
                int newX = i + dx[k];
                int newY = j + dy[k];
                if (newX < 0 || newX >= board.length || newY < 0 || newY >= board.length || board[newX][newY] == '*')
                    continue;
                helper(newX, newY, cur + 1, word, list);
            }
            list.remove(list.size() - 1);
            board[i][j] = old;
        }
    }
}
