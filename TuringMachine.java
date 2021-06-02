import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TuringMachine {

    String input;
    HashSet<String> setOfStates;
    String startState;
    String acceptState;
    ArrayList<String[]> transition;
    HashMap<String, Node> map = new HashMap<>();

    TuringMachine(HashSet<String> setOfStates, String startState, String acceptState, ArrayList<String[]> transition) {
	this.setOfStates = setOfStates;
	this.startState = startState;
	this.acceptState = acceptState;
	this.transition = transition;

	// construct TM
	for (String stateName : this.setOfStates) {
	    if (stateName.equals(this.acceptState)) {
		Node node = new Node(stateName);
		node.changeToAcceptState();
		this.map.put(stateName, node);
	    } else {
		this.map.put(stateName, new Node(stateName));
	    }
	}

	for (int trans = 0; trans < transition.size(); trans++) {
	    String[] t = transition.get(trans);

	    // trans: [q0, 0, 0, R, q0]
	    Node fromNode = this.map.get(t[0]);
	    Node toNode = this.map.get(t[4]);

	    Edge edge = new Edge(t, toNode);
	    fromNode.addEdge(edge);
	}

    }

    boolean check(String input) {
	StringBuilder inputSB = new StringBuilder(input);

	Node curr = this.map.get(this.startState);

	int idx = 0;
	// read all leading * at the input
	while (inputSB.charAt(idx) == '*') {
	    idx += 1;
	}

	while (curr.isAcceptState == false && (idx <= inputSB.length() && idx >= 0)) {
	    String readVal = String.valueOf(inputSB.charAt(idx));
	    boolean found = false;
	    for (int i = 0; i < curr.edges.size(); i++) {
		// if read value is equal to given c, go to pointsTo state
		if (curr.edges.get(i).trans[1].equals(readVal)) {
		    found = true;
		    String change2Val = curr.edges.get(i).trans[2];
		    inputSB.setCharAt(idx, change2Val.charAt(0));
		    if (idx == inputSB.length() - 1) {
			inputSB.append("*");
		    } else if (idx == 0) {
			inputSB.insert(0, "*");
			idx += 1;
		    }
		    String direction = curr.edges.get(i).trans[3];
		    curr = curr.goToNext(readVal);
		    if (curr == null) {
			return false;
		    }
		    if (direction.equals("L")) {
			idx--;
		    } else if (direction.equals("R")) {
			idx++;
		    }
		    break;
		}
	    }
	    if (found == false) {
		return false;
	    }
	}

	return curr.isAcceptState;
    }

    String run(String input) {
	StringBuilder inputSB = new StringBuilder(input);

	Node curr = this.map.get(this.startState);

	int idx = 0;
	// read all leading * at the input
	while (inputSB.charAt(idx) == '*') {
	    idx += 1;
	}

	while (curr.isAcceptState == false && (idx <= inputSB.length() && idx >= 0)) {
	    String readVal = String.valueOf(inputSB.charAt(idx));
	    boolean found = false;
	    for (int i = 0; i < curr.edges.size(); i++) {
		// if read value is equal to given c, go to pointsTo state
		if (curr.edges.get(i).trans[1].equals(readVal)) {
		    found = true;
		    String change2Val = curr.edges.get(i).trans[2];
		    inputSB.setCharAt(idx, change2Val.charAt(0));
		    if (idx == inputSB.length() - 1) {
			inputSB.append("*");
		    } else if (idx == 0) {
			inputSB.insert(0, "*");
			idx += 1;
		    }
		    String direction = curr.edges.get(i).trans[3];
		    curr = curr.goToNext(readVal);
		    if (curr == null) {
			return "Input string not accepted";
		    }
		    if (direction.equals("L")) {
			idx--;
		    } else if (direction.equals("R")) {
			idx++;
		    }
		    break;
		}
	    }
	    if (found == false) {
		return "Input string not accepted";
	    }
	}

	if (curr.isAcceptState == false) {
	    return "Input string not accepted";
	} else {
	    int left = 0;
	    while (inputSB.charAt(left) == '*') {
		left++;
	    }
	    int right = inputSB.length() - 1;
	    while (inputSB.charAt(right) == '*') {
		right--;
	    }
	    return inputSB.toString().substring(left, right + 1);
	}
    }

    class Node {

	String stateName;
	ArrayList<Edge> edges;
	boolean isAcceptState;

	Node(String stateName) {
	    this.stateName = stateName;
	    this.edges = new ArrayList<>();
	    this.isAcceptState = false;
	}

	Node goToNext(String c) {
	    for (int i = 0; i < this.edges.size(); i++) {
		// if read value is equal to given c, go to pointsTo state
		if (this.edges.get(i).trans[1].equals(c)) {
		    return this.edges.get(i).pointsTo;
		}
	    }
	    return null;
	}

	String getDirection(String c) {
	    String direction = null;
	    for (int i = 0; i < this.edges.size(); i++) {
		// if read value is equal to given c, go to pointsTo state
		if (this.edges.get(i).trans[1].equals(c)) {
		    direction = this.edges.get(i).trans[4];
		}
	    }
	    return direction;
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
	// if read 0, change it to 1 and go to 2 (left or right)
	String[] trans;
	Node pointsTo;

	Edge(String[] transition, Node to) {
	    this.trans = transition;
	    this.pointsTo = to;
	}
    }
}
