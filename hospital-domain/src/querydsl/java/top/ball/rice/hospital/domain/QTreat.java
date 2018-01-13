package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTreat is a Querydsl query type for Treat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTreat extends EntityPathBase<Treat> {

    private static final long serialVersionUID = 14778085L;

    public static final QTreat treat = new QTreat("treat");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath doctorId = createString("doctorId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath opinion = createString("opinion");

    public final StringPath pathogeny = createString("pathogeny");

    public final StringPath prescription = createString("prescription");

    public final StringPath result = createString("result");

    public final DateTimePath<java.util.Date> sickDate = createDateTime("sickDate", java.util.Date.class);

    public final StringPath sickHistory = createString("sickHistory");

    public final StringPath sickroomId = createString("sickroomId");

    public final StringPath suffererId = createString("suffererId");

    public QTreat(String variable) {
        super(Treat.class, forVariable(variable));
    }

    public QTreat(Path<? extends Treat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTreat(PathMetadata metadata) {
        super(Treat.class, metadata);
    }

}

