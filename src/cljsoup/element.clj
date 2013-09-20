(ns cljsoup.element
  (:use cljsoup.util))

(defn clone
  "Clone a document or element"
  [elm]
  {:pre [(or (is-element? elm) (is-document? elm))]}
  (let [element (inner-instance-of elm)
        cloned  (.clone element)]
    (if (is-element? elm)
      (make-element cloned)
      (make-document cloned))))

(defn inner-html
  "Get inner html of element instance."
  [elm]
  {:pre [(is-element? elm)]}
  (.html (inner-instance-of elm)))

(defn outer-html
  "Get inner html of element instance."
  [elm]
  {:pre [(is-element? elm)]}
  (.outerHtml (inner-instance-of elm)))

(defn select
  "Make jquery like search over dom"
  [elm, ^String query]
  {:pre [(or (is-document? elm) (is-element? elm))
         (instance? String query)]}
  (let [instance (inner-instance-of elm)]
    (make-collection (.select instance query))))

(defn size
  "Get elements collection size"
  [elm]
  {:pre [(is-collection? elm)]}
  (.size (inner-instance-of elm)))

(defn as-vector
  "Get collection as clojure vector of elements"
  [elm]
  {:pre [(is-collection? elm)]}
  (let [instance (inner-instance-of elm)]
    (vec (map #(.get instance %1) (range (.size instance))))))
