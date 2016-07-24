package vivareal.integration.model

import vivareal.domain.Property

class PropertiesJson {

	def totalProperties
	def properties

	def createProperty(jsonProperty){
		new Property(jsonProperty.baths, jsonProperty.beds, jsonProperty.squareMeters, jsonProperty.x, jsonProperty.y)
	}

	def createProperties(){	
		def realProperties	= []
        this.properties.each { jsonProperty ->                              
            realProperties.add(createProperty(jsonProperty)) 
        }

        realProperties
	}

}