// STAR-CCM+ macro: ultimate_macro_b1.java
// Written by STAR-CCM+ 13.04.011
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.prismmesher.*;
import star.vis.*;
import star.cadmodeler.*;
import star.meshing.*;

public class ultimate_macro_b1 extends StarMacro {

  static String rootPath = "\\\\icnas1.cc.ic.ac.uk\\vr616\\Desktop\\ACA\\CW2\\real_shit\\data\\radius\\";

  public void execute() {
    int iterations = 300;
    double nearWallThickness = 4.16e-5/14.551;
    int prismLayers = 15;
    double prismLayerThickness = 0.0233;
    double baseSize = 0.01;
    double radius = 110;
    while(radius <= 200) {
      execute0(
        iterations,
        nearWallThickness,
        prismLayers,
        prismLayerThickness,
        baseSize,
        radius,
        (int)radius
        );

      radius += 10;
    }
  }

  private String concatFilePath(String fileName, int fileSuffix) {
    return(rootPath + fileName + fileSuffix + ".csv");
  }

  private void execute0(
    int iterations,
    double nearWallThickness,
    int prismLayers,
    double prismLayerThickness,
    double baseSize,
    double radius,
    int fileSuffix) {

    Simulation simulation_0 =
      getActiveSimulation();

    Solution solution_0 =
      simulation_0.getSolution();

    solution_0.clearSolution(Solution.Clear.History, Solution.Clear.Fields, Solution.Clear.LagrangianDem);

    StepStoppingCriterion stepStoppingCriterion_0 =
      ((StepStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Maximum Steps"));

    stepStoppingCriterion_0.setMaximumNumberSteps(iterations);

    AutoMeshOperation2d autoMeshOperation2d_0 =
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    autoMeshOperation2d_0.getDefaultValues().get(PrismWallThickness.class).setValue(nearWallThickness);

    NumPrismLayers numPrismLayers_0 =
      autoMeshOperation2d_0.getDefaultValues().get(NumPrismLayers.class);

    IntegerValue integerValue_0 =
      numPrismLayers_0.getNumLayersValue();

    integerValue_0.getQuantity().setValue(prismLayers);

    PrismThickness prismThickness_0 =
      autoMeshOperation2d_0.getDefaultValues().get(PrismThickness.class);

    ((ScalarPhysicalQuantity) prismThickness_0.getAbsoluteSizeValue()).setValue(prismLayerThickness);

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValue(baseSize);

    Scene scene_2 =
      simulation_0.getSceneManager().createScene("3D-CAD View");

    scene_2.initializeAndWait();

    CadModel cadModel_0 =
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    simulation_0.get(SolidModelManager.class).editCadModel(cadModel_0, scene_2);

    scene_2.open();

    scene_2.setAdvancedRenderingEnabled(false);

    SceneUpdate sceneUpdate_2 =
      scene_2.getSceneUpdate();

    HardcopyProperties hardcopyProperties_2 =
      sceneUpdate_2.getHardcopyProperties();

    hardcopyProperties_2.setCurrentResolutionWidth(25);

    hardcopyProperties_2.setCurrentResolutionHeight(25);

    Scene scene_1 =
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    SceneUpdate sceneUpdate_1 =
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 =
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(1332);

    hardcopyProperties_1.setCurrentResolutionHeight(626);

    hardcopyProperties_2.setCurrentResolutionWidth(1330);

    hardcopyProperties_2.setCurrentResolutionHeight(625);

    scene_2.resetCamera();

    Scene scene_3 =
      simulation_0.getSceneManager().getScene("Mesh Scene 2");

    scene_3.initializeAndWait();

    PartDisplayer partDisplayer_3 =
      ((PartDisplayer) scene_3.getDisplayerManager().getDisplayer("Mesh 1"));

    partDisplayer_3.initialize();

    Scene scene_4 =
      simulation_0.getSceneManager().getScene("y+");

    scene_4.initializeAndWait();

    PartDisplayer partDisplayer_4 =
      ((PartDisplayer) scene_4.getDisplayerManager().getDisplayer("Outline 1"));

    partDisplayer_4.initialize();

    ScalarDisplayer scalarDisplayer_0 =
      ((ScalarDisplayer) scene_4.getDisplayerManager().getDisplayer("Scalar 1"));

    scalarDisplayer_0.initialize();

    CylinderPrimitiveFeature cylinderPrimitiveFeature_0 =
      ((CylinderPrimitiveFeature) cadModel_0.getFeature("Cylinder 1"));

    cylinderPrimitiveFeature_0.getRadius().setValue(radius);

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_2}));

