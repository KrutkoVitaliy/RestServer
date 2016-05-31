package pack.restserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.restserver.entity.Cloth;
import pack.restserver.service.ClothService;

import java.util.List;

@RestController
public class ClothController {

    @Autowired
    private ClothService service;

    @RequestMapping(value = "/clothes", method = RequestMethod.GET)
    @ResponseBody
    public List<Cloth> getAllClothes() {
        return service.getAll();
    }
    @RequestMapping(value = "/clothes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cloth getCloth(@PathVariable long id) {
        return service.getByID(id);
    }
    @RequestMapping(value = "/clothes", method = RequestMethod.POST)
    @ResponseBody
    public Cloth addCloth(@RequestBody Cloth cloth) {
        return service.add(cloth);
    }
    @RequestMapping(value = "/clothes/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteCloth(@PathVariable long id) {
        service.delete(id);
    }
}
