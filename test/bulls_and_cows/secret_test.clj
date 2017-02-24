(ns bulls-and-cows.secret-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.allowed-chars :refer :all]
            [bulls-and-cows.secret :refer :all]))

(deftest test-random-secret
  (testing "its size"
    (is (= 4 (count (random-secret)))))
  (testing "uniqueness of chars"
    (is (true? (apply distinct? (random-secret)))))
  (testing "its chars"
    (is (true? (every? #(.contains allowed-chars %) (random-secret))))))

