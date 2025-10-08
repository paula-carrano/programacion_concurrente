/*
Considerar un servidor de trimming que recibe un String y responde con el resultado
 de aplicar la funci´on trim al valor recibido.
 a) Dar una soluci´on para el caso en que el servidor resuelve solicitudes de un ´unico cliente a
 la vez.
 b) Extender la soluci´on para atender concurrentemente a un n´umero no acotado de clientes
 (i.e., con esquema de conexi´on).
*/

//----a

global Channel cliente_a_servidor = new Channel();
global Channel servidor_a_cliente = new Channel();

process Servidor:{
    String mensaje;
    String respuesta;

    while(true){
        mensaje= cliente_a_servidor.receive();
        respuesta = trim(mensaje);
        servidor_a_cliente.send(respuesta);
    }
};


process Cliente: {
    String texto;
    String resultado;

    texto = "   hola mundo   ";  
    cliente_a_servidor.send(texto);
    resultado= servidor_a_cliente.receive();
    print("Resultado del trim: '" + resultado + "'");
}


// ---b

global Channel cliente_a_servidor = new Channel();
global Channel servidor_a_cliente = new Channel();

process Servidor:{
    while(true){
        String mensaje= cliente_a_servidor.receive();
        thread (mensaje){
            String respuesta = trim(mensaje);
            servidor_a_cliente.send(respuesta);
        }
       
    }
};


process Cliente: {
    String texto = "   hola mundo   ";  
    cliente_a_servidor.send(texto);

    String resultado= servidor_a_cliente.receive();
    print("Resultado del trim: '" + resultado + "'");
}