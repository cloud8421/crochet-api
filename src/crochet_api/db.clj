(ns crochet-api.db
  (:require [crochet-api.postgres-extensions]
            [environ.core :refer [env]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname (str "//localhost:5432/" (:db env))
              :user "cloud"})
