package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBargainActivity is a Querydsl query type for BargainActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBargainActivity extends EntityPathBase<BargainActivity> {

    private static final long serialVersionUID = -652108295L;

    public static final QBargainActivity bargainActivity = new QBargainActivity("bargainActivity");

    public final QBase _super = new QBase(this);

    public final StringPath bargainAppId = createString("bargainAppId");

    public final DateTimePath<java.util.Date> beginTime = createDateTime("beginTime", java.util.Date.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath desp = createString("desp");

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final BooleanPath mustFollow = createBoolean("mustFollow");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> participantNum = createNumber("participantNum", Integer.class);

    public final EnumPath<BargainReceiveTypeEnum> receiveType = createEnum("receiveType", BargainReceiveTypeEnum.class);

    public final StringPath rule = createString("rule");

    public final StringPath seq = createString("seq");

    public final StringPath shareDesp = createString("shareDesp");

    public final StringPath shareImg = createString("shareImg");

    public final StringPath shareTitle = createString("shareTitle");

    public final StringPath shopName = createString("shopName");

    public final EnumPath<BargainStatusEnum> status = createEnum("status", BargainStatusEnum.class);

    public final StringPath tel = createString("tel");

    public final StringPath titleImg = createString("titleImg");

    public final ListPath<BargainActivity.UserTypeRandom, QBargainActivity_UserTypeRandom> userTypeRandom = this.<BargainActivity.UserTypeRandom, QBargainActivity_UserTypeRandom>createList("userTypeRandom", BargainActivity.UserTypeRandom.class, QBargainActivity_UserTypeRandom.class, PathInits.DIRECT2);

    public QBargainActivity(String variable) {
        super(BargainActivity.class, forVariable(variable));
    }

    public QBargainActivity(Path<? extends BargainActivity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBargainActivity(PathMetadata metadata) {
        super(BargainActivity.class, metadata);
    }

}

