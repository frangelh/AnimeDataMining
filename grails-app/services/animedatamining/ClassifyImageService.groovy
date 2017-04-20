package animedatamining

import grails.transaction.Transactional

@Transactional
class ClassifyImageService {
    Boolean isCartoon(String finalLocation,String filePath) {
        def proc = ["$finalLocation/beginProc", filePath].execute()
        String output = proc.text
        if (output.contains("file does not exist")) {
            return null
        } else if (output.contains("cartoon")) {
            return true
        } else if (output.contains("real")) {
            return false
        }
        return null
    }
}
