package br.com.devmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration // DIZEMOS AO SPRING QUE ESSA É UMA CLASSE DE CONFIGURAÇÃO
public class ConfiguracaoSpringMvc extends WebMvcConfigurerAdapter {


    @Bean //INDICAMOS AO SPRING QUE ESSE MÉTODO DEVE SER CHAMADO POR ELE E ELE DEVE GERENCIAR O OBJETO RETORNADO
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver resolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver( resolver );
        return templateEngine;
    }

    // COM ESSE MÉTODO, O SPRING SABE QUE VAI LIDAR COM O THYMELEAF NAS PAGINAS WEB



    @Override // CONTROLE AUTOMATICO DEFINIDO PELO SPRING PARA ATENDER REQUISIÇÕES / E /HOME
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        //SEMPRE QUE RECEBER REQUISIÇÕES DESSES ENDEREÇOS INFORMADOS, A VIEW HOME SERÁ EXIBIDA
        // OU SEJA, LOCALHOST:8080 SEMPRE VAI ABRIR home.html
    }


    // ESSA É A CONFIGURAÇÃO DO SPRING MVC
}
