// --- A

global Channel canal = new Channel();

process Servidor : {
    int contador = 0;
    String mensaje;

    while (true) {
        mensaje = canal.receive();

        if (mensaje == "sigue") {
            contador++;
        }else {
            print("Cantidad de 'sigue' recibidos: " + contador);
            contador = 0;
        }
    }
}


process Cliente : {
    canal.send("sigue");
    canal.send("sigue");
    canal.send("cuenta");     // servidor debe mostrar 2
    canal.send("sigue");
    canal.send("cuenta");
}

// ----B

global Channel cliente_a_servidor = new Channel();
global Channel servidor_a_cliente = new Channel();

process Servidor : {
    int contador = 0;
    String mensaje;

    while (true) {
        mensaje = cliente_a_servidor.receive();

        if (mensaje == "sigue") {
            contador++;
        }else {
            servidor_a_cliente.send(contador);
            contador = 0;
        }
    }
}


process Cliente : {
    int resultado;

    cliente_a_servidor.send("sigue");
    cliente_a_servidor.send("sigue");
    cliente_a_servidor.send("cuenta");

    resultado = servidor_a_cliente.receive();
    print("Cantidad de 'sigue' recibidos: " + resultado);  // Cantidad de 'sigue' recibidos: 2

    cliente_a_servidor.send("sigue");
    cliente_a_servidor.send("cuenta");

    resultado = servidor_a_cliente.receive();               // Cantidad de 'sigue' recibidos: 1
    print("Cantidad de 'sigue' recibidos: " + resultado);
}