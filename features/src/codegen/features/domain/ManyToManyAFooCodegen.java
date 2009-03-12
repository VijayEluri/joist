package features.domain;

import features.domain.queries.ManyToManyAFooQueries;
import java.util.ArrayList;
import java.util.List;
import joist.domain.AbstractDomainObject;
import joist.domain.Changed;
import joist.domain.Shim;
import joist.domain.orm.AliasRegistry;
import joist.domain.orm.ForeignKeyListHolder;
import joist.domain.uow.UoW;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotNull;
import org.exigencecorp.util.Copy;

public abstract class ManyToManyAFooCodegen extends AbstractDomainObject {

    protected static ManyToManyAFooAlias alias;
    public static final ManyToManyAFooQueries queries;
    private Integer id = null;
    private String name = null;
    private Integer version = null;
    private ForeignKeyListHolder<ManyToManyAFoo, ManyToManyAFooToBar> manyToManyAFooToBars = new ForeignKeyListHolder<ManyToManyAFoo, ManyToManyAFooToBar>((ManyToManyAFoo) this, ManyToManyAFooToBarCodegen.alias, ManyToManyAFooToBarCodegen.alias.manyToManyAFoo);
    protected Changed changed;

    static {
        alias = new ManyToManyAFooAlias("a");
        AliasRegistry.register(ManyToManyAFoo.class, alias);
        queries = new ManyToManyAFooQueries();
    }

    protected ManyToManyAFooCodegen() {
        this.addExtraRules();
    }

    private void addExtraRules() {
        this.addRule(new NotNull<ManyToManyAFoo>("name", Shims.name));
        this.addRule(new MaxLength<ManyToManyAFoo>("name", 100, Shims.name));
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.getChanged().record("id", this.id, id);
        this.id = id;
        if (UoW.isOpen()) {
            UoW.getIdentityMap().store(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.getChanged().record("name", this.name, name);
        this.name = name;
    }

    public Integer getVersion() {
        return this.version;
    }

    public List<ManyToManyAFooToBar> getManyToManyAFooToBars() {
        return this.manyToManyAFooToBars.get();
    }

    public void addManyToManyAFooToBar(ManyToManyAFooToBar o) {
        o.setManyToManyAFooWithoutPercolation((ManyToManyAFoo) this);
        this.addManyToManyAFooToBarWithoutPercolation(o);
    }

    public void removeManyToManyAFooToBar(ManyToManyAFooToBar o) {
        o.setManyToManyAFooWithoutPercolation(null);
        this.removeManyToManyAFooToBarWithoutPercolation(o);
    }

    protected void addManyToManyAFooToBarWithoutPercolation(ManyToManyAFooToBar o) {
        this.getChanged().record("manyToManyAFooToBars");
        this.manyToManyAFooToBars.add(o);
    }

    protected void removeManyToManyAFooToBarWithoutPercolation(ManyToManyAFooToBar o) {
        this.getChanged().record("manyToManyAFooToBars");
        this.manyToManyAFooToBars.remove(o);
    }

    public List<ManyToManyABar> getManyToManyABars() {
        List<ManyToManyABar> l = new ArrayList<ManyToManyABar>();
        for (ManyToManyAFooToBar o : this.getManyToManyAFooToBars()) {
            l.add(o.getManyToManyABar());
        }
        return l;
    }

    public void addManyToManyABar(ManyToManyABar o) {
        ManyToManyAFooToBar a = new ManyToManyAFooToBar();
        a.setManyToManyAFoo((ManyToManyAFoo) this);
        a.setManyToManyABar(o);
    }

    public void removeManyToManyABar(ManyToManyABar o) {
        for (ManyToManyAFooToBar a : Copy.shallow(this.getManyToManyAFooToBars())) {
            if (a.getManyToManyABar().equals(o)) {
                a.setManyToManyABar(null);
                a.setManyToManyAFoo(null);
                if (UoW.isOpen()) {
                    UoW.delete(a);
                }
            }
        }
    }

    public ManyToManyAFooChanged getChanged() {
        if (this.changed == null) {
            this.changed = new ManyToManyAFooChanged((ManyToManyAFoo) this);
        }
        return (ManyToManyAFooChanged) this.changed;
    }

    public static class Shims {
        public static final Shim<ManyToManyAFoo, Integer> id = new Shim<ManyToManyAFoo, Integer>() {
            public void set(ManyToManyAFoo instance, Integer id) {
                ((ManyToManyAFooCodegen) instance).id = id;
            }
            public Integer get(ManyToManyAFoo instance) {
                return ((ManyToManyAFooCodegen) instance).id;
            }
        };
        public static final Shim<ManyToManyAFoo, String> name = new Shim<ManyToManyAFoo, String>() {
            public void set(ManyToManyAFoo instance, String name) {
                ((ManyToManyAFooCodegen) instance).name = name;
            }
            public String get(ManyToManyAFoo instance) {
                return ((ManyToManyAFooCodegen) instance).name;
            }
        };
        public static final Shim<ManyToManyAFoo, Integer> version = new Shim<ManyToManyAFoo, Integer>() {
            public void set(ManyToManyAFoo instance, Integer version) {
                ((ManyToManyAFooCodegen) instance).version = version;
            }
            public Integer get(ManyToManyAFoo instance) {
                return ((ManyToManyAFooCodegen) instance).version;
            }
        };
    }

    public static class ManyToManyAFooChanged extends joist.domain.AbstractChanged {
        public ManyToManyAFooChanged(ManyToManyAFoo instance) {
            super(instance);
        }
        public boolean hasId() {
            return this.contains("id");
        }
        public Integer getOriginalId() {
            return (java.lang.Integer) this.getOriginal("id");
        }
        public boolean hasName() {
            return this.contains("name");
        }
        public String getOriginalName() {
            return (java.lang.String) this.getOriginal("name");
        }
        public boolean hasVersion() {
            return this.contains("version");
        }
        public Integer getOriginalVersion() {
            return (java.lang.Integer) this.getOriginal("version");
        }
        public boolean hasManyToManyAFooToBars() {
            return this.contains("manyToManyAFooToBars");
        }
    }

}