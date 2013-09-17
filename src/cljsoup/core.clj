(ns cljsoup.core
  (:import (org.jsoup Jsoup)
           (org.jsoup.parser Parser))
  (:gen-class))

(defrecord SoupElement [internal-instance])
(defrecord SoupDocument [internal-instance])


(defn is-document?
  "Test if a first argument is a document"
  [^SoupDocument element]
  (instance? SoupDocument element))


(defn is-element?
  "Test if a first argument is a element or
  document."

  ([element strict]
    (if (not strict)
      (or
        (instance? SoupElement element)
        (instance? SoupDocument element))
      (instance? SoupElement element)))

  ([element]
    (is-element? element false)))


(defn from-string
  "Parse string and creates a new Document element."
  [^String data]
  (let [parsed-data (Jsoup/parse data)]
    (SoupDocument. parsed-data)))


(defn body
  "Returns a body of document"
  [^SoupDocument elm]
  {:pre [(is-document? elm)]
   :post [(is-element? %)]}
  (let [instance (:internal-instance elm)
        element  (SoupElement. (.body instance))]
    element))


(defn head
  "Returns a head of document"
  [^SoupDocument elm]
  {:pre [(is-document? elm)]
   :post [(is-element? %)]}
  (let [instance (:internal-instance elm)
        element  (SoupElement. (.head instance))]
    element))


(defn clone
  "Clone a document or element"
  [^SoupElement elm]
  {:pre [(is-element? elm)]
   :post [(is-element? %)]}
  (let [instance (:internal-instance elm)
        cloned   (SoupElement. (.clone instance))]
    cloned))


(defn title
  "Get document title"
  [^SoupDocument elm]
  {:pre [(is-document? elm)]}
  (-> elm :internal-instance .title))


(defn set-title!
  "Set document title"
  [^SoupDocument elm, ^String title]
  {:pre [(is-document? elm)
         (instance? String title)]}
  (.title (:internal-instance elm) title))


(defn inner-html
  "Get inner html of element instance."
  [^SoupElement elm]
  {:pre [(is-element? elm)]}
  (-> elm :internal-instance .html))


(defn outer-html
  "Get inner html of element instance."
  [^SoupElement elm]
  {:pre [(is-element? elm)]}
  (-> elm :internal-instance .outerHtml))


;; (defn select
;;   "Make jquery like search over dom"
;;   {:pre [(is-document? elm)
;;          (instance? String title)]}
;;   [^SoupElement elm, ^String query]



