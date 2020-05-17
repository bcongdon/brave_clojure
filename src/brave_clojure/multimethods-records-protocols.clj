; Exercise 1
(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))
(defmethod full-moon-behavior :cat
  [were-creature]
  (str (:name were-creature) " will scurry off into the night"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from next door"})

(full-moon-behavior {:name "Andy the baker"
                     :were-type :simmons})

(full-moon-behavior {:name "Bob the cat"
                     :were-type :cat})

; Exercise 2
(defprotocol WereCreature
  (full-moon-behavior [x]))
(defrecord WereSimmons [name title]
  WereCreature
  (full-moon-behavior [x]
    (str name " will encourage people and sweat to the oldies")))

(full-moon-behavior (WereSimmons. "Simon" "Count"))

; Exercise 3
(defprotocol Greeter
  (warm-greeting [x])
  (cold-greeting [x]))

(extend-type java.lang.String
  Greeter
  (warm-greeting [x] (str "Greetings and salutations, " x))
  (cold-greeting [x] (str "Sup " x)))

(extend-protocol Greeter
  java.lang.Object
  (warm-greeting [x] "I can't see what you are, but I'm happy to see you")
  (cold-greeting [x] "Is an object worth greeting?")

  java.lang.Long
  (warm-greeting [x] "Nice number!")
  (cold-greeting [x] "I like floating points better"))
