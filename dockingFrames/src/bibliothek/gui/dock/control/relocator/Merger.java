/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */
package bibliothek.gui.dock.control.relocator;

import bibliothek.gui.DockStation;

/**
 * An algorithm used during drag and drop to merge two {@link DockStation}s.
 * @author Benjamin Sigg
 */
public interface Merger {
	/**
	 * Checks whether {@link #merge(DockStation, DockStation)} will succeed or not. This
	 * method will only be called if <code>parent</code> would accept all children
	 * of <code>child</code>. This method may be called during "drop" and during "move" operations.
	 * @param parent an existing station
	 * @param child a station that is dragged around and may be dropped onto <code>parent</code>
	 * @return whether {@link #merge(DockStation, DockStation)} will succeed
	 */
	public boolean canMerge( DockStation parent, DockStation child );
	
	/**
	 * Moves all children of <code>child</code> to <code>parent</code>, leaving <code>child</code>
	 * empty. This method is only called if {@link #canMerge(DockStation, DockStation) canMerge}
	 * returned <code>true</code> and if the <code>accept</code>-methods allow the operation.<br>
	 * If the parent of <code>child</code> is not <code>parent</code>, then <code>child</code> will be removed
	 * from its parent. Otherwise the child remains, allowing this {@link Merger} to reuse it.  
	 * @param parent the new parent of the children
	 * @param child the station to disolve
	 */
	public void merge( DockStation parent, DockStation child );
}
