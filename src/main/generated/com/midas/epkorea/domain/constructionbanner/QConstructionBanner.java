package com.midas.epkorea.domain.constructionbanner;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConstructionBanner is a Querydsl query type for ConstructionBanner
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConstructionBanner extends EntityPathBase<ConstructionBanner> {

    private static final long serialVersionUID = -942653356L;

    public static final QConstructionBanner constructionBanner = new QConstructionBanner("constructionBanner");

    public final NumberPath<Integer> constructionNo = createNumber("constructionNo", Integer.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public QConstructionBanner(String variable) {
        super(ConstructionBanner.class, forVariable(variable));
    }

    public QConstructionBanner(Path<? extends ConstructionBanner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConstructionBanner(PathMetadata metadata) {
        super(ConstructionBanner.class, metadata);
    }

}

