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

package org.metawidget.jsp.tagext.html.spring.subclass;

import junit.framework.TestCase;

import org.metawidget.jsp.tagext.html.spring.SpringMetawidgetTag;

public class SpringMetawidgetTagSubclassTest
	extends TestCase {

	//
	// Public methods
	//

	/**
	 * Test default configuration is tied to the superclass, not the subclass.
	 */

	public static void testDefaultConfiguration() {

		assertEquals( "/org/metawidget/jsp/tagext/html/spring/metawidget-spring-default.xml", new StaticSpringMetawidgetTagSubclass().getDefaultConfiguration() );
	}

	//
	// Inner class
	//

	public static class StaticSpringMetawidgetTagSubclass
		extends SpringMetawidgetTag {

		@Override
		public String getDefaultConfiguration() {

			return super.getDefaultConfiguration();
		}
	}
}
