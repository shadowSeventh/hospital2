package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSufferer is a Querydsl query type for Sufferer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSufferer extends EntityPathBase<Sufferer> {

    private static final long serialVersionUID = -1175087855L;

    public static final QSufferer sufferer = new QSufferer("sufferer");

    public final QUser _super = new QUser(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final StringPath headImg = _super.headImg;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath passWord = _super.passWord;

    //inherited
    public final StringPath phone = _super.phone;

    //inherited
    public final BooleanPath sex = _super.sex;

    public QSufferer(String variable) {
        super(Sufferer.class, forVariable(variable));
    }

    public QSufferer(Path<? extends Sufferer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSufferer(PathMetadata metadata) {
        super(Sufferer.class, metadata);
    }

}

