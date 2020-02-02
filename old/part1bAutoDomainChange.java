// STAR-CCM+ macro: part1bAutoDomainChange.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.vis.*;
import star.cadmodeler.*;
import star.flow.*;
import star.meshing.*;

public class part1bAutoDomainChange extends StarMacro {

  public void execute() {
    // int[] m= {1, 2,3,4,5,8,10,15,20,25,30,40,50,75,100,150,200,250,300,500};

    for(int i = 60; i <= 80; i++) {
		String clcd = "ClCd" + i + ".csv";
		String cp = "Cp" + i + ".csv";
		execute0(i,clcd, cp,"\\\\icnas1.cc.ic.ac.uk\\vr616\\Desktop\\ACA\\CW2\\part2\\star_files\\macros\\macros_data\\");
    }
	/* int[] m= {8,18,29,40,54,60};
	 for(int i = 0; i <  m.length; i++) {
		String clcd = "ClCd" + m[i] + ".csv";
		String cp = "Cp" + m[i] + ".csv";
		execute0(m[i],clcd, cp,"\\\\icnas1.cc.ic.ac.uk\\vr616\\Desktop\\ACA\\CW2\\part2\\star_files\\macros\\macros_data\\");
    } */
}

  private void execute0(double width, String cdClFile, String cpFile, String relativePath) {
    cdClFile = relativePath + cdClFile;
    cpFile = relativePath + cpFile;

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().createScene("3D-CAD View");

    scene_0.initializeAndWait();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    simulation_0.get(SolidModelManager.class).editCadModel(cadModel_0, scene_0);

    scene_0.open();

    scene_0.setAdvancedRenderingEnabled(false);

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(1332);

    hardcopyProperties_1.setCurrentResolutionHeight(619);

    hardcopyProperties_0.setCurrentResolutionWidth(1330);

    hardcopyProperties_0.setCurrentResolutionHeight(618);

    scene_0.resetCamera();

    CylinderPrimitiveFeature cylinderPrimitiveFeature_0 = 
      ((CylinderPrimitiveFeature) cadModel_0.getFeature("Cylinder 1"));

    cadModel_0.getFeatureManager().rollBack(cylinderPrimitiveFeature_0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    cylinderPrimitiveFeature_0.setCoordinateSystem(labCoordinateSystem_0);

    CadModelCoordinate cadModelCoordinate_0 = 
      cylinderPrimitiveFeature_0.getCenter();

    cadModelCoordinate_0.setCoordinateSystem(labCoordinateSystem_0);

    cadModelCoordinate_0.setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

    cylinderPrimitiveFeature_0.getRadius().setValue(width);

    cylinderPrimitiveFeature_0.markFeatureForEdit();

    cadModel_0.getFeatureManager().markDependentNotUptodate(cylinderPrimitiveFeature_0);

    cadModel_0.getFeatureManager().rollForwardToEnd();

    cadModel_0.update();

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_0}));

    hardcopyProperties_1.setCurrentResolutionWidth(1330);

    hardcopyProperties_1.setCurrentResolutionHeight(618);

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
