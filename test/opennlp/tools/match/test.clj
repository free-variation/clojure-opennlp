(ns opennlp.tools.match.test
  (:use [clojure.test])
  (:require [opennlp.tools.match :as m]))

(deftest basics
  ; bare value
  (is (= (m/match 1 
                  '?x ?x)
         1))
  ; simplest structural mismatch
  (is (= (m/match 1
                  '(?x) ?x)
         nil))
  ; destructure a cons
  (is (= (m/match '(hello world)
                  '(hello ?who) ?who)
         'world))
  ; destructure nested list
  (is (= (m/match '(this (is (a nested list)))
                  '(_ (_ (_ ?what _))) ?what)
         'nested))
  ; destructure a vector
  (is (= (m/match [1 2 3 4]
                  '[_ ?x ?y] (list ?x ?y)
                  '[_ _ ?x ?y] (list ?x ?y))
         '(3 4))))
