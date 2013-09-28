(ns cljsoup.util
  (:import (org.jsoup.parser Parser)
           (org.jsoup.nodes Element Document)
           (org.jsoup.select Elements)))

;; # Private util methods

(defn is-document?
  "Check if a current node is a document instance."
  [node]
  (instance? Document node))

(defn is-element?
  "Check if a current node is a element instance."
  [node]
  (instance? Element node))

(defn is-collection?
  "Check if a current node is a elements instance."
  [node]
  (instance? Elements node))
