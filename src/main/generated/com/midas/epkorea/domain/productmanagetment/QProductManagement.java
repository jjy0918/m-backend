package com.midas.epkorea.domain.productmanagetment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductManagement is a Querydsl query type for ProductManagement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductManagement extends EntityPathBase<ProductManagement> {

    private static final long serialVersionUID = 534012540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductManagement productManagement = new QProductManagement("productManagement");

    public final StringPath catalog = createString("catalog");

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final com.midas.epkorea.domain.category.QCategory categoryDetail;

    public final StringPath editor = createString("editor");

    public final BooleanPath expose = createBoolean("expose");

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final ListPath<com.midas.epkorea.domain.productmanagementtable.ProductManagementTable, com.midas.epkorea.domain.productmanagementtable.QProductManagementTable> productManagementTableList = this.<com.midas.epkorea.domain.productmanagementtable.ProductManagementTable, com.midas.epkorea.domain.productmanagementtable.QProductManagementTable>createList("productManagementTableList", com.midas.epkorea.domain.productmanagementtable.ProductManagementTable.class, com.midas.epkorea.domain.productmanagementtable.QProductManagementTable.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> registrationDate = createDateTime("registrationDate", java.sql.Timestamp.class);

    public QProductManagement(String variable) {
        this(ProductManagement.class, forVariable(variable), INITS);
    }

    public QProductManagement(Path<? extends ProductManagement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductManagement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductManagement(PathMetadata metadata, PathInits inits) {
        this(ProductManagement.class, metadata, inits);
    }

    public QProductManagement(Class<? extends ProductManagement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryDetail = inits.isInitialized("categoryDetail") ? new com.midas.epkorea.domain.category.QCategory(forProperty("categoryDetail")) : null;
    }

}

