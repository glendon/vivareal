package vivareal

import grails.test.mixin.TestFor
import spock.lang.Specification

import groovy.json.JsonSlurper 

@TestFor(Spotippos)
class SpotipposSpec extends Specification {

	def jsonSlurper = new JsonSlurper()
    def jsonDefault = jsonSlurper.parseText('{"Gode" : {"boundaries" : {"upperLeft" : {"x" : 0,"y" : 1000},"bottomRight" : {"x" : 600,"y" : 500}}}}')


	def spotippos

    def setup() {
    	spotippos = new Spotippos()
    	spotippos.area = jsonDefault
    }

    def cleanup() {
    }

    void "test to load provinces"() {
    	when : "there is provinces to be loaded"    	    		    		
        	def provincy = spotippos.getProvincy("Gode")        	
        then : "verify is there are 6 provinces"
        	provincy
        	provincy.name == "Gode"
        	provincy.boundaries.upperLeft.x == 0
        	provincy.boundaries.upperLeft.y == 1000
    }
}
