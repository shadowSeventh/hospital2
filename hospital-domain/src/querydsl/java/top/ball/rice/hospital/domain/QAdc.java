package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdc is a Querydsl query type for Adc
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdc extends EntityPathBase<Adc> {

    private static final long serialVersionUID = 1604464171L;

    public static final QAdc adc = new QAdc("adc");

    public final QBase _super = new QBase(this);

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

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final StringPath parentId = createString("parentId");

    public QAdc(String variable) {
        super(Adc.class, forVariable(variable));
    }

    public QAdc(Path<? extends Adc> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdc(PathMetadata metadata) {
        super(Adc.class, metadata);
    }

}

