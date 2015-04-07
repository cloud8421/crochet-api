(ns crochet-api.core
  (:require [crochet-api.project-resource :as project-resource]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [ring.middleware.cors :refer [wrap-cors]]
            [compojure.core :refer [defroutes context ANY GET POST]]))

(defroutes app
  (ANY "/" [] "hello")
  (ANY ["/projects/:uuid"] [uuid] (project-resource/project uuid))
  (context "/:facebook-id" [facebook-id]
           (ANY "/projects" [] (project-resource/projects facebook-id))))

(def handler
  (-> app
      wrap-params
      (wrap-cors #".*")
      wrap-with-logger))
