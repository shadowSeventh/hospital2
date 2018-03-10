package top.ball.rice.hospital.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1800607840L;

    public static final QUser user = new QUser("user");

    public final QBase _super = new QBase(this);

    public final StringPath addr = createString("addr");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath contacts = createString("contacts");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> dateCreated = _super.dateCreated;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath headImg = createString("headImg");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath IDNum = createString("IDNum");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath nickName = createString("nickName");

    public final StringPath passWord = createString("passWord");

    public final StringPath phone = createString("phone");

    public final BooleanPath sex = createBoolean("sex");

    public final StringPath telephone = createString("telephone");

    public final StringPath userName = createString("userName");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

