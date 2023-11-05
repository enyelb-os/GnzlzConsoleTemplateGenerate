
package gnzlz.console.database.sqlite.config.model;

import  gnzlz.console.database.sqlite.config.model.base.BaseProjectType;
import  gnzlz.console.database.sqlite.config.model.custom.ICProjectType;

public class ProjectType extends BaseProjectType<ProjectType> implements ICProjectType{

    @Override
    public ProjectType modelDB() {
        return this;
    }

}
