
package gnzlz.console.database.sqlite.config.model;

import gnzlz.console.database.sqlite.config.model.base.BaseProject;
import gnzlz.console.database.sqlite.config.model.custom.ICProject;

public class Project extends BaseProject<Project> implements ICProject {

    @Override
    public Project modelDB() {
        return this;
    }

}
