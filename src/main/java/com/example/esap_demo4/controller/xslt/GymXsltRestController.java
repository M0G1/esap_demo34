package com.example.esap_demo4.controller.xslt;

import com.example.esap_demo4.model.Gym;
import com.example.esap_demo4.service.GymService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.management.openmbean.CompositeData;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/xslt/gyms")
public class GymXsltRestController {

    @Autowired
    private GymService gymService;

    @RequestMapping(method = RequestMethod.GET)//, headers="accept=application/xml") // localhost:8080/shop
    public ModelAndView getCarsXSLT() throws JsonProcessingException {
        List<Gym> gyms = gymService.getAll();
        ModelAndView modelAndView = new ModelAndView("gyms");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(gyms)));
        modelAndView.addObject("xmlSource", source);
        return modelAndView;
    }
}
