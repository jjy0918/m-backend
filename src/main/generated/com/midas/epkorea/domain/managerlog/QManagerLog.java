package com.midas.epkorea.domain.managerlog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QManagerLog is a Querydsl query type for ManagerLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QManagerLog extends EntityPathBase<ManagerLog> {

    private static final long serialVersionUID = 130973908L;

    public static final QManagerLog managerLog = new QManagerLog("managerLog");

    public final StringPath id = createString("id");

    public final StringPath ip = createString("ip");

    public final DateTimePath<java.sql.Timestamp> loginDate = createDateTime("loginDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath sessionId = createString("sessionId");

    public final BooleanPath successLogin = createBoolean("successLogin");

    public QManagerLog(String variable) {
        super(ManagerLog.class, forVariable(variable));
    }

    public QManagerLog(Path<? extends ManagerLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManagerLog(PathMetadata metadata) {
        super(ManagerLog.class, metadata);
    }

}

