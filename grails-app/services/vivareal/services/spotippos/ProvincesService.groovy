package vivareal.services.spotippos

import vivareal.domain.Provincy

class ProvincesService {

    def provinces
    def vivaRealIntegrationService

    def loadProvinces = {
		provinces = Provincy.list()    	
    }

    def getProvinces(x, y) {

    	def provincesInTheArea = provinces.findAll { provincy ->                 
    		verifyPosition(provincy, x, y) 
    	}
    	provincesInTheArea
    }

    private boolean verifyPosition(provincy, x, y) { 	

    	def validateY = (provincy.bottomRightY <= y) && (y <= provincy.upperLeftY)
    	def validateX = (provincy.upperLeftX <= x) && (x <= provincy.bottomRightX)        

    	validateX && validateY	
    }

}
