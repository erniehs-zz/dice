(ns dice.path
  (:gen-class)
  (:require [dice.core :as core]))

(defn roll-ability-standard 
  "standard ability, roll 4 d6 discarding the lowest"
  ([] (let [d6 (core/make-die)]
        (core/sum-rolls (core/discard-lowest (core/rolln 4 d6))))))

(defn roll-ability-classic
  "classic ability, roll 3 d6"
  ([] (let [d6 (core/make-die)]
        (core/sum-rolls (core/rolln 3 d6)))))

(defn roll-ability-heroic
  "heroic ability rolls, 2 d6 + 6"
  ([] (let [d6 (core/make-die)]
        (+ 6 (core/sum-rolls (core/rolln 2 d6))))))
