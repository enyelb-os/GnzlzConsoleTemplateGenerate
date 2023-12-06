
package tools.gnzlz.console.database.sqlite.config.model.custom;

import tools.gnzlz.console.database.sqlite.config.model.Output;

public interface ICOutput {

    /*******************************************
	 * @Example
	 * public default <TypeData> nameMethod() {
	 *      model = modelDB();
     *      code ...
	 * }
	 *******************************************/

    public Output modelDB();

	public default Output defaultHash() {
		Output model = modelDB();
		model.hash(String.valueOf(model.hashCode()));
		return model;
	}

}
