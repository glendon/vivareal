package vivareal

class Spotippos {

	def area 
	transient def provinces = []

    //default 
    def afterLoad() {    	
   	}

    Provincy getProvincy(name) {    	
    	def recoveredProvincy = area."$name"

    	def provincy = null;
    	if (recoveredProvincy){
    		provincy = new Provincy(recoveredProvincy)
    		provincy.name = name
    	}
    	
    	provincy
    } 

}
