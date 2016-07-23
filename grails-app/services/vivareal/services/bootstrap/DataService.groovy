package vivareal.services.bootstrap

import grails.core.GrailsApplication
import grails.util.Environment

class DataService {

	def vivaRealIntegrationService
    def provincesService
    def areaManagerService

	GrailsApplication grailsApplication

    def loadApplicationData() {

		insertProvinces()
        provincesService.loadProvinces()
        insertProperties()
    }

    def insertProvinces() {

    	def insertProvinces = new Boolean(grailsApplication.config.getProperty('bootstrap.insert.provinces'))
    	insertProvinces = insertProvinces || Environment.current == Environment.TEST

    	if (insertProvinces) {
            vivaRealIntegrationService.provincesFromVivaReal().each{ provincy ->                
                    provincy.save(flush:true, failOnError:true)
            }
        }
    }

    def insertProperties() {

    	def insertProperties = new Boolean(grailsApplication.config.getProperty('bootstrap.insert.properties'))
    	insertProperties = insertProperties || Environment.current == Environment.TEST

    	if (insertProperties) {        

            vivaRealIntegrationService.propertiesFromVivaReal().each{ propertyVivaReal ->
                
                propertyVivaReal.title = "Imóvel $propertyVivaReal.id"
                areaManagerService.createNew(propertyVivaReal)              
            }
	    }
	} 
}
