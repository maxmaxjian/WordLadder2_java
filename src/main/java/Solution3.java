import javafx.util.Pair;

import java.util.*;

public class Solution3 implements Solution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return Collections.EMPTY_LIST;
        }
        List<List<String>> result = new ArrayList<>();
        Queue<Pair<List<String>, Set<String>>> queue = new LinkedList<>();
        queue.add(new Pair<>(Arrays.asList(beginWord), new HashSet<>(wordList)));
        while (!queue.isEmpty()) {
            Pair<List<String>,Set<String>> curr = queue.remove();
            System.out.println("curr = " + curr.getKey() + ", set = " + curr.getValue());
            if (diffInOne(curr.getKey().get(curr.getKey().size()-1),endWord)) {
                result.add(curr.getKey());
            } else {
                List<String> nextStrs = findNext(curr.getKey().get(curr.getKey().size()-1), curr.getValue());
                for (String nxStr : nextStrs) {
                    curr.getValue().remove(nxStr);
                    List<String> cpy = new ArrayList<>(curr.getKey());
                    cpy.add(nxStr);
//                        System.out.println("curr = " + cpy + ", set = " + curr.getValue());
                    queue.add(new Pair<>(cpy, curr.getValue()));
                }
            }
        }

        for (List<String> list : result) {
            list.add(endWord);
        }
        return result;
    }

    private List<String> findNext(String start, Set<String> cands) {
        List<String> next = new ArrayList<>();
        for (String cand : cands) {
            if (diffInOne(start, cand)) {
                next.add(cand);
            }
        }
        return next;
    }

    private boolean diffInOne(String s, String t) {
        boolean found = false;
        if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (!found) {
                        found = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return found;
    }
}
