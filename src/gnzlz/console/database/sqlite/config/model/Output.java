
package gnzlz.console.database.sqlite.config.model;

import  gnzlz.console.database.sqlite.config.model.base.BaseOutput;
import  gnzlz.console.database.sqlite.config.model.custom.ICOutput;

public class Output extends BaseOutput<Output> implements ICOutput{

    @Override
    public Output modelDB() {
        return this;
    }

}
