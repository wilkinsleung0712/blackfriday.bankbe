package com.blackfriday.startup;


import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebSerivceConfig extends WsConfigurerAdapter{
    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }
    
    @Bean(name="useroperation")//this is the actual wsdl name.
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema UserOperation) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("useroperationport");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.blackfridaybank.org/webservices/UserOperation");
        wsdl11Definition.setSchema(UserOperation);
        return wsdl11Definition;
    }
    
    @Bean //this is create a xsd bean return to application
    public XsdSchema UserOperation() {
        return new SimpleXsdSchema(new ClassPathResource("UserOperation.xsd"));
    }
    //adding the following method to return the correct xsd file before loaded to the soapui.
    @Bean //create a xsd bean for use.
    public XsdSchema UserDetails() {
        return new SimpleXsdSchema(new ClassPathResource("UserDetails.xsd"));
    }
    
}
