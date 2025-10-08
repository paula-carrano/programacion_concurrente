/*
Se desea definir un servidor que provea el siguiente comportamiento: por cada
 cliente que se conecta debe generar un n´umero pseudoaleatorio entero en el rango [0,10]. Los
 clientes deben intentar adivinar el n´umero generado por el servidor. Para ello env´ıan sucesiva
mente mensajes conteniendo un n´umero. Cada mensaje es contestado por el servidor indicando
 si el cliente acert´o o no. Notar que el cliente no puede enviar un nuevo mensaje hasta tanto el
 servidor no le conteste el anterior.
*/

global Channel c1= new Channel();
global Channel c2= new Channel();


process Server: {
    while (true){
        int numeroAleatorio = randint(0,10);
        Boolean acerto = false;

        while (!acerto){
            int intento = c2.receive();

            if (intento == numeroAleatorio){
                c1.send("ACERTASTE");
                acerto = true;
            }else {
                c1.send("FALLASTE")
            }
        }
    }
}

process Cliente:{
    int intento;
    Boolean adivinado=false;

    while(!adivinado){
        intento = randint(0,10);
        c2.send(intento);

        String respuesta = c1.receive();
        print ("Intento: " + intento + " -> " + respuesta);

        if(respuesta == "ACERTASTE") {
            adivinado = true;
        }
    }

}