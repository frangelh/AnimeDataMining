package animedatamining

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/$action?"(controller: "movil",parseRequest: true)
    }
}
