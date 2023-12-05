
package gnzlz.console.database.sqlite.config;

import gnzlz.console.database.migration.Args;
import gnzlz.console.database.migration.Database;
import gnzlz.console.database.migration.Output;
import gnzlz.console.database.migration.Project;
import org.sqlite.JDBC;
import tools.gnzlz.database.model.DBConfiguration;
import tools.gnzlz.database.model.DBConnection;
import tools.gnzlz.database.properties.PropertiesConnection;
import tools.gnzlz.database.properties.PropertiesMigration;
import tools.gnzlz.database.properties.PropertiesModel;

public class ConfigSQLite extends DBConfiguration {

    public static DBConnection connection = DBConfiguration.configuration(ConfigSQLite.class).connection();

    @Override
    protected void initConnection(PropertiesConnection connection) {
        connection
            .dialect(SQLite).protocol(JDBC.PREFIX).driver(new JDBC())
            .path(System.getProperty("user.dir").concat("/").concat("configtest.db")).name("config");
    }

    @Override
    protected void initMigration(PropertiesMigration migration) {
        migration.add(Project.class);
        migration.add(Database.class);
        migration.add(Args.class);
        migration.add(Output.class);
    }

    @Override
    protected void initModel(PropertiesModel model) {
        model.refresh(false).dateFormat("yyyy-MM-dd HH:mm:ss");
    }
}