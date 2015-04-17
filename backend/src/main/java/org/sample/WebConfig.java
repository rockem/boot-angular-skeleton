package org.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/app/");
//    }
    @Autowired
    private Environment env;

    @Value("${resources.projectroot}")
    private String projectRoot;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/app/").setViewName("forward:/app/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        boolean devMode = this.env.acceptsProfiles("development");

        String location = devMode ? "file:///" + getProjectRootRequired()  + "/src/main/resources/public/" :"classpath:public/";
        Integer cachePeriod = devMode ? 0 : null;

        registry.addResourceHandler("/**")
                .addResourceLocations(location)
                .setCachePeriod(cachePeriod);
    }

    private String getProjectRootRequired() {
        Assert.state(this.projectRoot != null, "Please set \"resources.projectRoot\" in application.yml");
        return this.projectRoot;
    }

}
