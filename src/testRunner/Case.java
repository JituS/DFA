package testRunner;

import finiteAutomata.FiniteAutomata;

import java.util.ArrayList;

interface Case {
  boolean verify(FiniteAutomata fa, ArrayList<String> eachCase);
}
