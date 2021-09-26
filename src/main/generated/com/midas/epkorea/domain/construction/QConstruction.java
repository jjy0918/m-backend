package com.midas.epkorea.domain.construction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConstruction is a Querydsl query type for Construction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConstruction extends EntityPathBase<Construction> {

    private static final long serialVersionUID = -1695840652L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConstruction construction = new QConstruction("construction");

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final com.midas.epkorea.domain.category.QCategory categoryDetail;

    public final BooleanPath expose = createBoolean("expose");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final DateTimePath<java.sql.Timestamp> registrationDate = createDateTime("registrationDate", java.sql.Timestamp.class);

    public final StringPath thumbnail = createString("thumbnail");

    public QConstruction(String variable) {
        this(Construction.class, forVariable(variable), INITS);
    }

    public QConstruction(Path<? extends Construction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConstruction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConstruction(PathMetadata metadata, PathInits inits) {
        this(Construction.class, metadata, inits);
    }

    public QConstruction(Class<? extends Construction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryDetail = inits.isInitialized("categoryDetail") ? new com.midas.epkorea.domain.category.QCategory(forProperty("categoryDetail")) : null;
    }

}

