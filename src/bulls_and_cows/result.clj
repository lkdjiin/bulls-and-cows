(ns bulls-and-cows.result)

(use '[clojure.set :only [intersection]])

(declare bulls cows)

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

