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

package org.metawidget.gwt.allwidgets.client;

import java.util.Date;

import org.metawidget.gwt.allwidgets.client.converter.DateConverter;
import org.metawidget.gwt.allwidgets.client.ui.AllWidgetsModule;
import org.metawidget.gwt.client.ui.Facet;
import org.metawidget.gwt.client.ui.GwtMetawidget;
import org.metawidget.gwt.client.ui.GwtUtils;
import org.metawidget.shared.allwidgets.model.AllWidgets;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * @author Richard Kennard
 */

public class GwtAllWidgetsTest
	extends GWTTestCase {

	//
	// Private statics
	//

	private static final int	TEST_FINISH_DELAY	= 50 * 5000;

	//
	// Public methods
	//

	@Override
	public String getModuleName() {

		return "org.metawidget.gwt.allwidgets.GwtAllWidgetsTest";
	}

	public void testAllWidgets()
		throws Exception {

		// Start app

		prepareBundle();
		final FlowPanel panel = new FlowPanel();
		final AllWidgetsModule allWidgetsModule = new AllWidgetsModule( panel );
		allWidgetsModule.onModuleLoad();

		final GwtMetawidget metawidget = (GwtMetawidget) panel.getWidget( 0 );

		executeAfterBuildWidgets( metawidget, new Timer() {

			@Override
			public void run() {

				// Test fields

				final FlexTable flexTable = (FlexTable) metawidget.getWidget( 0 );

				// Check what created, and edit it

				assertEquals( "Textbox:", flexTable.getText( 0, 0 ) );
				assertTrue( flexTable.getWidget( 0, 1 ) instanceof TextBox );
				assertEquals( "Textbox", metawidget.getValue( "textbox" ) );
				( (TextBox) flexTable.getWidget( 0, 1 ) ).setText( "Textbox1" );
				assertEquals( "*", flexTable.getText( 0, 2 ) );

				assertEquals( "Limited textbox:", flexTable.getText( 0, 3 ) );
				assertTrue( flexTable.getWidget( 0, 4 ) instanceof TextBox );
				assertTrue( 20 == ( (TextBox) flexTable.getWidget( 0, 4 ) ).getMaxLength() );
				assertEquals( "Limited Textbox", metawidget.getValue( "limitedTextbox" ) );
				( (TextBox) flexTable.getWidget( 0, 4 ) ).setText( "Limited Textbox1" );
				assertTrue( 6 == flexTable.getCellCount( 0 ) );

				assertEquals( "Textarea:", flexTable.getText( 1, 0 ) );
				assertTrue( flexTable.getWidget( 1, 1 ) instanceof TextArea );
				assertEquals( "Textarea", metawidget.getValue( "textarea" ) );
				( (TextArea) flexTable.getWidget( 1, 1 ) ).setText( "Textarea1" );
				assertTrue( 4 == ( (FlexCellFormatter) flexTable.getCellFormatter() ).getColSpan( 1, 1 ) );

				assertEquals( "Password:", flexTable.getText( 2, 0 ) );
				assertTrue( flexTable.getWidget( 2, 1 ) instanceof PasswordTextBox );
				assertEquals( "Password", metawidget.getValue( "password" ) );
				( (PasswordTextBox) flexTable.getWidget( 2, 1 ) ).setText( "Password1" );

				// Primitives

				assertEquals( "Byte:", flexTable.getText( 2, 3 ) );
				assertTrue( flexTable.getWidget( 2, 4 ) instanceof TextBox );
				assertEquals( String.valueOf( Byte.MAX_VALUE ), metawidget.getValue( "byte" ) );
				( (TextBox) flexTable.getWidget( 2, 4 ) ).setText( String.valueOf( Byte.MAX_VALUE - 1 ) );

				assertEquals( "Byte object:", flexTable.getText( 3, 0 ) );
				assertTrue( flexTable.getWidget( 3, 1 ) instanceof TextBox );
				assertEquals( String.valueOf( Byte.MIN_VALUE ), metawidget.getValue( "byteObject" ) );
				( (TextBox) flexTable.getWidget( 3, 1 ) ).setText( String.valueOf( Byte.MIN_VALUE + 1 ) );

				assertEquals( "Short:", flexTable.getText( 3, 3 ) );
				assertTrue( flexTable.getWidget( 3, 4 ) instanceof TextBox );
				assertEquals( String.valueOf( Short.MAX_VALUE ), metawidget.getValue( "short" ) );
				( (TextBox) flexTable.getWidget( 3, 4 ) ).setText( String.valueOf( Short.MAX_VALUE - 1 ) );

				assertEquals( "Short object:", flexTable.getText( 4, 0 ) );
				assertTrue( flexTable.getWidget( 4, 1 ) instanceof TextBox );
				assertEquals( String.valueOf( Short.MIN_VALUE ), metawidget.getValue( "shortObject" ) );
				( (TextBox) flexTable.getWidget( 4, 1 ) ).setText( String.valueOf( Short.MIN_VALUE + 1 ) );

				assertEquals( "Int:", flexTable.getText( 4, 3 ) );
				assertTrue( flexTable.getWidget( 4, 4 ) instanceof TextBox );
				assertEquals( String.valueOf( Integer.MAX_VALUE ), metawidget.getValue( "int" ) );
				( (TextBox) flexTable.getWidget( 4, 4 ) ).setText( String.valueOf( Integer.MAX_VALUE - 1 ) );

				assertEquals( "Integer object:", flexTable.getText( 5, 0 ) );
				assertTrue( flexTable.getWidget( 5, 1 ) instanceof TextBox );
				assertEquals( String.valueOf( Integer.MIN_VALUE ), metawidget.getValue( "integerObject" ) );
				( (TextBox) flexTable.getWidget( 5, 1 ) ).setText( String.valueOf( Integer.MIN_VALUE + 1 ) );

				assertEquals( "Ranged int:", flexTable.getText( 5, 3 ) );
				assertTrue( flexTable.getWidget( 5, 4 ) instanceof TextBox );
				assertEquals( "32", metawidget.getValue( "rangedInt" ) );
				( (TextBox) flexTable.getWidget( 5, 4 ) ).setText( "33" );

				assertEquals( "Ranged integer:", flexTable.getText( 6, 0 ) );
				assertTrue( flexTable.getWidget( 6, 1 ) instanceof TextBox );
				assertEquals( "33", metawidget.getValue( "rangedInteger" ) );
				( (TextBox) flexTable.getWidget( 6, 1 ) ).setText( "34" );

				assertEquals( "Long:", flexTable.getText( 6, 3 ) );
				assertTrue( flexTable.getWidget( 6, 4 ) instanceof TextBox );
				assertEquals( "42", metawidget.getValue( "long" ) );
				( (TextBox) flexTable.getWidget( 6, 4 ) ).setText( "43" );

				assertEquals( "", flexTable.getText( 7, 0 ) );
				assertTrue( flexTable.getWidget( 7, 1 ) instanceof TextBox );
				assertEquals( "43", metawidget.getValue( "longObject" ) );
				( (TextBox) flexTable.getWidget( 7, 1 ) ).setText( "44" );

				assertEquals( "Float:", flexTable.getText( 7, 3 ) );
				assertTrue( flexTable.getWidget( 7, 4 ) instanceof TextBox );
				assertEquals( "4.2", metawidget.getValue( "float" ) );
				( (TextBox) flexTable.getWidget( 7, 4 ) ).setText( "5.3" );

				assertEquals( "nullInBundle:", flexTable.getText( 8, 0 ) );
				assertTrue( flexTable.getWidget( 8, 1 ) instanceof TextBox );
				assertEquals( "4.3", metawidget.getValue( "floatObject" ) );
				( (TextBox) flexTable.getWidget( 8, 1 ) ).setText( "5.4" );

				assertEquals( "Double:", flexTable.getText( 8, 3 ) );
				assertTrue( flexTable.getWidget( 8, 4 ) instanceof TextBox );
				assertEquals( "42.2", metawidget.getValue( "double" ) );
				( (TextBox) flexTable.getWidget( 8, 4 ) ).setText( "53.3" );

				assertTrue( flexTable.getWidget( 9, 0 ) instanceof TextBox );
				assertTrue( 2 == flexTable.getFlexCellFormatter().getColSpan( 9, 0 ) );
				assertEquals( "43.3", metawidget.getValue( "doubleObject" ) );
				( (TextBox) flexTable.getWidget( 9, 0 ) ).setText( "54.4" );

				assertEquals( "Char:", flexTable.getText( 9, 2 ) );
				assertTrue( flexTable.getWidget( 9, 3 ) instanceof TextBox );
				assertEquals( "A", metawidget.getValue( "char" ) );
				assertTrue( 1 == ( (TextBox) flexTable.getWidget( 9, 3 ) ).getMaxLength() );
				( (TextBox) flexTable.getWidget( 9, 3 ) ).setText( "Z" );

				assertEquals( "Character object:", flexTable.getText( 10, 0 ) );
				assertTrue( flexTable.getWidget( 10, 1 ) instanceof TextBox );
				assertEquals( "Z", metawidget.getValue( "characterObject" ) );
				assertTrue( 1 == ( (TextBox) flexTable.getWidget( 10, 1 ) ).getMaxLength() );
				( (TextBox) flexTable.getWidget( 10, 1 ) ).setText( "A" );

				assertEquals( "Boolean:", flexTable.getText( 10, 3 ) );
				assertTrue( flexTable.getWidget( 10, 4 ) instanceof CheckBox );
				assertTrue( false == (Boolean) metawidget.getValue( "boolean" ) );
				( (CheckBox) flexTable.getWidget( 10, 4 ) ).setValue( true );

				assertEquals( "Boolean object:", flexTable.getText( 11, 0 ) );
				assertTrue( flexTable.getWidget( 11, 1 ) instanceof ListBox );
				assertTrue( 3 == ( (ListBox) flexTable.getWidget( 11, 1 ) ).getItemCount() );
				assertEquals( Boolean.TRUE.toString(), metawidget.getValue( "booleanObject" ) );
				GwtUtils.setListBoxSelectedItem( ( (ListBox) flexTable.getWidget( 11, 1 ) ), Boolean.FALSE.toString() );

				assertEquals( "Dropdown:", flexTable.getText( 11, 3 ) );
				assertTrue( flexTable.getWidget( 11, 4 ) instanceof ListBox );
				assertTrue( 4 == ( (ListBox) flexTable.getWidget( 11, 4 ) ).getItemCount() );
				assertEquals( "dropdown1", metawidget.getValue( "dropdown" ) );
				GwtUtils.setListBoxSelectedItem( ( (ListBox) flexTable.getWidget( 11, 4 ) ), "foo1" );

				assertEquals( "Dropdown with labels:", flexTable.getText( 12, 0 ) );
				assertTrue( flexTable.getWidget( 12, 1 ) instanceof ListBox );
				ListBox listbox = (ListBox) flexTable.getWidget( 12, 1 );
				assertTrue( 5 == listbox.getItemCount() );
				assertEquals( "foo2", listbox.getValue( 1 ) );
				assertEquals( "Foo #2", listbox.getItemText( 1 ) );
				assertEquals( "dropdown2", listbox.getValue( 2 ) );
				assertEquals( "Dropdown #2", listbox.getItemText( 2 ) );
				assertEquals( "bar2", listbox.getValue( 3 ) );
				assertEquals( "Bar #2", listbox.getItemText( 3 ) );
				assertEquals( "baz2", listbox.getValue( 4 ) );
				assertEquals( "Baz #2", listbox.getItemText( 4 ) );
				assertEquals( "dropdown2", metawidget.getValue( "dropdownWithLabels" ) );
				GwtUtils.setListBoxSelectedItem( ( (ListBox) flexTable.getWidget( 12, 1 ) ), "bar2" );

				assertEquals( "Not null dropdown:", flexTable.getText( 12, 3 ) );
				assertTrue( flexTable.getWidget( 12, 4 ) instanceof ListBox );
				assertTrue( 3 == ( (ListBox) flexTable.getWidget( 12, 4 ) ).getItemCount() );
				assertEquals( "0", metawidget.getValue( "notNullDropdown" ) );
				GwtUtils.setListBoxSelectedItem( ( (ListBox) flexTable.getWidget( 12, 4 ) ), "1" );

				assertEquals( "Not null object dropdown:", flexTable.getText( 13, 0 ) );
				assertTrue( flexTable.getWidget( 13, 1 ) instanceof ListBox );
				assertTrue( 6 == ( (ListBox) flexTable.getWidget( 13, 1 ) ).getItemCount() );
				assertEquals( "dropdown3", metawidget.getValue( "notNullObjectDropdown" ) );
				( (ListBox) flexTable.getWidget( 13, 1 ) ).setSelectedIndex( 0 );
				assertEquals( "*", flexTable.getText( 13, 2 ) );

				assertEquals( "Nested widgets:", flexTable.getText( 14, 0 ) );
				assertTrue( flexTable.getWidget( 14, 1 ) instanceof GwtMetawidget );
				assertTrue( 5 == flexTable.getFlexCellFormatter().getColSpan( 14, 1 ) );

				final GwtMetawidget metawidgetNested = metawidget.getWidget( "nestedWidgets" );

				executeAfterBuildWidgets( metawidgetNested, new Timer() {

					@Override
					public void run() {

						// Sanity checks

						assertTrue( null == metawidget.getWidget( (String[]) null ) );
						assertTrue( null == metawidget.getWidget( "foo" ) );
						assertTrue( null == metawidget.getWidget( "nestedWidgets", "foo" ) );
						assertTrue( 10 == metawidget.getMaximumInspectionDepth() );
						assertTrue( 9 == metawidgetNested.getMaximumInspectionDepth() );

						final FlexTable flexTableNested = (FlexTable) metawidgetNested.getWidget( 0 );

						assertEquals( "Further nested widgets:", flexTableNested.getText( 0, 0 ) );
						final GwtMetawidget metawidgetFurtherNested = (GwtMetawidget) metawidgetNested.getWidget( "furtherNestedWidgets" );

						executeAfterBuildWidgets( metawidgetFurtherNested, new Timer() {

							@Override
							public void run() {

								final FlexTable flexTableFurtherNested = (FlexTable) metawidgetFurtherNested.getWidget( 0 );

								assertEquals( "Further nested widgets:", flexTableFurtherNested.getText( 0, 0 ) );
								final GwtMetawidget metawidgetFurtherFurtherNested = metawidgetFurtherNested.getWidget( "furtherNestedWidgets" );

								executeAfterBuildWidgets( metawidgetFurtherFurtherNested, new Timer() {

									@Override
									public void run() {

										final FlexTable flexTableFurtherFurtherNested = (FlexTable) metawidgetFurtherFurtherNested.getWidget( 0 );
										assertTrue( 0 == flexTableFurtherFurtherNested.getRowCount() );

										assertEquals( "Nested textbox 1:", flexTableFurtherNested.getText( 1, 0 ) );
										assertTrue( flexTableFurtherNested.getWidget( 1, 1 ) instanceof TextBox );
										assertEquals( "Nested Textbox 1", metawidget.getValue( "nestedWidgets", "furtherNestedWidgets", "nestedTextbox1" ) );
										( (TextBox) flexTableFurtherNested.getWidget( 1, 1 ) ).setText( "Nested Textbox 1.1 (further)" );

										// (test getEffectiveNumberOfColumns)

										assertTrue( 3 == flexTableFurtherNested.getCellCount( 1 ) );

										assertEquals( "Nested textbox 2:", flexTableFurtherNested.getText( 2, 0 ) );
										assertTrue( flexTableFurtherNested.getWidget( 2, 1 ) instanceof TextBox );
										assertEquals( "Nested Textbox 2", metawidget.getValue( "nestedWidgets", "furtherNestedWidgets", "nestedTextbox2" ) );
										( (TextBox) flexTableFurtherNested.getWidget( 2, 1 ) ).setText( "Nested Textbox 2.2 (further)" );

										assertEquals( "Nested textbox 1:", flexTableNested.getText( 1, 0 ) );
										assertTrue( flexTableNested.getWidget( 1, 1 ) instanceof TextBox );
										assertEquals( "Nested Textbox 1", metawidget.getValue( "nestedWidgets", "nestedTextbox1" ) );
										( (TextBox) flexTableNested.getWidget( 1, 1 ) ).setText( "Nested Textbox 1.1" );

										assertEquals( "Nested textbox 2:", flexTableNested.getText( 2, 0 ) );
										assertTrue( flexTableNested.getWidget( 2, 1 ) instanceof TextBox );
										assertEquals( "Nested Textbox 2", metawidget.getValue( "nestedWidgets", "nestedTextbox2" ) );
										( (TextBox) flexTableNested.getWidget( 2, 1 ) ).setText( "Nested Textbox 2.2" );

										assertEquals( "Read only nested widgets:", flexTable.getText( 15, 0 ) );
										assertTrue( flexTable.getWidget( 15, 1 ) instanceof GwtMetawidget );

										final GwtMetawidget metawidgetReadOnlyNested = metawidget.getWidget( "readOnlyNestedWidgets" );

										executeAfterBuildWidgets( metawidgetReadOnlyNested, new Timer() {

											@Override
											public void run() {

												final FlexTable flexTableReadOnlyNested = (FlexTable) metawidgetReadOnlyNested.getWidget( 0 );

												assertEquals( "Further nested widgets:", flexTableReadOnlyNested.getText( 0, 0 ) );
												final GwtMetawidget metawidgetReadOnlyFurtherNested = metawidgetReadOnlyNested.getWidget( "furtherNestedWidgets" );

												executeAfterBuildWidgets( metawidgetReadOnlyFurtherNested, new Timer() {

													@Override
													public void run() {

														final FlexTable flexTableReadOnlyFurtherNested = (FlexTable) metawidgetReadOnlyFurtherNested.getWidget( 0 );
														assertTrue( 0 == flexTableReadOnlyFurtherNested.getRowCount() );

														assertEquals( "Nested textbox 1:", flexTableReadOnlyNested.getText( 1, 0 ) );
														assertTrue( flexTableReadOnlyNested.getWidget( 1, 1 ) instanceof Label );
														assertEquals( "Nested Textbox 1", metawidget.getValue( "readOnlyNestedWidgets", "nestedTextbox1" ) );

														assertEquals( "Nested textbox 2:", flexTableReadOnlyNested.getText( 2, 0 ) );
														assertTrue( flexTableReadOnlyNested.getWidget( 2, 1 ) instanceof Label );
														assertEquals( "Nested Textbox 2", metawidget.getValue( "readOnlyNestedWidgets", "nestedTextbox2" ) );

														assertEquals( "Nested widgets dont expand:", flexTable.getText( 16, 0 ) );
														assertTrue( flexTable.getWidget( 16, 1 ) instanceof TextBox );
														assertEquals( "Nested Textbox 1, Nested Textbox 2", ( (TextBox) flexTable.getWidget( 16, 1 ) ).getText() );
														( (TextBox) flexTable.getWidget( 16, 1 ) ).setText( "Nested Textbox 1.01, Nested Textbox 2.02" );

														assertEquals( "Read only nested widgets dont expand:", flexTable.getText( 16, 3 ) );
														assertTrue( flexTable.getWidget( 16, 4 ) instanceof Label );
														assertEquals( "Nested Textbox 1, Nested Textbox 2", ( (Label) flexTable.getWidget( 16, 4 ) ).getText() );

														assertEquals( "Date:", flexTable.getText( 17, 0 ) );
														assertTrue( flexTable.getWidget( 17, 1 ) instanceof TextBox );
														assertEquals( new DateConverter().convertForWidget( null, ( (AllWidgets) metawidget.getToInspect() ).getDate() ), metawidget.getValue( "date" ) );
														( (TextBox) flexTable.getWidget( 17, 1 ) ).setText( "bad date" );
														assertEquals( 3, flexTable.getCellCount( 17 ) );

														assertEquals( "Section Break", ( (Label) flexTable.getWidget( 18, 0 ) ).getText() );

														assertEquals( "Read only:", flexTable.getText( 19, 0 ) );
														assertTrue( flexTable.getWidget( 19, 1 ) instanceof Label );
														assertEquals( "Read Only", metawidget.getValue( "readOnly" ) );

														assertEquals( "", flexTable.getText( 19, 3 ) );
														Button doActionButton = (Button) flexTable.getWidget( 19, 4 );
														assertEquals( "Do action", doActionButton.getText() );
														assertTrue( doActionButton.isEnabled() );
														try {
															fireClickEvent( doActionButton );
															assertTrue( false );
														} catch ( Exception e ) {
															e.printStackTrace();
															assertEquals( "doAction called", e.getMessage() );
														}

														assertTrue( 21 == flexTable.getRowCount() );

														// Check IllegalArgumentException

														assertTrue( 6 == flexTable.getFlexCellFormatter().getColSpan( 20, 0 ) );
														assertTrue( 1 == flexTable.getCellCount( 20 ) );
														Button saveButton = (Button) ( (Facet) flexTable.getWidget( 20, 0 ) ).getWidget();
														assertEquals( "Save", saveButton.getText() );

														try {
															fireClickEvent( saveButton );
															assertTrue( false );
														} catch ( IllegalArgumentException e ) {
															assertEquals( "bad date", e.getMessage() );
														}

														// Check saving

														final String now = (String) new DateConverter().convertForWidget( null, new Date() );
														( (TextBox) flexTable.getWidget( 17, 1 ) ).setText( now );
														fireClickEvent( saveButton );

														executeAfterBuildWidgets( metawidget, new Timer() {

															@Override
															public void run() {

																final FlexTable readOnlyFlexTable = (FlexTable) metawidget.getWidget( 0 );

																assertEquals( "Textbox (i18n):", readOnlyFlexTable.getText( 0, 0 ) );
																assertEquals( "Textbox1", ( (Label) readOnlyFlexTable.getWidget( 1, 0 ) ).getText() );
																assertEquals( "Limited textbox (i18n):", readOnlyFlexTable.getText( 2, 0 ) );
																assertEquals( "Limited Textbox1", ( (Label) readOnlyFlexTable.getWidget( 3, 0 ) ).getText() );
																assertEquals( "Textarea (i18n):", readOnlyFlexTable.getText( 4, 0 ) );
																assertEquals( "Textarea1", ( (Label) readOnlyFlexTable.getWidget( 5, 0 ) ).getText() );
																assertEquals( "Password (i18n):", readOnlyFlexTable.getText( 6, 0 ) );
																assertTrue( readOnlyFlexTable.getWidget( 7, 0 ) instanceof SimplePanel );
																assertEquals( "Byte (i18n):", readOnlyFlexTable.getText( 8, 0 ) );
																assertEquals( "126", ( (Label) readOnlyFlexTable.getWidget( 9, 0 ) ).getText() );
																assertEquals( "Byte object (i18n):", readOnlyFlexTable.getText( 10, 0 ) );
																assertEquals( "-127", ( (Label) readOnlyFlexTable.getWidget( 11, 0 ) ).getText() );
																assertEquals( "Short (i18n):", readOnlyFlexTable.getText( 12, 0 ) );
																assertEquals( "32766", ( (Label) readOnlyFlexTable.getWidget( 13, 0 ) ).getText() );
																assertEquals( "Short object (i18n):", readOnlyFlexTable.getText( 14, 0 ) );
																assertEquals( "-32767", ( (Label) readOnlyFlexTable.getWidget( 15, 0 ) ).getText() );
																assertEquals( "Int (i18n):", readOnlyFlexTable.getText( 16, 0 ) );
																assertEquals( "2147483646", ( (Label) readOnlyFlexTable.getWidget( 17, 0 ) ).getText() );
																assertEquals( "Integer object (i18n):", readOnlyFlexTable.getText( 18, 0 ) );
																assertEquals( "-2147483647", ( (Label) readOnlyFlexTable.getWidget( 19, 0 ) ).getText() );
																assertEquals( "Ranged int (i18n):", readOnlyFlexTable.getText( 20, 0 ) );
																assertEquals( "33", ( (Label) readOnlyFlexTable.getWidget( 21, 0 ) ).getText() );
																assertEquals( "Ranged integer (i18n):", readOnlyFlexTable.getText( 22, 0 ) );
																assertEquals( "34", ( (Label) readOnlyFlexTable.getWidget( 23, 0 ) ).getText() );
																assertEquals( "Long (i18n):", readOnlyFlexTable.getText( 24, 0 ) );
																assertEquals( "43", ( (Label) readOnlyFlexTable.getWidget( 25, 0 ) ).getText() );
																assertEquals( "??????:", readOnlyFlexTable.getText( 26, 0 ) );
																assertEquals( "44", ( (Label) readOnlyFlexTable.getWidget( 27, 0 ) ).getText() );
																assertEquals( "Float (i18n):", readOnlyFlexTable.getText( 28, 0 ) );
																assertEquals( "5.3", ( (Label) readOnlyFlexTable.getWidget( 29, 0 ) ).getText() );
																assertTrue( 0 == readOnlyFlexTable.getCellCount( 30 ) );
																assertEquals( "5.4", ( (Label) readOnlyFlexTable.getWidget( 31, 0 ) ).getText() );
																assertEquals( "Double (i18n):", readOnlyFlexTable.getText( 32, 0 ) );
																assertEquals( "53.3", ( (Label) readOnlyFlexTable.getWidget( 33, 0 ) ).getText() );
																assertEquals( "54.4", ( (Label) readOnlyFlexTable.getWidget( 34, 0 ) ).getText() );
																assertTrue( 2 == readOnlyFlexTable.getFlexCellFormatter().getColSpan( 34, 0 ) );
																assertEquals( "Char (i18n):", readOnlyFlexTable.getText( 35, 0 ) );
																assertEquals( "Z", ( (Label) readOnlyFlexTable.getWidget( 36, 0 ) ).getText() );
																assertEquals( "Character object (i18n):", readOnlyFlexTable.getText( 37, 0 ) );
																assertEquals( "A", ( (Label) readOnlyFlexTable.getWidget( 38, 0 ) ).getText() );
																assertEquals( "Boolean (i18n):", readOnlyFlexTable.getText( 39, 0 ) );
																assertEquals( "true", ( (Label) readOnlyFlexTable.getWidget( 40, 0 ) ).getText() );
																assertEquals( "Boolean object (i18n):", readOnlyFlexTable.getText( 41, 0 ) );
																assertEquals( "false", ( (Label) readOnlyFlexTable.getWidget( 42, 0 ) ).getText() );
																assertEquals( "Dropdown (i18n):", readOnlyFlexTable.getText( 43, 0 ) );
																assertEquals( "foo1", ( (Label) readOnlyFlexTable.getWidget( 44, 0 ) ).getText() );
																assertEquals( "Dropdown with Labels (i18n):", readOnlyFlexTable.getText( 45, 0 ) );
																assertEquals( "bar2", ( (Label) readOnlyFlexTable.getWidget( 46, 0 ) ).getText() );
																assertEquals( "Not-null Dropdown (i18n):", readOnlyFlexTable.getText( 47, 0 ) );
																assertEquals( "1", ( (Label) readOnlyFlexTable.getWidget( 48, 0 ) ).getText() );
																assertEquals( "Not-null Object Dropdown (i18n):", readOnlyFlexTable.getText( 49, 0 ) );
																assertEquals( "foo3", ( (Label) readOnlyFlexTable.getWidget( 50, 0 ) ).getText() );
																assertEquals( "Nested Widgets (i18n):", readOnlyFlexTable.getText( 51, 0 ) );

																final GwtMetawidget readOnlyMetawidgetNested = (GwtMetawidget) readOnlyFlexTable.getWidget( 52, 0 );

																executeAfterBuildWidgets( readOnlyMetawidgetNested, new Timer() {

																	@Override
																	public void run() {

																		final FlexTable readOnlyFlexTableNested = (FlexTable) readOnlyMetawidgetNested.getWidget( 0 );

																		assertEquals( "Further Nested Widgets (i18n):", readOnlyFlexTableNested.getText( 0, 0 ) );
																		final GwtMetawidget readOnlyMetawidgetFurtherNested = (GwtMetawidget) readOnlyFlexTableNested.getWidget( 0, 1 );

																		executeAfterBuildWidgets( readOnlyMetawidgetFurtherNested, new Timer() {

																			@Override
																			public void run() {

																				final FlexTable readOnlyFlexTableFurtherNested = (FlexTable) readOnlyMetawidgetFurtherNested.getWidget( 0 );
																				assertEquals( "Nested Textbox 1.1 (further)", ( (Label) readOnlyFlexTableFurtherNested.getWidget( 1, 1 ) ).getText() );
																				assertEquals( "Nested Textbox 2.2 (further)", ( (Label) readOnlyFlexTableFurtherNested.getWidget( 2, 1 ) ).getText() );

																				assertEquals( "???nestedTextbox1???:", readOnlyFlexTableNested.getText( 1, 0 ) );
																				assertEquals( "Nested Textbox 1.1", ( (Label) readOnlyFlexTableNested.getWidget( 1, 1 ) ).getText() );
																				assertEquals( "Nested Textbox 2 (i18n):", readOnlyFlexTableNested.getText( 2, 0 ) );
																				assertEquals( "Nested Textbox 2.2", ( (Label) readOnlyFlexTableNested.getWidget( 2, 1 ) ).getText() );

																				final GwtMetawidget readOnlyMetawidgetNested2 = (GwtMetawidget) readOnlyFlexTable.getWidget( 54, 0 );

																				executeAfterBuildWidgets( readOnlyMetawidgetNested2, new Timer() {

																					@Override
																					public void run() {

																						FlexTable readOnlyFlexTableNested2 = (FlexTable) readOnlyMetawidgetNested2.getWidget( 0 );
																						assertEquals( "???nestedTextbox1???:", readOnlyFlexTableNested2.getText( 1, 0 ) );
																						assertEquals( "Nested Textbox 1", ( (Label) readOnlyFlexTableNested2.getWidget( 1, 1 ) ).getText() );
																						assertEquals( "Nested Textbox 2 (i18n):", readOnlyFlexTableNested2.getText( 2, 0 ) );
																						assertEquals( "Nested Textbox 2", ( (Label) readOnlyFlexTableNested2.getWidget( 2, 1 ) ).getText() );

																						assertEquals( "Nested Widgets (don't expand) (i18n):", readOnlyFlexTable.getText( 55, 0 ) );
																						assertEquals( "Nested Textbox 1.01, Nested Textbox 2.02", ( (Label) readOnlyFlexTable.getWidget( 56, 0 ) ).getText() );

																						assertEquals( "Read only Nested Widgets (don't expand) (i18n):", readOnlyFlexTable.getText( 57, 0 ) );
																						assertEquals( "Nested Textbox 1, Nested Textbox 2", ( (Label) readOnlyFlexTable.getWidget( 58, 0 ) ).getText() );

																						assertEquals( "Date (i18n):", readOnlyFlexTable.getText( 59, 0 ) );
																						assertEquals( now, ( (Label) readOnlyFlexTable.getWidget( 60, 0 ) ).getText() );

																						assertEquals( "Section Break (i18n)", ( (Label) readOnlyFlexTable.getWidget( 61, 0 ) ).getText() );
																						assertEquals( "aSectionStyleName", ( (Label) readOnlyFlexTable.getWidget( 61, 0 ) ).getStyleName() );

																						assertEquals( "Read only (i18n):", readOnlyFlexTable.getText( 62, 0 ) );
																						assertEquals( "Read Only", ( (Label) readOnlyFlexTable.getWidget( 63, 0 ) ).getText() );

																						assertTrue( 0 == readOnlyFlexTable.getCellCount( 64 ) );
																						Button readOnlyDoActionButton = (Button) readOnlyFlexTable.getWidget( 65, 0 );
																						assertEquals( "Action (i18n)", readOnlyDoActionButton.getText() );
																						assertTrue( !readOnlyDoActionButton.isEnabled() );

																						assertEquals( "Save", readOnlyFlexTable.getText( 66, 0 ) );
																						assertTrue( 67 == readOnlyFlexTable.getRowCount() );

																						// Test
																						// maximum
																						// inspection
																						// depth

																						metawidget.setMaximumInspectionDepth( 0 );

																						executeAfterBuildWidgets( metawidget, new Timer() {

																							@Override
																							public void run() {

																								assertTrue( null == metawidget.getWidget( "nestedWidgets" ) );
																								assertTrue( null == metawidget.getWidget( "readOnlyNestedWidgets" ) );
																								assertTrue( metawidget.getWidget( "readOnlyNestedWidgetsDontExpand" ) instanceof Label );

																								finish();
																							}
																						} );
																					}
																				} );
																			}
																		} );
																	}
																} );
															}
														} );
													}
												} );
											}
										} );
									}
								} );
							}
						} );
					}
				} );
			}
		} );

		// Test runs asynchronously

		delayTestFinish( TEST_FINISH_DELAY );
	}

	//
	// Private methods
	//

	/**
	 * Wrapped to avoid 'synthetic access' warning
	 */

	/* package private */void finish() {

		super.finishTest();
	}

	/* package private */void fireClickEvent( HasHandlers widget ) {

		Document document = Document.get();
		NativeEvent nativeEvent = document.createClickEvent( 0, 0, 0, 0, 0, false, false, false, false );
		DomEvent.fireNativeEvent( nativeEvent, widget );
	}

	//
	// Native methods
	//

	native void executeAfterBuildWidgets( GwtMetawidget metawidget, Timer timer )
	/*-{
		metawidget.@org.metawidget.gwt.client.ui.GwtMetawidget::mExecuteAfterBuildWidgets = timer;
		metawidget.@org.metawidget.gwt.client.ui.GwtMetawidget::buildWidgets()();
	}-*/;

	private native void prepareBundle()
	/*-{
		$wnd[ "bundle" ] = {
			"textbox": "Textbox (i18n)",
			"limitedTextbox": "Limited textbox (i18n)",
			"textarea": "Textarea (i18n)",
			"password": "Password (i18n)",
			"byte": "Byte (i18n)",
			"byteObject": "Byte object (i18n)",
			"short": "Short (i18n)",
			"shortObject": "Short object (i18n)",
			"int": "Int (i18n)",
			"integerObject": "Integer object (i18n)",
			"rangedInt": "Ranged int (i18n)",
			"rangedInteger": "Ranged integer (i18n)",
			"long": "Long (i18n)",
			"longObject": "Long object (i18n)",
			"float": "Float (i18n)",
			"nullInBundle": "",
			"double": "Double (i18n)",
			"char": "Char (i18n)",
			"characterObject": "Character object (i18n)",
			"boolean": "Boolean (i18n)",
			"booleanObject": "Boolean object (i18n)",
			"dropdown": "Dropdown (i18n)",
			"dropdownWithLabels": "Dropdown with Labels (i18n)",
			"notNullDropdown": "Not-null Dropdown (i18n)",
			"notNullObjectDropdown": "Not-null Object Dropdown (i18n)",
			"nestedWidgets": "Nested Widgets (i18n)",
			"furtherNestedWidgets": "Further Nested Widgets (i18n)",
			"nestedTextbox2": "Nested Textbox 2 (i18n)",
			"readOnlyNestedWidgets": "Read only Nested Widgets (i18n)",
			"nestedWidgetsDontExpand": "Nested Widgets (don't expand) (i18n)",
			"readOnlyNestedWidgetsDontExpand": "Read only Nested Widgets (don't expand) (i18n)",
			"date": "Date (i18n)",
			"sectionBreak": "Section Break (i18n)",
			"readOnly": "Read only (i18n)",
			"collection": "Collection (i18n)",
			"doAction": "Action (i18n)"
		};
	}-*/;
}