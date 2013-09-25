(ns cljsoup.document
  (:use cljsoup.util))

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
        instance (make-element (.head element))]
    instance))

(defn title
  "Get document title"
  [elm]
  {:pre [(is-document? elm)]}
  (.title (inner-instance-of elm)))
