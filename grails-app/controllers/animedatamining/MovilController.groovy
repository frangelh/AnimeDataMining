package animedatamining

import grails.converters.JSON
import sun.misc.BASE64Decoder

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class MovilController {

    def imagenService
    def ClassifyImageService

    def index() {
        render "Bienvenidos al API REST"
    }

    def dirPath() {
        //ejmplo tiene que ser la ruta completa
        String testImg = "/home/mrmomo/gitStuff/dataMiningStuff/AnimeDataMining/Screenshot from 2017-01-16 18-17-25.png"
        //ruta completa a donde esta la capeta final que tiene lo necesario
        String finalDir = servletContext.getRealPath("/final")
        Boolean imageType = ClassifyImageService.isCartoon(finalDir,testImg)
        if (imageType == null) {
            render "no image"
        } else if (imageType) {
            render "cartoon"
        } else {
            render "real"
        }
    }

    def procesarImagen(String imagen) {
        println "SUPERLOG:" + imagen
        RetornoImagen retorno = new RetornoImagen()
        try {
            retorno = imagenService.procesarImagen(imagen)
        } catch (MiExcepcion ex) {
            ex.printStackTrace()
            retorno = new RetornoImagen()
            retorno.error = true
            retorno.mensaje = ex.mensaje

        }
        render retorno as JSON

    }
}
