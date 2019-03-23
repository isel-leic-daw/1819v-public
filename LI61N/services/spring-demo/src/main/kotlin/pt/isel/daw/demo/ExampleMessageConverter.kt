package pt.isel.daw.demo

import net.glxn.qrgen.QRCode
import net.glxn.qrgen.image.ImageType
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractGenericHttpMessageConverter
import java.lang.reflect.Type


class ExampleMessageConverter : AbstractGenericHttpMessageConverter<QrCode>(MediaType("image","png")) {

    override fun supports(clazz: Class<*>) = QrCode::class.java.isAssignableFrom(clazz)

    override fun writeInternal(t: QrCode, type: Type?, outputMessage: HttpOutputMessage) {
        val stream = QRCode.from(t.value)
                .to(ImageType.PNG)
                .withSize(250, 250)
                .stream()
        stream.writeTo(outputMessage.body)
    }

    override fun read(type: Type, contextClass: Class<*>?, inputMessage: HttpInputMessage): QrCode {
        throw UnsupportedOperationException()
    }

    override fun readInternal(clazz: Class<out QrCode>, inputMessage: HttpInputMessage): QrCode {
        throw UnsupportedOperationException()
    }
}