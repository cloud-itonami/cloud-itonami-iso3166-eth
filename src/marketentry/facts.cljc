(ns marketentry.facts "Ethiopia market-entry catalog.")
(def catalog
  {"ETH" {:name "Ethiopia"
          :owner-authority "FPPA / e-GP"
          :legal-basis "Public Procurement and Property Administration Proclamation"
          :national-spec "e-GP supplier registration + trade registration"
          :provenance "https://www.ppa.gov.et/"
          :required-evidence ["Trade registration record" "e-GP registration record" "TIN record" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / FPPA"
          :rep-legal-basis "Ethiopian legal entity registration typically required for public awards"
          :rep-provenance "https://www.ppa.gov.et/"
          :corporate-number-owner-authority "Ministry of Trade / MoR"
          :corporate-number-legal-basis "Trade registration / TIN"
          :corporate-number-provenance "https://www.mot.gov.et/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
