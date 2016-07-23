package vivareal.services.integration

import vivareal.integration.model.*

class VivaRealIntegrationService {    

    def propertiesFromVivaReal() {

        def jsonDefault = new groovy.json.JsonSlurper()
            .parseText('https://raw.githubusercontent.com/VivaReal/code-challenge/master/properties.json'.toURL().text)

        def propertiesJson = new PropertiesJson(jsonDefault)    

        propertiesJson.createProperties()
    }

    def provincesFromVivaReal() {
    	def provinces =  []

    	def jsonDefault = new groovy.json.JsonSlurper()
    		.parseText('https://raw.githubusercontent.com/VivaReal/code-challenge/master/provinces.json'.toURL().text)

    	def provincy
		jsonDefault.entrySet().each {entry ->
			provincy = new ProvincyJson(entry.value)            
			provincy.name = entry.key        
			provinces.add(provincy.createProvincy())
		}

		provinces
    }
}
