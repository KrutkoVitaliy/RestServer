package pack.restserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pack.restserver.entity.Cloth;

import java.util.Date;

@RestController
@RequestMapping("/return")
public class ClothController {

    @RequestMapping(value = "/getParameter", method = RequestMethod.GET)
    @ResponseBody
    public Cloth getParameter() {
        return clothReturn();
    }

    private Cloth clothReturn() {
        Cloth cloth = new Cloth();
        cloth.setId(1);
        cloth.setAvailableDate(new Date());
        cloth.setTitle("FirstCloth");
        return cloth;
    }

}
