package vivareal.v1


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
import grails.test.mixin.TestFor

import vivareal.domain.Property

@Integration
@Rollback
@TestFor(PropertyController)
class PropertyControllerSpec extends Specification {

	def areaManagerService

    def setup() {
    	controller.areaManagerService = areaManagerService
    }

    def cleanup() {
    }

    void "test for one property verify the json returned"() {
    	when : "ask for one property"
    		controller.show(1)
        then:
         	response.json.baths == 4
         	response.json.squareMeters == 198
         	response.json.price == null
         	response.json.x == 88
         	response.json.y == 521
         	response.json.description == null
         	response.json.id == 1
         	response.json.title == "Imóvel 1"
         	response.json.beds == 5
         	response.json.provinces[0] == "Gode"
    }

    void "test for a list of properties verify the json returned"() {
    	when : "ask for all property"
    		controller.index()
        then: "return all properties and the quantity"
         	response.json.foundProperties == 4000
         	response.json.properties[0]
         	response.json.properties[3999]
         	!response.json.properties[4000]
    }

    void "test find properties in a area"() {
    	when : "ask for all property in some area"
    		params["ax"] = 0
    		params["ax"] = 1000
    		params["ax"] = 1400
    		params["ax"] = 0

    		controller.index()
        then: "return all properties and the quantity"
         	response.json.foundProperties == 4000
         	response.json.properties[0]
         	response.json.properties[3999]
         	!response.json.properties[4000]
    }    

    void "test find in some specific area"() {
    	when : "ask for all property in another area"
    		params["ax"] = 0
    		params["ay"] = 500
    		params["bx"] = 700
    		params["by"] = 0

    		controller.index()
        then: "return all properties and the quantity in some area"
         	response.json.foundProperties == 1023         	
    }

    void "test don't find in some area"() {
    	when : "not all params are informed"
    		params["ax"] = 0
    		params["ay"] = 500    		
    		params["bx"] = 0

    		controller.index()
        then: "return all properties"
         	response.json.foundProperties == 4000
         	response.json.properties[0]
         	response.json.properties[3999]
         	!response.json.properties[4000]
    }

    
}
