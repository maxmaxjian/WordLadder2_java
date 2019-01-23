import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SolutionTest {
    private String input1;
    private String input2;
    private List<String> input3;
    private List<List<String>> expected;
    private Solution soln = new Solution4();

    public SolutionTest(String input1, String input2, List<String> input3, List<List<String>> output) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.expected = output;
    }

    @Parameterized.Parameters
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
//                {"hit", "cog",  Arrays.asList("hot","dot","dog","lot","log","cog"),
//                Arrays.asList(Arrays.asList("hit","hot","lot","log","cog"),
//                        Arrays.asList("hit","hot","dot","dog","cog"))},
//                {"hit", "cog", Arrays.asList("hot","dot","dog","lot","log"),
//                        Collections.EMPTY_LIST},
                {"red", "tax", Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"),
                Arrays.asList(Arrays.asList("red","ted","tad","tax"),
                        Arrays.asList("red","ted","tex","tax"),
                        Arrays.asList("red","rex","tex","tax"))}
        });
    }

    @Test
    public void test() {
        assertEquals(expected, soln.findLadders(input1, input2, input3));
    }
}