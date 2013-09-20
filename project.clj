(defproject be.niwi/cljsoup "0.1.0"
  :description "Clojure html parser based on jsoup."
  :url "http://github.com/niwibe/cljsoup"
  :license {:name "BSD"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.jsoup/jsoup "1.7.2"]]
  :aot [cljsoup.core]
  :plugins [[lein-marginalia "0.7.1"]
            [codox "0.6.6"]])
