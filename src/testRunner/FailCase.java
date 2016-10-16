package testRunner;

import finiteAutomata.FiniteAutomata;

import java.util.ArrayList;

class FailCase implements Case {
    @Override
    public boolean verify(FiniteAutomata dfa, ArrayList<String> eachCase) {
      return !dfa.verify(eachCase);
    }
}