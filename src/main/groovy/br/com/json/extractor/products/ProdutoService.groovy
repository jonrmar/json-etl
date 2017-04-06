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

    final ServiceClient service
    final FileProcessor processor

    @Autowired
    ProdutoService(ServiceClient service, FileProcessor processor) {
        this.service = service
        this.processor = processor
    }

    def getProdutosFile(domain, environment, from, to){
        List<Product> produtos = service.getProducts(domain, environment, from, to)
        produtos?.each { Product produto ->
            processor.appendToFile(produto.productName)
        }
    }
}
