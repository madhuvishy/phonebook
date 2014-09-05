(ns phonebook.core
  (:require [clojure.java.shell :as shell])
  (:gen-class))

(defn create-phonebook 
  "Create new phonebook"
  [phonebook-name]
  (do
    (shell/sh "touch" (str "./" phonebook-name))
    (str "Created phonebook " phonebook-name " in the current directory.")))

(defn exists?
  "Check if phonebook exists"
  [phonebook-name]
  (try 
    (when (slurp phonebook-name) true)
    (catch Exception e false)))

(defn add-to-phonebook
  "Add entry to phonebook"
  [name number phonebook-name]
  (if (exists? phonebook-name)
    (let [phonebook (read-string (slurp phonebook-name))]
        (if (phonebook name)
          "Entry already exists"
          (spit phonebook-name (pr-str (assoc phonebook name number)))))))
