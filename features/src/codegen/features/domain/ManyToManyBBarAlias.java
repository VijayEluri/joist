package features.domain;

import java.util.ArrayList;
import java.util.List;
import joist.domain.DomainObject;
import joist.domain.orm.queries.Alias;
import joist.domain.orm.queries.JoinClause;
import joist.domain.orm.queries.columns.AliasColumn;
import joist.domain.orm.queries.columns.ForeignKeyAliasColumn;
import joist.domain.orm.queries.columns.IdAliasColumn;
import joist.domain.orm.queries.columns.IntAliasColumn;
import joist.domain.orm.queries.columns.StringAliasColumn;

public class ManyToManyBBarAlias extends Alias<ManyToManyBBar> {

    private final List<AliasColumn<ManyToManyBBar, ?, ?>> columns = new ArrayList<AliasColumn<ManyToManyBBar, ?, ?>>();
    public final IdAliasColumn<ManyToManyBBar> id = new IdAliasColumn<ManyToManyBBar>(this, "id", ManyToManyBBarCodegen.Shims.id);
    public final StringAliasColumn<ManyToManyBBar> name = new StringAliasColumn<ManyToManyBBar>(this, "name", ManyToManyBBarCodegen.Shims.name);
    public final IntAliasColumn<ManyToManyBBar> version = new IntAliasColumn<ManyToManyBBar>(this, "version", ManyToManyBBarCodegen.Shims.version);

    public ManyToManyBBarAlias(String alias) {
        super(ManyToManyBBar.class, "many_to_many_b_bar", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
    }

    public List<AliasColumn<ManyToManyBBar, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<ManyToManyBBar> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<ManyToManyBBar> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<ManyToManyBBar> getSubClassIdColumn() {
        return null;
    }

    public int getOrder() {
        return 13;
    }

    public <T extends DomainObject> JoinClause<T, ManyToManyBBar> on(ForeignKeyAliasColumn<T, ManyToManyBBar> on) {
        return new JoinClause<T, ManyToManyBBar>("INNER JOIN", this, on);
    }

}
