package br.com.json.extractor.products

import br.com.json.extractor.ServiceClient
import br.com.json.extractor.contract.Product
import br.com.json.extractor.file.FileProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by jonathan on 06/04/17.
 */
@Service
class ProdutoService {
    private static final Integer STEP = 49
    private final ServiceClient service
    private final FileProcessor processor

    @Autowired
    ProdutoService(ServiceClient service, FileProcessor processor) {
        this.service = service
        this.processor = processor
    }

    def getProdutosFile(String domain,String environment){
        String count = service.getCountProducts(domain, environment)
        1.step count.toInteger(), STEP, {
            List<Product> produtos = service.getProducts(domain, environment, it.toString(), (it+STEP).toString())
            produtos?.each { Product produto ->
                processor.appendToFile(produto.productName)
            }
        }
    }
}
