package vivareal

class UrlMappings {

    static mappings = {
        "/properties"(resources:'property', excludes:['edit', 'create', 'delete', 'patch', 'update'])
               
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
