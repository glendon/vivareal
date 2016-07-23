import vivareal.services.bootstrap.DataService
import vivareal.domain.Property
import vivareal.domain.Provincy

import grails.util.Environment

class BootStrap {

	def dataService

    def init = { servletContext ->        
        dataService.loadApplicationData()
       
    }
    def destroy = {
    	if (Environment.current == Environment.TEST) {
            Property.where { }.deleteAll()
            Provincy.where { }.deleteAll()
        }
    }
}
