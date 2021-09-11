package geilerbass.lampkicking.service;

import geilerbass.lampkicking.domain.HooverNavigator;
import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;
import geilerbass.lampkicking.model.HooverRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HooverController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hoover")
    public HooverReport getHoover() {
        return new HooverReport(new Coords(1, 3), 1);
    }

    @PostMapping("/hoover")
    public HooverReport postHoover(@RequestBody HooverRequest hooverRequest) {
        return new HooverNavigator(hooverRequest.getRoomSize(), hooverRequest.getPatches())
                .navigate(hooverRequest.getCoords(), hooverRequest.getInstructions());
    }
}
