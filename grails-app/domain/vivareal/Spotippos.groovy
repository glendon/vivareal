package vivareal

class Spotippos {

	def area 

    Provincy getProvincy(name) {

    	if (!area){
    		log.error ">>> The area can't be null"
    		throw new RuntimeException("The area can't be null")
    	}

    	if (!name){    	
    		throw new IllegalArgumentException("The name can't be null")
    	}

    	def recoveredProvincy = area."$name"

    	def provincy = null;
    	if (recoveredProvincy){
    		provincy = new Provincy(recoveredProvincy)
    		provincy.name = name
    	}
    	
    	provincy
    } 

}
