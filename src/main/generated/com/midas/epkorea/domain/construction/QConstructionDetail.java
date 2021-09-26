package com.midas.epkorea.domain.construction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConstructionDetail is a Querydsl query type for ConstructionDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConstructionDetail extends EntityPathBase<ConstructionDetail> {

    private static final long serialVersionUID = 1943069669L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConstructionDetail constructionDetail = new QConstructionDetail("constructionDetail");

    public final ListPath<com.midas.epkorea.domain.constructionbanner.ConstructionBanner, com.midas.epkorea.domain.constructionbanner.QConstructionBanner> banners = this.<com.midas.epkorea.domain.constructionbanner.ConstructionBanner, com.midas.epkorea.domain.constructionbanner.QConstructionBanner>createList("banners", com.midas.epkorea.domain.constructionbanner.ConstructionBanner.class, com.midas.epkorea.domain.constructionbanner.QConstructionBanner.class, PathInits.DIRECT2);

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final com.midas.epkorea.domain.category.QCategory categoryDetail;

    public final ListPath<com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImage, com.midas.epkorea.domain.constructiondetailimage.QConstructionDetailImage> detailimage = this.<com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImage, com.midas.epkorea.domain.constructiondetailimage.QConstructionDetailImage>createList("detailimage", com.midas.epkorea.domain.constructiondetailimage.ConstructionDetailImage.class, com.midas.epkorea.domain.constructiondetailimage.QConstructionDetailImage.class, PathInits.DIRECT2);

    public final BooleanPath expose = createBoolean("expose");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final DateTimePath<java.sql.Timestamp> registrationDate = createDateTime("registrationDate", java.sql.Timestamp.class);

    public final ListPath<com.midas.epkorea.domain.constructiontable.ConstructionTable, com.midas.epkorea.domain.constructiontable.QConstructionTable> tableList = this.<com.midas.epkorea.domain.constructiontable.ConstructionTable, com.midas.epkorea.domain.constructiontable.QConstructionTable>createList("tableList", com.midas.epkorea.domain.constructiontable.ConstructionTable.class, com.midas.epkorea.domain.constructiontable.QConstructionTable.class, PathInits.DIRECT2);

    public final StringPath thumbnail = createString("thumbnail");

    public QConstructionDetail(String variable) {
        this(ConstructionDetail.class, forVariable(variable), INITS);
    }

    public QConstructionDetail(Path<? extends ConstructionDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConstructionDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConstructionDetail(PathMetadata metadata, PathInits inits) {
        this(ConstructionDetail.class, metadata, inits);
    }

    public QConstructionDetail(Class<? extends ConstructionDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.categoryDetail = inits.isInitialized("categoryDetail") ? new com.midas.epkorea.domain.category.QCategory(forProperty("categoryDetail")) : null;
    }

}

