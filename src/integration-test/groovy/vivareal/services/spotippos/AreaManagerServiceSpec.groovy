package vivareal.services.spotippos

import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

import vivareal.domain.Property

@Integration
@Rollback
class AreaManagerServiceSpec extends Specification {	

    def areaManagerService

    def setup() {
        def tempProperty = new Property()
        tempProperty.id = 9999
        tempProperty.title = "Property test x1399 y999"       
        tempProperty.beds = 1
        tempProperty.baths = 1
        tempProperty.squareMeters = 20 
        tempProperty.x = 1399
        tempProperty.y = 999

        tempProperty.save(flush:true, failOnError:true)
    }

    def cleanup() {
        Property.where { id == 9999 }.deleteAll()
    }

    void "test insert a new property and find it in search by area"(){
        when : "insert a new property search for it in a specific area"            

            def propertiesInArea = areaManagerService.getAllPropertysIn(1380, 999, 1399, 950)

            def properties = propertiesInArea.findAll { property ->
                property.id == 9999
            }

        then: "verify if the property was found"  
            properties
            properties[0].id == 9999   
        
    }

    void "test can't find the new property"(){
        when : "search in another position"
            def propertiesInArea = areaManagerService.getAllPropertysIn(1380, 997, 1397, 950)

            def properties = propertiesInArea.findAll { property ->
                property.id == 9999
            }
        then : "can't be found"   
            properties.size() == 0
    }

    void "test try to recovering all properties"() {

        when : "ask for properties in the whole area"
            def properties = areaManagerService.getAllPropertysIn(0, 1000, 14000, 0)
            
        then : "verify if the quantity is 4001"
            properties.size() == 4001
    }
}
