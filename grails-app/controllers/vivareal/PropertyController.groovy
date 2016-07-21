package vivareal.v1

import vivareal.*
import grails.transaction.Transactional
import grails.rest.*
import grails.converters.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class PropertyController extends RestfulController {

	static namespace = 'v1'
    static responseFormats = ['json']
    PropertyController() {
        super(Property)
    }

    //GET /properties
    def index () {
        log.info "PROPERTY >>> Executing HTTP GET"
        //TODO arrumar o id, arrumar um id melhor para ser exibido e nao retornar o _id do mongo.
        
        respond Property.list()
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
            //TODO apply where the resource was created. my url.
            log.info "PROPERTY >>> The resource was created"
            property.save flush:true

            respond property, [status: CREATED]
        }
    }

    //GET /properties/${id}    
    def show (Integer id) {
    }

    //PUT /properties/${id}
    @Transactional
    def update () {

    }
    
    //DELETE  /properties/${id}
    @Transactional
    def delete () {
    }

}
