
package gnzlz.console.database.sqlite.config;

import gnzlz.console.database.migration.Database;
import gnzlz.console.database.migration.DatabaseProject;
import gnzlz.console.database.migration.Project;
import gnzlz.console.database.migration.ProjectType;
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
                .path(System.getProperty("user.dir").concat("/")).name("config.db");
    }

    @Override
    protected void initMigration(PropertiesMigration migration) {
        migration.add(ProjectType.class);
        migration.add(Project.class);
        migration.add(Database.class);
        migration.add(DatabaseProject.class);
    }

    @Override
    protected void initModel(PropertiesModel model) {
        model.refresh(false).dateFormat("yyyy-MM-dd HH:mm:ss");
    }
}