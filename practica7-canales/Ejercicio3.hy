/*
Se desea definir un servidor que funcione como una variable compartida a la que
 los clientes pueden acceder mediante oportunos mensajes. Proponer una soluci´on.
*/

global Channel canal     = new Channel();
global Channel respuesta = new Channel();


process Servidor: {
 int valor = 0;
 String tipo;
 int nuevoValor;


 while (true){
    tipo = canal.receive();

    if (tipo == "get"){
        respuesta.send(valor);
    }else if (tipo == "set"){
        nuevoValor = canal.receive();
        valor = nuevoValor;
    }
 }
}


process Cliente1:{
    int x;
    canal.send("set");
    canal.send(10);
    canal.send("get");
    x = respuesta.receive();
    print ("Cliente leyo el valor:" + x);
}

process Cliente2:{
    int y;
    canal.send("get");
    y = respuesta.receive();
    print ("Cliente leyo el valor:" + y);
}