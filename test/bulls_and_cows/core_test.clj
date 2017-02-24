(ns bulls-and-cows.core-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.core :refer :all]
            [clojure.string :as s]))

; https://clojure.github.io/clojure/clojure.test-api.html

(deftest test-sequences
  (testing "allowed-chars"
    (is (= allowed-chars ["0" "1" "2" "3" "4" "5" "6" "7" "8" "9"]))))

(deftest test-random-secret
  (testing "its size"
    (is (= 4 (count (random-secret)))))
  (testing "uniqueness of chars"
    (is (true? (apply distinct? (random-secret)))))
  (testing "its chars"
    (is (true? (every? #(.contains allowed-chars %) (random-secret))))))

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
