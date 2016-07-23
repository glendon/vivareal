package vivareal.services.spotippos

import grails.test.mixin.TestFor
import spock.lang.Specification

import vivareal.services.integration.VivaRealIntegrationService

@TestFor(ProvincesService)
class ProvincesServiceSpec extends Specification {

    def provincesService 

    def setup() {
      provincesService = new ProvincesService()
      provincesService.provinces = new VivaRealIntegrationService().provincesFromVivaReal()
    }

    def cleanup() {
    }

     void "test frontiers"() {
      when : "ask for a province from a valid coordinates in a frontier of 3 provinces"
        def provinces = provincesService.getProvinces(410, 500)
      
      then : "verify if it is 3"
        provinces.size() == 3
    }

   void "test answer which province some area belongs to"() {
   		when : "ask for a province from a valid coordinates"
   			def provinces = provincesService.getProvinces(88, 480)
   		then : "verify if it is the right provincy"
        provinces[0]
   			provinces[0].name == "Scavy"   			


      when : "ask for provinces that share the same area"
        provinces = provincesService.getProvinces(410, 700)
      then : "verify if it is the right provincy"
        provinces.size() == 2
        provinces[0].name == "Gode" || provinces[0].name == "Ruja"
        provinces[1].name == "Gode" || provinces[1].name == "Ruja"
   	}    

   	void "test to get provincy with success"() {
    	when : "load a provincy called Gode"    	    		    		
        	def provincy = provincesService.getProvincy("Gode")        	
        then : "verify if there are values"
        	provincy
        	provincy.name == "Gode"        	

        when : "load a provincy called Jaby"    	    		    		
        	provincy = provincesService.getProvincy("Jaby")        	
        then : "verify if there are values"
        	provincy
        	provincy.name == "Jaby"
    }    

    void "test fail when the name isn't informed"() {
    	when : "try to call in valid spotippos object"       		    		
        then : "fail whe the provincy name is null"
        	shouldFail(IllegalArgumentException) { provincesService.getProvincy(null) }
    }

    void "test fail to get provincy when there is no area"() {
      when : "load a provincy by name and there is no area"                     
          provincesService.provinces = null
        then : "it tails when try to get the provincy"
          shouldFail(RuntimeException) { provincesService.getProvincy("Gode") }
    }
}
