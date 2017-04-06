package br.com.json.extractor.contract

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Canonical

/**
 * Created by jonathan on 06/04/17.
 */
@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
class Product {
    String productName
}
