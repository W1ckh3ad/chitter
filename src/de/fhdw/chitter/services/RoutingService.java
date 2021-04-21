package de.fhdw.chitter.services;

import org.atmosphere.cpr.AtmosphereResource;

import de.fhdw.chitter.controller.api.ChitterController;
import de.fhdw.chitter.utils.MyFileHandler;

public class RoutingService {
    private AtmosphereResource resource;

    private String api404() {
        var response = resource.getResponse();
        response.setStatus(404);
        return "{'data': 'Die Ressource die Sie abgefragt haben ist nicht zu finden'}";
    }

    public RoutingService(AtmosphereResource resource) {
        this.resource = resource;
    }

    public String route() {

        var path = resource.getRequest().getPathInfo();
        String[] pathElems = path.split("/");
        if (pathElems.length == 0) {
            var ret = "";
            try {
                ret = MyFileHandler.readFromFile("src/de/fhdw/chitter/views/newsticker.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        }
        switch (pathElems[1]) {
        case "api": {
            switch (pathElems[2]) {
            case "chitter":
                var controller = new ChitterController();
                if (pathElems.length > 3) {
                    switch (pathElems[3]) {
                    case "topics":
                        return controller.topics();
                    default:
                        return controller.index(pathElems[3]);
                    }
                } else {
                    return controller.index();
                }
            default:
                return api404();
            }
        }
        default: {
            var ret = "";
            try {
                ret = MyFileHandler.readFromFile("src/de/fhdw/chitter/views/newsticker.html");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        }
        }
    }
}
