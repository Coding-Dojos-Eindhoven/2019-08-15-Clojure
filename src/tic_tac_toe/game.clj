(ns tic-tac-toe.game
  (:require [tic-tac-toe.utils :refer (print-board)]))

;; First, let's define a 2D array data structure to hold our initial board.
;; This is a vector of vectors in Clojure parlance.
(def empty-board 
  [[:. :. :.]
   [:. :. :.]
   [:. :. :.]])

(comment
  ;; We have a utility to nicely print those boards
  (print-board empty-board))


