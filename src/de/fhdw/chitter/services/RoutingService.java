package de.fhdw.chitter.services;

import org.atmosphere.cpr.AtmosphereResource;

import de.fhdw.chitter.controller.HomeController;
import de.fhdw.chitter.controller.api.ChitterController;

public class RoutingService {
    private ChitterController chitterController;
    private HomeController homeController;
    private AtmosphereResource resource;

    public RoutingService(AtmosphereResource resource) {
        chitterController = new ChitterController(resource);
        homeController = new HomeController();
        this.resource = resource;
    }

    public String route() {
        var path = resource.getRequest().getPathInfo();
        String[] pathElems = path.split("/");
        if (pathElems.length == 0) {
            return new HomeController().index();
        }
        switch (pathElems[1]) {
            case "api": {
                switch (pathElems[2]) {
                case "chitter":
                    if (pathElems.length > 3) {
                        switch (pathElems[3]) {
                        case "topics":
                            return chitterController.topics();
                        default:
                            return chitterController.index(pathElems[3]);
                        }
                    } else {
                        return chitterController.index();
                    }
                default:
                    return chitterController.error();
                }
            }
            default: {
                return homeController.error();
            }
        }
    }
}
