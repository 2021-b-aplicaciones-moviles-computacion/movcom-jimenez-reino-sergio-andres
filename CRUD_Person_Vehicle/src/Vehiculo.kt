import java.io.File

class Vehiculo (
    var cod: String,
    var tipo: String,
    var color: String,
    var llantas:Int,
    var motorizado:Boolean,
    var estado:Char,
    var avalado:Double,
    val idPersona: String
){

    companion object{
        var ruta = "src/data/vehiculo.txt"
        val file = File(ruta)
        //Create
        fun crearVehiculo(objVehiculo: Vehiculo){
            var vehiculoArchivo = ""

            vehiculoArchivo = vehiculoToString(objVehiculo) + "\n"
            val valor = validarIdPersona(objVehiculo.idPersona)

            var fileExists = file.exists()
            if(!fileExists){
                println("El archivo NO existe")
            }
            if (valor) {
                file.appendText(vehiculoArchivo)
            }else{
                println("La persona no existe o el ID de la persona es incorrecto")
            }

        }

        fun leerVehiculo(id: String):String{

            val text:List<String> = file.readLines()
            var personFile = "ID no encontrado"
            for(line in text){
                var num = line.toString().split(",").get(0)
                if (num.equals(id)){
                    personFile = line
                }
            }
            return personFile

        }

        fun actualizarVehiculo(objVehiculo: Vehiculo){
            var vehiculoArchivo = ""
            val cod = objVehiculo.cod
            vehiculoArchivo = vehiculoToString(objVehiculo)

            var data:String=""

            var text:ArrayList<String> = file.readLines() as ArrayList<String>
            for((i,line) in text.withIndex()){
                var valLine = line.toString().split(",").get(0)
                if (valLine.equals(cod)){
                    text[i] = vehiculoArchivo
                }
                data += text[i] + "\n"
            }
            file.writeText(data)

        }

        fun eliminarVehiculo(id: String){
            val file = File(ruta)
            var text:ArrayList<String> = file.readLines() as ArrayList<String>
            var data:String=""
            for((i,line) in text.withIndex()){
                var valLine = line.toString().split(",").get(0)
                if (valLine.equals(id)){
                    text.remove(line)
                }
                try {
                    data += text[i] + "\n"
                } catch (e: Exception){
                    break
                }

            }
            file.writeText(data)
        }

        fun vehiculoToString(objVehiculo: Vehiculo): String{
            var vehiculoArchivo = ""
            vehiculoArchivo += objVehiculo.cod + ","
            vehiculoArchivo += objVehiculo.tipo + ","
            vehiculoArchivo += objVehiculo.color + ","
            vehiculoArchivo += objVehiculo.llantas.toString() + ","
            vehiculoArchivo += objVehiculo.estado.toString() + ","
            vehiculoArchivo += objVehiculo.motorizado.toString() + ","
            vehiculoArchivo += objVehiculo.avalado.toString() + ","
            vehiculoArchivo += objVehiculo.idPersona
            return vehiculoArchivo
        }

        fun validarIdPersona(id:String):Boolean{
            var res = false
            val file = File("src/data/persona.txt")
            var text:ArrayList<String> = file.readLines() as ArrayList<String>
            var data:String=""
            for(line in text.withIndex()){
                var valLine = line.toString().split(",").get(0)
                if (valLine.equals(id)){
                    res = true
                    break
                }

            }
            return res
        }
    }

}