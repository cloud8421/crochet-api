(ns crochet-api.helpers.db
  (:require [clojure.java.jdbc :as jdbc]
            [crochet-api.db :refer [db-spec]]))

(defn with-rollback [test-function]
  (jdbc/with-db-transaction [connection db-spec]
    (test-function connection)
    (jdbc/db-set-rollback-only! connection)))
