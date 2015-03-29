(ns crochet-api.postgres-extensions
  (:require [cheshire.core :as cheshire]
            [clojure.java.jdbc :as jdbc])
  (:import [org.postgresql.util PGobject]))

;;; Alter clojure.java.jdbc's behaviour so that Postgresql 'json'
;;; columns will be returned as Clojure datastructures, transparently.
(extend-type org.postgresql.util.PGobject
  jdbc/IResultSetReadColumn
  (result-set-read-column [val rsmeta idx]
    (let [colType (.getColumnTypeName rsmeta idx)]
    (if (or (= colType "json")
            (= colType "jsonb"))
      (cheshire/parse-string (.getValue val) true)
      val))))
