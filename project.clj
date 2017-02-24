(defproject bulls-and-cows "0.1.0-SNAPSHOT"
  :description "Bulls and Cows on the terminal"
  :url "https://github.com/lkdjiin/bulls-and-cows"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot bulls-and-cows.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
