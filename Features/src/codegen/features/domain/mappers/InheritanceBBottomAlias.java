package features.domain.mappers;

import features.domain.InheritanceBBottom;
import features.domain.InheritanceBBottomCodegen;
import features.domain.InheritanceBMiddle;
import features.domain.InheritanceBRoot;
import features.domain.mappers.InheritanceBMiddleAlias;
import java.util.ArrayList;
import java.util.List;
import org.exigencecorp.domainobjects.queries.Alias;
import org.exigencecorp.domainobjects.queries.columns.AliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IdAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IntAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.StringAliasColumn;

public class InheritanceBBottomAlias extends Alias<InheritanceBBottom> {

    private final List<AliasColumn<InheritanceBBottom, ?, ?>> columns = new ArrayList<AliasColumn<InheritanceBBottom, ?, ?>>();
    private final IdAliasColumn<InheritanceBBottom> subClassId = new IdAliasColumn<InheritanceBBottom>(this, "id", null);
    public final StringAliasColumn<InheritanceBBottom> bottomName = new StringAliasColumn<InheritanceBBottom>(this, "bottom_name", InheritanceBBottomCodegen.Shims.bottomName);
    private final InheritanceBMiddleAlias baseAlias;
    public final StringAliasColumn<InheritanceBMiddle> middleName;
    public final IdAliasColumn<InheritanceBRoot> id;
    public final StringAliasColumn<InheritanceBRoot> name;
    public final IntAliasColumn<InheritanceBRoot> version;

    public InheritanceBBottomAlias(String alias) {
        super(InheritanceBBottom.class, InheritanceBMiddle.class, "inheritance_b_bottom", alias);
        this.baseAlias = new InheritanceBMiddleAlias(alias + "_b");
        this.columns.add(this.bottomName);
        this.middleName = this.baseAlias.middleName;
        this.id = this.baseAlias.id;
        this.name = this.baseAlias.name;
        this.version = this.baseAlias.version;
    }

    public InheritanceBBottomAlias(InheritanceBMiddleAlias baseAlias, String alias) {
        super(InheritanceBBottom.class, InheritanceBMiddle.class, "inheritance_b_bottom", alias);
        this.baseAlias = baseAlias;
        this.columns.add(this.bottomName);
        this.middleName = this.baseAlias.middleName;
        this.id = this.baseAlias.id;
        this.name = this.baseAlias.name;
        this.version = this.baseAlias.version;
    }

    public List<AliasColumn<InheritanceBBottom, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<InheritanceBRoot> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<InheritanceBRoot> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<InheritanceBBottom> getSubClassIdColumn() {
        return this.subClassId;
    }

    public Alias<InheritanceBMiddle> getBaseClassAlias() {
        return this.baseAlias;
    }

}
