import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class test {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	String startState = "a";
	String acceptState = "c";
	HashSet<String> setOfStates = new HashSet<String>(Arrays.asList("a", "b", "c"));
	ArrayList<String[]> transition = new ArrayList<>();
	transition.add(new String[] { "a", "0", "b" });
	transition.add(new String[] { "b", "1", "c" });
	transition.add(new String[] { "c", "0", "c" });
	transition.add(new String[] { "c", "1", "c" });

	String input = "01.0111";
	DFA myDFA = new DFA(setOfStates, startState, acceptState, transition);
	System.out.println(myDFA.run(input));
    }

}
