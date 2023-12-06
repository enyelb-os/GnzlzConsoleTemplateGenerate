
package tools.gnzlz.console.database.sqlite.config.model.custom;

import tools.gnzlz.console.database.sqlite.config.model.Args;

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
