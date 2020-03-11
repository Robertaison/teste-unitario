package br.com.caelum.leilao.desafio;

public class AnoBissexto {

    public static Boolean ehBissexto(int ano){
        Boolean ehBissexto = false;

        if (multiploDeQuatro(ano) && !multiploDeCem(ano)){
            ehBissexto = true;
        }
        if (multiploDeQuatro(ano) && multiploDeQuatrocessentos(ano)){
            ehBissexto = true;
        }

        return ehBissexto;
    }

    private static Boolean multiploDeQuatro(int ano) {
        if(ano%4==0){
            return true;
        }
        return false;
    }

    private static Boolean multiploDeQuatrocessentos(int ano) {
        if(ano%400==0){
            return true;
        }
        return false;
    }

    private static Boolean multiploDeCem(int ano){
        if(ano%100==0){
            return true;
        }
        return false;
    }
}
