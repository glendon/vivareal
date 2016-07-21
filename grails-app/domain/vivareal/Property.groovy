package vivareal

import org.bson.types.ObjectId

class Property {

	static mapWith = "mongo"

	String id
    Integer x
    Integer y
    String title
    BigDecimal price
    String description 
    Integer beds
    Integer baths
    Float squareMeters 

    static constraints = {
        title blank:false
        beds max: 5, min: 1, nullable: true
        baths max: 4, min: 1, nullable: true
		squareMeters max: 240 as Float, min:20 as Float
    }

    static mapping = {
        id generator : 'uuid2'
    }
}
