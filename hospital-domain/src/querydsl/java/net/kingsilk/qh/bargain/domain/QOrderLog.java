package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderLog is a Querydsl query type for OrderLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderLog extends EntityPathBase<OrderLog> {

    private static final long serialVersionUID = -1098193570L;

    public static final QOrderLog orderLog = new QOrderLog("orderLog");

    public final QBase _super = new QBase(this);

    public final StringPath adjustMoney = createString("adjustMoney");

    public final StringPath bargainAppId = createString("bargainAppId");

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

    public final EnumPath<OrderStatusEnum> lastStatus = createEnum("lastStatus", OrderStatusEnum.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath memo = createString("memo");

    public final StringPath orderId = createString("orderId");

    public final EnumPath<OrderSourceTypeEnum> orderSourceType = createEnum("orderSourceType", OrderSourceTypeEnum.class);

    public final StringPath shopId = createString("shopId");

    public final StringPath staffId = createString("staffId");

    public final EnumPath<OrderStatusEnum> status = createEnum("status", OrderStatusEnum.class);

    public QOrderLog(String variable) {
        super(OrderLog.class, forVariable(variable));
    }

    public QOrderLog(Path<? extends OrderLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderLog(PathMetadata metadata) {
        super(OrderLog.class, metadata);
    }

}

