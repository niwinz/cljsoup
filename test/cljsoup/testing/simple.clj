(ns cljsoup.testing.simple
  (:refer-clojure :exclude [first])
  (:import (org.jsoup.parser Parser)
           (org.jsoup.safety Whitelist))
  (:require [clojure.test :refer :all]
            [cljsoup.core :refer :all]
            [cljsoup.util :refer :all]
            [cljsoup.nodes :refer :all]
            [cljsoup.safety :refer :all]
            [clojure.string :as s]))

(def html-data "<head><title>simple title</title><head>
                <body><div><span>Hello</span><span>World</span></div></body>")

(def html-data-02 "<div id=\"foo\" data-foo=\"bar\">Hello World</div>")

(deftest entry-point-tests
  (testing "Testing from-string and conditionals"
    (let [doc (from-string html-data)]
      (is (is-document? doc))
      (is (is-element? doc))))

  (testing "Testing from-string with custom parser"
    (let [parser  (Parser/htmlParser)
          doc     (from-string html-data parser)]
      (is (is-document? doc)))))

(deftest document-tests
  (testing "Testing body"
    (let [_doc        (from-string html-data)
          _body       (body _doc)
          _body_html  (s/split (inner-html _body) #"\n")]
      (is (is-element? _body))
      (is (= (clojure.core/first _body_html) "<div>"))))

  (testing "Testing head"
    (let [_doc        (from-string html-data)
          _head       (head _doc)
          _head_html  (s/split (inner-html _head) #"\n")]
      (is (is-element? _head))
      (is (= (clojure.core/first _head_html) "<title>simple title</title>"))))

  (testing "Test get title"
    (let [_doc    (from-string html-data)]
      (is (= (title _doc) "simple title")))))

(deftest elements-tests
  (testing "Outer html"
    (let [_doc            (from-string html-data)
          _body           (body _doc)
          _html           (outer-html _body)
          _first_element  (clojure.core/first (s/split _html #"\n"))]
      (is (= _first_element "<body>"))))

  (testing "Clone document"
    (let [doc     (from-string html-data)
          cloned  (clone doc)]
      (is (not (identical? doc cloned)))))

  (testing "Convert to vector and use select"
    (let [doc   (from-string html-data)
          items  (select doc "span")]
      (is (is-collection? items))
      (is (= (count (as-vector items)) 2))))

  (testing "Access to attributes"
    (let [doc   (from-string html-data-02)
          attrs (attributes (get-element-by-id doc "foo"))]
      (is (= attrs {:id "foo" :data-foo "bar"}))))

  (testing "has text?"
    (let [doc   (from-string html-data-02)
          elm   (select doc "#foo")]
      (is (has-text? elm)))))


(deftest safety-tests
  (testing "whitelists"
    (let [wl1 (basic-whitelist)
          wl2 (relaxed-whitelist)
          wl3 (simpletext-whitelist)]
      (is (class wl1) Whitelist)
      (is (class wl2) Whitelist)
      (is (class wl3) Whitelist)))
  (testing "cleaning text"
    (let [html      "<div><b>hello world</b></div>"
          doc       (from-string html)
          whlist    (simpletext-whitelist)
          valid     (is-valid? doc whlist)
          cleandoc  (clean doc whlist)]
      (is (false? valid))
      (is (= (inner-html cleandoc) (str "<html>\n <head></head>\n <body>\n  "
                                        "<b>hello world</b>\n </body>\n</html>"))))))
