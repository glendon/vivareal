package vivareal.domain

import grails.converters.JSON

class Provincy {

	static mapWith = "mongo"

	Long id
	String name
	Integer upperLeftX
	Integer upperLeftY
	Integer bottomRightX
	Integer bottomRightY

	static mapping = {
        id generator : 'increment'
    }

    static {        
        JSON.registerObjectMarshaller(Provincy) { Provincy p ->
            return [
                name: p.name
            ]
        }
    }

	def isAreaAvailable (paramX, paramY) {
		def properties = Property.where {(x == paramX && y == paramY)}.list()

		//To make the code more understandable
		return properties.size() == 0
	}

}
