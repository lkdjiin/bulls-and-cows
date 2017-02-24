(ns bulls-and-cows.core
  (:require [clojure.string :as s]
            [bulls-and-cows.result :as result]
            [bulls-and-cows.secret :refer :all]
            [bulls-and-cows.allowed-chars :refer :all])
  (:gen-class))

(declare format-result padding allowed-chars? distinct-chars? valid-size?)

; Output part

(defn format-line
  "Returns a presentation string"
  [turn guess result]
  (str padding turn " - " (s/join guess) " - " (format-result result)))

(def padding (s/join (repeat 20 " ")))

(defn format-result
  "Returns a presentation string"
  [result]
  (str (:bulls result) "B " (:cows result) "C"))

; Input part

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

; Game logic part

(defn do-turn
  "This is a single turn in a game"
  []
  (print "Your guess? ")
  (flush)
  (let [current (get-guess (read-line))]
    (if (guess-valid? current)
      current
      (do-turn))))

(defn game
  "This is a complete game"
  []
  (let [secret (random-secret)]
    (println secret)
    (loop [turn 1]
      (let [guess (do-turn)]
        (println (format-line turn guess (result/bulls-and-cows secret guess)))
        (if (= guess secret)
          (println "You win!")
          (recur (inc turn)))))))

(defn -main
  [& args]
  (loop []
    (game)
    (println "Play again? y/n")
    (if (= "y" (read-line))
      (recur)
      (println "Bye!"))))
