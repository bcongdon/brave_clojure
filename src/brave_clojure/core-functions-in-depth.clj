
; Exersize 1
(def filename "src/brave_clojure/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def names
    (map :name (glitter-filter 3 (mapify (parse (slurp filename))))))


; Exercise 2
(defn my-append
    [suspect all-suspects]
    (reduce conj all-suspects [suspect]))

; Exercise 3
(defn validate
    [keys record]
    (def values (map #(get record %) keys))
    (not (some nil? values)))

(def validate-vamp (partial validate vamp-keys))

; Exercise 4
(defn to-csv
    [records]
    (def rows (map #(clojure.string/join "," (map second %)) records))
    (clojure.string/join "\n" (into rows [""])))
