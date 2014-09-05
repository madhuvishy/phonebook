(ns phonebook.core
  (:gen-class))

(defn exists?
  "Check if phonebook exists"
  [phonebook-name]
  (try 
    (when (slurp phonebook-name) true)
    (catch Exception e false)))

(defn create-phonebook 
  "Create new phonebook"
  [[phonebook-name]]
  (if (exists? phonebook-name)
    "Phonebook with same name already exists!"
    (do
      (spit (str "./" phonebook-name) (pr-str {}))
      (str "Created phonebook " phonebook-name " in the current directory."))))

(defn add-entry
  "Add entry to phonebook"
  [[name number phonebook-name]]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))]
        (if (phonebook name) 
          "Entry already exists"
          (do 
            (spit phonebook-name (pr-str (assoc phonebook name number)))
            "Entry added")))
    "Phonebook does not exist"))

(defn change-entry
  "Change an entry in the phonebook"
  [[name number phonebook-name]]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))]
        (if (phonebook name)
          (do
            (spit phonebook-name (pr-str (assoc phonebook name number)))
            "Entry changed"))
          "Entry doesn't exist")    
    "Phonebook does not exist"))

(defn remove-entry
  "Remove an entry in the phonebook"
  [[name phonebook-name]]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))]
        (if (phonebook name)
          (do
            (spit phonebook-name (pr-str (dissoc phonebook name)))
            "Entry removed"))
          "Entry doesn't exist")    
    "Phonebook does not exist"))

(defn lookup 
  "Lookup an entry by name on a phonebook"
  [[search-str phonebook-name]]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))
          names (keys phonebook)]
      (filter identity 
          (map (fn [name]
             (let [pattern (re-pattern  (str "(?i)" search-str))]
              (if (re-find pattern name) [name (phonebook name)] nil)))
           names)))
    "Phonebook does not exist"))

(defn reverse-lookup
  "Lookup an entry by number on a phonebook"
  [[search-number phonebook-name]]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))
          names (keys phonebook)]
       (filter identity 
          (map (fn [name]
            (if (= (phonebook name) search-number) [name (phonebook name)] nil))
           names)))
    "Phonebook does not exist"))

(defn help
  "Help for command line tool"
  []
  (str "Usage:\n"
       "create <phonebook-name>\n"
       "add <entry-name> <entry-number> <phonebook-name>\n"
       "change <entry-name> <entry-number> <phonebook-name>\n"
       "remove <entry-name> <phonebook-name>\n"
       "lookup <name-search-string> <phonebook-name>\n"
       "reverse-lookup <number-search-string> <phonebook-name>\n"))

(defn -main 
  "Main function"
  [& args]
  (let [command (first args)]
    (println
      (condp = command
      "help" (help)
      "create" (create-phonebook (rest args))
      "add" (add-entry (rest args))
      "change" (change-entry (rest args))
      "remove" (remove-entry (rest args))
      "lookup" (lookup (rest args))
      "reverse-lookup" (reverse-lookup (rest args))))))
