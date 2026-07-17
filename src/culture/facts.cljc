(ns culture.facts
  "Country-level regional-culture catalog for Ethiopia (ETH) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"ETH"
   [{:culture/id "eth.dish.injera"
     :culture/name "Injera"
     :culture/country "ETH"
     :culture/kind :dish
     :culture/summary "Fermented flatbread that is a staple in Ethiopia and Eritrea, described as a national dish significant to Ethiopian culture."
     :culture/url "https://en.wikipedia.org/wiki/Injera"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.dish.doro-wat"
     :culture/name "Doro wat"
     :culture/country "ETH"
     :culture/kind :dish
     :culture/summary "Spicy chicken stew, a variety of Ethiopian and Eritrean wat considered the national dish, shared from a communal bowl at formal and informal gatherings."
     :culture/url "https://en.wikipedia.org/wiki/Wat_(food)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.dish.kitfo"
     :culture/name "Kitfo"
     :culture/country "ETH"
     :culture/kind :dish
     :culture/summary "Ethiopian traditional dish of minced raw beef marinated in spices and clarified butter, originating among the Gurage people."
     :culture/url "https://en.wikipedia.org/wiki/Kitfo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.dish.shiro"
     :culture/name "Shiro"
     :culture/country "ETH"
     :culture/kind :dish
     :culture/summary "Stew of powdered chickpeas or broad beans with onions, garlic and spices, originating from northern Ethiopia and southern Eritrea and fundamental to both culinary traditions."
     :culture/url "https://en.wikipedia.org/wiki/Shiro_(food)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.beverage.tej"
     :culture/name "Tej"
     :culture/country "ETH"
     :culture/kind :beverage
     :culture/summary "Honey wine brewed and consumed in Ethiopia and Eritrea, made from honey, water and the shrub gesho; culturally significant as Ethiopia's national drink."
     :culture/url "https://en.wikipedia.org/wiki/Tej"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.product.coffee"
     :culture/name "Ethiopian coffee"
     :culture/country "ETH"
     :culture/kind :product
     :culture/summary "Ethiopia is where Coffea arabica originates; the country accounts for around 17% of the global coffee market, and coffee generates roughly 30-35% of its foreign income."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_production_in_Ethiopia"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.festival.timkat"
     :culture/name "Timkat"
     :culture/country "ETH"
     :culture/kind :festival
     :culture/summary "Ethiopian Orthodox celebration of Epiphany held on 19 January with Tabot processions; inscribed by UNESCO in 2019 on the Representative List of the Intangible Cultural Heritage of Humanity."
     :culture/url "https://en.wikipedia.org/wiki/Timkat"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "eth.heritage.lalibela"
     :culture/name "Lalibela"
     :culture/country "ETH"
     :culture/kind :heritage
     :culture/summary "Town in the Amhara Region of Ethiopia famous for its rock-cut monolithic churches, designated a UNESCO World Heritage Site in 1978."
     :culture/url "https://en.wikipedia.org/wiki/Lalibela"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-eth culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "ETH"))
                 " ETH entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
