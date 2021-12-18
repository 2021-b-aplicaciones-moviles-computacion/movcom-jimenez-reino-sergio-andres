import java.io.File

fun main(){
    println("Hello World")
    //tipos de variables
     // INMUTABLES (val)
    val inmutable:String = "Sergio";

    // MUTABLES (var)
    var mutable: String = "Jimenez";
    mutable = "Andres";

    //val > var

    //Sintaxis y Duck Typing

    val ejemploVariable = "Nombre"
    var edadEjemplo: Int = 12

    //Tipos de variables en JAVA

    val nombreProfesor: String = "Adrian Paez"
    val sueldo: Double = 1.2
    val estadoCivil: Char

    if (true) {
        //Verdadero
    }else{
        //Falso
    }

    //switch
    val estadoCivilWhen: String = "S"
    when (estadoCivilWhen){
        ("S") -> {
            println("Acercarse")
        }
        ("C") -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")

    }

    fun imprimirNombre(nombre: String){
        println("Nombre: ${nombre}")
        val data = re
    }

    fun calcularSueldo(
        sueldo: Double,
        tasa: Double = 12.00,
        bonoEspecial: Double? = null,
    ): Double{
        //String

        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo * (100/tasa) + bonoEspecial
        }

    }

    calcularSueldo(100.00,14.00,25.00)

    calcularSueldo(
        bonoEspecial = 15.00,
        //tasa = 12.00
    sueldo = 150.00
    )

    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1000.00
    )

    //Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
        arregloDinamico.forEachIndexed {
            indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    val respuestaMapDos = arregloDinamico.map {
        it + 15
    }

    println(respuestaMapDos)

    //FILTER ->FILTRAR EL ARREGLO

    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual :Int ->
            val mayorACinco: Boolean = valorActual > 5
            return@filter mayorACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny :Boolean = arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all(valorActual > 5)
        }
    println(respuestaAll); //false

    //REDUCE -> Valor acumulado
    //Valor acumulado siempre empieza en 0 -> en Kotlin
    //a=[1,2,3,4,5] => Valor acumulado 15
    val respuestaReduce: Int = arregloDinamico
        .reduce{
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) //Logica de negocio
        }
    println("Reduce: "+respuestaReduce) // 15



    val arregloDanio = arrayListOf<Int>(12,15,8,10)
    val respuestaReduceFold = arregloDanio
        .fold(
            100, //Acumulado inicial
            {
                acumulado, valorActualIteracion ->
                return@fold acumulado - valorActualIteracion
            }
        )
    println(respuestaReduceFold)


    val vidaActual: Double = arregloDinamico
        .map { it * 2.3 } //arreglo
        .filter { it > 20 } //arreglo
        .fold(100.00, {acc, i -> acc - i})
        .also {println(it) }//3.4

    println("Valor vida actual ${vidaActual}")


    //CLASES
    println("CLASES \n")
    val ejemploUno = Sumar(1,2)
    val ejemploDos = Sumar(null, 2)
    val ejemploTres = Sumar(1, null)
    val ejemploCuatro = Sumar(null,null)

    println(ejemploUno.sumar())
    println(Sumar.historiaSumas)
    println(ejemploDos.sumar())
    println(Sumar.historiaSumas)
    println(ejemploTres.sumar())
    println(Sumar.historiaSumas)
    println(ejemploCuatro.sumar())
    println(Sumar.historiaSumas)
    println(Sumar.pi)


}

abstract class NumerosJava {
    protected val numeroUno: Int //Propiedad clase
    private val numeroDos: Int //Propiedad clase

    constructor(
        uno: Int,
        dos: Int,
    ) {
        numeroUno = uno
        numeroDos = dos
        println("Inicializar")
    }
}

abstract class Numeros(
    protected var numeroUno: Int, //Propiedad clase
    protected var numeroDos: Int //Propiedad clase
) {
    init {
        println("Inicializar")
    }
}

class Suma(
    //Constructor primario

    uno: Int,
    dos: Int,
):Numeros( // Constructor padre (super)
    uno,
    dos

) {
    init {
        this.numeroUno
        this.numeroDos
    }

    fun sumar(): Int {
        val total: Int = numeroUno + numeroDos

        return total
    }

}


class Sumar(uno: Int,
            dos: Int
): Numeros( //Constructor padre
uno,
dos
){
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor( // Segundo constructor
        uno: Int?, //parametro
        dos: Int   //parametro
    ): this ( //Llamada constructor primario
        if (uno == null) 0 else uno,
        dos
            ){
        //bloque de codigo segundo constructor
    }

    constructor( // tercer constructor
        uno: Int,
        dos: Int?
    ): this(
        // llamado constructor primario
    uno,
        if(dos == null) 0 else dos,
    ){
        //bloque codigo tercer constructor
    }

    constructor( // Cuarto constructor
        uno: Int?,
        dos: Int?
    ): this(
        // llamado constructor primario
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos,
    ){
        //bloque codigo tercer constructor
    }

    // public fun sumar(): Int
    fun sumar(): Int {
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        val file = File("hola").writeText("hola")
        val bufferedReader = file.
        return total
    }

    //Singleton (solo hay una instancia de estas cosas)
    companion object { // METODOS y PROPIEDADES STATICAS
        val pi = 3.14
        val historiaSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historiaSumas.add(valorNuevaSuma)
        }
    }




}




