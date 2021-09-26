package com.midas.epkorea.domain.productmanagementtable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductManagementTable is a Querydsl query type for ProductManagementTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductManagementTable extends EntityPathBase<ProductManagementTable> {

    private static final long serialVersionUID = -1149896044L;

    public static final QProductManagementTable productManagementTable = new QProductManagementTable("productManagementTable");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final NumberPath<Integer> productManagementNo = createNumber("productManagementNo", Integer.class);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    public QProductManagementTable(String variable) {
        super(ProductManagementTable.class, forVariable(variable));
    }

    public QProductManagementTable(Path<? extends ProductManagementTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductManagementTable(PathMetadata metadata) {
        super(ProductManagementTable.class, metadata);
    }

}

