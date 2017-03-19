(ns dice.core_test
  (:use clojure.test)
  (:require [dice.core :as core]))

(def repeat-count 30)

(deftest rolld-n
  (is (nil? (core/rolld)))
  (is (nil? (core/rolld nil)))
  (is (= (core/rolld 0) 0))
  (is (= (core/rolld 1) 1))
  (repeatedly repeat-count (is (<= 1 (core/rolld 10) 10))))

(deftest create-die
  (is (= 6 (:sides (core/make-die))))
  (is (= 6 (:sides (core/make-die nil))))
  (is (= 6 (:sides (core/make-die 1))))
  (is (= 6 (:sides (core/make-die -1))))
  (is (= 8 (:sides (core/make-die 8))))
  (is (= 12 (:sides (core/make-die 12)))))

(deftest roll-die
  (let [d8 (core/make-die 8)] 
    (repeatedly repeat-count (is (<= 1 (core/roll d8) 8)))))

(deftest roll-dice
  (let [d4 (core/make-die 4)
        d6 (core/make-die 6)
        d8 (core/make-die 8) 
        d12 (core/make-die 12)]
    (is (nil? (core/roll)))
    (repeatedly repeat-count (is (= (count (core/roll d4 d12)) 2)))
    (repeatedly repeat-count (is (= (count (core/roll d4 d6 d8 d12)) 4)))
    (repeatedly repeat-count (is (<= 4 (reduce + (core/roll d4 d6 d8 d12)) 30)))))
