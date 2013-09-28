(ns cljsoup.safety
  (:import (org.jsoup.safety Whitelist Cleaner)
           (org.jsoup.nodes Document))
  (:use cljsoup.util))

(defn basic-whitelist
  "Returns a basic whitelist instance."
  []
  (Whitelist/basic))

(defn relaxed-whitelist
  "Returns a relaxed whitelist instance."
  []
  (Whitelist/relaxed))

(defn simpletext-whitelist
  "Returns a whitelist instance what only
  accepts text formatting tags."
  []
  (Whitelist/simpleText))

(defn clean
  "Clean a dirty document using a concrete whitelist."
  [^Document doc, ^Whitelist whitelist]
  (let [cleaner (Cleaner. whitelist)]
    (.clean cleaner doc)))

(defn is-valid?
  "Determines if the input document is valid,
  against the whitelist."
  [^Document doc, ^Whitelist whitelist]
  (let [cleaner (Cleaner. whitelist)]
    (.isValid cleaner doc)))

;; TODO: Add dsl for build custom whitelists

