package br.com.json.extractor

import br.com.json.extractor.contract.Product
import groovy.util.logging.Slf4j
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

import static br.com.json.extractor.util.StringManipulator.getTotalProducts

/**
 * Created by jonathan on 06/04/17.
 */
@Service
@Slf4j
class ServiceClient {

    List<Product> getProducts(String domain, String environment, String from, String to){
        String urlFromStore = "https://${domain}.${environment}.com.br/"
        String path = "api/catalog_system/pub/products/search/"
        def parameters = [_from:from, _to:to]
        getList(urlFromStore+path, parameters, new ParameterizedTypeReference<List<Product>>(){})
    }

    def getCountProducts(domain, environment){
        String urlFromStore = "https://${domain}.${environment}.com.br/"
        String path = "api/catalog_system/pub/products/search/"
        ResponseEntity response = doGet(urlFromStore+path, [:])

        getTotalProducts(response.headers.get('resources').first())
    }

    private <T> T getList(String path, Map<String, String> query, ParameterizedTypeReference<T> type) {
        try {
            HttpHeaders h = new HttpHeaders()
            h.put('accept', ['application/json'])
            HttpEntity<String> e = new HttpEntity<>(h)

            UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(path)
            query.each {k,v -> builder.queryParam(k, v)}
            def response = new RestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.GET, e, type)
            return response.body
        } catch (HttpStatusCodeException ex) {
            if (ex.statusCode == HttpStatus.NOT_FOUND) {
                return null
            }
            throw ex
        }
    }

    private doGet(String path, Map<String, String> query) {
        try {
            HttpHeaders h = new HttpHeaders()
            h.put('accept', ['application/json'])
            HttpEntity<String> e = new HttpEntity<>(h)

            UriComponentsBuilder builder =  UriComponentsBuilder.fromHttpUrl(path)
            query.each {k,v -> builder.queryParam(k, v)}
            def response = new RestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.GET, e, String.class)
            return response
        } catch (HttpStatusCodeException ex) {
            if (ex.statusCode == HttpStatus.NOT_FOUND) {
                return null
            }
            throw ex
        }
    }
}
