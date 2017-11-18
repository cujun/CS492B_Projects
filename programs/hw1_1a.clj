(use 'nstools.ns)
(ns+ hw1_1a
     (:use [anglican.core :exclude [-main]] 
           [anglican emit runtime stat]) 
     (:gen-class))

(defquery next_birth [curr_birth] 
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
          (observe da curr_birth)
          (= res :twins)))
      (let [res (sample db)]
        (do
          (observe db curr_birth)
          (= res :twins))))))

(defn -main
  [& args]
  (def x (doquery :importance next_birth [:twins]))
  (def y (take 10000 x))

  (defn f [m] (exp (:log-weight m)))
  (defn g [m]
    (if (= (:result m) true) (f m) 0.0))

  (println
    (/ (reduce + (map g y))
       (reduce + (map f y))))
)
