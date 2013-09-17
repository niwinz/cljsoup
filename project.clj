(defproject be.niwi/cjlsoup "0.1.0"
  :description "Clojure html parser based on jsoup."
  :url "http://github.com/niwibe"
  :license {:name "BSD"
            :url "http://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.jsoup/jsoup "1.7.2"]]
  :aot [cljsoup.core])