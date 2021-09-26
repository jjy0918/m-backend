package com.midas.epkorea.domain.manager;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QManager is a Querydsl query type for Manager
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QManager extends EntityPathBase<Manager> {

    private static final long serialVersionUID = -825506478L;

    public static final QManager manager = new QManager("manager");

    public final StringPath belong = createString("belong");

    public final BooleanPath cmCooling = createBoolean("cmCooling");

    public final BooleanPath cmEss = createBoolean("cmEss");

    public final BooleanPath cmLighting = createBoolean("cmLighting");

    public final BooleanPath cmRailroad = createBoolean("cmRailroad");

    public final BooleanPath cmUpsSts = createBoolean("cmUpsSts");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final BooleanPath pmCooling = createBoolean("pmCooling");

    public final BooleanPath pmEss = createBoolean("pmEss");

    public final BooleanPath pmLighting = createBoolean("pmLighting");

    public final BooleanPath pmRailroad = createBoolean("pmRailroad");

    public final BooleanPath pmUpsSts = createBoolean("pmUpsSts");

    public final DateTimePath<java.sql.Timestamp> registrationDate = createDateTime("registrationDate", java.sql.Timestamp.class);

    public final StringPath role = createString("role");

    public QManager(String variable) {
        super(Manager.class, forVariable(variable));
    }

    public QManager(Path<? extends Manager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManager(PathMetadata metadata) {
        super(Manager.class, metadata);
    }

}

