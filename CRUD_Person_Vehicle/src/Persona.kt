import java.io.File
import java.sql.DriverManager

class Persona (
    var id: String,
    var nombre: String,
    var apellido: String,
    var edad:Int,
    var sexo:Char
){

    companion object{
        var ruta = "src/data/persona.txt"
        //Create
        fun crearPersona(objPersona: Persona){
            var personaArchivo = ""
            val id: String =objPersona.id
            val nombre: String =objPersona.nombre
            val apellido: String =objPersona.apellido
            val edad:Int =objPersona.edad
            val sexo:Char =objPersona.sexo

            personaArchivo += id + ","
            personaArchivo += nombre + ","
            personaArchivo += apellido + ","
            personaArchivo += edad.toString() + ","
            personaArchivo += sexo + "\n"

            var objArchivo = File(ruta)
            var fileExists = objArchivo.exists()
            if(!fileExists){
                println("El archivo NO existe")
            }
            objArchivo.appendText(personaArchivo)

        }

        fun leerPersona(id: String):String{
            val file = File(ruta)
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

        fun actualizarPersona(objPersona: Persona){
            val file = File(ruta)
            var personaArchivo = ""
            var id: String =objPersona.id
            var nombre: String =objPersona.nombre
            var apellido: String =objPersona.apellido
            var edad:Int =objPersona.edad
            var sexo:Char =objPersona.sexo


            personaArchivo += id + ","
            personaArchivo += nombre + ","
            personaArchivo += apellido + ","
            personaArchivo += edad.toString() + ","
            personaArchivo += sexo

            var data:String=""

            var text:ArrayList<String> = file.readLines() as ArrayList<String>
            for((i,line) in text.withIndex()){
                var valLine = line.toString().split(",").get(0)
                if (valLine.equals(id)){
                    text[i] = personaArchivo
                }
                data += text[i] + "\n"
            }
            if(data.equals("")){
                file.delete()
            }
            file.writeText(data)

        }

        fun eliminarPersona(id: String){
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

        fun validarIdPersona(id:String):Boolean{
            var res = false
            val file = File(ruta)
            var text:List<String> = file.readLines()
            var data:String=""
            for(line in text){
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