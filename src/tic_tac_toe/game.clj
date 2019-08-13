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
  (print-board empty-board)

  ;; Building blocks. One thing we'll need to use while playing, is
  ;; updating the board according to the players' moves. We can use
  ;; `assoc-in` to update the data structure defined above:
  (def board0 empty-board)      ; define a var called `board0` that is set to `empty-board`
  (assoc-in board0 [1 1] :X)    ; set the element in row 1 col 1 to :X
  (print-board board0)          ; print the board

  ;; EXERCISE: look at the printed output and try to explain it.

  ;; What happened? The board didn't change! That's because of _immutability_.
  ;; In functional programming, all data is immutable. It cannot change, ever.
  ;; But that doesn't make sense, we need the board to change!

  ;; No worries: instead of modifying the board given to it, `assoc-in` returns
  ;; a _copy_ of the board that contains the desired changes.
  (def board1 (assoc-in empty-board [1 1] :X))
  (print-board board1))

