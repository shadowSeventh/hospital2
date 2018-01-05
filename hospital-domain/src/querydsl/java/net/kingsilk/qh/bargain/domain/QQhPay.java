package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QQhPay is a Querydsl query type for QhPay
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQhPay extends EntityPathBase<QhPay> {

    private static final long serialVersionUID = 653285321L;

    public static final QQhPay qhPay = new QQhPay("qhPay");

    public final QBase _super = new QBase(this);

    public final NumberPath<Integer> balanceAmount = createNumber("balanceAmount", Integer.class);

    public final StringPath brandAppId = createString("brandAppId");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath memo = createString("memo");

    public final StringPath orderId = createString("orderId");

    public final DateTimePath<java.util.Date> payTime = createDateTime("payTime", java.util.Date.class);

    public final EnumPath<PayTypeEnum> payType = createEnum("payType", PayTypeEnum.class);

    public final NumberPath<Integer> refundAmount = createNumber("refundAmount", Integer.class);

    public final StringPath seq = createString("seq");

    public final StringPath shopId = createString("shopId");

    public final NumberPath<Integer> thirdPayAmount = createNumber("thirdPayAmount", Integer.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public final StringPath userId = createString("userId");

    public QQhPay(String variable) {
        super(QhPay.class, forVariable(variable));
    }

    public QQhPay(Path<? extends QhPay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQhPay(PathMetadata metadata) {
        super(QhPay.class, metadata);
    }

}

