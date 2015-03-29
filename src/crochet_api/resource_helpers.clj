(ns crochet-api.resource-helpers
   (:require [cheshire.core :refer :all]))

(defn read-body [ctx]
  (slurp (get-in ctx [:request :body])))

(defn extract-params-from [ctx]
  (parse-string (read-body ctx)))

(defn is-valid-json [ctx]
  (try
    (extract-params-from ctx)
    (catch Exception e false)))

(defn is-malformed-json [ctx]
  (not (is-valid-json ctx)))
