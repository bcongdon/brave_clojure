(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(def order-details-validations
  {:name
   ["Please enter a name" not-empty]

   :email
   ["Please enter an email address" not-empty

    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})

(def order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmons@gmail.com"})

; Exercise 1
(defmacro when-valid
  [to-validate validations & actions]
  `(let [errors# (validate ~to-validate ~validations)]
     (when (empty? errors#)
       ~@actions)))

; Exercise 2
(defmacro my-or
  "Just like 'or', but mine"
  ([] nil)
  ([x] x)
  ([x & rest]
   `(let [or# ~x]
      (if or#
        or#
        (my-or ~@rest)))))
