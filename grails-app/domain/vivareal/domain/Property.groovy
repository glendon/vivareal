package vivareal.domain

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
    static hasMany = [provinces:Provincy]

    static constraints = {
        title blank:false
        beds max: 5, min: 1, nullable: true
        baths max: 4, min: 1, nullable: true
		squareMeters max: 240 as Float, min:20 as Float
        provinces nullable : true
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

    def setProvinces(provincesParam) {
        if (!this.provinces) {
            this.provinces = new ArrayList<Provincy>()
        }            

        provincesParam.each { provincy ->        
            this.provinces.add(provincy.name)
        }
    }
}
