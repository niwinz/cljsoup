(ns cljsoup.testing.simple
  (:require [clojure.test :refer :all]
            [cljsoup.core :refer :all]
            [clojure.string :as s]))

(def html-data "<head><title>simple title</title><head>
                <body><div><span>Hello</span><span>World</span></div></body>")

(deftest a-test
  (testing "Testing from-string and conditionals"
    (let [doc (from-string html-data)]
      (is (is-document? doc))
      (is (not (is-element? doc)))))

  (testing "Testing body"
    (let [_doc        (from-string html-data)
          _body       (body _doc)
          _body_html  (s/split (inner-html _body) #"\n")]
      (is (is-element? _body))
      (is (= (first _body_html) "<div>"))))

  (testing "Testing head"
    (let [_doc        (from-string html-data)
          _head       (head _doc)
          _head_html  (s/split (inner-html _head) #"\n")]
      (is (is-element? _head))
      (is (= (first _head_html) "<title>simple title</title>"))))

  (testing "Test get title"
    (let [_doc    (from-string html-data)]
      (is (= (title _doc) "simple title"))))

  (testing "Set title"
    (let [_doc (from-string html-data)]
      (set-title! _doc "foo-title")
      (is (= (title _doc) "foo-title"))))

  (testing "Outer html"
    (let [_doc            (from-string html-data)
          _body           (body _doc)
          _html           (outer-html _body)
          _first_element  (first (s/split _html #"\n"))]
      (is (= _first_element "<body>"))))

  (testing "Clone document"
      (let [doc     (from-string html-data)
            cloned  (clone doc)]
        (is (not (identical? doc cloned))))))
