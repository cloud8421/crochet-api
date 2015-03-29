{:dev  {:ragtime {:database "jdbc:postgresql://localhost:5432/crochet-api-development?user=cloud"}
        :env {:db "crochet-api-development"}}
 :test {:ragtime  {:database "jdbc:postgresql://localhost:5432/crochet-api-test?user=cloud"}
        :env {:db "crochet-api-test"}}}
