
package gnzlz.console.database.sqlite.config.model.custom;

import gnzlz.console.database.sqlite.config.model.DatabaseProject;

public interface ICDatabaseProject {

    /*******************************************
	 * @Example
	 * public default <TypeData> nameMethod() {
	 *      model = modelDB();
     *      code ...
	 * }
	 *******************************************/

    public DatabaseProject modelDB();

}
