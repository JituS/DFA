import commons.State;
import commons.Transitions;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TransitionsTest {
  @Test
  public void shouldReturnNextState() throws Exception {
    State q1 = new State("q1");
    State q2 = new State("q2");
    Transitions Transitions = new Transitions();
    Transitions.setTransition(q1, q2, "1");
    Set<State> nextState = Transitions.process(new HashSet<State>(){{add(q1);}}, "1");
    Assert.assertEquals(new HashSet<State>(){{add(q2);}}, nextState);
  }

  @Test
  public void shouldReturnNullIfStateIsNotPresent() throws Exception {
    State q1 = new State("q1");
    State q2 = new State("q2");
    Transitions Transitions = new Transitions();
    Transitions.setTransition(q1, q2, "1");
    Set<State> nextState = Transitions.process(new HashSet<State>(){{add(q2);}}, "1");
    Assert.assertEquals(new HashSet<State>(), nextState);
  }
}