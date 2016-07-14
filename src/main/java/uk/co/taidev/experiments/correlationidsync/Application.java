package uk.co.taidev.experiments.correlationidsync;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import uk.co.taidev.experiments.correlationidsync.web.filters.CorrelationHeaderFilter;
import uk.co.taidev.experiments.correlationidsync.web.filters.MDCFilter;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "uk.co.taidev.experiments.correlationidsync")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public FilterRegistrationBean correlationHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new CorrelationHeaderFilter());
        filterRegBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegBean.setOrder(1);
        return filterRegBean;
    }
    
    @Bean
    public FilterRegistrationBean correlationLoggingFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new MDCFilter());
        filterRegBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegBean.setOrder(2);
        return filterRegBean;
    }

}
