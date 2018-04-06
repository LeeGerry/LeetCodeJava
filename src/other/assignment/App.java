package other.assignment;

/**
 * Created by Gary on 4/2/18.
 */
public class App {
    public static void main(String[] args) {
        String path = "/Users/liguorui/Desktop/assignment/words_small.txt";
        GameEngine g = new GameEngine();
        g.loadLexicon(path);
    }
}
