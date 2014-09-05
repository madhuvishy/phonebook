phonebook
=========

Command line phonebook tool in Clojure

Usage:

```
lein run help

create <phonebook-name>
add <entry-name> <entry-number> <phonebook-name>
change <entry-name> <entry-number> <phonebook-name>
remove <entry-name> <phonebook-name>
lookup <name-search-string> <phonebook-name>
reverse-lookup <number-search-string> <phonebook-name>

```

Run any of the commands as ``` lein run command <command-name> <args>```

Can also be compiled to standalone jar using

```
lein uberjar
java -jar <jar-name> args

```

Tests - There are not many tests at the moment, but to run them,

```
lein test
```
