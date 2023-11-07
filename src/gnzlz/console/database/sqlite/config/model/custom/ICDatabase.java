
package  gnzlz.console.database.sqlite.config.model.custom;

import  gnzlz.console.database.sqlite.config.model.Database;
import gnzlz.console.database.sqlite.config.model.Project;

public interface ICDatabase {

    /*******************************************
	 * @Example
	 * public default <TypeData> nameMethod() {
	 *      model = modelDB();
     *      code ...
	 * }
	 *******************************************/

    public Database modelDB();

	public default Database defaultHash() {
		Database model = modelDB();
		model.hash(String.valueOf(model.hashCode()));
		return model;
	}

}
