package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder_ShippingAddress is a Querydsl query type for ShippingAddress
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QOrder_ShippingAddress extends BeanPath<Order.ShippingAddress> {

    private static final long serialVersionUID = -860094370L;

    public static final QOrder_ShippingAddress shippingAddress = new QOrder_ShippingAddress("shippingAddress");

    public final StringPath adc = createString("adc");

    public final StringPath memo = createString("memo");

    public final StringPath phone = createString("phone");

    public final StringPath receiver = createString("receiver");

    public final StringPath street = createString("street");

    public QOrder_ShippingAddress(String variable) {
        super(Order.ShippingAddress.class, forVariable(variable));
    }

    public QOrder_ShippingAddress(Path<? extends Order.ShippingAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder_ShippingAddress(PathMetadata metadata) {
        super(Order.ShippingAddress.class, metadata);
    }

}

