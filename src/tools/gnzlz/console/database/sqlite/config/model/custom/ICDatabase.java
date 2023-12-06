
package tools.gnzlz.console.database.sqlite.config.model.custom;

import tools.gnzlz.console.database.sqlite.config.model.Database;

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
