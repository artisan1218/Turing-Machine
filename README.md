
This repo includes two types of automata:
1. DFA (Deterministic Finite Automata)
2. Turing Machine

### 1. DFA (Deterministic Finite Automata)
DFA is represented by 5-tuple:
1. ∑ is the non-empty set of input alphabet/symbols: `String input`
2. S is the non-empty set of states: `HashSet<String> setOfStates`
3. s0 is the starting state: `String startState`
4. 𝛿 is the transition function: 𝛿 : S × ∑ -> S: `ArrayList<String[]> transition`, the String array must have three elements in order: fromNode, transition value, toNode
5. F is the set of final/accepting states: `String acceptState`

When initialize the DFA class, four types of elements need to be presented: the non-empty set of states, the starting state, the set of final/accepting states and the transition function.\
Sample code:
```
String startState = "a";
String acceptState = "c";
HashSet<String> setOfStates = new HashSet<String>(Arrays.asList("a", "b", "c"));
ArrayList<String[]> transition = new ArrayList<>();
transition.add(new String[] { "a", "0", "b" });
transition.add(new String[] { "b", "1", "c" });
transition.add(new String[] { "c", "0", "c" });
transition.add(new String[] { "c", "1", "c" });

DFA myDFA = new DFA(setOfStates, startState, acceptState, transition);
```
This DFA has a diagram that looks like this:\
<img src="https://user-images.githubusercontent.com/25105806/119215683-1246a080-ba84-11eb-9842-68403bacffa9.png" width="50%" height="50%">


When testing a specific input string, the input need to be in String type, and `.run()` function will output whether the current DFA accepts the given input string: 
```
String input = "01.0111";
System.out.println(myDFA.run(input));
```
This example builds a DFA that will accepty any 0/1 string that starts with `01` sequence
* Note that the kleene star(\*) can be used to represent any character.


### 2. Turing Machine
Turing Machine is represented by 7-tuple:
1. ∑ is the non-empty set of input alphabet/symbols: `String input`, which stands for an infinitely long input tape string.
2. Non-empty set of symbols, tape alphabets: not needed when initialize the TM.
3. S is the non-empty set of states: `HashSet<String> setOfStates`
4. s0 is the starting state: `String startState`
5. 𝛿 is the transition function: 𝛿 : S × ∑ -> S: `ArrayList<String[]> transition`, the String array must have five elements in order: fromNode, read value, update value, direction, toNode
6. F is the set of final/accepting states: `String acceptState`
7. Reject states: default state, not needed when iniitialize the TM.

When initialize the TM class, four types of elements need to be presented: the non-empty set of states, the starting state, the set of final/accepting states and the transition function.

This Turing Machine reads a 0/1 tape string as input and delete the last two elements of this input.
Sample code:
```
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

TuringMachine tm = new TuringMachine(setOfStates, startState, acceptState, transition);
```
This Turing Machine has a diagram that looks like this:\
<img src="https://user-images.githubusercontent.com/25105806/119282596-0f1bf380-bbef-11eb-9438-89efffb10280.png" width="65%" height="65%">

When testing a specific input string, the input need to be in String type, and `.run()` function will output the result. If the current Turing Machine does not accept the given ipnut string, the result will be `"Input string not accepted"`: 
```
String input = "*01100*";
System.out.println(tm.run(input));
```
Output: `011`

* Note that the input string must start and end with `*`, which stands for the boundary of infinitely long input tape string.
* If the Turing Machine does not halt due to a bad configuration, the Turing Machine will run forever due to Turing undecidability.
