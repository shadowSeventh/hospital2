package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDoctorOrderRecord is a Querydsl query type for DoctorOrderRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctorOrderRecord extends EntityPathBase<DoctorOrderRecord> {

    private static final long serialVersionUID = 1993134315L;

    public static final QDoctorOrderRecord doctorOrderRecord = new QDoctorOrderRecord("doctorOrderRecord");

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

    public final EnumPath<top.ball.rice.hospital.core.OrderStatusEnum> status = createEnum("status", top.ball.rice.hospital.core.OrderStatusEnum.class);

    public final StringPath suffererId = createString("suffererId");

    public QDoctorOrderRecord(String variable) {
        super(DoctorOrderRecord.class, forVariable(variable));
    }

    public QDoctorOrderRecord(Path<? extends DoctorOrderRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoctorOrderRecord(PathMetadata metadata) {
        super(DoctorOrderRecord.class, metadata);
    }

}

