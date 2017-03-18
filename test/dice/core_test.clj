(ns dice.core_test
  (:use clojure.test)
  (:require [dice.core :as core]))

(deftest create-die
  (is (= 8 (:sides (core/make-die 8)))))

(deftest roll-die
  (let [d8 (core/make-die 8)] 
    (is (<= 1 (core/roll d8) 8))))
