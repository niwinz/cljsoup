(ns cljsoup.core
  (:use cljsoup.util)
  (:import (org.jsoup Jsoup)
           (org.jsoup.parser Parser)))

(defn from-string
  "Parse string and creates a new Document element."
  ([^String data]
    (Jsoup/parse data))
  ([^String data, ^Parser parser]
    (Jsoup/parse data "" parser)))
