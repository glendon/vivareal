package vivareal.controllers.v1

import org.springframework.transaction.annotation.Transactional
import grails.rest.*
import grails.converters.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

import vivareal.domain.Property

@Transactional
class PropertyController extends RestfulController {

    def areaManagerService

	static namespace = 'v1'
    static responseFormats = ['json']
    PropertyController() {
        super(Property)
    }

    //GET /properties
    //properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
    def index () {

        Integer ax = params.int("ax")
        Integer ay = params.int("ay")
        Integer bx = params.int("bx")
        Integer by = params.int("by")

        log.info "PROPERTY >>> Executing HTTP GET"
        
        def properties
        def response
        if (ax != null && ay != null && bx != null && by != null) {

            properties = areaManagerService.getAllPropertysIn(ax, ay, bx, by)
            response = [foundProperties : properties.size(), properties : properties]
            respond response
        }else{
            properties = Property.list()
            response = [foundProperties : properties.size(), properties : properties]
            respond response
        }

        
    }

    //POST /properties
    @Transactional
    def save () {
        log.info "PROPERTY >>> Executing HTTP POST"

        def property = new Property(request.JSON)
        
        if(property.hasErrors()) {
            log.info "PROPERTY >>> There was some validation problem"
            transactionStatus.setRollbackOnly()
            respond property.errors, [status: UNPROCESSABLE_ENTITY]
        }
        else {
            areaManagerService.createNew(property)
            
            if (property.hasErrors()){
                log.info "PROPERTY >>> There was some validation problem"
                transactionStatus.setRollbackOnly()
                respond property.errors, [status: UNPROCESSABLE_ENTITY]
            }else{
                log.info "PROPERTY >>> The resource was created"
                respond property, [status: CREATED]
            }

            
        }
    }

    //GET /properties/${id}    
    def show (Long id) {
        log.info "PROPERTY >>> Geting the resource $id"
        def property = Property.findById(id)
        println "Id quesisado $id"            
        println property 
        println property as grails.converters.JSON

        if (property) {
            respond property, [status : OK]
        }else {
            render  status : NOT_FOUND
        }
    }

    /*
    //PUT /properties/${id}
    @Transactional
    def update () {

    }
    
    //DELETE  /properties/${id}
    @Transactional
    def delete () {
    }
    */

}
