package testRunner;

import dfa.FiniteAutomata;

import java.util.ArrayList;

public interface Cases {
  boolean verify(FiniteAutomata fa, ArrayList<String> eachCase);
}
