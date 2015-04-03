(ns crochet-api.project-test
  (:require [clojure.test :refer :all]
            [cheshire.core :refer :all]
            [crochet-api.project :refer :all]
            [crochet-api.project-resource :refer :all]
            [crochet-api.helpers.db :refer :all]))

(def payload-fixture
  (parse-string (slurp "./test/crochet_api/payload.json")))

(defn layouts-data []
  (let [layouts (get payload-fixture "layouts")]
    (generate-string (first layouts))))

(deftest create-project-test
  (testing "It creates a project"
    (with-rollback (fn [conn]
                     (create! {:name "My new project"
                               :layouts (layouts-data)}
                              {:conn conn})
                     (let [project-count (:count (first (project-count {} {:conn conn})))]
                       (is (= project-count 1)))))))
