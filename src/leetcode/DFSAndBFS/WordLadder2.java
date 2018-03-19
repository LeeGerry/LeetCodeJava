package leetcode.DFSAndBFS;

import java.util.*;

/**
 * LeetCode 126
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     Return
     [
         ["hit","hot","dot","dog","cog"],
         ["hit","hot","lot","log","cog"]
     ]

 */
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord); // 把endWord加到set中，保证构建无向图Graph的完整性

        List<List<String>> res = new ArrayList<>();
        if (!wordSet.contains(endWord)) return res; // 如果图中没有endWord直接返回即可

        List<String> path = new LinkedList<>(); // 用来保存路径
        Map<String, List<String>> graph = new HashMap<>(); // 用来保存无向图
        Map<String, Integer> dist = new HashMap<>(); // 用来保存树状层次，便于DFS倒着找路径的时候使用
        bfs(graph, dist, beginWord, wordSet); // 用bfs来构建图，同时构建树状层次结构。
        dfs(res, path, endWord, beginWord, dist, graph); // 用无向图和辅助树状层次结构来进行深搜，找到所有最短路径
        return res;
    }

    /** 辅助函数：根据给定word找出邻接点列表。
     *  例如给定dog, set为["hot","dot","dog","lot","log","cog"],那么返回["dot", "log", "cog"], 不包含自身
     */
    List<String> getNeighbars(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                char[] chs = word.toCharArray();
                if (c != chs[i]) { // 排除自身
                    chs[i] = c;
                    String next = new String(chs);
                    if (wordSet.contains(next))     res.add(next);
                }
            }
        }
        return res;
    }
    // bfs 构建无向图和dist结构
    void bfs(Map<String, List<String>> graph, Map<String, Integer> dist, String beginWord, Set<String>  wordSet) {
        Queue<String> queue =  new LinkedList<>();
        queue.offer(beginWord);
        dist.put(beginWord, 0);
        for (String w: wordSet) graph.put(w, new LinkedList<>()); // 初始化图中的个节点，Key是节点，value 是邻接表
        while (!queue.isEmpty()) {
            String word = queue.poll();
            List<String> neighbors = getNeighbars(word, wordSet);
            for (String neighbor : neighbors) {
                graph.get(neighbor).add(word);
                if (!dist.containsKey(neighbor)) {
                    dist.put(neighbor, dist.get(word) + 1); // 树状层次
                    queue.offer(neighbor);
                }
            }
        }
    }
    // dfs 反向构建路径
    void dfs(List<List<String>> res,
             List<String> path,
             String word, String beginWord,
             Map<String, Integer> dist,
             Map<String, List<String>> graph) {
        if (word.equals(beginWord)) { // 如果找到了开始节点，说明一条完整的路径构造完毕
            path.add(0, word); // 加入头
            res.add(new LinkedList<>(path)); // 加入结果
            path.remove(0); // 回溯
        }
        // 不是开始节点，那么遍历该节点邻接表，邻接表中是能到达该节点的上层节点
        for (String neighbor: graph.get(word)) {
            // 这里是层次控制，保证每次找的都是紧挨着的上层
            if (dist.containsKey(neighbor) && dist.get(word) == dist.get(neighbor) + 1) {
                path.add(0, word);//把当前节点加入路径
                dfs(res, path, neighbor, beginWord, dist, graph);//沿着neighbor节点继续向上找
                path.remove(0); // 回溯
            }
        }
    }



    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","cit","dot","cag","cig","dog","lot","log","cog"};
        String[] wordList2 = {"hot","dot","dog","lot","log","cog"};
        List<String> list1 = Arrays.asList(wordList);
        List<String> list2 = Arrays.asList(wordList2);
//        WordLadder2 wl = new WordLadder2();
//        System.out.println(wl.findLadders(beginWord, endWord, list1));
        WordLadder2 wl2 = new WordLadder2();
        System.out.println(wl2.findLadders(beginWord, endWord, list2));

    }
}
