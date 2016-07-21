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

    void "test to get provincy with success"() {
    	when : "load a provincy by name"    	    		    		
        	def provincy = spotippos.getProvincy("Gode")        	
        then : "verify if there are values"
        	provincy
        	provincy.name == "Gode"
        	provincy.boundaries.upperLeft.x == 0
        	provincy.boundaries.upperLeft.y == 1000
    }

    void "test fail to get provincy when there is no area"() {
    	when : "load a provincy by name and there is no area"    	    		    		
        	spotippos.area = null
        then : "it tails when try to get the provincy"
        	shouldFail(RuntimeException) { spotippos.getProvincy("Gode") }
    }

    void "test fail when the name isn't informed"() {
    	when : "try to call in valid spotippos object"    
        then : "fail whe the provincy name is null"
        	shouldFail(IllegalArgumentException) { spotippos.getProvincy(null) }
    }
}
