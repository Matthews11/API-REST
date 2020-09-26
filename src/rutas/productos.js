// llama al modulo undercore
const { object } = require("underscore");

// recibe del app la ruta  y al mismo tiempo lo exporta
module.exports= function(app){
    
    const _ = require("underscore");
    const Productos= require("../../ejemplo.json");
    //definiendo las rutas y el metodo get
    app.get("/API/GET/productos",(req,res)=>{
      res.json(Productos);

      });

     //definiendo las rutas y el metodo post
      app.post("/API/POST/productos",(req,res)=>{
        const {Nombre,Marca,Precio}=req.body;

        //validacion de los datos recibido
        if (Nombre&&Marca&&Precio)
        {
            // se calcula el id 
            const id = Productos.length+1;
            // captura los parametros
            const newproducto = {...req.body,id}
            //agrega los parametros a productos
            Productos.push(newproducto);
            //envia los productos actualizado
            res.json(Productos);
        }
        else
        {
            res.status(500).json("ERROR");
        }
        });


        app.delete('/API/DELETE/productos/:id', (req, res) => {
            // captura el id
            const {id} = req.params;
            // recorre el arreglo de producto
            _.each (Productos, (Producto,i) => {
                // si encuentra el id obtenido lo quita 
                if (Producto.id == id )
                {
                    Productos.splice(i, 1);
                }
            });
            // envia la lista actualizada
            res.send(Productos);
        });

        //ruta para hacer la actualizacion
        app.put('/API/PUT/productos/:id', (req, res) => {
            // captura el id
            const {id} = req.params;
            const {Nombre,Marca,Precio}=req.body;
             //validacion de los datos recibido
        if (Nombre&&Marca&&Precio)
        {
            _.each (Productos, (Productos,i) => {
                // si encuentra el id actualiza 
                if (Productos.id == id )
                {
                    Productos.id=id;
                    Productos.Nombre=Nombre;
                    Productos.Marca=Marca;
                    Productos.Precio=Precio;
                }
        });
            //muestra los datos en pantalla 
           res.json(Productos);
           
        }
        //mensaje de error
        else {
            res.status(500).json({error: 'ERROR'});
        }

    });


    //ruta para hacer la actualizacion
    app.patch('/API/PATCH/productos/:id', (req, res) => {
        // captura el id
        const {id} = req.params;
        const {Nombre,Marca,Precio}=req.body;
         //validacion de los datos recibido
    if (Nombre&&Marca&&Precio)
    {
        _.each (Productos, (Productos,i) => {
            // si encuentra el id actualiza 
            if (Productos.id == id )
            {
                Productos.Nombre=Nombre;
                Productos.Marca=Marca;
                Productos.Precio=Precio;
            }
    });

       res.json(Productos);
       
    }
    else {
        res.status(500).json({error: 'ERROR'});
    }

});



};
