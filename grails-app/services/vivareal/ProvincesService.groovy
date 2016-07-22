package vivareal


class ProvincesService {

    def provinces
    def vivaRealIntegrationService

    def loadProvinces = {
		//provinces = Property.list()    	
        provinces = vivaRealIntegrationService.loadProvincesFromVivaReal()
    }

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
    		verifyPosition(provincy.boundaries, x, y) 
    	}
    	provincesInTheArea
    }

    private boolean verifyPosition(boundaries, x, y) {
		if (!boundaries){
    		log.error ">>> Boundaries can't be null"
    		throw new RuntimeException("Boundaries can't be null")
    	}    	
    	
    	def validateY = (boundaries.bottomRight.y <= y) && (y <= boundaries.upperLeft.y)
    	def validateX = (boundaries.upperLeft.x <= x) && (x <= boundaries.bottomRight.x)    	

    	validateX && validateY	
    }

}
