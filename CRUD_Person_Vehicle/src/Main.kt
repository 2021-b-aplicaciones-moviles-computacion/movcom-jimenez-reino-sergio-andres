import java.io.*
import java.sql.DriverManager
import java.util.*
import kotlin.collections.ArrayList
import Persona
import java.lang.NumberFormatException

fun main(){
    var next:Boolean = true
    var option:Int = 0

    println("**********************SISTEMA DE PERSONAS Y VEHICULOS**********************")

    do{
        println("1. Administrar Persona")
        println("2. Administrar Vehículo")
        println("0. Salir")


        do {
            print("\n Opción: ")
            try {
                option = readLine()!!.toInt()
                break
            }catch (e: NumberFormatException){
                println("Ingrese una de las opciones")
            }

        }while(true)
        when (option){
            (0) -> {
                next=false
            }
            (1) -> {
                menuPersona()
            }
            (2) -> {
                menuVehiculo()
            }

        }
    }while (next)
    println("%%%%%%%%%%%%%%%%%%%%PROGRAMA FINALIZADO%%%%%%%%%%%%%%%%%%%%")
}


fun menuPersona(){
    var next:Boolean = true
    var option:Int = 0
    var nombre:String;
    var apellido: String;
    var edad:Int;
    var sexo:Char
    var objPersona:Persona

    var id:String
    var personaString:String

    val ruta = "src/data/persona.txt"

    println("-----------------------------------ADMINISTACIÓN DE PERSONAS-----------------------------------")

    do{

        println("-----------------------------------")
        id = listarTabla(ruta)
        println("-----------------------------------")
        println("1. Crear Persona")
        println("2. Leer Persona")
        println("3. Actualizar Persona")
        println("4. Eliminar Persona")
        println("0. Salir del programa")

        do {
            print("\n Opción: ")
            try {
                option = readLine()!!.toInt()
                break
            }catch (e: NumberFormatException){
                println("Ingrese una de las opciones")
            }

        }while(true)

        when (option){
            (0) -> {
                next = false
            }
            (1) -> {
                println("Ingrese los datos de la persona \n")
                print("nombre: ")
                nombre = readLine().toString()
                print("apellido: ")
                apellido = readLine().toString()
                print("edad: ")
                edad = readLine()!!.toInt()
                print("sexo (M/F): ")
                sexo = readLine()!![0]
                objPersona = Persona(id, nombre,apellido,edad,sexo)
                Persona.crearPersona(objPersona)

            }
            (2) -> {
                print("\n Ingrese el ID de la persona: ")
                id = readLine().toString()
                personaString = Persona.leerPersona(id)
                if(!personaString.equals("ID no encontrado")) {
                    mostrarPersona(personaString)
                }else{
                    println(personaString)
                }
            }
            (3) -> {
                print("\n Ingrese el ID de la persona: ")
                id = readLine().toString()
                personaString = Persona.leerPersona(id)
                if(!personaString.equals("ID no encontrado")){
                    mostrarPersona(personaString)
                    println("Ingrese los datos de la persona \n")
                    print("nombre: ")
                    nombre = readLine().toString()
                    print("apellido: ")
                    apellido = readLine().toString()
                    print("edad: ")
                    edad = readLine()!!.toInt()
                    print("sexo (M/F): ")
                    sexo = readLine()!![0]
                    objPersona = Persona(id, nombre,apellido,edad,sexo)
                    Persona.actualizarPersona(objPersona)
                }else{
                    println(personaString)
                }
            }

            (4) -> {
                print("\n Ingrese el ID de la persona: ")
                id = readLine().toString()
                personaString = Persona.leerPersona(id)
                if(!personaString.equals("ID no encontrado")) {
                    mostrarPersona(personaString)
                    val vehiculos = Vehiculo.contarVehiculos(id)
                    println("Esta persona cuenta con ${vehiculos} Vehiculos \n ¿Quiere eliminar a esta Persona?")
                    do {
                        print("Ingrese S/N: ")
                        var eli = readLine().toString()
                        if (eli.equals("S")) {
                            Persona.eliminarPersona(id)
                            if(vehiculos>0){
                                Vehiculo.eliminarVehiculos(id)
                            }
                        }
                    }while (!eli.equals("S") && !eli.equals("N"))

                }else{
                    println(personaString)
                }
            }
        }
    }while (next)
}

