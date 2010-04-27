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

package bibliothek.gui.dock.station.screen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.layout.AbstractDockableProperty;
import bibliothek.gui.dock.layout.DockableProperty;
import bibliothek.util.Version;
import bibliothek.util.xml.XElement;

/**
 * This property is used on {@link ScreenDockStation ScreenDockStations}
 * to determine the bounds of the {@link ScreenDockDialog dialogs}.
 * @author Benjamin Sigg
 */
public class ScreenDockProperty extends AbstractDockableProperty {
    private int x, y, width, height;
    private boolean fullscreen;
    
    public String getFactoryID() {
        return ScreenDockPropertyFactory.ID;
    }
    
    /**
     * Constructs a new property
     */
    public ScreenDockProperty(){
    	// do nothing
    }

    /**
     * Constructs a new property
     * @param x the x-coordinate of the dialog
     * @param y the y-coordinate of the dialog
     * @param width the width of the dialog
     * @param height the height of the dialog
     */
    public ScreenDockProperty( int x, int y, int width, int height ){
    	this( x, y, width, height, false );
    }
    
    /**
     * Constructs a new property
     * @param x the x-coordinate of the dialog
     * @param y the y-coordinate of the dialog
     * @param width the width of the dialog
     * @param height the height of the dialog
     * @param fullscreen if set, then the window should actually be in fullscreen mode
     */
    public ScreenDockProperty( int x, int y, int width, int height, boolean fullscreen ){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fullscreen = fullscreen;
    }
    

    public DockableProperty copy() {
        ScreenDockProperty copy = new ScreenDockProperty( x, y, width, height, fullscreen );
        copy( copy );
        return copy;
    }

    public void store( DataOutputStream out ) throws IOException {
        Version.write( out, Version.VERSION_1_0_8 );
        out.writeInt( x );
        out.writeInt( y );
        out.writeInt( width );
        out.writeInt( height );
        out.writeBoolean( fullscreen );
    }
    
    public void store( XElement element ) {
        element.addElement( "x" ).setInt( x );
        element.addElement( "y" ).setInt( y );
        element.addElement( "width" ).setInt( width );
        element.addElement( "height" ).setInt( height );
        element.addElement( "fullscreen" ).setBoolean( fullscreen );
    }

    public void load( DataInputStream in ) throws IOException {
        Version version = Version.read( in );
        version.checkCurrent();
        x = in.readInt();
        y = in.readInt();
        width = in.readInt();
        height = in.readInt();
        
        if( version.compareTo( Version.VERSION_1_0_8 ) >= 0 ){
        	fullscreen = in.readBoolean();
        }
    }

    public void load( XElement element ) {
        x = element.getElement( "x" ).getInt();
        y = element.getElement( "y" ).getInt();
        width = element.getElement( "width" ).getInt();
        height = element.getElement( "height" ).getInt();
        
        XElement xfullscreen = element.getElement( "fullscreen" );
        if( xfullscreen != null ){
        	fullscreen = xfullscreen.getBoolean();
        }
    }
    
    /**
     * Gets the height of the dialog.
     * @return the height
     * @see #setHeight(int)
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the dialog.
     * @param height the height
     */
    public void setHeight( int height ) {
        this.height = height;
    }

    /**
     * Gets the width of the dialog.
     * @return the width
     * @see #setWidth(int)
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the dialog.
     * @param width the new width
     */
    public void setWidth( int width ) {
        this.width = width;
    }

    /**
     * Gets the x-coordinate of the dialog.
     * @return the x-coordinate
     * @see #setX(int)
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the location of the left size of the dialog.
     * @param x the x-coordinate
     */
    public void setX( int x ) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the dialog
     * @return the y-coordinate
     * @see #setY(int)
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the location of the top side of the dialog.
     * @param y the y-coordinate
     */
    public void setY( int y ) {
        this.y = y;
    }
    
    /**
     * Tells whether this location describes an element that is in fullscreen mode.
     * @return the state
     */
    public boolean isFullscreen() {
		return fullscreen;
	}
    
    /**
     * Sets the fullscreen mode
     * @param fullscreen the new state
     */
    public void setFullscreen( boolean fullscreen ) {
		this.fullscreen = fullscreen;
	}

    @Override
    public String toString(){
	    return getClass().getName() + "[x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
    
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + height;
		result = prime * result + width;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals( Object obj ){
		if( this == obj )
			return true;
		if( !super.equals( obj ) )
			return false;
		if( !(obj instanceof ScreenDockProperty) )
			return false;
		ScreenDockProperty other = (ScreenDockProperty)obj;
		if( height != other.height )
			return false;
		if( width != other.width )
			return false;
		if( x != other.x )
			return false;
		if( y != other.y )
			return false;
		return true;
	}
}
