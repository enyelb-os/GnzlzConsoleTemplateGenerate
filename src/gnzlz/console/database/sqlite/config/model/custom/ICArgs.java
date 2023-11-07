
package  gnzlz.console.database.sqlite.config.model.custom;

import  gnzlz.console.database.sqlite.config.model.Args;
import gnzlz.console.database.sqlite.config.model.Project;

public interface ICArgs {

    /*******************************************
	 * @Example
	 * public default <TypeData> nameMethod() {
	 *      model = modelDB();
     *      code ...
	 * }
	 *******************************************/

    public Args modelDB();

	public default Args defaultHash() {
		Args model = modelDB();
		model.hash(String.valueOf(model.hashCode()));
		return model;
	}

}
