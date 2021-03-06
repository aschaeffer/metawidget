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

package org.metawidget.util;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Richard Kennard
 */

public class ArrayUtilsTest
	extends TestCase {

	//
	// Public methods
	//

	public void testArrayUtils()
		throws Exception {

		String[] compareTo = new String[] { "foo", "bar", "baz" };

		// fromString

		assertTrue( Arrays.equals( compareTo, ArrayUtils.fromString( "foo,bar,baz" ) ) );
		assertEquals( 0, ArrayUtils.fromString( null ).length );

		// toString

		assertEquals( "|foo|bar|baz|", ArrayUtils.toString( compareTo, "|", true, true ) );
		assertEquals( "1,2,3", ArrayUtils.toString( new byte[] { 1, 2, 3 } ) );
		assertEquals( "foo\\,bar,bar\\,foo", ArrayUtils.toString( new String[] { "foo,bar", "bar,foo" } ) );

		// add

		assertTrue( Arrays.equals( compareTo, ArrayUtils.add( new String[] { "foo", "bar" }, "baz" ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.add( (String[]) null, compareTo ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.add( new String[] { "foo" }, new String[] { "bar", "baz" } ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.add( compareTo, (String[]) null ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.add( compareTo, new String[0] ) ) );
		assertTrue( Arrays.equals( new String[] { "foo", "bar", "baz", null }, ArrayUtils.add( compareTo, (String) null ) ) );

		// addAt

		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( new String[] { "foo", "bar" }, 2, "baz" ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( (String[]) null, 2, compareTo ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( new String[] { "bar", "baz" }, 0, new String[] { "foo" } ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( compareTo, 0, (String[]) null ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( compareTo, 0, new String[0] ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( new String[] { "foo", "baz" }, 1, "bar" ) ) );
		assertTrue( Arrays.equals( compareTo, ArrayUtils.addAt( new String[] { "baz" }, 0, new String[] { "foo", "bar" } ) ) );

		// indexOf

		assertEquals( ArrayUtils.indexOf( null, "bar" ), -1 );
		assertEquals( ArrayUtils.indexOf( compareTo, "bar" ), 1 );
		assertEquals( ArrayUtils.indexOf( compareTo, "abc" ), -1 );
		assertEquals( ArrayUtils.indexOf( new String[] { "foo", null }, null ), 1 );

		// removeAt

		assertTrue( Arrays.equals( new String[] { "bar", "baz" }, ArrayUtils.removeAt( compareTo, 0 ) ) );
		assertTrue( Arrays.equals( new String[] { "foo", "baz" }, ArrayUtils.removeAt( compareTo, 1 ) ) );
		assertTrue( Arrays.equals( new String[] { "foo", "bar" }, ArrayUtils.removeAt( compareTo, 2 ) ) );
		assertTrue( Arrays.equals( new String[] { "baz" }, ArrayUtils.removeAt( ArrayUtils.removeAt( compareTo, 0 ), 0 ) ) );
		assertTrue( Arrays.equals( new String[] { "bar" }, ArrayUtils.removeAt( ArrayUtils.removeAt( compareTo, 0 ), 1 ) ) );
		assertTrue( Arrays.equals( new String[0], ArrayUtils.removeAt( ArrayUtils.removeAt( ArrayUtils.removeAt( compareTo, 0 ), 0 ), 0 ) ) );
	}
}
