(ns crochet-api.project-resource
  (:require [crochet-api.project :as project]
            [cheshire.core :refer :all]
            [crochet-api.liberator-helpers :refer :all]
            [clojure.walk :refer [keywordize-keys]]
            [liberator.core :refer [defresource]]))

(def default-limit 50)

(defn update-project [project data]
  (let [uuid {:uuid (str (:uuid project))}
        params (merge (keywordize-keys data) {:uuid (:uuid project)})]
    (project/update! params)))

(defresource projects []
  :available-media-types ["application/json"]
  :known-content-type? #(check-content-type % ["application/json"])
  :allowed-methods [:get]
  :handle-ok (fn [_] (project/all {:limit default-limit})))

(defresource project [uuid]
  :available-media-types ["application/json"]
  :allowed-methods [:get :put :delete]
  :malformed? #(parse-json % ::data)
  :exists? (fn [_]
            (let [pj (project/find-by-uuid {:uuid uuid})]
              (if-not (empty? pj)
                {::project (first pj)})))
  :existed? (fn [_] (empty? (project/find-by-uuid {:uuid uuid} ::sentinel)))
  :new? (fn [_] (empty? (project/find-by-uuid {:uuid uuid} ::sentinel)))
  :can-post-to-missing? false
  :put! (fn [ctx] (update-project (::project ctx) (::data ctx)))
  :handle-ok ::project)
