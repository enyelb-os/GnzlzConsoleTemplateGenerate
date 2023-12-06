
package tools.gnzlz.console.database.sqlite.config.model;

import tools.gnzlz.console.database.sqlite.config.model.base.BaseDatabase;
import tools.gnzlz.console.database.sqlite.config.model.custom.ICDatabase;

public class Database extends BaseDatabase<Database> implements ICDatabase {

    @Override
    public Database modelDB() {
        return this;
    }

}
