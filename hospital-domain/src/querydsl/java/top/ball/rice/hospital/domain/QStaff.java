package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = 13910443L;

    public static final QStaff staff = new QStaff("staff");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final BooleanPath disabled = createBoolean("disabled");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath memo = createString("memo");

    public final StringPath userId = createString("userId");

    public QStaff(String variable) {
        super(Staff.class, forVariable(variable));
    }

    public QStaff(Path<? extends Staff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStaff(PathMetadata metadata) {
        super(Staff.class, metadata);
    }

}

