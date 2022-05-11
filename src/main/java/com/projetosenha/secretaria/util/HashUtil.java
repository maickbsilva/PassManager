package com.projetosenha.secretaria.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashUtil {
	public static String hash(String palavra) {
		//"tempero" do hash
		String salt= "b@n@n@";
		//adicionar o "tempero" a palavra
		palavra = salt + palavra;
		//criar o hash
		 String hash = Hashing.sha384().hashString(palavra, StandardCharsets.UTF_8).toString();
		 //retorna o hash
		 return hash;
	}
}
