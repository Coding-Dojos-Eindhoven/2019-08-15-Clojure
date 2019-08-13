(ns tic-tac-toe.utils
  (:require [clojure.string :as str]))

(defn- row->str [row]
  (apply str (map name row)))
  
(defn board->str [board]
  (str
    "\n"
    (str/join "\n" (map row->str board))))

(defn print-board [board]
  (println (board->str board)))

(comment 
  (def sample-board 
    [[:X :. :O]
     [:. :X :.]
     [:. :. :O]])
  
  (print-board sample-board))

