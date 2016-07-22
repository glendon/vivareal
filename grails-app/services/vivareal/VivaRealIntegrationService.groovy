package vivareal


class VivaRealIntegrationService {
	
    def insertPropertiesFromVivaReal(){

        def properties = loadPropertiesFromVivaReal()
        log.debug(">>> Saving  $properties.size properties")

        properties.each{ propertyVivaReal ->
            
            propertyVivaReal.title = "Imóvel $propertyVivaReal.id"
            propertyVivaReal.save(flush:true, failOnError:true)
        }
    }

    def loadPropertiesFromVivaReal() {
        def properties =  []

        def jsonDefault = new groovy.json.JsonSlurper()
            .parseText('https://raw.githubusercontent.com/VivaReal/code-challenge/master/properties.json'.toURL().text)

        def propertiesJson = new PropertiesJson(jsonDefault)    

        def property
        propertiesJson.properties.each { tempProperty ->        
            property = new Property(tempProperty.baths, tempProperty.beds, tempProperty.id, tempProperty.squareMeters, tempProperty.x, tempProperty.y)
            properties.add(property) 
        }

        properties                      
    }

    def loadProvincesFromVivaReal() {
    	def provinces =  []

    	def jsonDefault = new groovy.json.JsonSlurper()
    		.parseText('https://raw.githubusercontent.com/VivaReal/code-challenge/master/provinces.json'.toURL().text)

    	def provincy
		jsonDefault.entrySet().each {entry ->
			provincy = new Provincy(entry.value)            
			provincy.name = entry.key            
			provinces.add(provincy)
		}

		provinces
    }
}
