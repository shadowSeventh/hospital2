package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBargainAward is a Querydsl query type for BargainAward
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBargainAward extends EntityPathBase<BargainAward> {

    private static final long serialVersionUID = 1537263539L;

    public static final QBargainAward bargainAward = new QBargainAward("bargainAward");

    public final QBase _super = new QBase(this);

    public final StringPath awardImg = createString("awardImg");

    public final StringPath bargainId = createString("bargainId");

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

    public final NumberPath<Integer> minTargetPrice = createNumber("minTargetPrice", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QBargainAward(String variable) {
        super(BargainAward.class, forVariable(variable));
    }

    public QBargainAward(Path<? extends BargainAward> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBargainAward(PathMetadata metadata) {
        super(BargainAward.class, metadata);
    }

}

