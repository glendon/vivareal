package vivareal

import org.springframework.beans.factory.annotation.*
import org.springframework.transaction.annotation.Transactional

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

    def allocatingArea (Property property) {
    	if (isAreaValid(property.x, property.y)){
    			def provinces = provincesService.getProvinces(x, y)
				
				def freeArea = false;
				provinces.each {provincy ->
    				freeArea = provincy.isAreaAvailable(property.x, property.y)
				}

				if (freeArea) {
					property.provinces = provinces	
				}else{
					throw new IllegalArgumentException("The selected area is occupied, change the x and/or y values.")	
				}

    		}else{
    			throw new IllegalArgumentException("The selected area is invalid, change the x and/or y values.")
    		}
    }

    def getAllPropertysIn(upperLeftX, upperLeftY, bottomRightX, bottomRightY) {

    }


}
