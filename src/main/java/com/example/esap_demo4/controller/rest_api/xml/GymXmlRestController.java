package com.example.esap_demo4.controller.rest_api.xml;

import com.example.esap_demo4.model.Gym;
import com.example.esap_demo4.model.SeasonPass;
import com.example.esap_demo4.service.GymService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api/xml/gyms", produces = MediaType.APPLICATION_ATOM_XML_VALUE)
public class GymXmlRestController {
    @Autowired
    private GymService gymService;


    @PostMapping("/create")
    public void create(@RequestBody Gym gym){gymService.create(gym);}

    @GetMapping
    public List<Gym> getAll() {
//        List<Gym> gyms = ;
//        return gyms;
        return gymService.getAll();
    }

    @GetMapping("/{id}")
    public Gym get(@PathVariable Long id) {
        return gymService.get(id);
    }

    @PatchMapping("/{id}/update")
    public void update(@PathVariable("id") Long gymId, @RequestBody Gym gym) {
        gymService.update(gymId, gym);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        gymService.delete(id);
    }

    @GetMapping("/{id}/season_passes")
    public ResponseEntity<List<SeasonPass>> getSeasonPasses(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("gymId", String.valueOf(id));
        return new ResponseEntity<>(gymService.getSeasonPasses(id), httpHeaders, HttpStatus.OK);
    }

}
