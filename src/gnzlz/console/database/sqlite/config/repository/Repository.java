package gnzlz.console.database.sqlite.config.repository;

import gnzlz.console.database.sqlite.config.repository.data.Hash;

public class Repository {

    /**
     * parseHash
     * @param id id
     */
    public static Hash parseHash(String id) {
        if (!id.isBlank()) {
            var data = id.split(":");
            if (data.length == 2) {
                return new Hash(data[0], data[1]);
            }
        }
        return new Hash(id, id);
    }
}
