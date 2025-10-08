/*
Se desea definir un servicio de codificaci´on y transmisi´on de informaci´on llamado
 T. El servicio T recibe la informaci´on que un cliente C quiere enviar a un servicio remoto R. El
 servicio T es el encargado de codificar cada mensaje recibido desde C y de reenviarlo a R. Puede
 asumir que cuenta con la funci´on codificar que se encarga de codificar un mensaje.
 String codificar(String mensaje)
 Se solicita:
 a) Dar el c´odigo para el servicio T.
 b) Suponer ahora que el servicio T debe permitir a un cliente reenviar la informaci´on a distin
tos servicios remotos. Para ello el cliente debe indicar al servicio T qui´en ser´a el destinatario
 de los mensajes que se enviar´an a continuaci´on (los destinatarios estar´an identificados por
 un canal). Por ejemplo, el cliente puede comenzar indicando al servicio T que los mensajes
 que se enviar´an a continuaci´on deber´an ser reenviados al servicio remoto 1, luego todos los
 mensajes que env´ıe a continuaci´on el cliente, y hasta tanto este no notifique a T que desea
 cambiar el destinatario, deber´an ser codificados y reenviados al servicio remoto 1.
 Asumir que el cliente siempre comenzar´a indicando al servicio T cual es el primer servicio
 remoto al que se deber´an reenviar los mensajes. Sin embargo, no puede realizar ninguna
 suposici´on sobre la cantidad de mensajes que se enviar´an a cada servidor remoto.
 Dar una soluci´on para el servicio T que exhiba el comportamiento descripto.
 c) Suponer ahora que para codificar cada mensaje el servicio T necesita contar con una clave
 nueva que es generada por otro servicio de codificaci´on K. Para esto, el servicio K posee
 un canal p´ublico donde espera recibir el canal para enviar la nueva clave. El servicio T
 deber´a codificar cada mensaje del cliente utilizando una clave distinta, siempre provistas
 por el servicio K. Para realizar la codificaci´on con una clave puede utilizar la siguiente
 funci´on:
 String codificar(String mensaje, String clave)
 Dar una implementaci´on para el servicio T descripto anteriormente. Considere que la ge
neraci´on de las claves puede tomar alg´un tiempo, intente ser lo m´as eficiente posible.
*/

//---  a) Dar el c´odigo para el servicio T.

global Channel canalCliente = new Channel();
global Channel canalRemoto  = new Channel();


process servicioT: {
    while(true){
        String mensaje =canalCliente.receive();
        String mensajeDecodificado= codificar(mensaje);
        canalRemoto.send(mensajeDecodificado);
    }
}


//   b) Suponer ahora que el servicio T debe permitir a un cliente reenviar la informaci´on a distin
tos servicios remotos. Para ello el cliente debe indicar al servicio T qui´en ser´a el destinatario
 de los mensajes que se enviar´an a continuaci´on (los destinatarios estar´an identificados por
 un canal). Por ejemplo, el cliente puede comenzar indicando al servicio T que los mensajes
 que se enviar´an a continuaci´on deber´an ser reenviados al servicio remoto 1, luego todos los
 mensajes que env´ıe a continuaci´on el cliente, y hasta tanto este no notifique a T que desea
 cambiar el destinatario, deber´an ser codificados y reenviados al servicio remoto 1.
 Asumir que el cliente siempre comenzar´a indicando al servicio T cual es el primer servicio
 remoto al que se deber´an reenviar los mensajes. Sin embargo, no puede realizar ninguna
 suposici´on sobre la cantidad de mensajes que se enviar´an a cada servidor remoto.
 Dar una soluci´on para el servicio T que exhiba el comportamiento descripto.

 global Channel canalCliente = new Channel();
global Channel canalRemoto  = new Channel();


process servicioT: {
    while(true){
        String mensaje =canalCliente.receive();
        String mensajeDecodificado= codificar(mensaje);
        canalRemoto.send(mensajeDecodificado);
    }
}