fun menuVehiculo(){
    var next:Boolean = true
    var option:Int = 0
    var cod: String
    var tipo: String
    var color: String
    var llantas:Int
    var motorizado:Boolean
    var estado:Char
    var avalado:Double
    var idPersona: String

    var objVehiculo:Vehiculo

    var VehiculoString:String

    val ruta = "src/data/vehiculo.txt"

    println("-----------------------------------ADMINISTACIÓN DE VEHICULOS-----------------------------------")

    do{

        println("-----------------------------------")
        cod = listarTabla(ruta)
        println("-----------------------------------")
        println("1. Crear Vehiculo")
        println("2. Leer Vehiculo")
        println("3. Actualizar Vehiculo")
        println("4. Eliminar Vehiculo")
        println("0. Salir del programa")

        do {
            print("\n Opción: ")
            try {
                option = readLine()!!.toInt()
                break
            }catch (e: NumberFormatException){
                println("Ingrese una de las opciones")
            }

        }while(true)
        when (option){
            (0) -> {
                next = false
            }
            (1) -> {
                print("Ingrese el ID del Dueño del vehículo: ")
                idPersona = readLine().toString()
                val res = Persona.validarIdPersona(idPersona)
                if(res){
                    println("Ingrese los datos del Vehiculo \n")
                    print("tipo de Vehículo: ")
                    tipo = readLine().toString()
                    print("Color: ")
                    color = readLine().toString()
                    print("Número de llantas: ")
                    llantas = readLine()!!.toInt()
                    print("Motorizado? (true/false): ")
                    motorizado = readLine().toBoolean()
                    print("Estado (Bueno=B/ Regular=R/ Malo=M): ")
                    estado = readLine()!![0]
                    print("Avalado por: ")
                    avalado = readLine()!!.toDouble()


                    objVehiculo = Vehiculo(
                        cod,
                        tipo,
                        color,
                        llantas,
                        motorizado,
                        estado,
                        avalado,
                        idPersona
                    )
                    Vehiculo.crearVehiculo(objVehiculo)
                }else{
                    println("La persona no existe o el ID de la persona es incorrecto")
                }


            }
            (2) -> {
                print("\n Ingrese el ID de la Vehiculo: ")
                cod = readLine().toString()
                VehiculoString = Vehiculo.leerVehiculo(cod)
                if(!VehiculoString.equals("ID no encontrado")) {
                    mostrarVehiculo(VehiculoString)
                }else{
                    println(VehiculoString)
                }
            }
            (3) -> {
                print("\n Ingrese el ID de la Vehiculo: ")
                cod = readLine().toString()
                VehiculoString = Vehiculo.leerVehiculo(cod)
                if(!VehiculoString.equals("ID no encontrado")){
                    mostrarVehiculo(VehiculoString)
                    println("Ingrese los datos de la Vehiculo \n")
                    print("tipo de Vehículo: ")
                    tipo = readLine().toString()
                    print("Color: ")
                    color = readLine().toString()
                    print("Número de llantas: ")
                    llantas = readLine()!!.toInt()
                    print("Motorizado? (true/false): ")
                    motorizado = readLine().toBoolean()
                    print("Estado (Bueno=B/ Regular=R/ Malo=M): ")
                    estado = readLine()!![0]
                    print("Avalado por: ")
                    avalado = readLine()!!.toDouble()
                    print("ID del Dueño: ")
                    idPersona = readLine().toString()
                    objVehiculo = Vehiculo(
                        cod,
                        tipo,
                        color,
                        llantas,
                        motorizado,
                        estado,
                        avalado,
                        idPersona
                    )
                    Vehiculo.actualizarVehiculo(objVehiculo)
                }else{
                    println(VehiculoString)
                }
            }

            (4) -> {
                print("\n Ingrese el ID de la Vehiculo: ")
                cod = readLine().toString()
                VehiculoString = Vehiculo.leerVehiculo(cod)
                if(!VehiculoString.equals("ID no encontrado")) {
                    mostrarVehiculo(VehiculoString)
                    println("¿Quiere eliminar a este Vehiculo?")
                    do {
                        print("Ingrese S/N: ")
                        var eli = readLine().toString()
                        if (eli.equals("S")) {
                            Vehiculo.eliminarVehiculo(cod)
                        }
                    }while (!eli.equals("S") && !eli.equals("N"))

                }else{
                    println(VehiculoString)
                }
            }
        }
    }while (next)
}

fun mostrarPersona(persona:String){
    val imprimir = persona.split(",")
    println("##-------------------------------##")
    println("ID: ${imprimir[0]}")
    println("Nombre: ${imprimir[1]}")
    println("Apellido: ${imprimir[2]}")
    println("Edad: ${imprimir[3]}")
    println("Sexo: ${imprimir[4]}")
    println("##-------------------------------##")
    print("Presiona Enter para continuar")
    readLine()
}


fun mostrarVehiculo(persona:String){
    val imprimir = persona.split(",")
    println("##-------------------------------##")
    println("COD: ${imprimir[0]}")
    println("Tipo: ${imprimir[1]}")
    println("Color: ${imprimir[2]}")
    println("Llantas: ${imprimir[3]}")
    println("Motorizado: ${imprimir[4]}")
    println("Estado: ${imprimir[5]}")
    println("Avalado: ${imprimir[6]}")
    println("ID Persona: ${imprimir[7]}")
    println("##-------------------------------##")
    print("Presiona Enter para continuar")
    readLine()
}


fun listarTabla(ruta:String):String{
    var last:String = "";
    var num:Int = 0;
    var max:Int = -1;
    try {
        val file = File(ruta)
        val text:List<String> = file.readLines()
        for(line in text){
            println(line)
            try {
                last = line.toString().split(",").get(0)
                num = last.toInt()
                if (num>max){
                    max=num
                }
            }catch (e: Exception){
                println("El archivo esta vacio")
            }

        }

        max+=1
        last = max.toString()

    }catch (e: IOException){
        println("⚠ El archivo NO existe")
    }
    return last
}