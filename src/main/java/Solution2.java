import java.util.*;

public class Solution2 implements Solution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> cands = new HashSet<>(wordList);
        List<List<String>> result = laddersFrom(beginWord, endWord, cands);
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

    private List<List<String>> laddersFrom(String start, String end, Set<String> cands) {
        if (start.equals(end)) {
            List<List<String>> result = new ArrayList<>();
            List<String> temp = new ArrayList<>();
            temp.add(start);
            result.add(temp);
            return result;
        } else {
            List<List<String>> result = new ArrayList<>();
            List<String> next = findNext(start, cands);
            for (String nx : next) {
                Set<String> copy = new HashSet<>(cands);
                copy.remove(nx);
                List<List<String>> temp = laddersFrom(nx, end, copy);
                for (List<String> list : temp) {
                    ((ArrayList<String>)list).add(0, start);
                    result.add(list);
                }
            }
            return result;
        }
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
