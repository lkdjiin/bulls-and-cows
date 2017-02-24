(ns bulls-and-cows.output
  (:require [clojure.string :as s]))

(def padding (s/join (repeat 20 " ")))

(defn format-result
  "Returns a presentation string"
  [result]
  (str (:bulls result) "B " (:cows result) "C"))

(defn format-line
  "Returns a presentation string"
  [turn guess result]
  (str padding turn " - " (s/join guess) " - " (format-result result)))
