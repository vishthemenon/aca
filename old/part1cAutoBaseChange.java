// STAR-CCM+ macro: part1cAutoBaseChange.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class part1cAutoBaseChange extends StarMacro {

  public void execute() {
	  
	double[] m= {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1};
	// double[] m= {0.0004, 0.0003, 0.0002, 0.0001};
	for(int i = 0; i <  m.length; i++) {
		String clcd = "ClCd" + m[i]*100 + ".csv";
		String cp = "Cp" + m[i]*100 + ".csv";
		execute0(m[i],clcd, cp,"\\\\icnas1.cc.ic.ac.uk\\vr616\\Desktop\\ACA\\CW2\\part2\\star_files\\macros\\macros_data2\\");
    }

    // execute0(0.04,"ClCd400.csv", "Cp400.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.05,"ClCd500.csv", "Cp500.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.06,"ClCd600.csv", "Cp600.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.07,"ClCd700.csv", "Cp700.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.08,"ClCd800.csv", "Cp800.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.09,"ClCd900.csv", "Cp900.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
    // execute0(0.1,"ClCd1000.csv", "Cp1000.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
// execute0(0.0004,"ClCd4.csv", "Cp4.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
// execute0(0.0003,"ClCd3.csv", "Cp3.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
// execute0(0.0002,"ClCd2.csv", "Cp2.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
// execute0(0.0001,"ClCd1.csv", "Cp1.csv","\\\\icnas3.cc.ic.ac.uk\\nmd16\\ComputationalAerodynamicsSandbox\\aca-docker\\files\\coursework\\part2\\star_files\\macros\\macro_data\\");
  }

  private void execute0(double base, String cdClFile, String cpFile, String relativePath) {
    cdClFile = relativePath + cdClFile;
    cpFile = relativePath + cpFile;

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValue(base);

    MeshPipelineController meshPipelineController_0 = 
      simulation_0.get(MeshPipelineController.class);

    meshPipelineController_0.generateVolumeMesh();

    ResidualPlot residualPlot_0 = 
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    residualPlot_0.open();

    Solution solution_0 = 
      simulation_0.getSolution();

    solution_0.clearSolution(Solution.Clear.History, Solution.Clear.Fields, Solution.Clear.LagrangianDem);

    simulation_0.getSimulationIterator().runAutomation();

    MonitorPlot monitorPlot_0 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Lift and Drag coefficient"));

    Cartesian2DAxisManager cartesian2DAxisManager_0 = 
      ((Cartesian2DAxisManager) monitorPlot_0.getAxisManager());

    cartesian2DAxisManager_0.setAxesBounds(new Vector(Arrays.asList(new star.common.AxisManager.AxisBounds("Bottom Axis", 1.0, false, 1000.0, false), new star.common.AxisManager.AxisBounds("Left Axis", -0.05371483835076627, false, 3.050702382807832, false))));

    monitorPlot_0.export(resolvePath(cdClFile), ",");

   XYPlot xYPlot_0 = 
      ((XYPlot) simulation_0.getPlotManager().getPlot("Pressure coefficient"));

    xYPlot_0.open();

    PlotUpdate plotUpdate_0 = 
      monitorPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_2 = 
      plotUpdate_0.getHardcopyProperties();

    hardcopyProperties_2.setCurrentResolutionWidth(1332);

    hardcopyProperties_2.setCurrentResolutionHeight(619);

    PlotUpdate plotUpdate_1 = 
      xYPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_4 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_4.setCurrentResolutionWidth(1330);

    hardcopyProperties_4.setCurrentResolutionHeight(618);

    Cartesian2DAxisManager cartesian2DAxisManager_1 = 
      ((Cartesian2DAxisManager) xYPlot_0.getAxisManager());

    cartesian2DAxisManager_1.setAxesBounds(new Vector(Arrays.asList(new star.common.AxisManager.AxisBounds("Bottom Axis", 0.0013520000000000001, false, 0.9999962499999999, false), new star.common.AxisManager.AxisBounds("Left Axis", -3.0916403304136635, false, 1.0218322851068062, false))));

    xYPlot_0.export(resolvePath(cpFile), ",");
  }
}
