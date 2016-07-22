package vivareal

class Provincy {

	//static mapWith = "mongo"
	//String id

	def name
	def boundaries

	//static mapping = {
    //    id generator : 'uuid2'
    //}

	def isAreaAvailable (paramX, paramY) {
		def properties = Property.where {(x == paramX && y == paramY)}.list()

		//To make the code more understandable
		return properties.size() == 0
	}

}
