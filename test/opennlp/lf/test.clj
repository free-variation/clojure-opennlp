(ns opennlp.lf.test
  (:use [clojure.test])
  (:require [opennlp.nlp :as nlp]
            [opennlp.treebank :as tb]
            
            [clojure.java.io :as io]))

;(defonce tokenize (make-tokenizer "models/en-token.bin"))
;(defonce pos-tag (make-pos-tagger "models/en-pos-maxent.bin"))
(defonce parser (tb/make-treebank-parser "parser-model/en-parser-chunking.bin"))

(defn parse-test-sentences
  "produces a file of parses for an input file of sentences"
  [in out]
  (with-open [rdr (io/reader in)]
    (with-open [wtr (io/writer out)]
      (doseq [sent (line-seq rdr)
              parse (parser [sent])]
        (.write wtr parse)
        (.write wtr "\n")))))