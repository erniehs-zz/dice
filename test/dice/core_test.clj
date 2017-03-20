(ns dice.core_test
  (:use clojure.test)
  (:require [dice.core :as core]))

(def ^:private repeat-count 30)

(deftest rolld-n
  (let [rolld #'core/rolld]
     (is (nil? (rolld)))
     (is (nil? (rolld nil)))
     (is (= (rolld 0) 0))
     (is (= (rolld 1) 1))
     (repeatedly repeat-count #(is (<= 1 (rolld 10) 10)))))

(deftest create-die
  (is (= 6 (:sides (core/make-die))))
  (is (= 6 (:sides (core/make-die nil))))
  (is (= 6 (:sides (core/make-die 1))))
  (is (= 6 (:sides (core/make-die -1))))
  (is (= 8 (:sides (core/make-die 8))))
  (is (= 12 (:sides (core/make-die 12)))))

(deftest roll-die
  (let [d8 (core/make-die 8)] 
    (repeatedly repeat-count #(is (<= 1 (core/roll d8) 8)))))

(deftest roll-dice
  (let [d4 (core/make-die 4)
        d6 (core/make-die 6)
        d8 (core/make-die 8) 
        d12 (core/make-die 12)]
    (is (nil? (core/roll)))
    (repeatedly repeat-count #(is (= (count (core/roll d4 d12)) 2)))
    (repeatedly repeat-count #(is (= (count (core/roll d4 d6 d8 d12)) 4)))
    (repeatedly repeat-count #(is (<= 4 (reduce + (core/roll d4 d6 d8 d12)) 30)))))

(deftest rolln-dice
  (let [d6 (core/make-die)]
    (is (nil? (core/rolln)))
    (repeatedly repeat-count #(is (= (count (core/rolln 5 d6)) 5)))))

(deftest discard-lowest-dice
  (let [d6 (core/make-die)]
    (is (nil? (core/discard-lowest)))
    (is (= (frequencies `(1 2 3)) (frequencies (core/discard-lowest `(0 1 2 3)))))
    (is (= (frequencies `(5 5 1 1 3 9 10)) (frequencies (core/discard-lowest `(5 5 1 0 1 3 10 9)))))
    (is (= 3 (count (core/discard-lowest (core/roll d6 d6 d6 d6)))))))
  

