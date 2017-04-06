package br.com.json.extractor.file

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

/**
 * Created by jonathan on 06/04/17.
 */
@Service
@Slf4j
class FileProcessor {

    def appendToFile(String message){
        def file = new File('produtos.txt')

        file.withWriter('UTF-8') { writer ->
            writer.write(message)
        }
    }
}
