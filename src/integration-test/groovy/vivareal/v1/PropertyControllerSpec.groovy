package vivareal.v1


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
import grails.test.mixin.TestFor

@Integration
@Rollback
@TestFor(PropertyController)
class PropertyControllerSpec extends Specification {


    def setup() {
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

    void "test insert one property"() {
    	when : "ask for one property"
    		controller.index()
        then:
         	response.json.foundProperties == 4000
         	response.json.properties[0]
         	response.json.properties[3999]
         	!response.json.properties[4000]
    }
}
