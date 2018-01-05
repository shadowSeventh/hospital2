package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 651755526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order");

    public final QBase _super = new QBase(this);

    public final QOrder_ShippingAddress addr;

    public final StringPath bargainAppId = createString("bargainAppId");

    public final ListPath<String, StringPath> bargainAwards = this.<String, StringPath>createList("bargainAwards", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath bargainId = createString("bargainId");

    public final StringPath buyerMemo = createString("buyerMemo");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath deliveryTemplateId = createString("deliveryTemplateId");

    public final NumberPath<Integer> freight = createNumber("freight", Integer.class);

    //inherited
    public final StringPath id = _super.id;

    public final NumberPath<Integer> integral = createNumber("integral", Integer.class);

    public final StringPath invoiceTitle = createString("invoiceTitle");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath needInvoice = createString("needInvoice");

    public final NumberPath<Integer> paymentAmount = createNumber("paymentAmount", Integer.class);

    public final StringPath qhPayId = createString("qhPayId");

    public final StringPath sellerMemo = createString("sellerMemo");

    public final StringPath seq = createString("seq");

    public final StringPath shopId = createString("shopId");

    public final DateTimePath<java.util.Date> sinceTakeTime = createDateTime("sinceTakeTime", java.util.Date.class);

    public final EnumPath<OrderSourceTypeEnum> sourceType = createEnum("sourceType", OrderSourceTypeEnum.class);

    public final EnumPath<OrderStatusEnum> status = createEnum("status", OrderStatusEnum.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final StringPath userId = createString("userId");

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addr = inits.isInitialized("addr") ? new QOrder_ShippingAddress(forProperty("addr")) : null;
    }

}

