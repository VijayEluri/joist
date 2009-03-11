package joist.domain.migrations.columns;

/** Store YYYY-MM-DD. */
public class DateColumn extends AbstractColumn<DateColumn> {

    public DateColumn(String name) {
        super(name);
    }

    public String toSql() {
        return "\"" + this.getName() + "\" date,";
    }

}