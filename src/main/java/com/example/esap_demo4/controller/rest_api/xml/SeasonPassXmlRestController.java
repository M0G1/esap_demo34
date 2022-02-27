package com.example.esap_demo4.controller.rest_api.xml;

import com.example.esap_demo4.model.SeasonPass;
import com.example.esap_demo4.service.SeasonPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/xml/season_passes", produces = MediaType.APPLICATION_XML_VALUE)
public class SeasonPassXmlRestController {

    @Autowired
    private SeasonPassService seasonPassService;

    @PostMapping("/create")
    public void create(@RequestBody SeasonPass seasonPass, @RequestAttribute Long gymId) {
        seasonPassService.create(seasonPass, gymId);
    }

    @GetMapping
    public List<SeasonPass> getAll() {
        return seasonPassService.getAll();
    }

    @GetMapping("/{id}")
    public SeasonPass get(@PathVariable Long id) {
        return seasonPassService.get(id);
    }

    @PatchMapping("/{id}/update")
    public void update(@PathVariable("id") Long seasonPassId, @RequestBody SeasonPass seasonPass, @RequestAttribute Long gymId) {
        seasonPassService.update(seasonPassId, seasonPass, gymId);
    }
}
