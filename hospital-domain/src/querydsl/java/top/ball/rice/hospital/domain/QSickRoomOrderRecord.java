package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSickRoomOrderRecord is a Querydsl query type for SickRoomOrderRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSickRoomOrderRecord extends EntityPathBase<SickRoomOrderRecord> {

    private static final long serialVersionUID = -1379171375L;

    public static final QSickRoomOrderRecord sickRoomOrderRecord = new QSickRoomOrderRecord("sickRoomOrderRecord");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    public final DateTimePath<java.util.Date> deadline = createDateTime("deadline", java.util.Date.class);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath sickroomId = createString("sickroomId");

    public final EnumPath<top.ball.rice.hospital.core.OrderStatusEnum> status = createEnum("status", top.ball.rice.hospital.core.OrderStatusEnum.class);

    public final StringPath suffererId = createString("suffererId");

    public QSickRoomOrderRecord(String variable) {
        super(SickRoomOrderRecord.class, forVariable(variable));
    }

    public QSickRoomOrderRecord(Path<? extends SickRoomOrderRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSickRoomOrderRecord(PathMetadata metadata) {
        super(SickRoomOrderRecord.class, metadata);
    }

}

