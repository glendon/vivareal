package vivareal

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
    static def provinces

    static constraints = {
        title blank:false
        beds max: 5, min: 1, nullable: true
        baths max: 4, min: 1, nullable: true
		squareMeters max: 240 as Float, min:20 as Float

        x nullable: true
        y nullable: true
        price nullable: true
        description nullable: true
    }

    static mapping = {
        id generator : 'uuid2'
    }

    Property(baths, beds, id, squareMeters, x, y) {
        this.baths = baths
        this.beds = beds
        this.id = id
        this.squareMeters = squareMeters
        this.x = x
        this.y = y
    }
}
