
package  gnzlz.console.database.sqlite.config.model.custom;

import  gnzlz.console.database.sqlite.config.model.Output;
import gnzlz.console.database.sqlite.config.model.Project;

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
