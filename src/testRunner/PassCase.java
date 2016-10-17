package testRunner;

import finiteAutomata.FiniteAutomata;

import java.util.ArrayList;

class PassCase implements Case {
  @Override
  public boolean verify(FiniteAutomata finiteAutomata, ArrayList<String> eachCase) {
    return finiteAutomata.verify(eachCase);
  }
}