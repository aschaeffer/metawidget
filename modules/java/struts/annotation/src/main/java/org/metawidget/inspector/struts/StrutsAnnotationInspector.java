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

package org.metawidget.inspector.struts;

import static org.metawidget.inspector.struts.StrutsInspectionResultConstants.*;

import java.util.Map;

import org.metawidget.inspector.impl.BaseObjectInspector;
import org.metawidget.inspector.impl.BaseObjectInspectorConfig;
import org.metawidget.inspector.impl.propertystyle.Property;
import org.metawidget.util.CollectionUtils;

/**
 * Inspects annotations defined by Metawidget's Struts support (declared in this same package).
 *
 * @author Richard Kennard
 */

public class StrutsAnnotationInspector
	extends BaseObjectInspector {

	//
	// Constructor
	//

	public StrutsAnnotationInspector() {

		this( new BaseObjectInspectorConfig() );
	}

	public StrutsAnnotationInspector( BaseObjectInspectorConfig config ) {

		super( config );
	}

	//
	// Protected methods
	//

	@Override
	protected Map<String, String> inspectProperty( Property property )
		throws Exception {

		Map<String, String> attributes = CollectionUtils.newHashMap();

		// ExpressionLookup

		UiStrutsLookup expressionLookup = property.getAnnotation( UiStrutsLookup.class );

		if ( expressionLookup != null ) {
			attributes.put( STRUTS_LOOKUP_NAME, expressionLookup.name() );
			attributes.put( STRUTS_LOOKUP_PROPERTY, expressionLookup.property() );

			String labelName = expressionLookup.labelName();

			if ( !"".equals( labelName ) ) {
				attributes.put( STRUTS_LOOKUP_LABEL_NAME, labelName );
			}

			String labelProperty = expressionLookup.labelProperty();

			if ( !"".equals( labelProperty ) ) {
				attributes.put( STRUTS_LOOKUP_LABEL_PROPERTY, labelProperty );
			}
		}

		return attributes;
	}
}
