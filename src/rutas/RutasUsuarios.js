

// recibe del app la ruta  y al mismo tiempo lo exporta
module.exports= function(app){
    const Productos= require("../../ejemplo.json");
//drefine la ruta para hacer el todo get
app.get("/API/home",(req,res)=>{
    res.json(Productos);
});

}


