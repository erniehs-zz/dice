(ns dice.core
  (:gen-class))

(defn ^:private rolld
  "rolls a d-sided die, no arg or nil results in nil"
  ([] nil)
  ([d] (if (nil? d) nil (+ (rand-int d) (if (= d 0) 0 1)))))

(defrecord ^:private Die [sides])

(defn make-die
  "makes an n sided die, n > 1 or a d6 is created!"
  ([] (Die. 6))
  ([n] (if (nil? n) (Die. 6) (if (> n 1) (Die. n) (Die. 6))))) 

(defn roll
  "rolls a die, or dice"
  ([] nil)
  ([die] (rolld (:sides die)))
  ([die & more] (map roll (conj more die))))

(defn rolln
  "rolls n die"
  ([] nil)
  ([n die] (if (> n 1) (repeatedly n #(rolld (:sides die))) nil)))

(defn discard-lowest
  "discard the lowest value!"
  ([] nil)
  ([r] (let [x (split-with (partial < (apply min r)) r)] 
         (concat (first x) (drop 1 (last x))))))

(defn sum-rolls
  "sum the rolls"
  ([] nil)
  ([r] (reduce + r)))

