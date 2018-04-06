package other;

import java.util.*;

/**
 * Created by Gary on 3/28/18.
 */
public class FindScore {
    private int L;
    private Map<Integer, Set<String>> map;
    private Set<String> words;
    private char[][] board;
    private int m, n;

    public FindScore(Set<String> ws, char[][] b, int l){
        words = ws;
        board = b;
        L = l;
        map = new HashMap<>();
    }
    public void createMapByDfsAndBackTracking() {
        if (board == null || board.length == 0 || board[0].length == 0
                || words == null || words.size() == 0)  return ;
        m = board.length; n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                StringBuilder sb = new StringBuilder();
                help(i, j, sb);
            }
        }
        //System.out.println(map);
    }

    private void help(int i, int j, StringBuilder sb) {
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] == '*') return;
        sb.append(board[i][j]);
        String s = sb.toString();
        if (s.length() >= L && words.contains(s)){
            Set<String> list = map.get(s.length());
            if (list == null) map.put(s.length(), new HashSet<>());
            map.get(s.length()).add(s);
        }
        char old = board[i][j];
        board[i][j] = '*';
        // 上，下，左，右，左上，右上，左下，右下
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = { 0, 0,-1, 1, -1,  1,-1, 1};
        for (int k = 0; k < 8; k++) {
            int newX = i + dx[k];
            int newY = j + dy[k];
            if (newX < 0 || newX >= m
                    || newY < 0 || newY >= n
                    || board[newX][newY] == '*')
                continue;
            help(newX, newY, sb);
        }
        board[i][j] = old;
        sb.deleteCharAt(sb.length() - 1);
    }

    private void printMap(){
        if (map == null || map.size() == 0) {
            System.out.println("map为空，没有找到！");
            return;
        }
        for (Map.Entry<Integer, Set<String>> entry: map.entrySet()) {
            Set<String> words = entry.getValue();
            System.out.println("长度为:" + entry.getKey() + "的单词有" + words.size() + "个,分别为:" + words.toString());
        }

    }
    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'C', 'A'},
                {'A', 'L', 'E', 'P'},
                {'H', 'N', 'B', 'O'},
                {'Q', 'T', 'T', 'Y'},
        };
        Set<String> words = new HashSet<>();
        words.add("ALBEE");words.add("ALCAE");words.add("ALEPOT");
        words.add("ANELE");words.add("BECAP");words.add("BELAH");
        words.add("BELEE");words.add("BENTHAL");words.add("BENTY");
        words.add("BLENT");words.add("CAPEL");words.add("CAPOT");
        words.add("CENTO");words.add("CLEAN");words.add("ELEAN");
        words.add("LEANT");words.add("LENTH");words.add("LENTO");
        words.add("NEELE");words.add("PEACE");words.add("PEELE");
        words.add("PELEAN");words.add("PENAL");words.add("THANE");
        words.add("TOECAP");words.add("TOPEE");
        FindScore fs = new FindScore(words, board, 5);
        fs.createMapByDfsAndBackTracking();
        fs.printMap();
    }
}
