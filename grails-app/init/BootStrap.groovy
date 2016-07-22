
import vivareal.*
import grails.util.Environment

class BootStrap {

	def vivaRealIntegrationService
    def provincesService

    def init = { servletContext ->

        provincesService.loadProvinces()

    	if (Environment.current == Environment.TEST) {
           vivaRealIntegrationService.insertPropertiesFromVivaReal()           
	    }
    }
    def destroy = {
    	if (Environment.current == Environment.TEST) {
            Property.where { }.deleteAll()
        }
    }
}
