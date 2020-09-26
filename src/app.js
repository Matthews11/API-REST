
// llama a los modulos express y morgan funcioanan para crear el server 

const express = require("express");
const app = express();
const morgan = require("morgan"); 

//funciones para que el servidor se procese
// morgan funciona para que por consola se vea las peticiones
app.use(morgan("dev"));
// le permite al servidor recibir formatos json para que los procese
app.use(express.json());

//rutas del servidor para hacer peticiones
 require("./rutas/RutasUsuarios")(app);
 require("./rutas/productos")(app);

//configuraciones del puerto del server
app.set ("port", process.env.PORT||4000);

// servidor
app.listen(app.get("port"), () =>{
console.log("servidor encendido en el puerto 4000");
});

 