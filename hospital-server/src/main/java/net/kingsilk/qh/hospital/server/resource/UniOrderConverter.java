package net.kingsilk.qh.hospital.server.resource;

import net.kingsilk.qh.bargain.api.*;
import org.springframework.core.convert.converter.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

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
