package joist.domain.orm.queries.columns;

import joist.domain.DomainObject;
import joist.domain.Shim;
import joist.domain.orm.queries.Alias;
import joist.domain.orm.queries.JoinClause;
import joist.domain.orm.queries.Where;

/**
 * @param T the domain object the column is within
 * @param W the domain object the column points to
 */
public class ForeignKeyAliasColumn<T extends DomainObject, W extends DomainObject> extends AliasColumn<T, Integer, Integer> {

    public ForeignKeyAliasColumn(Alias<T> alias, String name, Shim<T, Integer> shim) {
        super(alias, name, shim);
    }

    public Where eq(W value) {
        return new Where(this.getQualifiedName() + " = ?", value.getId());
    }

    public Where eq(Integer value) {
        return new Where(this.getQualifiedName() + " = ?", value);
    }

    public JoinClause<T, W> on(Alias<W> on) {
        return new JoinClause<T, W>("INNER JOIN", this.getAlias(), on.getIdColumn(), this);
    }

    public JoinClause<T, W> onLeftOuter(Alias<W> on) {
        return new JoinClause<T, W>("LEFT OUTER JOIN", this.getAlias(), on.getIdColumn(), this);
    }

    public JoinClause<T, W> on(IdAliasColumn<W> on) {
        return new JoinClause<T, W>("INNER JOIN", this.getAlias(), on, this);
    }

}