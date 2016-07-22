package vivareal

class UrlMappings {

    static mappings = {
        "/properties"(resources:'property', excludes:['edit', 'create', 'delete', 'patch', 'update'])
        

        "/"(controller: 'application', action:'index')        
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
