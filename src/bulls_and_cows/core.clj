(ns bulls-and-cows.core
  (:require [clojure.string :as s]
            [bulls-and-cows.result :as result]
            [bulls-and-cows.output :as out]
            [bulls-and-cows.input :as inp]
            [bulls-and-cows.secret :refer :all]
            [bulls-and-cows.allowed-chars :refer :all])
  (:gen-class))

(declare game do-turn)

(def debug-on true)
(def debug-off false)

(defn -main
  "If called with any args, run in debug-mode ON, that is print the secret
  at the beginning of a game."
  [& args]
  (loop []
    (if args
      (game debug-on)
      (game debug-off))
    (println "Play again? y/n")
    (if (= "y" (read-line))
      (recur)
      (println "Bye!"))))

(defn game
  "This is a complete game"
  [debug-mode]
  (let [secret (random-secret)]
    (when debug-mode (println secret))
    (loop [turn 1]
      (let [guess (do-turn)]
        (println (out/format-line turn guess (result/bulls-and-cows secret guess)))
        (if (= guess secret)
          (println "You win!")
          (recur (inc turn)))))))

(defn do-turn
  "This is a single turn in a game"
  []
  (print "Your guess? ")
  (flush)
  (let [current (inp/get-guess (read-line))]
    (if (inp/guess-valid? current)
      current
      (do-turn))))
