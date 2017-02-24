(ns bulls-and-cows.input
  (:require [clojure.string :as s]
            [bulls-and-cows.allowed-chars :refer :all]))

(declare allowed-chars? distinct-chars? valid-size?)

(defn get-guess
  [raw-input]
  (s/split (s/replace raw-input #" " "") #""))

(defn guess-valid?
  "Returns true if the guess entry is valid"
  [guess]
  (and (allowed-chars? guess) (distinct-chars? guess) (valid-size? guess)))

(defn allowed-chars?
  [guess]
  (every? #(.contains allowed-chars %) guess))

(defn distinct-chars?
  [guess]
  (apply distinct? guess))

(defn valid-size?
  [guess]
  (= 4 (count guess)))
