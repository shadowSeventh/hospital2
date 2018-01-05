package net.kingsilk.qh.hospital.server.conf;

import net.kingsilk.qh.hospital.service.HospitalProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConf {
    @Bean
    public HospitalProperties qhBargainProperties() {
        return new HospitalProperties();
    }
//
//    @Bean
//    FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(
//            Set<Converter> converters
//    ) {
//        FormattingConversionServiceFactoryBean fac = new FormattingConversionServiceFactoryBean();
//        fac.setConverters(converters);
//        return fac;
//    }
}
