(ns crochet-api.project-resource
  (:require [crochet-api.project :as project]
            [cheshire.core :refer :all]
            [crochet-api.liberator-helpers :refer :all]
            [clojure.walk :refer [keywordize-keys]]
            [liberator.core :refer [defresource]]))

(def default-limit 50)

(defn to-insertable [json]
  (-> (keywordize-keys json)
      (update-in [:layouts] #(generate-string %))))

(defn create-project [facebook-id data]
  (.println System/out facebook-id)
  (project/create! (merge {:facebook_id facebook-id} (to-insertable data))))

(defn update-project [project data]
  (let [uuid {:uuid (str (:uuid project))}
        params (merge
                 {:uuid (:uuid project)}
                 (to-insertable data))]
    (project/update! params)))

(defn delete-project [project]
  (let [uuid {:uuid (str (:uuid project))}]
    (project/delete! uuid)))

(defresource projects [facebook-id]
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :known-content-type? #(check-content-type % ["application/json"])
  :malformed? #(parse-json % ::data)
  :post! #(create-project facebook-id (::data %))
  :handle-ok (fn [_] (project/by-user {:facebook_id facebook-id
                                       :limit default-limit})))

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
  :can-put-to-missing? false
  :put! #(update-project (::project %) (::data %))
  :delete! #(delete-project (::project %))
  :handle-ok ::project)
