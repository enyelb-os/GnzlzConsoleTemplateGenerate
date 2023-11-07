
package  gnzlz.console.database.sqlite.config.model.custom;

import  gnzlz.console.database.sqlite.config.model.Project;

public interface ICProject {

    /*******************************************
	 * @Example
	 * public default <TypeData> nameMethod() {
	 *      model = modelDB();
     *      code ...
	 * }
	 *******************************************/

    public Project modelDB();

	public default Project defaultHash() {
		Project model = modelDB();
		model.hash(String.valueOf(model.hashCode()));
		return model;
	}

}
