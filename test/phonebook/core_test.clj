(ns phonebook.core-test
  (:require [clojure.test :refer :all]
            [phonebook.core :refer :all]))

(deftest test-create-phonebook
  (testing "Create-new-phonebook"
    (is (= (create-phonebook ["testpb.pb"]) 
           "Created phonebook testpb.pb in the current directory."))
    (is (= (create-phonebook ["testpb.pb"])
           "Phonebook with same name already exists!"))))

(deftest test-add-entry
  (testing "Adding new entry"
    (is (= (add-entry ["Madhu Vishy" "9084940482" "testpb.pb"])
           "Entry added"))
    (is (= (add-entry ["Madhu Vishy" "9084940482" "testpb.pb"])
           "Entry already exists"))))

(deftest test-change-entry
  (testing "Changing an entry"
    (is (= (change-entry ["Madhu Vishy" "9084040482" "testpb.pb"])
           "Entry changed"))
    (is (= (change-entry ["Madhu" "9084940482" "testpb.pb"])
           "Entry already exists"))))
