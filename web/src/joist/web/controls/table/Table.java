package joist.web.controls.table;

import java.util.ArrayList;
import java.util.List;

import joist.web.Control;
import joist.web.util.HtmlWriter;

import org.exigencecorp.bindgen.Binding;
import org.exigencecorp.util.Inflector;


// Should extend AbstractContainer?
public class Table<T> implements Control {

    private String id;
    private String label;
    private List<Column> columns = new ArrayList<Column>();
    private List<T> list = new ArrayList<T>();
    private Binding<? super T> current = null;
    // Only set if doing paging
    private Integer pageNumber;
    private Integer pageRows;

    public Table(String id) {
        this.setId(id);
        this.setLabel(Inflector.humanize(id));
    }

    public String getId() {
        return this.id;
    }

    public void onProcess() {
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setCurrent(Binding<? super T> binding) {
        this.current = binding;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
        column.setTable(this);
    }

    public void render(HtmlWriter w) {
        this.renderHeader(w);
        this.renderRows(w);
        this.renderFooter(w);
    }

    private void renderHeader(HtmlWriter w) {
        w.line("<h3>{}</h3>", this.getLabel());
        w.line("<table id={}>", this.getId());
        w.line("  <thead>");
        w.line("    <tr>");
        for (Column column : this.columns) {
            w.append("      <th id=\"{}.{}\">", this.getId(), column.getId());
            column.renderHeader(w);
            w.line("</th>");
        }
        w.line("    </tr>");
        w.line("  </thead>");
    }

    private void renderRows(HtmlWriter w) {
        w.line("  <tbody>");
        int i = 0;
        for (T object : this.list) {
            this.current.set(object);
            w.line("    <tr>");
            for (Column column : this.columns) {
                column.setCurrentRowIndex(i);
                w.append("      <td id={}>", column.getFullId());
                column.render(w);
                w.line("</td>");
            }
            w.line("    </tr>");
            i++;
        }
        w.line("  </tbody>");
    }

    private void renderFooter(HtmlWriter w) {
        w.line("</table>");
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageRows() {
        return this.pageRows;
    }

    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

}