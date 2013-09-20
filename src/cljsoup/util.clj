(ns cljsoup.util)

;; # Private util methods

(defn make-type [doc-type instance]
    {:inner instance :type doc-type})

(defn make-document [instance]
  (make-type :document instance))

(defn make-element [instance]
  (make-type :element instance))

(defn make-collection [instance]
  (make-type :collection instance))

(defn is-type? [doc-type type-instance]
    (= (:type type-instance) doc-type))

(defn inner-instance-of [elm]
    (:inner elm))

(defn type-of [elm]
    (:type elm))

;; FIXME: update metadata for add docstring
;; for each method
(def is-document? (partial is-type? :document))
(def is-element? (partial is-type? :element))
(def is-collection? (partial is-type? :collection))
