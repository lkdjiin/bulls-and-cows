(ns bulls-and-cows.output-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.output :refer :all]))

(deftest test-output
  (testing "format result"
    (is (= "0B 2C" (format-result {:bulls 0 :cows 2})))
    (is (= "1B 3C" (format-result {:bulls 1 :cows 3}))))
  (testing "format line"
    (is (=
           "                    7 - 0123 - 2B 1C"
           (format-line 7 ["0" "1" "2" "3"] {:bulls 2 :cows 1})))))

