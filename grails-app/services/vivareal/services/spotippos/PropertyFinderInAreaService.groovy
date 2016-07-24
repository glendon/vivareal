package vivareal.services.spotippos

import vivareal.domain.Property

class PropertyFinderInAreaService {

	static scope = "request"

	def upperLeftX
	def upperLeftY
	def bottomRightX
	def bottomRightY

	def allValuesArePresent 

	def whenUpperLeftXIs(value){
		upperLeftX = value
		this
	}

	def andUpperLeftYIs(value){
		upperLeftY = value
		this
	}

	def andBottomRightXIs(value){
		bottomRightX = value
		this
	}

	def andBottomRightYIs(value){
		bottomRightY = value
		this
	}

	def allValuesArePresent() {
		upperLeftX != null && upperLeftY != null && bottomRightX != null && bottomRightY != null		
	}

    def returnPropertiesInArea() {

    	def properties
    	if (allValuesArePresent()) {
            properties = Property.where {(x >= upperLeftX && x <= bottomRightX && y >= bottomRightY && y <= upperLeftY)}.list()
            
        }else{
            properties = Property.list()
        }

    	[foundProperties : properties.size(), properties : properties]        
    }    
}
