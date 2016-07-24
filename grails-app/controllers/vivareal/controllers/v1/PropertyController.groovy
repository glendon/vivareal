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
    def propertyFinderInAreaService

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

        respond propertyFinderInAreaService.whenUpperLeftXIs(ax).andUpperLeftYIs(ay)
            .andBottomRightXIs(bx).andBottomRightYIs(by).returnPropertiesInArea()
        
    }

    //POST /properties
    @Transactional
    def save () {
        log.info "PROPERTY >>> Executing HTTP POST"

        def property = new Property(request.JSON)
        
        areaManagerService.createNew(property)
        
        if (property.hasErrors()){
            log.info "PROPERTY >>> There was some validation problems"
            transactionStatus.setRollbackOnly()
            respond property.errors, [status: UNPROCESSABLE_ENTITY]
        }else{
            log.info "PROPERTY >>> The resource was created"
            respond property, [status: CREATED]
        }
    }

    //GET /properties/${id}    
    def show (Long id) {
        
        def property = Property.findById(id)        

        if (property) {
            respond property, [status : OK]
        }else {
            render  status : NOT_FOUND
        }
    }

}
