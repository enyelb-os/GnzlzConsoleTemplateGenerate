
package gnzlz.console.database.sqlite.config.model;

import  gnzlz.console.database.sqlite.config.model.base.BaseDatabaseProject;
import  gnzlz.console.database.sqlite.config.model.custom.ICDatabaseProject;

public class DatabaseProject extends BaseDatabaseProject<DatabaseProject> implements ICDatabaseProject{

    @Override
    public DatabaseProject modelDB() {
        return this;
    }

}
