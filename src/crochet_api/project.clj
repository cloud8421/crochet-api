(ns crochet-api.project
  (:require [yesql.core :refer [defqueries]]
            [crochet-api.db :as db]))

(defqueries "crochet_api/queries/project.sql"
  {:connection db/db-spec})
