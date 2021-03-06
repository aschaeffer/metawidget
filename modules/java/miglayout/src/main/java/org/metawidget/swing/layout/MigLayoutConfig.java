// Metawidget (licensed under LGPL)
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package org.metawidget.swing.layout;

import org.metawidget.layout.iface.LayoutException;
import org.metawidget.util.simple.ObjectUtils;

/**
 * Configures a MigLayout prior to use. Once instantiated, Layouts are immutable.
 *
 * @author Richard Kennard
 */

public class MigLayoutConfig {

	//
	// Private members
	//

	private int		mNumberOfColumns	= 1;

	private boolean	mSupportMnemonics	= true;

	private boolean mDebugMode;

	//
	// Public methods
	//

	/**
	 * @return this, as part of a fluent interface
	 */

	public MigLayoutConfig setNumberOfColumns( int numberOfColumns ) {

		if ( numberOfColumns < 1 ) {
			throw LayoutException.newException( "numberOfColumns must be >= 1" );
		}

		mNumberOfColumns = numberOfColumns;

		return this;
	}

	/**
	 * @return this, as part of a fluent interface
	 */

	public MigLayoutConfig setSupportMnemonics( boolean supportMnemonics ) {

		mSupportMnemonics = supportMnemonics;

		return this;
	}

	/**
	 * @param debugMode
	 *            true to enable MigLayout debug mode
	 * @return this, as part of a fluent interface
	 */

	public MigLayoutConfig setDebugMode( boolean debugMode ) {

		mDebugMode = debugMode;

		return this;
	}

	@Override
	public boolean equals( Object that ) {

		if ( this == that ) {
			return true;
		}

		if ( !ObjectUtils.nullSafeClassEquals( this, that )) {
			return false;
		}

		if ( mNumberOfColumns != ( (MigLayoutConfig) that ).mNumberOfColumns ) {
			return false;
		}

		if ( mSupportMnemonics != ( (MigLayoutConfig) that ).mSupportMnemonics ) {
			return false;
		}

		if ( mDebugMode != ( (MigLayoutConfig) that ).mDebugMode ) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		int hashCode = 1;
		hashCode = 31 * hashCode + mNumberOfColumns;
		hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode( mSupportMnemonics );
		hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode( mDebugMode );

		return hashCode;
	}

	//
	// Protected methods
	//

	protected int getNumberOfColumns() {

		return mNumberOfColumns;
	}

	protected boolean isSupportMnemonics() {

		return mSupportMnemonics;
	}

	protected boolean isDebugMode(){

		return mDebugMode;
	}
}
