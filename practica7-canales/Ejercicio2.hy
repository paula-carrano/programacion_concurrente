/*
Se desea implementar un servidor que recibe mensajes de (exactamente) dos clientes
 distintos y muestra por pantalla la concatenaci´on de un par de mensajes (uno de cada cliente).
 Cada cliente continuamente env´ıa un mensaje y espera una cantidad aleatoria de tiempo. El
 servidor recibe los mensajes de los clientes y por cada par de mensajes recibidos muestra por
 pantalla su concatenaci´on.
*/

global Channel canalC1= new Channel();
global Channel canalC2= new Channel();


process Servidor:{
    String mensaje1;
    String mensaje2;

    while(true){
        mensaje1 = canal1.receive();                //bloquea hasta tener data
        mensaje2 = canal2.receive();                //bloquea hasta tener data
        print("Concatenación: " + mensaje1 + mensaje2);
    }
}

process Cliente1: {
    while(true){
        canalC1.send("A");
        wait(random(1000,3000));
    }
}


process Cliente2: {
    while(true){
        canalC2.send("B");
        wait(random(1000,3000));
    }
}