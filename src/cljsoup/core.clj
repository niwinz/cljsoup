(ns cljsoup.core
  (:use cljsoup.util)
  (:import (org.jsoup Jsoup)
           (org.jsoup.parser Parser)))

(defn from-string
  "Parse string and creates a new Document element."

  ([^String data]
    (let [doc (Jsoup/parse data)
          instance (make-document doc)]
      instance))

  ([^String data, ^Parser parser]
    (let [doc (Jsoup/parse data "" parser)
          instance (make-document doc)]
      instance)))
