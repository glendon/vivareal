package vivareal

import grails.util.Environment

class Property {

	static mapWith = "mongo"

	Long id
    Integer x
    Integer y
    String title
    BigDecimal price
    String description 
    Integer beds
    Integer baths
    Float squareMeters 
    def provinces

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
        if (Environment.current == Environment.TEST) {
            id generator : 'assigned' 
        }else{
            id generator : 'increment'
        }
        
    }

    Property(baths, beds, id, squareMeters, x, y) {
        this.baths = baths
        this.beds = beds
        this.id = id
        this.squareMeters = squareMeters
        this.x = x
        this.y = y
    }

    def setProvinces(provinces) {
        this.provinces = []

        provinces.each { provincy ->
            this.provinces.add(provincy.name)
        }
    }
}
