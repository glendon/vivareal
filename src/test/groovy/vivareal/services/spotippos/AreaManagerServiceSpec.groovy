package vivareal.services.spotippos

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(AreaManagerService)
class AreaManagerServiceSpec extends Specification {

    def areaManagerService

    def setup() {
      areaManagerService = new AreaManagerService()
      areaManagerService.xLimit = 1400
      areaManagerService.yLimit = 1000
    }

    def cleanup() {
    }

    void "test inform when is a invalid area"() {
   		when : "ask if it is a invalid coordinates"
   			def invalid = areaManagerService.isAreaValid(2000, 2000)
   		then : "verify if the result is false"
   			!invalid
   	}

   	void "test inform when is a valid area"() {
   		when : "ask if it is a valid coordinates"
   			def valid = areaManagerService.isAreaValid(1400, 1000)
   		then : "verify if the result is true"
   			valid
   	}
}
