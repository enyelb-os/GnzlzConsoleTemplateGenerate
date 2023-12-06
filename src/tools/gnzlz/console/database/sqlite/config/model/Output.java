
package tools.gnzlz.console.database.sqlite.config.model;

import tools.gnzlz.console.database.sqlite.config.model.base.BaseOutput;
import tools.gnzlz.console.database.sqlite.config.model.custom.ICOutput;

public class Output extends BaseOutput<Output> implements ICOutput{

    @Override
    public Output modelDB() {
        return this;
    }

}
