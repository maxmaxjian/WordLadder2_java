import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution1 implements Solution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<Integer> visit = new ArrayList<>(Collections.nCopies(wordList.size(), 0));
        List<List<String>> result = laddersFrom(beginWord, endWord, wordList, visit);
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < result.size(); i++) {
            len = Math.min(len, result.get(i).size());
        }
        List<List<String>> rs = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).size() == len) {
                rs.add(result.get(i));
            }
        }
        return rs;
    }

    private List<List<String>> laddersFrom(String start, String end, List<String> wordList, List<Integer> visit) {
        if (start.equals(end)) {
            List<List<String>> result = new ArrayList<>();
            List<String> temp = new ArrayList<>();
            temp.add(start);
            result.add(temp);
            return result;
        } else {
            List<List<String>> result = new ArrayList<>();
            List<String> next = findNext(start, wordList, visit);
            for (String nx : next) {
                List<Integer> copy = new ArrayList<>(visit);
                for (int i = 0; i < copy.size(); i++) {
                    if (wordList.get(i).equals(nx)) {
                        copy.set(i, 1);
                        break;
                    }
                }
                List<List<String>> temp = laddersFrom(nx, end, wordList, copy);
                for (List<String> list : temp) {
                    ((ArrayList<String>)list).add(0, start);
                    result.add(list);
                }
            }
            return result;
        }
    }

    private List<String> findNext(String start, List<String> wordList, List<Integer> visit) {
        List<String> next = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (visit.get(i) == 0 && diffInOne(start, wordList.get(i))) {
                next.add(wordList.get(i));
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
