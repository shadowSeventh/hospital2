package net.kingsilk.qh.bargain.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBargainActivity_UserTypeRandom is a Querydsl query type for UserTypeRandom
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBargainActivity_UserTypeRandom extends BeanPath<BargainActivity.UserTypeRandom> {

    private static final long serialVersionUID = 1003407229L;

    public static final QBargainActivity_UserTypeRandom userTypeRandom = new QBargainActivity_UserTypeRandom("userTypeRandom");

    public final NumberPath<Integer> higherPrice = createNumber("higherPrice", Integer.class);

    public final NumberPath<Integer> lowerPrice = createNumber("lowerPrice", Integer.class);

    public final EnumPath<BargainUserTypeEnum> userType = createEnum("userType", BargainUserTypeEnum.class);

    public QBargainActivity_UserTypeRandom(String variable) {
        super(BargainActivity.UserTypeRandom.class, forVariable(variable));
    }

    public QBargainActivity_UserTypeRandom(Path<? extends BargainActivity.UserTypeRandom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBargainActivity_UserTypeRandom(PathMetadata metadata) {
        super(BargainActivity.UserTypeRandom.class, metadata);
    }

}

