module GnzlzConsoleTemplateGenerate {

    opens gnzlz.console.database.sqlite.config;
    opens gnzlz.console.database.migration;
    opens gnzlz.console.file.json.project.data;

    requires com.google.gson;
    requires sqlite.jdbc;

    requires tools.gnzlz.database;
    requires tools.gnzlz.command;
    requires tools.gnzlz.filetemplate;
}