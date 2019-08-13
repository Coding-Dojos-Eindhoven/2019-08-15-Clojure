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
  (print-board board1)

  ;; So far, we've been using `def`, which creates global variables in the
  ;; current namespace. That's fine for a constant like `empty-board` above,
  ;; but we should not use this for more temporary things like `board1`.
  ;; Instead, we can use `let` to create what is called _local bindings_:
  (let [board1 (assoc-in empty-board [1 1] :X)]
    (print-board board1))

  ;; It looks like we're going to do this updating of boards more then once,
  ;; so let's define a function for that.
  (defn update-board [board row col player]
    (assoc-in board [row col] player))
  (let [board1 (update-board empty-board 1 1 :X)]
    (print-board board1)))

;; Great. Now let's think a bit about our game state. So far, we've talked
;; about the board, but we will also need to keep track of whose turn it is.
;; Let's define a hash map that holds our initial game state.
(def initial-game-state
  {:board empty-board
   :next-player :X})

;; When taking turns, it is convenient to have a function that tells us
;; who the next player is, given the current player.
;;
;; EXERCISE: Define a function `other-player` that takes a player as input
;;           and returns the other player. So :X gives :O and :O gives :X.
(defn other-player [player]
  (if (= :X player)
    :O
    :X))

(comment
  (= :O (other-player :X))
  (= :X (other-player :O))

  ;; Now we can create a function `turn` that takes a row and a column as
  ;; input, and updates the game state.
  ;;
  ;; EXERCISE: Fill in the blanks:
  (defn turn [game-state row col]
    (let [current-player _
          updated-board _]
      (print-board updated-board)
      {:board updated-board
       :next-player _}))
  (defn turn [game-state row col]
    (let [current-player (:next-player game-state)
          updated-board (update-board (:board game-state) row col current-player)]
      (print-board updated-board)
      {:board updated-board
       :next-player (other-player current-player)}))
  (turn initial-game-state 1 1)
 
  ;; EXERCISE: Using everything we have defined so far, without
  ;;           introducing anything new, play a couple of games
  ;;           with somebody else in the REPL. If you must, it's
  ;;           OK to use `def` for this.
  ;; REFLECTION: How did you do it? How did you keep track of state?

  ;; Here's the game I played against myself. It was a tie!
  (let [state1 (turn initial-game-state 1 1)
        state2 (turn state1 0 0)
        state3 (turn state2 2 0)
        state4 (turn state3 0 2)
        state5 (turn state4 0 1)
        state6 (turn state5 2 1)
        state7 (turn state6 1 0)
        state8 (turn state7 1 2)
        state9 (turn state8 2 2)]
    state9)

  ;; But we're not really interested in all those intermediate state, are we?
  ;; We could have written the same game like this:
  (turn (turn (turn (turn (turn (turn (turn (turn (turn initial-game-state
                                                    1 1)
                                              0 0)
                                        2 0)
                                  0 2)
                            0 1)
                      2 1)
                1 0)
          1 2)
    2 2))

