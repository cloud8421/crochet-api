(ns crochet-api.core
  (:require [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [ring.middleware.cors :refer [wrap-cors]]
            [compojure.core :refer [defroutes ANY GET POST]]))

(defroutes app
  (ANY  "/" [] "hello"))

(def handler
  (-> app
      wrap-params
      (wrap-cors #".*")
      wrap-with-logger))
