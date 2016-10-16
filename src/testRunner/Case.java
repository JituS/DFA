package testRunner;

import finiteAutomata.FiniteAutomata;

import java.util.ArrayList;

public interface Case {
  boolean verify(FiniteAutomata fa, ArrayList<String> eachCase);
}
