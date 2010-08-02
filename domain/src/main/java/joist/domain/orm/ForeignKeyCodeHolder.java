package joist.domain.orm;

import joist.domain.Code;
import joist.domain.util.Codes;

/** A value holder that will lazy load the foreign key that points to a code/enum. */
public class ForeignKeyCodeHolder<T extends Enum<T>> {

  private final Class<T> codeClass;
  private T instance;

  public ForeignKeyCodeHolder(Class<T> codeClass) {
    this.codeClass = codeClass;
  }

  public T get() {
    return this.instance;
  }

  public void set(T instance) {
    this.instance = instance;
  }

  public Integer getId() {
    if (this.instance == null) {
      return null;
    }
    return ((Code) this.instance).getId();
  }

  public void setId(Integer id) {
    this.instance = (id == null) ? null : Codes.fromInt(this.codeClass, id);
  }

}
