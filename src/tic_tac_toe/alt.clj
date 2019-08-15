(ns tic-tac-toe.alt
  (:require [tic-tac-toe.utils :refer [print-board]]))
            

(def initial-game-state
  {:rows [[:. :. :.] [:. :. :.] [:. :. :.]]
   :cols [[:. :. :.] [:. :. :.] [:. :. :.]]
   :fwd-diag [:. :. :.]
   :back-diag [:. :. :.]
   :next-player :X})

(defn other-player [player] 
  (if (= :X player)
    :O
    :X))

(defn update-state [game-state [row col]]
  (let [player (:next-player game-state)]
    (-> game-state
        (assoc-in [:rows row col] player)
        (assoc-in [:cols col row] player)
        (cond-> (= row col) 
                (assoc-in [:fwd-diag row] player))
        (cond-> (= 2 (+ row col))
                (assoc-in [:back-diag row] player))
        (update-in [:next-player] other-player))))

(defn three-in-a-row [[a b c]]
  (when (and (= a b c)
             (not (= :. a)))
    a))

(defn add-index-to-winner [idx winner]
  (when winner {:who winner :index idx}))

(defn index-of-three-in-a-row [rows-or-cols]
  (let [maybe-winners (map three-in-a-row rows-or-cols)]
    (first (remove nil? (map-indexed 
                          add-index-to-winner 
                          maybe-winners))))) 

(comment 
  (def rows-or-cols [[:O :. :.] [:X :X :X] [:. :. :.]]))
    
(defn has-winner [game-state]
  (let [row-winner (index-of-three-in-a-row (:rows game-state))
        col-winner (index-of-three-in-a-row (:cols game-state))
        fwd-winner (three-in-a-row (:fwd-diag game-state))
        back-winner (three-in-a-row (:back-diag game-state))]
    (cond 
      row-winner (merge row-winner {:why :row})
      col-winner (merge col-winner {:why :col})
      fwd-winner {:who fwd-winner
                  :why :fwd-diag}
      back-winner {:who back-winner
                   :why :back-diag})))
      
(comment 
  (update-state initial-game-state [2 1])
  (update-state initial-game-state [0 0])
  (update-state initial-game-state [0 2])
  (update-state initial-game-state [2 0])
  (update-state initial-game-state [1 1])
  
  (def game-state
        {:rows [[:O :. :.] [:O :X :X] [:O :. :.]]
         :cols [[:O :O :O] [:. :. :.] [:. :. :.]]
         :fwd-diag [:O :O :O]
         :back-diag [:. :. :.]
         :next-player :O})
  (has-winner game-state))

(defn turn [game-state move]
  (let [updated-state (update-state game-state move)]
    (print-board (:rows updated-state))
    updated-state))

(defn winner-info->str [winner]
  (str "Player " (name (:who winner)) " won!\n  (reason: "
    (case (:why winner)
      :back-diag (str "diagonal from top left to bottom right")
      :fwd-diag (str "diagonal from top right to bottom left")
      :row (str "row " (:index winner))
      :col (str "cols " (:index winner)))
    ")"))

(defn print-winner-info [winner]
  (println (winner-info->str winner)))

(defn turn-with-winner-check [state move]
  (let [new-state (turn state move)
        winner (has-winner new-state)]
    (if winner
      (do
        (print-winner-info winner)
        (reduced winner))
      new-state)))

;; bit of duplication from `game.clj`, should be extracted to separate ns of course:
(defn read-turn []
  (println "Where?")
  (map #(Integer. %) (rest (re-find #"([0-2]+) ([0-2]+)" (read-line)))))
(defn read-game [] (repeatedly 9 read-turn))

(defn play-game []
  (reduce
    turn-with-winner-check
    initial-game-state
    (read-game)))


