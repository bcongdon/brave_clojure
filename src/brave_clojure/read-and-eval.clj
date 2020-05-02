; Exercise 1
(def l1 (list println "Ben" "Moon"))
(def l2 (quote (println "Ben" "Moon")))
(def l3 '(println "Ben" "Moon"))
(def l4 (read-string "(println \"Ben\" \"Moon\")"))

; Exercise 2
(defn is-mul-div?
  [op]
  (or (= op '*) (= op '/)))

(defn infix
  [[a op1 b op2 c op3 d]]
  (if (is-mul-div? op2)
    (if (is-mul-div? op1)
      (list op3 d (list op2 c (list op1 a b)))
      (list op1 a (list op3 (list op2 b c) d)))
    (if (is-mul-div? op3)
      (list op2 (list op3 c d) (list op1 a b))
      (list op3 (list op2 (list op1 a b) c) d))))
(eval (infix '(1 + 3 * 4 - 5))) ; => 8
