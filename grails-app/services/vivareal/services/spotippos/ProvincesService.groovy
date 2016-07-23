package vivareal.services.spotippos

import vivareal.domain.Provincy

class ProvincesService {

    def provinces
    def vivaRealIntegrationService

    def loadProvinces = {
		provinces = Provincy.list()    	
    }

    /*@PostConstruct
    def init() {
      // GORM accesible from here
    }*/

    Provincy getProvincy(name) {

    	if (!provinces){
    		log.error ">>> Provinces can't be null"
    		throw new RuntimeException("Provinces can't be null")
    	}

    	if (!name){    	
    		throw new IllegalArgumentException("The name can't be null")
    	}    	

     	def province = provinces.findAll{ provincy -> 
     		provincy.name == name
     	}

     	if (province.size() > 1) {
     		throw new RuntimeException("Somethin went wrong, it's not permited to exist two Provinces with the same name")
     	}

     	province.size() == 1 ? province[0] : null;

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
