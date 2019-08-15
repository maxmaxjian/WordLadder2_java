import java.util.*;

public class Solution4 implements Solution {
    @Override
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return Collections.EMPTY_LIST;
        }
        Map<String, List<String>> map = twoEndBFS(beginWord, endWord, new HashSet<>(wordList));
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
        List<List<String>> rs = getResult(Arrays.asList(endWord), beginWord, map);
        int len = Integer.MAX_VALUE;
        for (List list : rs) {
            len = Math.min(len, list.size());
        }
        List<List<String>> result = new ArrayList<>();
        for (List list : rs) {
            if (list.size() == len) {
                result.add(list);
            }
        }
        return result;
    }

    private List<List<String>> getResult(List<String> curr, String beginWord, Map<String, List<String>> map) {
        if (beginWord.equals(curr.get(0))) {
            return Arrays.asList(curr);
        } else {
            List<List<String>> result = new ArrayList<>();
            if (map.containsKey(curr.get(0))) {
                for (String prev : map.get(curr.get(0))) {
                    if (!curr.contains(prev)) {
                        ArrayList<String> copy = new ArrayList<>(curr);
                        copy.add(0, prev);
                        result.addAll(getResult(copy, beginWord, map));
                    }
                }
            }
            return result;
        }
    }

    private Map<String, List<String>> twoEndBFS(String beginWord, String endWord, Set<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        Set<String> words = new HashSet<>(wordList);
        genMap(words, begin, end, map, true);
        return map;
    }

    private void genMap(Set<String> words, Set<String> begin, Set<String> end, Map<String, List<String>> map, boolean forward) {
//        System.out.println("Before swapping: begin = " + new ArrayList<>(begin) + ", end = " + new ArrayList<>(end));
//        boolean found = false; forward = true;
//        if (end.size() > begin.size()) {
//            Set<String> temp = begin;
//            begin = end;
//            end = temp;
//            forward = false;
//        }
//        System.out.println("After swapping: begin = " + new ArrayList<>(begin) + ", end = " + new ArrayList<>(end));
//        words.removeAll(begin);
////        words.removeAll(end);
        Set<String> currSet;
        if (forward) {
            words.removeAll(begin);
            currSet = begin;
        } else {
            words.removeAll(end);
            currSet = end;
        }
        System.out.println("CurrSet = " + new ArrayList<>(currSet));

        if (words.isEmpty()) {
            return;
        }

        Set<String> set = new HashSet<>();
        for (String curr : currSet) {
            for (int i = 0; i < curr.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch != curr.charAt(i)) {
                        String next = new StringBuilder().append(curr, 0, i).append(ch)
                                .append(curr.substring(i + 1)).toString();
                        if (words.contains(next)) {
                            set.add(next);
                            String key = forward ? next : curr;
                            String val = forward ? curr : next;
                            if (!map.containsKey(key)) {
                                map.put(key, new ArrayList<>());
                            }
                            map.get(key).add(val);
                        }
                    }
                }
            }
        }
        if (!set.isEmpty()) {
            if (forward) {
                begin = set;
            } else {
                end = set;
            }
            genMap(words, begin, end, map, !forward);
        }
    }

}
