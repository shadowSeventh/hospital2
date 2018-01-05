package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBargainApp is a Querydsl query type for BargainApp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBargainApp extends EntityPathBase<BargainApp> {

    private static final long serialVersionUID = 64169207L;

    public static final QBargainApp bargainApp = new QBargainApp("bargainApp");

    public final QBase _super = new QBase(this);

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

    public final ListPath<String, StringPath> payLog = this.<String, StringPath>createList("payLog", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath shopId = createString("shopId");

    public final StringPath userId = createString("userId");

    public QBargainApp(String variable) {
        super(BargainApp.class, forVariable(variable));
    }

    public QBargainApp(Path<? extends BargainApp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBargainApp(PathMetadata metadata) {
        super(BargainApp.class, metadata);
    }

}

