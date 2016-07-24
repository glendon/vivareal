package vivareal.services.spotippos

import org.springframework.beans.factory.annotation.*
import org.springframework.transaction.annotation.Transactional

import grails.converters.JSON
import org.grails.web.json.JSONObject

import vivareal.domain.Property


@Transactional
class AreaManagerService {

	def provincesService
	
	@Value('${spotippos.limit.x}')
    Integer xLimit
    @Value('${spotippos.limit.y}')
    Integer yLimit

    def createNew(property) {
        property.isInValid(xLimit).and(yLimit).thenAddListProvinces(provincesService).thenInsert()
    }

}
