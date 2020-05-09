; Exercise 1
(def counter (atom 0))
(dotimes [_ 5] (swap! counter inc))
(println @counter)

; Exercise 2
(def get-quote #(slurp "https://www.braveclojure.com/random-quote"))

(defn count-words
  [quote]
  (frequencies (clojure.string/split quote #"\s+")))

(defn quote-word-count
  [n]
  (let [word-counts (atom {})]
    (dorun (pmap #(let [quote @%]
                    (swap! word-counts
                           (fn [current-word-counts]
                             (merge-with + current-word-counts (count-words quote)))))
                 (repeatedly n #(future (get-quote)))))
    @word-counts))

; Exercise 3
(def max-hp 40)
(def hp-per-potion 10)

(defn player-validator
  [{:keys [hp potions]}]
  (and (>= hp 0)
       (<= hp max-hp)
       (>= potions 0)))

(def player1 (ref {:hp 40 :potions 1} :validator player-validator))
(def player2 (ref {:hp 15 :potions 0} :validator player-validator))

(defn heal-with-potion
  [target healer]
  (dosync
   (when (and (> (:potions @healer) 0) (<= (:hp @target)))
     (alter healer update-in [:potions] dec)
     (alter target update-in [:hp] #(min (+ % hp-per-potion) max-hp)))))
