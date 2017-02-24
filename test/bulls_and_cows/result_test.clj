(ns bulls-and-cows.result-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.result :refer :all]))

(deftest test-number-of-bulls
  (testing "no bulls"
    (is (= 0 (bulls ["0" "1" "2" "3"] ["4" "5" "6" "7"]))))
  (testing "1 bull"
    (is (= 1 (bulls ["0" "1" "2" "3"] ["0" "2" "3" "1"]))))
  (testing "2 bulls"
    (is (= 2 (bulls ["0" "1" "2" "3"] ["0" "3" "2" "1"]))))
  (testing "4 bulls"
    (is (= 4 (bulls ["0" "1" "2" "3"] ["0" "1" "2" "3"])))))

(deftest test-number-of-cows
  (testing "no goods"
    (is (= 0 (cows ["0" "1" "2" "3"] ["4" "5" "6" "7"])))
    (is (= 0 (cows ["0" "1" "2" "3"] ["0" "1" "2" "3"]))))
  (testing "3 goods"
    (is (= 3 (cows ["0" "1" "2" "3"] ["0" "2" "3" "1"])))))

(deftest test-number-of-bulls-and-cows
  (testing "0 bulls 0 cows"
    (is (=
          {:bulls 0 :cows 0}
          (bulls-and-cows ["0" "1" "2" "3"] ["4" "5" "6" "7"]))))
  (testing "1 bulls 2 cows"
    (is (=
          {:bulls 1 :cows 2}
          (bulls-and-cows ["0" "1" "2" "3"] ["0" "2" "1" "7"])))))

