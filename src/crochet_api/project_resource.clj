(ns crochet-api.project-resource
  (:require [crochet-api.project :as project]
            [cheshire.core :refer :all]
            [crochet-api.resource-helpers :refer :all]
            [liberator.core :refer [defresource]]))

(def default-limit 50)

(defresource projects []
  :available-media-types ["application/json"]
  :allowed-methods [:get]
  :handle-ok (fn [_] (project/all {:limit default-limit})))
