(ns bulls-and-cows.secret
  (:require [bulls-and-cows.allowed-chars :refer :all]))

(defn random-secret
  "Returns a random sequence of 4 unique allowed-chars"
  []
  (take 4 (shuffle allowed-chars)))

