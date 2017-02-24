(ns bulls-and-cows.core-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.core :refer :all]
            [bulls-and-cows.allowed-chars :refer :all]
            [clojure.string :as s]))

; https://clojure.github.io/clojure/clojure.test-api.html

(deftest test-sequences
  (testing "allowed-chars"
    (is (= allowed-chars ["0" "1" "2" "3" "4" "5" "6" "7" "8" "9"]))))

(deftest test-output
  (testing "format result"
    (is (= "0B 2C" (format-result {:bulls 0 :cows 2})))
    (is (= "1B 3C" (format-result {:bulls 1 :cows 3}))))
  (testing "format line"
    (is (=
           "                    7 - 0123 - 2B 1C"
           (format-line 7 ["0" "1" "2" "3"] {:bulls 2 :cows 1})))))

(deftest test-input-validity
  (testing "it's a vector"
    (let [expected ["0" "1" "2" "3"]]
      (is (= expected (get-guess "0123")))
      (is (= expected (get-guess "  0123  ")))
      (is (= expected (get-guess " 0 1 2 3 ")))))
  (testing "validation"
    (is (true? (guess-valid? (get-guess "  0 1 2 3  "))))
    (is (false? (guess-valid? (get-guess "  0 1 2 3 4 "))))
    (is (false? (guess-valid? (get-guess "  0 1 2   ")))))
  (testing "uniqueness of chars"
    (is (true? (guess-valid? (get-guess "0123"))))
    (is (false? (guess-valid? (get-guess "0023")))))
  (testing "allowed chars"
    (is (true? (guess-valid? (get-guess "0123"))))
    (is (false? (guess-valid? (get-guess "012A"))))))
