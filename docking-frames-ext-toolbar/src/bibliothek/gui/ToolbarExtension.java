package bibliothek.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bibliothek.gui.dock.layout.DockablePropertyFactory;
import bibliothek.gui.dock.layout.PropertyTransformer;
import bibliothek.gui.dock.station.toolbar.ToolbarContainerPropertyFactory;
import bibliothek.gui.dock.station.toolbar.ToolbarPropertyFactory;
import bibliothek.gui.dock.util.extension.Extension;
import bibliothek.gui.dock.util.extension.ExtensionName;

/**
 * Allows seamless integration of the toolbar extension into the core and common
 * library without them having any references back to the toolbar project.
 * @author Benjamin Sigg
 */
public class ToolbarExtension implements Extension{
	@Override
	public void install( DockController controller ){
		// nothing to do
	}

	@Override
	public void uninstall( DockController controller ){
		// nothing to do
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> Collection<E> load( DockController controller, ExtensionName<E> extension ){
		if( extension.getName().equals( PropertyTransformer.FACTORY_EXTENSION )){
			return (Collection<E>)createPropertyFactoryExtension();
		}
		
		return null;
	}
	
	private Collection<DockablePropertyFactory> createPropertyFactoryExtension(){
		List<DockablePropertyFactory> result = new ArrayList<DockablePropertyFactory>();
		result.add( new ToolbarPropertyFactory() );
		result.add( new ToolbarContainerPropertyFactory() );
		return result;
	}
}
