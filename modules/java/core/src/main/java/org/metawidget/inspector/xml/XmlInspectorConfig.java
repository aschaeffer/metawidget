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

package org.metawidget.inspector.xml;

import java.io.InputStream;

import org.metawidget.inspector.impl.BaseXmlInspectorConfig;
import org.metawidget.inspector.impl.propertystyle.PropertyStyle;

/**
 * Configures an XmlInspector prior to use. Once instantiated, Inspectors are immutable.
 *
 * @author Richard Kennard
 */

public class XmlInspectorConfig
	extends BaseXmlInspectorConfig {

	//
	// Constructor
	//

	public XmlInspectorConfig() {

		setDefaultFile( "metawidget-metadata.xml" );
	}

	//
	// Public methods
	//

	/**
	 * Overridden to provide a covariant return type for our fluent interface.
	 */

	@Override
	public XmlInspectorConfig setInputStream( InputStream stream ) {

		return (XmlInspectorConfig) super.setInputStream( stream );
	}

	/**
	 * Overridden to provide a covariant return type for our fluent interface.
	 */

	@Override
	public XmlInspectorConfig setRestrictAgainstObject( PropertyStyle restrictAgainstObject ) {

		return (XmlInspectorConfig) super.setRestrictAgainstObject( restrictAgainstObject );
	}

	/**
	 * Overridden to provide a covariant return type for our fluent interface.
	 */

	@Override
	public XmlInspectorConfig setInferInheritanceHierarchy( boolean inferInheritanceHierarchy ) {

		return (XmlInspectorConfig) super.setInferInheritanceHierarchy( inferInheritanceHierarchy );
	}

	/**
	 * Overridden to provide a covariant return type for our fluent interface.
	 */

	@Override
	public XmlInspectorConfig setValidateAgainstClasses( PropertyStyle validateAgainstClasses ) {

		return (XmlInspectorConfig) super.setValidateAgainstClasses( validateAgainstClasses );
	}
}
