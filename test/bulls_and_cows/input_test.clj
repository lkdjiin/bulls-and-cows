(ns bulls-and-cows.input-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.input :refer :all]))

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
