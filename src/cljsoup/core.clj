(ns cljsoup.core
  (:import (org.jsoup Jsoup)))

;; Private util methods

(defn- make-type [doc-type instance]
    {:inner instance :type doc-type})

(defn- make-document [instance]
  (make-type :document instance))

(defn- make-element [instance]
  (make-type :element instance))

(defn- is-type? [doc-type type-instance]
    (= (:type type-instance) doc-type))

(defn- inner-instance-of [elm]
    (:inner elm))

(defn- type-of [elm]
    (:type elm))

;; Public api

;; FIXME: update metadata for add docstring
;; for each method
(def is-document? (partial is-type? :document))
(def is-element? (partial is-type? :element))

(defn from-string
  "Parse string and creates a new Document element."
  [^String data]
  (let [doc (Jsoup/parse data)
        instance (make-document doc)]
    instance))

(defn body
  "Returns a body of document"
  [elm]
  {:pre [(is-document? elm)]}
  (let [element (inner-instance-of elm)
        instance (make-element (.body element))]
    instance))

(defn head
  "Returns a head of document"
  [elm]
  {:pre [(is-document? elm)]}
  (let [element (inner-instance-of elm)
        instance (make-element element)]
    instance))

(defn clone
  "Clone a document or element"
  [elm]
  {:pre [(or (is-element? elm) (is-document? elm))]}
  (let [element (inner-instance-of elm)
        cloned  (.clone element)]
    (if (is-element? elm)
      (make-element cloned)
      (make-document cloned))))

(defn title
  "Get document title"
  [elm]
  {:pre [(is-document? elm)]}
  (.title (inner-instance-of elm)))

(defn set-title!
  "Set document title"
  [elm, ^String title]
  {:pre [(is-document? elm)
         (instance? String title)]}
  (.title (inner-instance-of elm) title))

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

;; (defn select
;;   "Make jquery like search over dom"
;;   {:pre [(is-document? elm)
;;          (instance? String title)]}
;;   [^Element elm, ^String query]
