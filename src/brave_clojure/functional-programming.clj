; Exercise 1
(def attr #(comp % :attributes))

; Exercise 2
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

(defn three-comp
  [f g h]
  (fn [& args]
    (f (g (apply h args)))))

(defn my-comp
    ; Base cases
  ([] identity)
  ([f] f)
    ; Recursive case
  ([f & rest]
   (fn [& args]
     (f (apply (apply my-comp rest) args)))))

; Exercise 3
(defn my-assoc-in
  [m [k & ks] v]
  (let
   [curr-val (or (get m k) {})
    new-m (if (associative? curr-val) curr-val {})]
    (cond
      (nil? k) m
      (empty? ks) (assoc m k v)
      :else (assoc m k (my-assoc-in new-m ks v)))))

; Exercise 4
(def people [{:name "Alice" :age 23} {:name "Bob" :age 24}])
(def aged (update-in people [0 :age] + 10))

; Exercise 5
(defn my-update-in
  [m [k & ks] fn & args]
  (let
   [curr-val (or (get m k) {})
    new-map (if (associative? curr-val) curr-val {})]
    (cond
      (nil? k) m
      (empty? ks) (assoc m k (apply fn curr-val args))
      :else (assoc m k (apply my-update-in new-map ks fn args)))))
