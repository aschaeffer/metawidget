// Metawidget ${project.version} (licensed under LGPL)
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

/**
 * @namespace Metawidget for pure JavaScript environments.
 */

var metawidget = metawidget || {};

( function() {

	'use strict';

	/**
	 * @namespace Utilities.
	 */

	metawidget.util = metawidget.util || {};

	/**
	 * Uncamel cases the given name (e.g. from 'fooBarBaz' to 'Foo Bar Baz').
	 */

	metawidget.util.uncamelCase = function( name ) {

		return name.charAt( 0 ).toUpperCase() + name.slice( 1 ).replace( /([^ ])([A-Z0-9])/g, function( $1, $2, $3 ) {

			return $2 + ' ' + $3;
		} );
	};

	/**
	 * Capitalizes the first letter of the given name (e.g. from 'fooBarBaz' to
	 * 'FooBarBaz').
	 */

	metawidget.util.capitalize = function( name ) {

		return name.charAt( 0 ).toUpperCase() + name.slice( 1 );
	};

	/**
	 * Camel cases the given array of names (e.g. from ['foo','bar','baz'] to
	 * 'fooBarBaz').
	 * 
	 * @return the camel cased name. Or an empty string if no name
	 */

	metawidget.util.camelCase = function( names ) {

		var toString = '';
		var length = names.length;

		if ( length > 0 ) {
			toString += names[0];
		}

		for ( var loop = 1; loop < length; loop++ ) {
			toString += metawidget.util.capitalize( names[loop] );
		}

		return toString;
	};

	/**
	 * Gets a camelCased id based on the given attributes.name and the given
	 * mw.path.
	 */

	metawidget.util.getId = function( elementName, attributes, mw ) {

		if ( mw.path !== undefined ) {
			var splitPath = mw.path.split( '.' );

			if ( splitPath[0] === 'object' ) {
				splitPath = splitPath.slice( 1 );
			}

			if ( attributes.name && elementName !== 'entity' ) {
				splitPath.push( attributes.name );
			} else if ( splitPath.length == 0 ) {
				return undefined;
			}

			return metawidget.util.camelCase( splitPath );
		}

		if ( attributes !== undefined ) {
			return attributes.name;
		}
	};

	/**
	 * Returns true if the given node has child <em>elements</em>. That is,
	 * their <tt>nodeType === 1</tt>. Ignores other sorts of child nodes,
	 * such as text nodes.
	 */

	metawidget.util.hasChildElements = function( node ) {

		var childNodes = node.childNodes;

		for ( var loop = 0, length = childNodes.length; loop < length; loop++ ) {

			if ( childNodes[loop].nodeType === 1 ) {
				return true;
			}
		}

		return false;
	};

	/**
	 * @true if the given attributes define 'large' or 'wide'.
	 */

	metawidget.util.isSpanAllColumns = function( attributes ) {

		if ( attributes === undefined ) {
			return false;
		}

		if ( attributes.large === 'true' ) {
			return true;
		}

		if ( attributes.wide === 'true' ) {
			return true;
		}

		return false;
	};

	/**
	 * Splits the given path into its type and an array of names (e.g.
	 * 'foo.bar['baz']' into type 'foo' and names ['bar','baz']).
	 * 
	 * @returns an object with properties 'type' and 'names' (provided there is
	 *          at least 1 name)
	 */

	metawidget.util.splitPath = function( path ) {

		var splitPath = {};

		if ( path !== undefined ) {

			// Match at every '.' and '[' boundary

			var pathArray = path.match( /([^\.\[\]]*)/g );
			splitPath.type = pathArray[0];

			for ( var loop = 1, length = pathArray.length; loop < length; loop++ ) {

				// Ignore empty matches

				if ( pathArray[loop] === '' ) {
					continue;
				}

				if ( splitPath.names === undefined ) {
					splitPath.names = [];
				}

				// Strip surrounding spaces and quotes (eg. foo[ 'bar' ])

				var stripQuotes = pathArray[loop].match( /^(?:\s*(?:\'|\"))([^\']*)(?:(?:\'|\")\s*)$/ );

				if ( stripQuotes !== null && stripQuotes[1] !== undefined ) {
					pathArray[loop] = stripQuotes[1];
				}

				splitPath.names.push( pathArray[loop] );
			}
		}

		return splitPath;
	};

	/**
	 * Appends the 'path' property from the given Metawidget to the 'name'
	 * property in the given attributes.
	 */

	metawidget.util.appendPath = function( attributes, mw ) {

		if ( mw.path !== undefined ) {
			return mw.path + '.' + attributes.name;
		}

		if ( mw.toInspect !== undefined ) {
			return typeof ( mw.toInspect ) + '.' + attributes.name;
		}

		return 'object.' + attributes.name;
	};

	/**
	 * Traverses the given 'toInspect' along properties defined by the array of
	 * 'names'.
	 */

	metawidget.util.traversePath = function( toInspect, names ) {

		if ( toInspect === undefined ) {
			return undefined;
		}

		if ( names !== undefined ) {
			for ( var loop = 0, length = names.length; loop < length; loop++ ) {

				toInspect = toInspect[names[loop]];

				// We don't need to worry about array indexes here: they should
				// have been parsed out by splitPath

				if ( toInspect === undefined ) {
					return undefined;
				}
			}
		}

		return toInspect;
	};

	/**
	 * Combines the given first inspection result with the given second
	 * inspection result.
	 * <p>
	 * Inspection results are expected to be JSON Schema objects. They are
	 * combined based on their 'name' property. If no elements match, new
	 * elements are appended.
	 */

	metawidget.util.combineInspectionResults = function( existingInspectionResult, newInspectionResult ) {

		// Inspector may return undefined

		if ( newInspectionResult === undefined ) {
			return;
		}

		// Combine based on propertyName

		for ( var propertyName in newInspectionResult ) {

			var value = newInspectionResult[propertyName];

			if ( value instanceof Array ) {
				existingInspectionResult[propertyName] = value.slice( 0 );
				continue;
			}

			if ( value instanceof Object && !( value instanceof Array )) {
				existingInspectionResult[propertyName] = existingInspectionResult[propertyName] || {};
				metawidget.util.combineInspectionResults( existingInspectionResult[propertyName], value );
				continue;
			}

			existingInspectionResult[propertyName] = value;
		}
	};

	/**
	 * Strips the first section off the section attribute (if any).
	 */

	metawidget.util.stripSection = function( attributes ) {

		var section = attributes.section;

		// (undefined means 'no change to current section')

		if ( section === undefined ) {
			return undefined;
		}

		if ( !( section instanceof Array )) {
			delete attributes.section;
			return section;
		}

		switch ( section.length ) {

			// (empty String means 'end current section')
			case 0:
				delete attributes.section;
				return '';

			case 1:
				delete attributes.section;
				return section[0];

			case 2:
				attributes.section = section.slice( 1 );
				return section[0];
		}
	};
} )();