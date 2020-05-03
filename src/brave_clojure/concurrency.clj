(def search-engines
  {:google "https://www.google.com/search?q%3D"
   :bing "https://www.bing.com/search?q%3D"})

; Exercise 1
(defn search-1
  [query]
  (map deref (map (fn [engine]
                    (future (slurp (str engine query))))
                  (vals search-engines))))

; Exercise 2
(defn search-2
  [query engines]
  (map deref (map (fn [engine]
                    (future (slurp (str engine query))))
                  (vals engines))))

; Exercise 3
(defn extract-urls
  [html]
  (re-seq #"https?://[^\"]*" html))

(defn all-urls
  [query engines]
  (let [results (search-2 query engines)]
    (mapcat extract-urls results)))
