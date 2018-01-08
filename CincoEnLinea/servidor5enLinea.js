/**
 * Nombre del proyecto:
 *    5 en linea.
 *
 * Nombres de los desarrolladores:
 *    Mariana Cadena Romero
 *    Esmeralda Jimenez Ramos
 *
 * Fecha en la que se inició el programa:
 *    28-noviembre-2017
 *
 * Descripción: Juego que lleva por nombre '5 en linea' el cual esta disponible
 * para todo publico, tiene la capacidad de soportar multijugador de dos
 * participantes en tiempo real y de realizar registro de nuevos usuarios,
 * así como consultar la puntuacion de todos los jugadores.
 */

var io = require("socket.io")(7000);
var listaUsuarios = [];
var numJugadores=0;
var JugadorDAO = {
  usuario:""
};

io.on("connection",function(socket){
  console.log("Cliente conectado");

function buscarUsuario(usuario){
  for(i=0; i<listaUsuarios.length; i++){
    if(usuario === listaUsuarios[i].username){
      //Una vez encontrado el usuario, se retorna el canal para comunicarse
      //con el usuario
      return listaUsuarios[i].room
    }
  }
  return null;
}

socket.on("EnviarInvitacion", function(usuario){
  var room = buscarUsuario(usuario);
  if(room === null){
    socket.emit("UsuarioNoEncontrado")
  }else{
    socket.emit("UsuarioEncontrado")
    //Emit donde se muestra la invitacion al jugador invitado
    //Se le envia un emmit espevificamente a un usuario (to)
    //Se recibe el canal, y el nombre de usuario que envía la invitacion
    socket.broadcast.to(room).emit("MostrarInvitacion", socket.room, socket.username)
  }
});

socket.on("IniciarPartida", function(room){
  socket.room = room;
  //Con join se establece una comunicacion entre los dos jugadores
  socket.join(room);
  socket.broadcast.to(room).emit("AbrirTablero", socket.username);
});

socket.on("Realizar tiro", function(pos){
  socket.broadcast.to(socket.room).emit("MostrarTiroRival", pos);

});

socket.on("Avisar a perdedor", function(){
  socket.broadcast.to(socket.room).emit("Mostrar mensaje perdedor");
});

socket.on("Separar canales", function(){
  //si el room que tengo es diferente a mi id entonces no estoy en mi canal
  // o sea soy invitado
  if (socket.room != socket.id) {
    //Se corta la comunicación con el otro jugador
    socket.leave(socket.room);
    //Se le asigna a mi room mi mismo id
    socket.room = socket.id;
  }
});

socket.on("Finalizar sesion", function(usuario){
  var pos;
  for (var i = 0; i < listaUsuarios.length; i++) {
    if(usuario === listaUsuarios[i].username){
      pos = i;
      break;
    }
  }
  //Se borra el usuario que cerro sesion de la lista de usuarios conectados
  //en el servidor
  listaUsuarios.splice(pos, 1);
});

socket.on("Jugadores conectados", function(usuario) {
    numJugadores++;
    //Se le asigna un nombre al socket que es el nombre del user que llega
    socket.username = usuario;
    //Canal por el cual se comunicará con ese usuario
    socket.room = socket.id;
    listaUsuarios.push(socket);




});
});
