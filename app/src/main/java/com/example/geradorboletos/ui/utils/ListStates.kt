package com.example.geradorboletos.ui.utils

class ListStates {
    companion object {
        val States = listOf<String>(
            "Selecione um estado:",
            "(AC) Acre",
            "(AL) Alagoas",
            "(AP) Amapá",
            "(AM) Amazonas",
            "(BA) Bahia",
            "(CE) Ceará",
            "(DF) Distrito Federal",
            "(ES) Espírito Santo",
            "(GO) Goiás",
            "(MA) Maranhão",
            "(MT) Mato Grosso",
            "(MS) Mato Grosso do Sul",
            "(MG) Minas Gerais",
            "(PA) Pará",
            "(PB) Paraíba",
            "(PR) Paraná",
            "(PE) Pernambuco",
            "(PI) Piauí",
            "(RJ) Rio de Janeiro",
            "(RN) Rio Grande do Norte",
            "(RS) Rio Grande do Sul",
            "(RO) Rondônia",
            "(RR) Roraima",
            "(SC) Santa Catarina",
            "(SP) São Paulo",
            "(SE) Sergipe",
            "(TO) Tocantins"
        )

        fun findPositionByInicials(inicials: String): Int{
            for (i in 1..States.size) {
                if(States[i].substring(1,3) == inicials){
                    return i
                }
            }
            return 0
        }
    }
}