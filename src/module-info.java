module GnzlzConsoleTemplateGenerate {

    opens gnzlz.console.database.sqlite.config;
    opens gnzlz.console.database.sqlite.config.model;
    opens gnzlz.console.database.migration;
    opens gnzlz.console.file.json.project.data;
    opens gnzlz.console;

    requires com.google.gson;
    requires org.xerial.sqlitejdbc;
    requires mysql.connector.j;

    requires tools.gnzlz.database;
    requires tools.gnzlz.command;
    requires tools.gnzlz.filetemplate;
}