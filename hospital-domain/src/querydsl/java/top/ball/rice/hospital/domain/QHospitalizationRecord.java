package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHospitalizationRecord is a Querydsl query type for HospitalizationRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHospitalizationRecord extends EntityPathBase<HospitalizationRecord> {

    private static final long serialVersionUID = 772291238L;

    public static final QHospitalizationRecord hospitalizationRecord = new QHospitalizationRecord("hospitalizationRecord");

    public final QBase _super = new QBase(this);

    public final StringPath changeWard = createString("changeWard");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath sickRoomId = createString("sickRoomId");

    public final StringPath sickRoomOrderId = createString("sickRoomOrderId");

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath suffererId = createString("suffererId");

    public QHospitalizationRecord(String variable) {
        super(HospitalizationRecord.class, forVariable(variable));
    }

    public QHospitalizationRecord(Path<? extends HospitalizationRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHospitalizationRecord(PathMetadata metadata) {
        super(HospitalizationRecord.class, metadata);
    }

}

