// Metawidget Examples (licensed under BSD License)
//
// Copyright (c) Richard Kennard
// All rights reserved
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright notice,
//   this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright notice,
//   this list of conditions and the following disclaimer in the documentation
//   and/or other materials provided with the distribution.
// * Neither the name of Richard Kennard nor the names of its contributors may
//   be used to endorse or promote products derived from this software without
//   specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
// OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
// OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
// OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGE.

package org.metawidget.example.gwt.addressbook.client.ui;

import java.util.List;

import org.metawidget.example.gwt.addressbook.client.rpc.ContactsService;
import org.metawidget.example.gwt.addressbook.client.rpc.ContactsServiceAsync;
import org.metawidget.example.shared.addressbook.model.BusinessContact;
import org.metawidget.example.shared.addressbook.model.Contact;
import org.metawidget.example.shared.addressbook.model.ContactSearch;
import org.metawidget.example.shared.addressbook.model.ContactType;
import org.metawidget.example.shared.addressbook.model.PersonalContact;
import org.metawidget.gwt.client.ui.Facet;
import org.metawidget.gwt.client.ui.GwtMetawidget;
import org.metawidget.gwt.client.ui.GwtUtils;
import org.metawidget.gwt.client.ui.layout.FlexTableLayout;
import org.metawidget.gwt.client.ui.layout.FlexTableLayoutConfig;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.ColumnFormatter;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Richard Kennard
 */

