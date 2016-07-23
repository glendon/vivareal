package vivareal.services.spotippos

import org.springframework.beans.factory.annotation.*
import org.springframework.transaction.annotation.Transactional

import grails.converters.JSON
import org.grails.web.json.JSONObject

import vivareal.domain.Property


@Transactional
class AreaManagerService {

	def provincesService
	
	@Value('${spotippos.limit.x}')
    Integer xLimit
    @Value('${spotippos.limit.y}')
    Integer yLimit

    def isAreaValid(x, y) {
    	x <= xLimit && y <= yLimit
    }    

    def createNew(property) {
        
        if (isAreaValid(property.x, property.y)){
            def provinces = provincesService.getProvinces(property.x, property.y)
                
            property.setProvinces(provinces)                    
        }else{                
            property.errors.reject("property.insert.invalid.area")
        }   

        if (!property.hasErrors()){            
            property.insert(flush:true, failOnError:true)
        }        
    }

    def getAllPropertysIn(upperLeftX, upperLeftY, bottomRightX, bottomRightY) {

        def properties = Property.where {(x >= upperLeftX && x <= bottomRightX && y >= bottomRightY && y <= upperLeftY)}.list()

        properties
    }    

}
