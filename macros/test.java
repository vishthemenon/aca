// STAR-CCM+ macro: test.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;

public class test extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Solution solution_0 = 
      simulation_0.getSolution();

    solution_0.clearSolution(Solution.Clear.History);
  }
}
