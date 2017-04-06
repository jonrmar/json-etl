package br.com.json.extractor.products

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by jonathan on 06/04/17.
 */
@RestController
@RequestMapping('/products')
@Slf4j
class ProductsController {

    final ProdutoService service

    @Autowired

    ProductsController(ProdutoService service) {
        this.service = service
    }

    @RequestMapping(path = "/find/{domain}/{environment}/", method = RequestMethod.GET)
    findProducts(@PathVariable('domain') String domain, @PathVariable('environment') String environment,
                 @RequestParam('from') String from, @RequestParam('to') String to){
        log.info("Getting products from: $domain")
        service.getProdutosFile(domain, environment, from, to)
        new ResponseEntity(HttpStatus.OK)
    }
}
