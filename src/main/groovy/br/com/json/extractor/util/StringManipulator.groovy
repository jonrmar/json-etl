package br.com.json.extractor.util

/**
 * Created by jonathan on 07/04/17.
 */
class StringManipulator {

    static getTotalProducts(String resource){
        resource.substring(resource.indexOf("/")+1, resource.size())
    }
}