    hardcopyProperties_1.setCurrentResolutionWidth(1330);

    hardcopyProperties_1.setCurrentResolutionHeight(625);

    autoMeshOperation2d_0.execute();

    ResidualPlot residualPlot_0 =
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    PlotUpdate plotUpdate_0 =
      residualPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_3 =
      plotUpdate_0.getHardcopyProperties();

    hardcopyProperties_3.setCurrentResolutionWidth(1331);

    hardcopyProperties_3.setCurrentResolutionWidth(1330);

    hardcopyProperties_3.setCurrentResolutionHeight(625);

    SceneUpdate sceneUpdate_3 =
      scene_4.getSceneUpdate();

    HardcopyProperties hardcopyProperties_4 =
      sceneUpdate_3.getHardcopyProperties();

    hardcopyProperties_4.setCurrentResolutionWidth(1330);

    hardcopyProperties_4.setCurrentResolutionHeight(625);

    simulation_0.getSimulationIterator().runAutomation();

    CurrentView currentView_1 =
      scene_4.getCurrentView();

    currentView_1.setInput(new DoubleVector(new double[] {0.39156760023825005, -0.03982903919860919, -0.04388134184154113}), new DoubleVector(new double[] {0.39156760023825005, -0.03982903919860919, 2.2102321768983324}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.3011885755561549, -0.05549233630486871, -0.044968842272403275}), new DoubleVector(new double[] {0.3011885755561549, -0.05549233630486871, 1.7902880632876492}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.26265857029694595, -0.06216984717648461, -0.045430414385752016}), new DoubleVector(new double[] {0.26265857029694595, -0.06216984717648461, 1.6112592569588844}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.41762657137656556, -0.037264275574350546, -0.002436205027324778}), new DoubleVector(new double[] {0.41762657137656556, -0.037264275574350546, 1.6112592569588844}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.39190203639115423, -0.044745390340516084, -0.006287417537072404}), new DoubleVector(new double[] {0.39190203639115423, -0.044745390340516084, 1.3051199981366963}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.37106516305297105, -0.05080509330111017, -0.006392320278756802}), new DoubleVector(new double[] {0.37106516305297105, -0.05080509330111017, 1.057147198490724}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    currentView_1.setInput(new DoubleVector(new double[] {0.4693275557042182, -0.029878843014203384, -0.003964612414458779}), new DoubleVector(new double[] {0.4693275557042182, -0.029878843014203384, 1.057147198490724}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 14.114923188392885, 0);

    MonitorPlot monitorPlot_0 =
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Lift and Drag coefficient"));

    monitorPlot_0.export(resolvePath(concatFilePath("CLCD", fileSuffix)), ",");

    XYPlot xYPlot_0 =
      ((XYPlot) simulation_0.getPlotManager().getPlot("Pressure coefficient"));

    xYPlot_0.export(resolvePath(concatFilePath("CP", fileSuffix)), ",");

    residualPlot_0.export(resolvePath(concatFilePath("Res", fileSuffix)), ",");

    HistogramPlot histogramPlot_0 =
      ((HistogramPlot) simulation_0.getPlotManager().getPlot("Y+"));

    histogramPlot_0.export(resolvePath(concatFilePath("Y+", fileSuffix)), ",");
  }
}
