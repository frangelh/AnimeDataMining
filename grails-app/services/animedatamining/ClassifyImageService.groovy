package animedatamining

import grails.transaction.Transactional

@Transactional
class ClassifyImageService {

    Boolean isCartoon(String finalLocation,String filePath) {

        def proc = ["/bin/sh", "-c", finalLocation + "/beginProc", filePath].execute()
        String output =proc.text
        if (output.contains("file does not exist")) {
            return null
        }
        else if(output.contains("cartoon")){
            return true
        }
        return false
    }
}
