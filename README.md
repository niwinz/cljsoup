# cljsoup

Clojure html/xml parser based on jsoup.

This is a small library tha wraps a well java library for parsing html
into a clojure idiomatic api.

Currently it does not support all api that jsoup support out the box. But,
if you need some method that is not implemented in cljsoup, let me know or
make a pull-request."

## User Guide

### Parse document

```clojure
user=> (require '[cljsoup.core :as cr]
                '[cljsoup.document :as dc])
user=> (def document (cr/from-string "<head><title>Hello World</title></head>"))
```


## Api Documentation

FIXME

## License

Copyright © 2013 Andrey Antukh

Distributed under the Apache 2.0 License.
