// Metawidget
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

package android.widget;

import android.content.Context;
import android.view.View;

/**
 * Dummy implementation for unit testing.
 *
 * @author Richard Kennard
 */

public class DatePicker
	extends View
{
	//
	// Constructor
	//

	public DatePicker( Context context )
	{
		super( context );
	}

	//
	// Public methods
	//

	public int getDayOfMonth()
	{
		return 0;
	}

	public int getYear()
	{
		return 0;
	}

	public int getMonth()
	{
		return 0;
	}

	public void updateDate( int i, int month, int date )
	{
		// Do nothing
	}
}
