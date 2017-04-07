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
        createFile().append("$message\n")
    }

    def createFile(){
        new File('./src/main/resources/produtos.txt')
    }
}
