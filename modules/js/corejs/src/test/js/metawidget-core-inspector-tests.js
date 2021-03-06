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

( function() {

	'use strict';

	describe( "The CompositeInspector", function() {

		it( "composes inspection results", function() {

			var inspector = new metawidget.inspector.CompositeInspector( [ function( toInspect, type ) {

				return {
					properties: {
						"foo": {
							override: "base",
							type: "fooType"
						},
						"bar": {
							"type": "string"
						}
					}
				};
			}, function( toInspect, type ) {

				return {
					properties: {
						"baz": {},
						"foo": {
							override: "overridden",
							required: "fooRequired"							
						}
					}
				};
			} ] );

			var inspectionResult = inspector.inspect();

			expect( inspectionResult.name ).toBeUndefined();
			expect( inspectionResult.type ).toBeUndefined();
			expect( inspectionResult.properties.foo.override ).toBe( 'overridden' );
			expect( inspectionResult.properties.foo.required ).toBe( 'fooRequired' );
			expect( inspectionResult.properties.bar.type ).toBe( 'string' );
			expect( inspectionResult.properties.baz ).toBeDefined();
		} );

		it( "defensively copies inspectors", function() {

			// Direct

			var inspectors = [ function( toInspect, type ) {

				return {
					name: "foo"
				};
			} ];

			var inspector = new metawidget.inspector.CompositeInspector( inspectors );
			var inspectionResult = inspector.inspect();
			expect( inspectionResult.name ).toBe( 'foo' );

			inspectionResult.name = undefined;
			inspectionResult = inspector.inspect();
			expect( inspectionResult.name ).toBe( 'foo' );

			// Via config

			var config = {
				inspectors: [ function( toInspect, type ) {

					return {
						name: "foo"
					};
				} ]
			};

			inspector = new metawidget.inspector.CompositeInspector( config );
			inspectionResult = inspector.inspect();
			expect( inspectionResult.name ).toBe( 'foo' );

			expect( config.inspectors.length ).toBe( 1 );
			config.inspectors.splice( 0, 1 );
			expect( config.inspectors.length ).toBe( 0 );
			inspectionResult = inspector.inspect();
			expect( inspectionResult.name ).toBe( 'foo' );
		} );

		it( "defensively copies inspection results", function() {

			var originalResult = {
				name: "foo",
				type: "fooType"
			};

			var inspector = new metawidget.inspector.CompositeInspector( [ function( toInspect, type ) {

				return originalResult;
			} ] );

			var inspectionResult = inspector.inspect();

			expect( inspectionResult.name ).toBe( 'foo' );
			expect( inspectionResult.type ).toBe( 'fooType' );

			inspectionResult.type = 'barType';

			inspectionResult = inspector.inspect();

			expect( inspectionResult.name ).toBe( 'foo' );
			expect( inspectionResult.type ).toBe( 'fooType' );
		} );
	} );

	describe( "The PropertyTypeInspector", function() {

		it( "inspects JavaScript objects", function() {

			var inspector = new metawidget.inspector.PropertyTypeInspector();
			var inspectionResult = inspector.inspect( {
				foo: "Foo",
				bar: "Bar",
				date: new Date(),
				object: {},
				action: function() {

				},
				array: [],
				bool: true,
				num: 46
			} );

			expect( inspectionResult.type ).toBe( 'object' );
			expect( inspectionResult.properties.foo.type ).toBe( 'string' );
			expect( inspectionResult.properties.bar.type ).toBe( 'string' );
			expect( inspectionResult.properties.date.type ).toBe( 'date' );
			expect( inspectionResult.properties.object.type ).toBeUndefined();
			expect( inspectionResult.properties.action.type ).toBe( 'function' );
			expect( inspectionResult.properties.array.type ).toBe( 'array' );
			expect( inspectionResult.properties.bool.type ).toBe( 'boolean' );
			expect( inspectionResult.properties.num.type ).toBe( 'number' );
		} );

		it( "ignores undefined objects", function() {

			var inspector = new metawidget.inspector.PropertyTypeInspector();

			expect( inspector.inspect() ).toBeUndefined();
			expect( inspector.inspect( undefined ) ).toBeUndefined();
			expect( inspector.inspect( {} ).name ).toBeUndefined();
			expect( inspector.inspect( {} ).type ).toBe( 'object' );
			expect( inspector.inspect( {}, 'foo' ).name ).toBeUndefined();
			expect( inspector.inspect( {}, 'foo' ).type ).toBe( 'object' );
			expect( inspector.inspect( {}, 'foo', [ 'bar' ] ).name ).toBe( 'bar' );
			expect( inspector.inspect( {}, 'foo', [ 'bar' ] ).type ).toBeUndefined();
		} );

		it( "does not ignore empty strings", function() {

			var inspector = new metawidget.inspector.PropertyTypeInspector();

			expect( inspector.inspect( '' ).type ).toBe( 'string' );
			expect( inspector.inspect( {
				'foo': ''
			}, 'ignore', [ 'foo' ] ).type ).toBe( 'string' );
		} );

		it( "inspects parent name", function() {

			var inspector = new metawidget.inspector.PropertyTypeInspector();

			expect( inspector.inspect( {
				'foo': ''
			}, 'ignore', [ 'foo' ] ).name ).toBe( 'foo' );

			for ( var prop in inspector.inspect( {
				'foo': ''
			}, 'ignore', [] ) ) {
				expect( prop ).toNotBe( 'name' );
			}

			for ( var prop in inspector.inspect( {
				'foo': ''
			}, 'ignore', [] ) ) {
				expect( prop ).toNotBe( 'name' );
			}
		} );
	} );
} )();