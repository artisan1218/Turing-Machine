import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DFA {

    String input;
    HashSet<String> setOfStates;
    String startState;
    String acceptState;
    ArrayList<String[]> transition;
    HashMap<String, Node> map = new HashMap<>();

    DFA(HashSet<String> setOfStates, String startState, String acceptState, ArrayList<String[]> transition) {
	this.setOfStates = setOfStates;
	this.startState = startState;
	this.acceptState = acceptState;
	this.transition = transition;

	// construct the DFA
	for (String stateName : this.setOfStates) {
	    if (stateName.equals(this.acceptState)) {
		Node node = new Node();
		node.changeToAcceptState();
		this.map.put(stateName, node);
	    } else {
		this.map.put(stateName, new Node());
	    }
	}

	for (int trans = 0; trans < transition.size(); trans++) {
	    String[] t = transition.get(trans);
	    Node fromState = this.map.get(t[0]);
	    String value = t[1];
	    Node toState = this.map.get(t[2]);
	    Edge eVal = new Edge(value, toState);
	    fromState.addEdge(eVal);
	    // this is the enable "." feature
	    fromState.addEdge(new Edge(".", toState));
	}
    }

    boolean run(String input) {
	this.input = input;
	boolean match = false;

	Node curr = this.map.get(this.startState);
	for (int i = 0; i < this.input.length(); i++) {
	    String c = String.valueOf(input.charAt(i));
	    curr = curr.goToNext(c);
	    if (curr == null) {
		break;
	    }
	}

	if (curr != null && curr.isAcceptState) {
	    match = true;
	}

	return match;
    }

    class Node {
	ArrayList<Edge> edges;
	boolean isAcceptState;

	// a node can go to different edges
	Node() {
	    this.edges = new ArrayList<>();
	    this.isAcceptState = false;
	}

	Node goToNext(String c) {
	    for (int i = 0; i < this.edges.size(); i++) {
		// go to the next state if the current char matches or the val is dot
		if (this.edges.get(i).val.equals(c) || this.edges.get(i).val.equals(".")) {
		    return this.edges.get(i).pointsTo;
		}
	    }
	    return null;
	}

	void addEdge(Edge e) {
	    this.edges.add(e);
	}

	void changeToAcceptState() {
	    this.isAcceptState = true;
	}

	boolean isAcceptState() {
	    return this.isAcceptState;
	}

    }

    class Edge {
	String val;
	Node pointsTo;

	// an edge has a value and points to another state
	Edge(String v, Node to) {
	    this.val = v;
	    this.pointsTo = to;
	}
    }
}