public class AddressBookModule
	implements EntryPoint {

	//
	// Package-level members
	//

	Panel					mPanel;

	ContactSearch			mContactSearch;

	FlexTable				mContacts;

	/**
	 * List of Contacts in the FlexTable.
	 * <p>
	 * We maintain this list separately, as the FlexTable doesn't contain the <code>id</code>s we
	 * need for loading.
	 */

	List<Contact>			mContactsList;

	ContactsServiceAsync	mContactsService;

	ContactDialog			mPersonalContactDialog;

	ContactDialog			mBusinessContactDialog;

	//
	// Constructor
	//

	public AddressBookModule() {

		this( RootPanel.get() );
	}

	public AddressBookModule( Panel panel ) {

		mPanel = panel;
	}

	//
	// Public methods
	//

	public void onModuleLoad() {

		// Model

		mContactsService = (ContactsServiceAsync) GWT.create( ContactsService.class );
		mContactSearch = new ContactSearch();

		// Results table

		mContacts = new FlexTable();
		mContacts.setStyleName( "data-table" );

		ColumnFormatter formatter = mContacts.getColumnFormatter();
		formatter.setStyleName( 0, "column-half" );
		formatter.setStyleName( 1, "column-half" );

		reloadContacts();
		mContacts.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				int rowIndex = mContacts.getCellForEvent( event ).getRowIndex();

				// Ignore clicks on the header row

				if ( rowIndex == 0 ) {
					return;
				}

				// Load the id at the clicked row

				long contactId = mContactsList.get( rowIndex - 1 ).getId();

				mContactsService.load( contactId, new AsyncCallback<Contact>() {

					public void onFailure( Throwable caught ) {

						Window.alert( caught.getMessage() );
					}

					public void onSuccess( Contact contact ) {

						showContactDialog( contact );
					}
				} );
			}
		} );

		// Metawidget

		final GwtMetawidget metawidget = new GwtMetawidget();
		metawidget.setDictionaryName( "bundle" );

		FlexTableLayoutConfig layoutConfig = new FlexTableLayoutConfig();
		layoutConfig.setTableStyleName( "table-form" );
		layoutConfig.setColumnStyleNames( "table-label-column", "table-component-column" );
		layoutConfig.setFooterStyleName( "buttons" );
		metawidget.setLayout( new FlexTableLayout( layoutConfig ) );

		metawidget.setToInspect( mContactSearch );

		// Embedded buttons

		Facet buttonsFacet = new Facet();
		buttonsFacet.setName( "buttons" );
		metawidget.add( buttonsFacet );

		FlowPanel panel = new FlowPanel();
		buttonsFacet.add( panel );

		Dictionary dictionary = Dictionary.getDictionary( "bundle" );

		Button searchButton = new Button( dictionary.get( "search" ) );
		searchButton.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				// Example of manual mapping. See ContactDialog for an example of using automatic
				// Bindings

				mContactSearch.setFirstname( (String) metawidget.getValue( "firstname" ) );
				mContactSearch.setSurname( (String) metawidget.getValue( "surname" ) );

				String type = metawidget.getValue( "type" );

				if ( type == null || "".equals( type ) ) {
					mContactSearch.setType( null );
				} else {
					mContactSearch.setType( ContactType.valueOf( type ) );
				}

				reloadContacts();
			}
		} );
		panel.add( searchButton );

		Button addPersonalButton = new Button( dictionary.get( "addPersonal" ) );
		addPersonalButton.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				showContactDialog( new PersonalContact() );
			}
		} );
		panel.add( addPersonalButton );

		Button addBusinessButton = new Button( dictionary.get( "addBusiness" ) );
		addBusinessButton.addClickHandler( new ClickHandler() {

			public void onClick( ClickEvent event ) {

				showContactDialog( new BusinessContact() );
			}
		} );
		panel.add( addBusinessButton );

		// Add to either RootPanel or the given Panel (for unit tests)

		if ( mPanel instanceof RootPanel ) {
			RootPanel.get( "metawidget" ).add( metawidget );
			RootPanel.get( "contacts" ).add( mContacts );
		} else {
			mPanel.add( metawidget );
			mPanel.add( mContacts );
		}
	}

	public Panel getPanel() {

		return mPanel;
	}

	public ContactsServiceAsync getContactsService() {

		return mContactsService;
	}

	public void reloadContacts() {

		// Header

		CellFormatter cellFormatter = mContacts.getCellFormatter();
		mContacts.setText( 0, 0, "Name" );
		cellFormatter.setStyleName( 0, 0, "header" );
		mContacts.setText( 0, 1, "Contact" );
		cellFormatter.setStyleName( 0, 1, "header" );
		mContacts.setHTML( 0, 2, "&nbsp;" );
		cellFormatter.setStyleName( 0, 2, "header" );

		// Contacts

		mContactsService.getAllByExample( mContactSearch, new AsyncCallback<List<Contact>>() {

			public void onFailure( Throwable caught ) {

				Window.alert( caught.getMessage() );
			}

			public void onSuccess( List<Contact> contacts ) {

				mContactsList = contacts;
				int row = 1;

				// Add the given contacts

				for ( Contact contact : contacts ) {
					mContacts.setText( row, 0, contact.getFullname() );
					mContacts.setText( row, 1, GwtUtils.toString( contact.getCommunications(), ',' ) );

					Image image = new Image();

					if ( contact instanceof BusinessContact ) {
						image.setUrl( "media/business-small.gif" );
					} else {
						image.setUrl( "media/personal-small.gif" );
					}

					mContacts.setWidget( row, 2, image );

					row++;
				}

				// Cleanup any extra rows

				while ( mContacts.getRowCount() > row ) {
					mContacts.removeRow( row );
				}
			}
		} );
	}

	//
	// Private methods
	//

	/* package private */void showContactDialog( Contact contact ) {

		if ( contact instanceof BusinessContact ) {
			if ( mBusinessContactDialog == null ) {
				mBusinessContactDialog = new ContactDialog( AddressBookModule.this, contact );
			} else {
				mBusinessContactDialog.rebind( contact );
			}

			mBusinessContactDialog.show();
			return;
		}

		if ( mPersonalContactDialog == null ) {
			mPersonalContactDialog = new ContactDialog( AddressBookModule.this, contact );
		} else {
			mPersonalContactDialog.rebind( contact );
		}

		mPersonalContactDialog.show();
		return;
	}
}
