package com.midas.epkorea.domain.constructiontable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QConstructionTable is a Querydsl query type for ConstructionTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConstructionTable extends EntityPathBase<ConstructionTable> {

    private static final long serialVersionUID = 929164114L;

    public static final QConstructionTable constructionTable = new QConstructionTable("constructionTable");

    public final NumberPath<Integer> constructionNo = createNumber("constructionNo", Integer.class);

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    public QConstructionTable(String variable) {
        super(ConstructionTable.class, forVariable(variable));
    }

    public QConstructionTable(Path<? extends ConstructionTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConstructionTable(PathMetadata metadata) {
        super(ConstructionTable.class, metadata);
    }

}

