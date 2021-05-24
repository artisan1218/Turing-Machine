import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TMtest {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	String startState = "a";
	String acceptState = "d";
	HashSet<String> setOfStates = new HashSet<String>(Arrays.asList("a", "b", "c", "d"));
	ArrayList<String[]> transition = new ArrayList<>();
	transition.add(new String[] { "a", "0", "0", "R", "a" });
	transition.add(new String[] { "a", "1", "1", "R", "a" });
	transition.add(new String[] { "a", "*", "*", "L", "b" });
	transition.add(new String[] { "b", "1", "*", "L", "c" });
	transition.add(new String[] { "b", "0", "*", "L", "c" });
	transition.add(new String[] { "c", "1", "*", "L", "d" });
	transition.add(new String[] { "c", "0", "*", "L", "d" });

	String input = "*01100*";
	TuringMachine tm = new TuringMachine(setOfStates, startState, acceptState, transition);
	System.out.println(tm.run(input));

    }

}
