package org.exigencecorp.domainobjects.codegen.tasks;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.exigencecorp.domainobjects.codegen.Codegen;
import org.exigencecorp.domainobjects.codegen.CodegenConfig;
import org.exigencecorp.domainobjects.migrations.DatabaseBootstrapper;
import org.exigencecorp.domainobjects.migrations.Migrater;
import org.exigencecorp.domainobjects.migrations.MigraterConfig;
import org.exigencecorp.domainobjects.migrations.PermissionFixer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DomainObjectBuilder {

    public String host = "localhost";
    public String databaseName;
    public String databaseAppUsername;
    public String databaseAppPassword;
    public String databaseSaUsername;
    public String databaseSaPassword;
    public CodegenConfig codegenConfig = new CodegenConfig();
    public MigraterConfig migraterConfig = new MigraterConfig();
    private final Map<String, DataSource> dss = new HashMap<String, DataSource>();

    public DomainObjectBuilder(String projectName) {
        this.databaseName = projectName;
        this.databaseAppUsername = projectName + "_role";
        this.databaseAppPassword = projectName + "_role";
        this.databaseSaUsername = "sa";
        this.databaseSaPassword = "";
        this.migraterConfig.setProjectNameForDefaults(projectName);
        this.codegenConfig.setProjectNameForDefaults(projectName);
    }

    public void createDatabase() {
        new DatabaseBootstrapper(//
            this.getDataSourceForSystemTableAsSaUser(),
            this.databaseName,
            this.databaseAppUsername,
            this.databaseAppPassword).dropAndCreate();
    }

    public void migrateDatabase() {
        new Migrater(this.getDataSourceForAppTableAsSaUser(), this.migraterConfig).migrate();
    }

    public void fixPermissions() {
        PermissionFixer pf = new PermissionFixer(this.getDataSourceForAppTableAsSaUser());
        pf.setOwnerOfAllTablesTo(this.databaseSaUsername);
        pf.setOwnerOfAllSequencesTo(this.databaseAppUsername);
        pf.grantAllOnAllTablesTo(this.databaseAppUsername);
        pf.grantAllOnAllSequencesTo(this.databaseAppUsername);
    }

    public void codegen() {
        new Codegen(this.getDataSourceForAppTableAsSaUser(), this.codegenConfig).generate();
    }

    private DataSource getDataSourceForAppTableAsSaUser() {
        return this.getDs(this.host, this.databaseName, this.databaseSaUsername, this.databaseSaPassword);
    }

    private DataSource getDataSourceForSystemTableAsSaUser() {
        return this.getDs(this.host, "postgres", this.databaseSaUsername, this.databaseSaPassword);
    }

    private DataSource getDs(String host, String databaseName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String key = host + "." + databaseName + "." + username + "." + password;
        if (!this.dss.containsKey(key)) {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setJdbcUrl("jdbc:postgresql://" + host + "/" + databaseName);
            ds.setUser(username);
            ds.setPassword(password);
            ds.setMaxPoolSize(3);
            ds.setInitialPoolSize(1);
            this.dss.put(key, ds);
        }

        return this.dss.get(key);
    }

}
