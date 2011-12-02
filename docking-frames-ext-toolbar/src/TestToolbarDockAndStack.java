import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.Orientation;
import bibliothek.gui.dock.ComponentDockable;
import bibliothek.gui.dock.DefaultDockable;
import bibliothek.gui.dock.ExpandableToolbarItemStrategy;
import bibliothek.gui.dock.StackDockStation;
import bibliothek.gui.dock.ToolbarContainerDockStation;
import bibliothek.gui.dock.ToolbarGroupDockStation;
import bibliothek.gui.dock.ToolbarDockStation;
import bibliothek.gui.dock.toolbar.expand.DefaultExpandableToolbarItemStrategy;
import bibliothek.gui.dock.toolbar.expand.ExpandedState;

public class TestToolbarDockAndStack{

	/**
	 * @param args
	 */
	public static void main( String[] args ){

		DockController controller = new DockController();

		JPanel pane = new JPanel(new BorderLayout());

		/**
		 * Create a ToolbarContainerDockStation
		 * */
		ToolbarContainerDockStation toolbarStationWest = new ToolbarContainerDockStation(
				Orientation.VERTICAL);
		pane.add(toolbarStationWest.getComponent(), BorderLayout.WEST);
		ToolbarContainerDockStation toolbarStationNorth = new ToolbarContainerDockStation(
				Orientation.HORIZONTAL);
		pane.add(toolbarStationNorth.getComponent(), BorderLayout.NORTH);
		controller.add(toolbarStationWest);
		controller.add(toolbarStationNorth);

		// Disable the expand state action button
		controller.getProperties().set(
				ExpandableToolbarItemStrategy.STRATEGY,
				new DefaultExpandableToolbarItemStrategy(){
					@Override
					public boolean isEnabled( Dockable item, ExpandedState state ){
						return false;
					}
				});

		ToolbarDockStation group1 = new ToolbarDockStation();
		JButton button = new JButton("One");
		button.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		ComponentDockable dockable1 = new ComponentDockable(button);
		group1.drop(dockable1);
		button = new JButton("One");
		button.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		ComponentDockable dockable2 = new ComponentDockable(button);
		group1.drop(dockable2);
		button = new JButton("One");
		button.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		ComponentDockable dockable3 = new ComponentDockable(button);
		group1.drop(dockable3);
		button = new JButton("One");
		button.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		ComponentDockable dockable4 = new ComponentDockable(button);
		group1.drop(dockable4);

		ToolbarGroupDockStation toolbar1 = new ToolbarGroupDockStation();
		toolbar1.drop(group1);
		toolbarStationWest.drop(toolbar1);

		ToolbarDockStation group2 = new ToolbarDockStation();
		ComponentDockable dockable5 = new ComponentDockable(new JButton("One"));
		group2.drop(dockable5);
		ComponentDockable dockable6 = new ComponentDockable(new JButton("One"));
		group2.drop(dockable6);
		ToolbarDockStation group3 = new ToolbarDockStation();
		ComponentDockable dockable7 = new ComponentDockable(
				new JButton("One"));
		group3.drop(dockable7);
		ComponentDockable dockable8 = new ComponentDockable(
				new JButton("One"));
		group3.drop(dockable8);

		ToolbarGroupDockStation toolbar2 = new ToolbarGroupDockStation();
		toolbar2.drop(group2);
		toolbar2.drop(group3);
		toolbarStationNorth.drop(toolbar2);

		/**
		 * Create a stack and add it in the center area
		 * */
		StackDockStation stackStation = new StackDockStation();
		controller.add(stackStation);
		DefaultDockable dockable9 = new DefaultDockable("One");
		stackStation.drop(dockable9);
		DefaultDockable dockable10 = new DefaultDockable("One");
		stackStation.drop(dockable10);
		// controller.add(stackStation);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		pane.add(stackStation.getComponent(), BorderLayout.CENTER);
		// toolbarStation.drop( stackStation );
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

		/**
		 * Display frame
		 * */
		JFrame frame = new JFrame();
		frame.getContentPane().add(pane);
		// frame.add( toolbarStation.getComponent() );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(20, 20, 400, 400);
		frame.setVisible(true);

	}

}