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
        String testImg = "image.jpg"
        //ruta completa a donde esta la capeta final que tiene lo necesario
        String finalDir = servletContext.getRealPath("/modelarffmineriadedatos")
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
        println "SUPERLOG:" + imagen.length()
        RetornoImagen retorno = new RetornoImagen()
        try {
            retorno = imagenService.procesarImagen(imagen)

            String testImg = "image.jpg"
            //ruta completa a donde esta la capeta final que tiene lo necesario
            String finalDir = servletContext.getRealPath("/modelarffmineriadedatos")
            Boolean imageType = ClassifyImageService.isCartoon(finalDir,testImg)
            if (imageType == null) {
               retorno.resultado  = "No Reconozido"
            } else if (imageType) {
                retorno.resultado  = "Imagen Caricatura"
            } else {
                retorno.resultado  = "Imagen Real"
            }

        } catch (MiExcepcion ex) {
            ex.printStackTrace()
            retorno = new RetornoImagen()
            retorno.error = true
            retorno.mensaje = ex.mensaje

        }
        render retorno as JSON

    }
}
