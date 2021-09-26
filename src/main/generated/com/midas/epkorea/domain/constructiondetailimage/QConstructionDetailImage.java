package com.midas.epkorea.domain.constructiondetailimage;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConstructionDetailImage is a Querydsl query type for ConstructionDetailImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConstructionDetailImage extends EntityPathBase<ConstructionDetailImage> {

    private static final long serialVersionUID = 1950081066L;

    public static final QConstructionDetailImage constructionDetailImage = new QConstructionDetailImage("constructionDetailImage");

    public final NumberPath<Integer> constructionNo = createNumber("constructionNo", Integer.class);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public QConstructionDetailImage(String variable) {
        super(ConstructionDetailImage.class, forVariable(variable));
    }

    public QConstructionDetailImage(Path<? extends ConstructionDetailImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConstructionDetailImage(PathMetadata metadata) {
        super(ConstructionDetailImage.class, metadata);
    }

}

