package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSickRoom is a Querydsl query type for SickRoom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSickRoom extends EntityPathBase<SickRoom> {

    private static final long serialVersionUID = 977931406L;

    public static final QSickRoom sickRoom = new QSickRoom("sickRoom");

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

    public final StringPath Seq = createString("Seq");

    public final StringPath seq = createString("seq");

    public final EnumPath<top.ball.rice.hospital.core.SickRoomStatusEnum> status = createEnum("status", top.ball.rice.hospital.core.SickRoomStatusEnum.class);

    public final EnumPath<top.ball.rice.hospital.core.SickRoomTypeEnum> type = createEnum("type", top.ball.rice.hospital.core.SickRoomTypeEnum.class);

    public QSickRoom(String variable) {
        super(SickRoom.class, forVariable(variable));
    }

    public QSickRoom(Path<? extends SickRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSickRoom(PathMetadata metadata) {
        super(SickRoom.class, metadata);
    }

}

