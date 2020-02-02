// STAR-CCM+ macro: part1dAutoAoaChange.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;

public class part1dAutoAoaChange extends StarMacro {

  public void execute() {
    execute0(0.0,"ClCd0.csv", "dump1.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    execute0(2.0,"ClCd2.csv", "dump2.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    execute0(4.0,"ClCd4.csv", "residual4.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    execute0(6.0,"ClCd6.csv", "dump3.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    execute0(8.0,"ClCd8.csv", "dump4.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");

  }

  private void execute0(double aoa, String cdClFile, String residualFile, String relativePath) {
    cdClFile = relativePath + cdClFile;
    residualFile = relativePath + residualFile;

    Simulation simulation_0 = 
      getActiveSimulation();

    Solution solution_0 = 
      simulation_0.getSolution();

    solution_0.clearSolution(Solution.Clear.History, Solution.Clear.Fields, Solution.Clear.LagrangianDem);

    ExpressionReport expressionReport_0 = 
      ((ExpressionReport) simulation_0.getReportManager().getReport("AoA"));

    expressionReport_0.setDefinition(String.valueOf(aoa));

    simulation_0.getSimulationIterator().runAutomation();

    MonitorPlot monitorPlot_0 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Lift and Drag coefficient"));

    Cartesian2DAxisManager cartesian2DAxisManager_2 = 
      ((Cartesian2DAxisManager) monitorPlot_0.getAxisManager());

    cartesian2DAxisManager_2.setAxesBounds(new Vector(Arrays.asList(new star.common.AxisManager.AxisBounds("Bottom Axis", 1.0, false, 500.0, false), new star.common.AxisManager.AxisBounds("Left Axis", -0.3043176346255413, false, 1.3398518979130776, false))));

    monitorPlot_0.export(resolvePath(cdClFile), ",");

    ResidualPlot residualPlot_0 = 
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    Cartesian2DAxisManager cartesian2DAxisManager_1 = 
      ((Cartesian2DAxisManager) residualPlot_0.getAxisManager());

    cartesian2DAxisManager_1.setAxesBounds(new Vector(Arrays.asList(new star.common.AxisManager.AxisBounds("Bottom Axis", 1.0, false, 500.0, false), new star.common.AxisManager.AxisBounds("Left Axis", 3.0826106687329624E-5, true, 41.515497780004424, true))));

    residualPlot_0.export(resolvePath(residualFile), ",");
  }
}
