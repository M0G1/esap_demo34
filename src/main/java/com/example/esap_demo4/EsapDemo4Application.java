package com.example.esap_demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@SpringBootApplication
public class EsapDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(EsapDemo4Application.class, args);
    }

    @Bean
    public ViewResolver getXSLTViewResolver() {

        XsltViewResolver xsltResolver = new XsltViewResolver();
        xsltResolver.setOrder(1);
        xsltResolver.setSourceKey("xmlSource");

        xsltResolver.setViewClass(XsltView.class);
        xsltResolver.setViewNames("gyms", "season_passes");
        xsltResolver.setPrefix("classpath:/xslt/");
        xsltResolver.setSuffix(".xslt");

        return xsltResolver;
    }
}
