//Estudiante: Maria Fernanda Uch Dzib
//Matrícula: 200300643

import java.io.File
import javax.swing.JOptionPane
import javax.swing.JScrollPane
import javax.swing.JTextArea


//Agregar la funcionalidad de lectura de archivos de texto
//La función lee un archivo de entrada
fun leer_archivo_texto(lectura_archivo:String):String {
    var archivo = File(lectura_archivo)
    var texto = archivo.readText()
    return texto
}

fun railfence_cifrar(texto:String, rail:Int): String
{
    var resul:String = ""
    var repeticion = 2 * (rail -1) //Cada vez que inicia el patron en zigzag, cada 4
    for (r in 0..rail-1){
        for (t in texto.indices){
            if (t % repeticion == r ||  t % repeticion == repeticion-r){ //
                resul+=texto[t]
            }
        }
    }
    return resul
}

fun railfence_descifrar(texto:String, rail:Int): String {
    var resul = Array (texto.length){""} //Array vacio
    var repeticion = 2 * (rail -1)
    var aux_i = 0
    for (r in 0..rail-1){
        for (t in texto.indices){
            if (t % repeticion == r || t % repeticion == repeticion-r){

                resul[t] = texto[aux_i++].toString() //La posición se agrega en orden
            }
        }
    }
    return (resul).reduce{a,b -> a+b} //Convertir el arreglo en string
}

fun main(){
    var option = 0
    var resul:String = ""
    var texto = ""
    var accion = ""
    var path_resultado = File("src/resultado.txt")
    do {
        option = Integer.parseInt(JOptionPane.showInputDialog("Cifrado Railfence\n---Menú---\n1. Cifrar\n2. Descifrar\n3. Salir"))

        if (option != 1 && option != 2) {
            break
        }
        //var path = JOptionPane.showInputDialog(null, "Introduce el path del archivo de entrada:")
        //var texto = leer_archivo_texto(path)

        var rails = 3 //Se utilizan 3 rieles
        JOptionPane.showMessageDialog(null,"Número de Rails = $rails")
        when (option){
            1 -> {
                accion = "cifrado"
                var path_cifrar = "src/cifrado.txt"

                texto = leer_archivo_texto(path_cifrar)
                resul = railfence_cifrar(texto, rails)
                path_resultado.writeText(resul)

                JOptionPane.showMessageDialog(null, "El texto de entrada es: \n$texto")
                JOptionPane.showMessageDialog(null, "Resultado guardado en el archivo ${path_resultado}")

            }
            2 -> {
                accion = "descifrado"
                var path_descifrar = "src/descifrado.txt"

                texto = leer_archivo_texto(path_descifrar)
                resul = railfence_descifrar(texto, rails)
                path_resultado.writeText(resul)

                JOptionPane.showMessageDialog(null, "El texto de entrada es: \n$texto")
                JOptionPane.showMessageDialog(null, "Resultado guardado en el archivo ${path_resultado}")
            }
        }
        //Para la visualización del texto cifrado o descifrado
        val area_resultado = JTextArea("$resul")
        area_resultado.lineWrap = true
        area_resultado.wrapStyleWord = true
        area_resultado.isEditable = false

        val scrollPane = JScrollPane(area_resultado)
        scrollPane.preferredSize = java.awt.Dimension(300, 150)

        //println("\nEl texto $accion del archivo es: \n$resul") //Imprimir en consola
        JOptionPane.showMessageDialog(null, scrollPane, "El texto $accion del archivo es:",
            JOptionPane.INFORMATION_MESSAGE)

    }while (true)
}
