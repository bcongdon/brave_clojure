; Exercise 1
(defn greeter
  [name]
  (str "Hello " name "! :)"))

(defn combine-3
  [a b c]
  (vector a b c))

(defn combine-3-list
  [a b c]
  (list a b c))

(defn make-person
  [name height]
  (hash-map :name name :height height))

(defn dedup-3
  [a b c]
  (hash-set a b c))

; Exercise 2
(defn plus100
  [x]
  (+ x 100))

; Exercise 3
(defn dec-maker
  [delta]
  #(- % delta))

; Exercise 4
(defn mapset
  [func items]
  (loop [remaining items
         final #{}]
    (if (empty? remaining)
      final
      (let [[part & remaining] remaining]
        (recur remaining
               (conj final (func part)))))))

(defn mapset-easy
  [func items]
  (set (map func items)))

; Exercise 5 and 6
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn make-part
  [part suffix]
  {:name (clojure.string/replace (:name part) #"^left-" (str "extra-" suffix "-"))
   :size (:size part)})

(defn matching-parts
  [part num_parts]
  (map #(make-part part (str %)) (range 1 (inc num_parts))))

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts num-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (conj (set (matching-parts part num-parts)) part)))
          []
          asym-body-parts))
