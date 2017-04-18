package animedatamining

import grails.converters.JSON
import sun.misc.BASE64Decoder

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class MovilController {

    def imagenService
    def index() {
        render "Bienvenidos al API REST"
    }

    def procesarImagen(String imagen ){
        println "SUPERLOG:"+imagen
        RetornoImagen retorno = new RetornoImagen()
        try {
            retorno = imagenService.procesarImagen(imagen)
        }catch (MiExcepcion ex){
            ex.printStackTrace()
            retorno = new RetornoImagen()
            retorno.error = true
            retorno.mensaje = ex.mensaje

        }
        render retorno as JSON

    }
}
