package gnzlz.console;

public class ModelBase {

    public static String typeValue(String type){
        String split[] = type.split(" ");
        type = split == null ? type : split[0];
        String args = split != null && split.length > 1 && !split[1].equals("()")? split[1]: "";

        if(args.equals("UNSIGNED")){
            args = "";
        }

        if(type.equalsIgnoreCase("INT"))
            return "INTEGER" + args;
        if(type.equalsIgnoreCase("BIT"))
            return "BOOLEAN" + args;
        if(type.equalsIgnoreCase("SMALLINTEGER"))
            return "SMALLINT"+ args;
        if(type.equalsIgnoreCase("BIGINTEGER") || type.equalsIgnoreCase("LONG"))
            return "BIGINT" + args;
        if(type.equalsIgnoreCase("NUMERIC"))
            return "NUMBER" + args;
        if(type.equalsIgnoreCase("VARBINARY") || type.equalsIgnoreCase("BINARY"))
            return "STRING.BINARY" + args;
        if(type.equalsIgnoreCase("DATETIME"))
            return "DATE" + args;
        if(type.equalsIgnoreCase("DATE") || type.equalsIgnoreCase("TIMESTAMP"))
            return "DATEONLY" + args;
        if(type.equalsIgnoreCase("TIMESTAMP"))
            return "DATETIME" + args;
        if(type.equalsIgnoreCase("VARCHAR") || type.equalsIgnoreCase("LONGVARCHAR"))
            return "STRING" + args;
        if(type.equalsIgnoreCase("LONGVARCHAR") || type.equalsIgnoreCase("LONGTEXT") || type.equalsIgnoreCase("MEDIUMTEXT"))
            return "TEXT" + args;
        if(type.equalsIgnoreCase("CLOB"))
            return "BLOB" + args;

        return type + args;
    }

    public static String typeDefault(String type, String def){
        String split[] = type.split(" ");
        type = split == null ? type : split[0];

        if(type.equalsIgnoreCase("DATETIME") || type.equalsIgnoreCase("DATE") || type.equalsIgnoreCase("TIMESTAMP") ||
            type.equalsIgnoreCase("TIMESTAMP") || type.equalsIgnoreCase("VARCHAR") || type.equalsIgnoreCase("LONGVARCHAR") ||
            type.equalsIgnoreCase("LONGVARCHAR") || type.equalsIgnoreCase("CLOB"))
            return "'" + def + "'";

        return def;
    }
}
