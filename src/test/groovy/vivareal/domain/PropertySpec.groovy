package vivareal.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Property)
class PropertySpec extends Specification {

	def property = new Property()

    def setup() {
    	//Populate with valid values
    	property.title = "Property #1"    	
    	property.beds = 1
    	property.baths = 1
    	property.squareMeters = 20 
    }

    def cleanup() {
    }

    void "test title in blank"() {
    	when : "is blank"
            property.title = ""          
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "isn't blank"
            property.title = "Now I have a title"          
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()	
    }

    void "test limits of beds"() {
        when : "more then 5"
            property.beds = 6          
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "less then 1"
            property.beds = 0            
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "min limit 1"
            property.beds = 1            
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()

        when : "max limit 5"
            property.beds = 5            
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()        
    }

    void "test limits of baths"() {
        when : "more then 4"
            property.baths = 5         
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "less then 1"
            property.baths = 0            
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "min limit 1"
            property.baths = 1            
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()

        when : "max limit 4"
            property.baths = 4       
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()        
    }

    void "test squareMeters limit"() {
        when : "more then 240"
            property.squareMeters = 241       
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "less then 20"
            property.squareMeters = 19            
        then : "validation fails"
        	!property.validate()
        	property.hasErrors()

        when : "min limit 20"
            property.squareMeters = 20          
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()

        when : "max limit 240"
            property.squareMeters = 240     
        then : "validation pass"
        	property.validate()
        	!property.hasErrors()        
    }

}
