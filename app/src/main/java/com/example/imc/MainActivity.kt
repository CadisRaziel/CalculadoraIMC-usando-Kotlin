package com.example.imc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.example.imc.databinding.ActivityMainBinding
import java.lang.Float //nao esquecer de importa quando for transformar em numero float ou double

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn_calcular = binding.btnCalcular
        val resultado = binding.txtResultado

        //para verificar se o usuario não colocou a altura e peso e apertou o botão calcular
        //text = captura o que o usuario digita (precisamos converter pra string toString())
        btn_calcular.setOnClickListener {
            val editPeso = binding.txtEditPeso.text.toString()
            val editAltura = binding.txtEditAltura.text.toString()

            if(editPeso.isEmpty()){ //se o editPeso estiver vazio, vai mostrar uma mensagem pra informar o peso
                resultado.setText("Informe o seu peso")
            } else if(editAltura.isEmpty()){ //mesma coias pra altura
                resultado.setText("Informe sua altura")
            } else { //caso os dois campos estiverem preenchidos ele vem para o else aonde tem um metodo que vai criar o calculo
                CalculoDeIMC()
            }
        }

    }

    private fun CalculoDeIMC(){
        //precisamos fazer o biding aqui tambem pois ele nao esta dentro da classe acima
        val pesoID = binding.txtEditPeso
        val alturaID = binding.txtEditAltura

        //para realizar os calculos precisamos transformar o texto que pegamos do usuario que vem como string "os numeros" e transformar em inteiros
        //o peso vai ser numero inteiro
        //e a altura vai ser numero float ou double, vamos usar o float(para podermos colocar 1.90, 1.70 etc...)


        //para transformar em numero inteiro...
        val peso = Integer.parseInt(pesoID.text.toString())

        //para transformar em Float..
        val altura = Float.parseFloat(alturaID.text.toString())

        //calculo do imc
        val imc = peso / (altura * altura)

        //variavel que vai recerber a "mensagem" para exibir o resultado
        val resultadoCalc = binding.txtResultado

        //utilizando o When (é igual if e else porém mais simples)
        val mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade(Grau 1)"
            imc <= 39.9 -> "Obesidade(Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        //converter o imc(calculo) para texto, para ser apresentado
        imc.toString()

        //quando o usuario colocar o peso e altura e apertar calcular
        resultadoCalc.setText("IMC: $imc \n $mensagem")

    }


//    depois disso precisamos associar o icone no codigo no mainActivity.kt/
//    no mainActivity.kt
//    antes da ultima } clique com o botão direito na tela vai aparecer
//    "Generate" > "Override Methods" e digite "OnCreateOptionsMenu" e selecione ele
    //para criar o icone na barra de menu \/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }

//    para adicionar funcionalidade ao icone
//    abaixo do metodo criado para incluir o icone
//    clique com o botão direito "Generate" > "Override Methods" > e digite "onOptionsItemSelected"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset -> {
                val limparEditPeso = binding.txtEditPeso
                val limparEditAltura = binding.txtEditAltura
                val limparMensagem = binding.txtResultado

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}