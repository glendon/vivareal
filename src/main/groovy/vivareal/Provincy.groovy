package vivareal

class Provincy {

	static mapWith = "mongo"

	String id
	def name
	def boundaries

	static mapping = {
        id generator : 'uuid2'
    }

	/*
		"boundaries" : {
			"upperLeft" : {
				"x" : 0,
				"y" : 1000
			},
			"bottomRight" : {
				"x" : 600,
				"y" : 500
			}
		}
	*/

	def isAreaAvailable (paramX, paramY) {
		def properties = Property.where {(x == paramX && y == paramY)}.list()

		//To make the code more understandable
		return properties.size() == 0
	}

}
