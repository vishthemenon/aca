// STAR-CCM+ macro: ultimate_macro_AOA.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;

public class ultimate_macro_AOA extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    Units units_1 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    SolidModelPart solidModelPart_0 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("aerofoil"));

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    simulation_0.get(SimulationPartManager.class).rotateParts(new NeoObjectVector(new Object[] {solidModelPart_0}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new NeoObjectVector(new Object[] {units_1, units_1, units_1}), -0.0349065850398866, labCoordinateSystem_0);

    simulation_0.get(SimulationPartManager.class).rotateParts(new NeoObjectVector(new Object[] {solidModelPart_0}), new DoubleVector(new double[] {0.0, 0.0, 1.0}), new NeoObjectVector(new Object[] {units_1, units_1, units_1}), 0.0523598775598299, labCoordinateSystem_0);
  }
}
