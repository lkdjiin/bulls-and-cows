(ns bulls-and-cows.core-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.core :refer :all]
            [bulls-and-cows.allowed-chars :refer :all]
            [clojure.string :as s]))

; https://clojure.github.io/clojure/clojure.test-api.html

(deftest test-sequences
  (testing "allowed-chars"
    (is (= allowed-chars ["0" "1" "2" "3" "4" "5" "6" "7" "8" "9"]))))
