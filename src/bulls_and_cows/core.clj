(ns bulls-and-cows.core
  (:require [clojure.string :as s])
  (:gen-class))

(use '[clojure.set :only [intersection]])

(declare bulls cows format-result padding allowed-chars? distinct-chars?
         valid-size?)

(def allowed-chars ["0" "1" "2" "3" "4" "5" "6" "7" "8" "9"])

; Secret part

(defn random-secret
  "Returns a random sequence of 4 unique allowed-chars"
  []
  (take 4 (shuffle allowed-chars)))

; Computing result part

(defn bulls-and-cows
  "Returns number of bulls and number of cows"
  [secret guess]
  {:bulls (bulls secret guess) :cows (cows secret guess)})

(defn bulls
  "Returns the number of bulls (good char/good place)"
  [secret guess]
  (loop [counter 0, a secret, b guess]
    (if (empty? a)
      counter
      (recur
        (if (= (first a) (first b))
          (inc counter)
          counter)
        (rest a)
        (rest b)))))

(defn cows
  "Returns the number of cows (good char/bad place)"
  [secret guess]
  (- (count (intersection (set secret) (set guess))) (bulls secret guess)))

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
        (println (format-line turn guess (bulls-and-cows secret guess)))
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
