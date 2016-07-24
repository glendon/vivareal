package vivareal.domain

import grails.util.Environment
import grails.converters.JSON

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

    static transients = ['inValidPosition']
    def inValidPosition

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

    static {        
        JSON.registerObjectMarshaller(Property) { Property p ->
            p.tempList = provincesAsStringList(p.provinces)                                    
             
            return [
                id: p.id,
                x: p.x,
                y: p.y,
                title: p.title,
                price: p.price,
                description: p.description,
                beds: p.beds,
                baths: p.baths,
                squareMeters: p.squareMeters,
                provinces: p.tempList
            ]
        }
    }

    static def provincesAsStringList(provinces) {
        def list = []
        provinces.each { p ->
            list.add(p.name)
        }
        list
    }

    static mapping = {
        id generator : 'increment'                
    }

    Property(baths, beds, squareMeters, x, y) {
        this.baths = baths
        this.beds = beds
        this.id = id
        this.squareMeters = squareMeters
        this.x = x
        this.y = y
    }

    def addListProvinces(provincesParam) {
        if (!this.provinces) {
            this.provinces = []
        }            

        provincesParam.each { provincy ->        
            this.provinces.add(provincy)
        }
    }


    def isInValid(xLimit) {
        inValidPosition = x <= xLimit

        this
    }

    def and(yLimit) {
        inValidPosition = inValidPosition && (y <= yLimit)

        if (!inValidPosition) {
            this.errors.reject("property.insert.invalid.area")
        }      

        this 
    }

    def thenAddListProvinces(provincesService) {
        if (!this.provinces) {
            this.provinces = []
        }            

        provincesService.getProvinces(x, y).each { provincy ->        
            this.provinces.add(provincy)
        }

        this
    }

    def thenInsert() {

        if (!this.hasErrors()){            
            this.insert(flush:true, failOnError:true)
        } 

        this
    }
}
