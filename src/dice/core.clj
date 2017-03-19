(ns dice.core
  (:gen-class))

(defn rolld
  "rolls a d-sided die, no arg or nil results in nil"
  ([] nil)
  ([d] (if (nil? d) nil (+ (rand-int d) (if (= d 0) 0 1)))))

(defrecord Die [sides])

(defn make-die
  "makes an n sided die, n > 1 or a d6 is created!"
  ([] (Die. 6))
  ([n] (if (nil? n) (Die. 6) (if (> n 1) (Die. n) (Die. 6))))) 

;
; I was thinking of using a protocol...
;
(defn roll
  "rolls a die, or dice"
  ([] nil)
  ([die] (rolld (:sides die)))
  ([die & more] (map roll (conj more die))))

(def discard-lowest
  [] nil
  [dice] ())
