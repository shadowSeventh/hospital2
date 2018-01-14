package top.ball.rice.hospital.server.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.transform.CompileStatic;
import groovy.transform.TypeCheckingMode;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import top.ball.rice.hospital.service.util.errorHandler.Oauth2ResponseErrorHandler;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class RestTemplateConf {

    // ========================================================================== 内网


    @Bean
    @CompileStatic(TypeCheckingMode.SKIP)
    HttpMessageConverters httpMessageConverters(ObjectMapper objectMapper) {
        // 启用 Jaxb 注解
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(objectMapper);

        // 微信部分API返回的content-type 不正确，是 text/plain
        jsonConverter.setSupportedMediaTypes(new ArrayList<>(
                Arrays.asList(
                        MediaType.APPLICATION_JSON,
                        new MediaType("application", "*+json"),
                        MediaType.TEXT_PLAIN)
        ));


        return new HttpMessageConverters(true, Arrays.asList(

                // 启用 Jaxb 注解
                jsonConverter,

                // String 类型 ： ISO-8859-1 -> UTF-8
                new StringHttpMessageConverter(Charset.forName("UTF-8"))
        ));
    }


//    @Bean
//    ClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpClient httpClient = HttpClientBuilder.create()
//                .build();
//
//        // HttpComponentsClientHttpRequestFactory 与日志级别 "logging.level.org.apache.http: DEBUG" 配合
//        // 以方便调试 RestTemplate 请求头、响应头
//        ClientHttpRequestFactory fac = new HttpComponentsClientHttpRequestFactory(httpClient)
//        return fac
//    }

    /**
     * 方便打印日志
     */
    @Bean
    RestTemplateCustomizer myRestTemplateCustomizer(
            ClientHttpRequestFactory clientHttpRequestFactory
    ) {
        return new RestTemplateCustomizer() {

            @Override
            public void customize(RestTemplate restTemplate) {

//                HttpClient httpClient = HttpClientBuilder.create()
//                        .build();

                // HttpComponentsClientHttpRequestFactory 与日志级别 "logging.level.org.apache.http: DEBUG" 配合
                // 以方便调试 RestTemplate 请求头、响应头
//                ClientHttpRequestFactory fac = new HttpComponentsClientHttpRequestFactory(httpClient)
                restTemplate.setRequestFactory(clientHttpRequestFactory);

            }
        };
    }

    /**
     * 访问内网的 RestTemplate
     *
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    @Scope("prototype")
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate;
    }

    // ========================================================================== 外网 + 代理

    /**
     * 要访问外网的 HttpClient
     *
     * @param prop
     * @return
     */


    /**
     * 访问外网的 RestTemplate
     *
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    @Scope("prototype")
    RestTemplate wwwRestTemplate(
            RestTemplateBuilder restTemplateBuilder,
            HttpClient wwwHttpClient
    ) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(wwwHttpClient));
        return restTemplate;
    }


    @Bean
    @Scope("prototype")
    RestTemplate oauthRestTemplate(
            RestTemplateBuilder restTemplateBuilder
    ) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<ClientHttpRequestInterceptor> newInterceptors = new LinkedList<>(restTemplate.getInterceptors());
//        newInterceptors.add(new HttpRequestInterceptorService());
        restTemplate.setInterceptors(newInterceptors);
        HttpClient httpClient = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.setErrorHandler(new Oauth2ResponseErrorHandler());
        return restTemplate;
    }


}
