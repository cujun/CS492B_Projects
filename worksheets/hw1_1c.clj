;; gorilla-repl.fileformat = 1

;; @@
(use 'nstools.ns)
(ns+ hw1_1b
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

(def x (doquery :importance reflect_measure [-27.020 3.570 8.191 9.898 9.603 9.945 10.056]))
(def y (take 10000 x))


(defn f [m] (exp (:log-weight m)))
(defn g [m]
  (if (= (:result m) true) (f m) 0.0))

(println
  (/ (reduce + (map g y))
     (reduce + (map f y))))
;; @@
;; ->
;;; 0.0
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@

;; @@
