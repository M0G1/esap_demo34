package com.example.esap_demo4.controller.rest_api.json;

import com.example.esap_demo4.model.Gym;
import com.example.esap_demo4.model.SeasonPass;
import com.example.esap_demo4.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/json/gyms", produces = MediaType.APPLICATION_JSON_VALUE)
public class GymJsonRestController {
    @Autowired
    private GymService gymService;


    @PostMapping("/create")
    public void create(@RequestBody Gym gym){gymService.create(gym);}


    @GetMapping
    public List<Gym> getAll() {
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
