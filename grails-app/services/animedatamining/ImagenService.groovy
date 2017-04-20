package animedatamining

import grails.transaction.Transactional
import sun.misc.BASE64Decoder

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Transactional
class ImagenService {

    RetornoImagen procesarImagen(String imagen){
        RetornoImagen retorno = new RetornoImagen()
        if(imagen==null){
            throw new MiExcepcion(codigo: MiExcepcion.ERROR_VALIDACION, mensaje: "La imagen no es valida")
        }

        try {
            BufferedImage image = null
            byte[] imageByte
            BASE64Decoder decoder = new BASE64Decoder()
            imageByte = decoder.decodeBuffer(imagen)
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)
            image = ImageIO.read(bis)
            bis.close()

            // write the image to a file
            String filename = "image.jpg"
            File outputfile = new File(filename)
            ImageIO.write(image, "jpg", outputfile)
            retorno.mensaje = "Procesado Correctamente"

            return retorno
        }catch (Exception e){
            e.printStackTrace()
            throw new MiExcepcion(codigo: MiExcepcion.ERROR_SERVIDOR,mensaje: "Error Procesando la imagen")

        }
    }

}
