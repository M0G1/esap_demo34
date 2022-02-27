package com.example.esap_demo4.controller.xslt;

import com.example.esap_demo4.model.SeasonPass;
import com.example.esap_demo4.service.SeasonPassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/xslt/season_passes")
public class SeasonPassXsltRestController {

    @Autowired
    private SeasonPassService seasonPassService;

    @RequestMapping(method = RequestMethod.GET)//, headers="accept=application/xml") // localhost:8080/shop
    public ModelAndView getCarsXSLT() throws JsonProcessingException {
        List<SeasonPass> seasonPasses = seasonPassService.getAll();
        ModelAndView modelAndView = new ModelAndView("season_passes");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(seasonPasses)));
        modelAndView.addObject("xmlSource", source);
        return modelAndView;
    }
}
