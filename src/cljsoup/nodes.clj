(ns cljsoup.nodes
  (:use cljsoup.util)
  (:refer-clojure :exclude [first])
  (:import (org.jsoup.parser Parser)
           (org.jsoup.nodes Element Document)
           (org.jsoup.select Elements)))

;; Document Api

(defn body
  "Returns a body of document"
  [^Document elm]
  {:pre [(is-document? elm)]}
  (.body elm))

(defn head
  "Returns a head of document"
  [^Document elm]
  {:pre [(is-document? elm)]}
  (.head elm))

(defn title
  "Get document title"
  [^Document elm]
  {:pre [(is-document? elm)]}
  (.title elm))

;; Element api

(defn clone
  "Clone a document or element"
  [elm]
  {:pre [(or (is-element? elm) (is-document? elm))]}
  (.clone elm))

(defn inner-html
  "Get inner html of element instance."
  [elm]
  {:pre [(is-element? elm)]}
  (.html elm))

(defn outer-html
  "Get inner html of element instance."
  [elm]
  {:pre [(is-element? elm)]}
  (.outerHtml elm))

(defn select
  "Make jquery like search over dom"
  [elm, ^String query]
  {:pre [(or (is-document? elm) (is-element? elm))
         (instance? String query)]}
  (.select elm query))

(defn children
  "Get element children elements."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (.children elm))

(defn class-name
  "Get class name from current element."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (.className elm))

(defn get-element-by-id
  "Get element by id from current element."
  [elm, ^String id]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (.getElementById elm id))

(defn get-elements-by-class
  "Get elements by class name from current element."
  [elm, ^String clsname]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (.getElementsByClass elm clsname))

(defn has-text?
  "Test if current element has any text node and not whitespace."
  [elm]
  {:pre [(or
           (is-document? elm)
           (is-element? elm)
           (is-collection? elm))]}
  (.hasText elm))

(defn size
  "Get elements collection size"
  [elm]
  {:pre [(is-collection? elm)]}
  (.size elm))

(defn as-vector
  "Get collection as clojure vector of elements"
  [elm]
  {:pre [(is-collection? elm)]}
  (let [result (atom [])]
    (dotimes [n (.size elm)]
      (let [item (.get elm n)]
        (swap! result conj item)))
    (vec @result)))

(defn attributes
  "Get attributes from current element as clojure map."
  [elm]
  {:pre [(or (is-document? elm) (is-element? elm))]}
  (let [inner-attrs (-> elm .attributes .asList)
        num-attrs   (-> elm .attributes .size)
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
  (.first elm))
