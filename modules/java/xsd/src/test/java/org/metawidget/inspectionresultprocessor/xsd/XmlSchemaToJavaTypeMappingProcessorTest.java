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

package org.metawidget.inspectionresultprocessor.xsd;

import junit.framework.TestCase;

/**
 * @author Richard Kennard
 */

public class XmlSchemaToJavaTypeMappingProcessorTest
	extends TestCase {

	//
	// Public methods
	//

	public void testMapping()
		throws Exception {

		// Set up

		String inputXml = "<?xml version=\"1.0\"?>";
		inputXml += "<inspection-result xmlns=\"http://metawidget.org/inspection-result\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://metawidget.org/inspection-result http://metawidget.org/xsd/inspection-result-1.0.xsd\" version=\"1.0\">";
		inputXml += "<entity type=\"Foo\">";
		inputXml += "<property name=\"01\" type=\"string\"/>";
		inputXml += "<property name=\"01a\" type=\"xs:string\"/>";
		inputXml += "<property name=\"02\" type=\"boolean\"/>";
		inputXml += "<property name=\"03\" type=\"decimal\"/>";
		inputXml += "<property name=\"04\" type=\"float\"/>";
		inputXml += "<property name=\"05\" type=\"double\"/>";
		inputXml += "<property name=\"06\" type=\"duration\"/>";
		inputXml += "<property name=\"07\" type=\"xsd:dateTime\"/>";
		inputXml += "<property name=\"08\" type=\"time\"/>";
		inputXml += "<property name=\"09\" type=\"date\"/>";
		inputXml += "<property name=\"10\" type=\"normalizedString\"/>";
		inputXml += "<property name=\"11\" type=\"integer\"/>";
		inputXml += "<property name=\"12\" type=\"nonPositiveInteger\"/>";
		inputXml += "<property name=\"13\" type=\"negativeInteger\"/>";
		inputXml += "<property name=\"14\" type=\"long\"/>";
		inputXml += "<property name=\"15\" type=\"int\"/>";
		inputXml += "<property name=\"16\" type=\"short\"/>";
		inputXml += "<property name=\"17\" type=\"byte\"/>";
		inputXml += "<property name=\"18\" type=\"nonNegativeInteger\"/>";
		inputXml += "<property name=\"19\" type=\"unsignedLong\"/>";
		inputXml += "<property name=\"20\" type=\"unsignedInt\"/>";
		inputXml += "<property name=\"21\" type=\"unsignedShort\"/>";
		inputXml += "<property name=\"22\" type=\"unsignedByte\"/>";
		inputXml += "<property name=\"23\" type=\"positiveInteger\"/>";
		inputXml += "</entity></inspection-result>";

		// Run processor

		XmlSchemaToJavaTypeMappingProcessor<Object> processor = new XmlSchemaToJavaTypeMappingProcessor<Object>();
		String outputXml = processor.processInspectionResult( inputXml, null, null, null );

		// Test result

		String validateXml = "<inspection-result xmlns=\"http://metawidget.org/inspection-result\" version=\"1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://metawidget.org/inspection-result http://metawidget.org/xsd/inspection-result-1.0.xsd\">";
		validateXml += "<entity type=\"Foo\">";
		validateXml += "<property name=\"01\" type=\"java.lang.String\"/>";
		validateXml += "<property name=\"01a\" type=\"java.lang.String\"/>";
		validateXml += "<property name=\"02\" type=\"boolean\"/>";
		validateXml += "<property name=\"03\" type=\"java.math.BigDecimal\"/>";
		validateXml += "<property name=\"04\" type=\"float\"/>";
		validateXml += "<property name=\"05\" type=\"double\"/>";
		validateXml += "<property name=\"06\" type=\"long\"/>";
		validateXml += "<property name=\"07\" type=\"java.util.Date\"/>";
		validateXml += "<property name=\"08\" type=\"java.util.Date\"/>";
		validateXml += "<property name=\"09\" type=\"java.util.Date\"/>";
		validateXml += "<property name=\"10\" type=\"java.lang.String\"/>";
		validateXml += "<property name=\"11\" type=\"int\"/>";
		validateXml += "<property name=\"12\" type=\"int\"/>";
		validateXml += "<property name=\"13\" type=\"int\"/>";
		validateXml += "<property name=\"14\" type=\"long\"/>";
		validateXml += "<property name=\"15\" type=\"int\"/>";
		validateXml += "<property name=\"16\" type=\"short\"/>";
		validateXml += "<property name=\"17\" type=\"byte\"/>";
		validateXml += "<property name=\"18\" type=\"int\"/>";
		validateXml += "<property name=\"19\" type=\"long\"/>";
		validateXml += "<property name=\"20\" type=\"int\"/>";
		validateXml += "<property name=\"21\" type=\"short\"/>";
		validateXml += "<property name=\"22\" type=\"byte\"/>";
		validateXml += "<property name=\"23\" type=\"int\"/>";
		validateXml += "</entity>";
		validateXml += "</inspection-result>";

		assertEquals( validateXml, outputXml );
	}
}
