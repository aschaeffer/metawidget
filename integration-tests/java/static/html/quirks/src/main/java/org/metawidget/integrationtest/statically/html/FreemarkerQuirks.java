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

package org.metawidget.integrationtest.statically.html;

import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiLabel;

public class FreemarkerQuirks {

	//
	// Private members
	//

	private String	mName;

	private int		mAge;

	private boolean	mRetired;

	//
	// Public methods
	//

	public String getName() {

		return mName;
	}

	public void setName( String name ) {

		mName = name;
	}

	@UiComesAfter( "name" )
	public int getAge() {

		return mAge;
	}

	public void setAge( int age ) {

		mAge = age;
	}

	@UiComesAfter( "age" )
	@UiLabel( "Retired yet?" )
	public boolean isRetired() {

		return mRetired;
	}

	public void setRetired( boolean retired ) {

		mRetired = retired;
	}
}