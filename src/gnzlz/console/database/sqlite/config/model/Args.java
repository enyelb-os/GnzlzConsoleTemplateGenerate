
package gnzlz.console.database.sqlite.config.model;

import  gnzlz.console.database.sqlite.config.model.base.BaseArgs;
import  gnzlz.console.database.sqlite.config.model.custom.ICArgs;

public class Args extends BaseArgs<Args> implements ICArgs{

    @Override
    public Args modelDB() {
        return this;
    }

}
