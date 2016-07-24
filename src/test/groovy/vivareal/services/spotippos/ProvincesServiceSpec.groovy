package vivareal.services.spotippos

import grails.test.mixin.TestFor
import spock.lang.Specification

import vivareal.services.integration.VivaRealIntegrationService

@TestFor(ProvincesService)
class ProvincesServiceSpec extends Specification {

    def provincesService 

    def setup() {
      provincesService = new ProvincesService()

      def vivaRealIntegrationService = new VivaRealIntegrationService()
      vivaRealIntegrationService.provincesJsonUrl = 'https://raw.githubusercontent.com/VivaReal/code-challenge/master/provinces.json'
      provincesService.provinces = vivaRealIntegrationService.provincesFromVivaReal()
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
}
