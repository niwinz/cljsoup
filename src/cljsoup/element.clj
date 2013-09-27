(ns cljsoup.element
  (:use cljsoup.util)
  (:refer-clojure :exclude [first]))

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

(defn children
  "Get element children elements."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (let [childs (.children (inner-instance-of elm))]
    (make-collection childs)))

(defn class-name
  "Get class name from current element."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (.className (inner-instance-of elm)))

(defn get-element-by-id
  "Get element by id from current element."
  [elm, ^String id]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (let [element  (.getElementById (inner-instance-of elm) id)]
    (make-element element)))

(defn get-elements-by-class
  "Get elements by class name from current element."
  [elm, ^String clsname]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (let [element  (.getElementsByClass (inner-instance-of elm) clsname)]
    (make-collection element)))

(defn has-text?
  "Test if current element has any text node and not whitespace."
  [elm]
  {:pre [(or
           (is-document? elm)
           (is-element? elm)
           (is-collection? elm))]}
  (.hasText (inner-instance-of elm)))

(defn size
  "Get elements collection size"
  [elm]
  {:pre [(is-collection? elm)]}
  (.size (inner-instance-of elm)))

(defn as-vector
  "Get collection as clojure vector of elements"
  [elm]
  {:pre [(is-collection? elm)]}
  (let [instance (inner-instance-of elm)
        result (atom [])]
    (dotimes [n (.size instance)]
      (let [item (.get instance n)]
        (swap! result conj item)))
    (vec (map make-element @result))))

(defn attributes
  "Get attributes map from current element."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (let [inner       (inner-instance-of elm)
        inner-attrs (-> inner .attributes .asList)
        num-attrs   (-> inner .attributes .size)
        final-attrs (atom {})]
    (dotimes [n num-attrs]
      (let [attribute (.get inner-attrs n)
            attrname  (.getKey attribute)
            attrvalue (.getValue attribute)]
        (swap! final-attrs assoc (keyword attrname) attrvalue)))
    @final-attrs))

(defn first
  "Get a first element from one collection."
  [elm]
  {:pre [(is-collection? elm)]}
  (make-collection (.first (inner-instance-of elm))))
