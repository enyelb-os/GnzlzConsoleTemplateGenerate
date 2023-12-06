
package tools.gnzlz.console.database.sqlite.config.model;

import tools.gnzlz.console.database.sqlite.config.model.base.BaseArgs;
import tools.gnzlz.console.database.sqlite.config.model.custom.ICArgs;

public class Args extends BaseArgs<Args> implements ICArgs{

    @Override
    public Args modelDB() {
        return this;
    }

}
