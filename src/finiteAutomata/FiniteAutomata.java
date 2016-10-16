package finiteAutomata;

import java.util.List;

public interface FiniteAutomata {
  boolean verify(List<String> inputString);
  String getName();
}
