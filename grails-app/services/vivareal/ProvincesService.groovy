package vivareal


class ProvincesService {

    def provinces

    def loadProvinces = {
		provinces = Property.list()    	
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
     		throw new RuntimeException("Somethin went wrong, it's not permited to exist to Provinces with the same name")
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

    def loadProvincesFromVivaReal() {
    	provinces =  []

    	def jsonDefault = new groovy.json.JsonSlurper()
    		.parseText('https://raw.githubusercontent.com/VivaReal/code-challenge/master/provinces.json'.toURL().text)

    	def provincy
		jsonDefault.entrySet().each {entry ->
			provincy = new Provincy(entry.value)
			provincy.name = entry.key
			provinces.add(provincy)
		}
    }

}
