package features.domain.builders;

import features.domain.ParentG;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ParentGBuilderCodegen extends AbstractBuilder<ParentG> {

  public ParentGBuilderCodegen(ParentG instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public ParentGBuilder id(Long id) {
    get().setId(id);
    return (ParentGBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentGBuilder name(String name) {
    get().setName(name);
    return (ParentGBuilder) this;
  }

  public ParentGBuilder with(String name) {
    return name(name);
  }

  @Override
  public ParentGBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (ParentGBuilder) super.defaults();
  }

  public ChildGBuilder parentOneChildG() {
    if (get().getParentOneChildG() == null) {
      return null;
    }
    return Builders.existing(get().getParentOneChildG());
  }

  public ChildGBuilder parentTwoChildG() {
    if (get().getParentTwoChildG() == null) {
      return null;
    }
    return Builders.existing(get().getParentTwoChildG());
  }

  public ParentG get() {
    return (features.domain.ParentG) super.get();
  }

  @Override
  public ParentGBuilder ensureSaved() {
    doEnsureSaved();
    return (ParentGBuilder) this;
  }

}