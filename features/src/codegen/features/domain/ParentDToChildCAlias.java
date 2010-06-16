package features.domain;

import java.util.ArrayList;
import java.util.List;
import joist.domain.orm.queries.Alias;
import joist.domain.orm.queries.columns.AliasColumn;
import joist.domain.orm.queries.columns.ForeignKeyAliasColumn;
import joist.domain.orm.queries.columns.IdAliasColumn;
import joist.domain.orm.queries.columns.IntAliasColumn;

public class ParentDToChildCAlias extends Alias<ParentDToChildC> {

    private final List<AliasColumn<ParentDToChildC, ?, ?>> columns = new ArrayList<AliasColumn<ParentDToChildC, ?, ?>>();
    public final IdAliasColumn<ParentDToChildC> id = new IdAliasColumn<ParentDToChildC>(this, "id", ParentDToChildCCodegen.Shims.id);
    public final IntAliasColumn<ParentDToChildC> version = new IntAliasColumn<ParentDToChildC>(this, "version", ParentDToChildCCodegen.Shims.version);
    public final ForeignKeyAliasColumn<ParentDToChildC, ParentDChildC> parentDChildC = new ForeignKeyAliasColumn<ParentDToChildC, ParentDChildC>(this, "parent_d_child_c_id", ParentDToChildCCodegen.Shims.parentDChildCId);
    public final ForeignKeyAliasColumn<ParentDToChildC, ParentD> parentD = new ForeignKeyAliasColumn<ParentDToChildC, ParentD>(this, "parent_d_id", ParentDToChildCCodegen.Shims.parentDId);

    public ParentDToChildCAlias(String alias) {
        super(ParentDToChildC.class, "parent_d_to_child_c", alias);
        this.columns.add(this.id);
        this.columns.add(this.version);
        this.columns.add(this.parentDChildC);
        this.columns.add(this.parentD);
    }

    public List<AliasColumn<ParentDToChildC, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<ParentDToChildC> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<ParentDToChildC> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<ParentDToChildC> getSubClassIdColumn() {
        return null;
    }

    public int getOrder() {
        return 34;
    }

}