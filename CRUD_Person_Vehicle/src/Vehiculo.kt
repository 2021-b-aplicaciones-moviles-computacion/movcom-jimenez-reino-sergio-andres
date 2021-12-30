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

            var fileExists = file.exists()
            if(!fileExists){
                println("El archivo NO existe")
            }
                file.appendText(vehiculoArchivo)

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
            if(data.equals("")){
                file.delete()
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



        fun eliminarVehiculos(id: String){
            val file = File(ruta)
            var text:ArrayList<String> = file.readLines() as ArrayList<String>
            var data:String=""
            for((i,line) in text.withIndex()){

                var idVal:String = line.toString().split(",").get(7)

                if (idVal.equals(id)){
                    text.remove(line)
                }
                try {
                    data += text[i] + "\n"
                } catch (e: Exception){
                    break
                }
            }
            if(data.equals("")){
                file.delete()
            }
            file.writeText(data)
        }

        fun contarVehiculos(id: String):Int{
            val file = File(ruta)
            var text:List<String> = file.readLines()
            var num:Int = 0;
            for(line in text){
                var idVal:String = line.toString().split(",").get(7)

                if (idVal.equals(id)){
                    println("${idVal} y ${id}")
                    num+=1
                }
            }
            return num
        }
    }

}