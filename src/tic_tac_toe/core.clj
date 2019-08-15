(ns tic-tac-toe.core
  (:gen-class)
  (:require [tic-tac-toe.game :refer :all]))

(defn -main []
  (println "Welcome to Tic Tac Toe")
  (reduce turn initial-game-state (read-game)))

