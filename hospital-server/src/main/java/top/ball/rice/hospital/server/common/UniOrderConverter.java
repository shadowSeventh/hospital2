package top.ball.rice.hospital.server.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class UniOrderConverter implements Converter<Sort.Order, UniOrder> {

    @Override
    public UniOrder convert(Sort.Order source) {
        UniOrder o = new UniOrder();
        o.setProperty(source.getProperty());
        o.setDesc(Sort.Direction.DESC.equals(source.getDirection()));
        return o;
    }
}
