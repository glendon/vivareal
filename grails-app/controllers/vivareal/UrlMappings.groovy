package vivareal

class UrlMappings {

    static mappings = {
        "/properties"(resources:'property', excludes:['edit', 'create'])
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'application', action:'index')        
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
