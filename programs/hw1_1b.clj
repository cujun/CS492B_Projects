(use 'nstools.ns)
(ns+ hw1_1b
     (:use [anglican.core :exclude [-main]] 
           [anglican emit runtime stat]) 
     (:gen-class))

(defquery get_species [first_birth second_birth]
  (let [species (sample (categorical 
                          {:A (/ 1 2),
                           :B (/ 1 2)}))
        da (categorical
             {:single (/ 9 10),
              :twins (/ 1 10)})
        db (categorical
             {:single (/ 8 10),
              :twins (/ 2 10)})]
    (if (= species :A)
      (let [res (sample da)]
        (do
          (observe da first_birth)
          (observe da second_birth)))
      (let [res (sample db)]
        (do
          (observe db first_birth)
          (observe db second_birth))))
    (= species :A)))

(defn -main
  [& args]
  (def x (doquery :importance get_species [:twins :single]))
  (def y (take 10000 x))

  (defn f [m] (exp (:log-weight m)))
  (defn g [m]
    (if (= (:result m) true) (f m) 0.0))

  (println
    (/ (reduce + (map g y))
       (reduce + (map f y))))
)
