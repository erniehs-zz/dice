(defproject dice "0.0.1-SNAPSHOT"
  :description "a dice roller"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [dice.core])
