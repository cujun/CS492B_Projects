(use 'nstools.ns)
(ns+ hw1_1c
  (:like anglican-user.worksheet))

(defquery reflect_measure [m1 m2 m3 m4 m5 m6 m7]
  (let [mean (sample (normal 0 50))
        sd1 (sample (uniform-continuous 0 25))
        sd2 (sample (uniform-continuous 0 25))
        sd3 (sample (uniform-continuous 0 25))
        sd4 (sample (uniform-continuous 0 25))
        sd5 (sample (uniform-continuous 0 25))
        sd6 (sample (uniform-continuous 0 25))
        sd7 (sample (uniform-continuous 0 25))]
    (observe (normal mean sd1) m1)
    (observe (normal mean sd2) m2)
    (observe (normal mean sd3) m3)
    (observe (normal mean sd4) m4)
    (observe (normal mean sd5) m5)
    (observe (normal mean sd6) m6)
    (observe (normal mean sd7) m7)
    [mean, sd1, sd2, sd3, sd4, sd5, sd6, sd7]))


(def res_mu [])
(def res_sd1 [])
(def res_sd2 [])
(def res_sd3 [])
(def res_sd4 [])
(def res_sd5 [])
(def res_sd6 [])
(def res_sd7 [])

(loop [i 0]
  (def x (doquery :importance reflect_measure [-27.020 3.570 8.191 9.898 9.603 9.945 10.056]))
  (def y (take 1000 x))


  (defn f [m] (exp (:log-weight m)))
  (defn g [m]
    (* (get (:result m) 0) (exp (:log-weight m))))
  (defn g1 [m]
    (* (get (:result m) 1) (exp (:log-weight m))))
  (defn g2 [m]
    (* (get (:result m) 2) (exp (:log-weight m))))
  (defn g3 [m]
    (* (get (:result m) 3) (exp (:log-weight m))))
  (defn g4 [m]
    (* (get (:result m) 4) (exp (:log-weight m))))
  (defn g5 [m]
    (* (get (:result m) 5) (exp (:log-weight m))))
  (defn g6 [m]
    (* (get (:result m) 6) (exp (:log-weight m))))
  (defn g7 [m]
    (* (get (:result m) 7) (exp (:log-weight m))))

  (if (< i 5000)
    (do (def res_mu (conj res_mu (/ (reduce + (map g y))
                                    (reduce + (map f y)))))
      (def res_sd1 (conj res_sd1 (/ (reduce + (map g1 y))
                                    (reduce + (map f y)))))
      (def res_sd2 (conj res_sd2 (/ (reduce + (map g2 y))
                                    (reduce + (map f y)))))
      (def res_sd3 (conj res_sd3 (/ (reduce + (map g3 y))
                                    (reduce + (map f y)))))
      (def res_sd4 (conj res_sd4 (/ (reduce + (map g4 y))
                                    (reduce + (map f y)))))
      (def res_sd5 (conj res_sd5 (/ (reduce + (map g5 y))
                                    (reduce + (map f y)))))
      (def res_sd6 (conj res_sd6 (/ (reduce + (map g6 y))
                                    (reduce + (map f y)))))
      (def res_sd7 (conj res_sd7 (/ (reduce + (map g7 y))
                                    (reduce + (map f y)))))
      (recur (inc i)))
    ()))
(plot/histogram res_mu)
(plot/histogram res_sd1)
(plot/histogram res_sd2)
(plot/histogram res_sd3)
(plot/histogram res_sd4)
(plot/histogram res_sd5)
(plot/histogram res_sd6)
(plot/histogram res_sd7)
