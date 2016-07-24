package vivareal.services.spotippos

import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

import grails.core.GrailsApplication

import vivareal.domain.Property

@Integration
@Rollback

class PropertyFinderInAreaServiceSpec extends Specification {

	def propertyFinderInAreaService
	
    def setupSpec() {

        def tempProperty = new Property()        
        tempProperty.title = "Property test x1399 y999"       
        tempProperty.beds = 1
        tempProperty.baths = 1
        tempProperty.squareMeters = 20 
        tempProperty.x = 1399
        tempProperty.y = 999

        tempProperty.save(flush:true, failOnError:true)
    }

    def setup() {    	
    }

    def cleanup() {
        Property.where { title == "Property test x1399 y999" }.deleteAll()
    }

    void "test insert a new property and find it in search by area"(){
        when : "insert a new property search for it in a specific area"                        

            def response = propertyFinderInAreaService.whenUpperLeftXIs(1380).andUpperLeftYIs(999)
            .andBottomRightXIs(1399).andBottomRightYIs(950).returnPropertiesInArea()

            def properties = response.properties.findAll { property ->
                property.title = "Property test x1399 y999"
            }

        then: "verify if the property was found"  
            properties
            properties[0].title == "Property test x1399 y999"          
    }

    void "test can't find the new property"(){
        when : "search in another position"            

            def response = propertyFinderInAreaService.whenUpperLeftXIs(1380).andUpperLeftYIs(997)
            .andBottomRightXIs(1397).andBottomRightYIs(950).returnPropertiesInArea()

            def properties = response.properties.findAll { property ->
                property.title == "Property test x1399 y999"
            }
        then : "can't be found"   
            properties.size() == 0
    }

    void "test try to recovering all properties"() {

        when : "ask for properties in the whole area"

            def response = propertyFinderInAreaService.whenUpperLeftXIs(0).andUpperLeftYIs(1000)
            .andBottomRightXIs(1400).andBottomRightYIs(0).returnPropertiesInArea()
            
        then : "verify if the quantity returned is the total of properties stored"
            response.foundProperties == Property.list().size()
    }
}
