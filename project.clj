(defproject crochet-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.9.2"]
            [lein-environ "1.0.0"]
            [ragtime/ragtime.lein "0.3.8"]]
  :ring {:handler crochet-api.core/handler
         :nrepl {:start? true :port 12345}}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [environ "1.0.0"]
                 [yesql "0.5.0-rc2"]
                 [ragtime "0.3.8"]
                 [cheshire "5.4.0"]
                 [liberator "0.12.2"]
                 [compojure "1.3.2"]
                 [clj-http "1.1.0"]
                 [log4j/log4j "1.2.16" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.clojure/tools.logging "0.3.1"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-mock "0.2.0"]
                 [jumblerg/ring.middleware.cors "1.0.1"]
                 [ring.middleware.logger "0.5.0"]]
  :ragtime {:migrations ragtime.sql.files/migrations})
