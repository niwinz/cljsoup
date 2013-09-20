(ns cljsoup.core
  (:use cljsoup.util)
  (:import (org.jsoup Jsoup)))

(defn from-string
  "Parse string and creates a new Document element."
  [^String data]
  (let [doc (Jsoup/parse data)
        instance (make-document doc)]
    instance))
