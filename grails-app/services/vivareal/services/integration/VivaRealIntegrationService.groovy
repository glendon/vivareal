package vivareal.services.integration

import vivareal.integration.model.*
import org.springframework.beans.factory.annotation.*

class VivaRealIntegrationService {

    @Value('${spotippos.url.properties}')
    String propertiesJsonUrl
    
    @Value('${spotippos.url.provinces}')
    String provincesJsonUrl    

    def propertiesFromVivaReal() {
        println "propertiesFromVivaReal: " + propertiesJsonUrl

        def jsonDefault = new groovy.json.JsonSlurper()
            .parseText(propertiesJsonUrl.toURL().text)

        def propertiesJson = new PropertiesJson(jsonDefault)    

        propertiesJson.createProperties()
    }

    def provincesFromVivaReal() {
    	def provinces =  []
        println "provincesFromVivaReal: " + provincesJsonUrl
    	def jsonDefault = new groovy.json.JsonSlurper()
    		.parseText(provincesJsonUrl.toURL().text)

    	def provincy
		jsonDefault.entrySet().each {entry ->
			provincy = new ProvincyJson(entry.value)            
			provincy.name = entry.key        
			provinces.add(provincy.createProvincy())
		}

		provinces
    }
}
