package testRunner;

import dfa.FiniteAutomata;

import java.util.ArrayList;

public interface Case {
  boolean verify(FiniteAutomata fa, ArrayList<String> eachCase);
}
