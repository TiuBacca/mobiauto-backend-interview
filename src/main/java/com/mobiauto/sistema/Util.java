package com.mobiauto.sistema;

import br.com.caelum.stella.validation.CNPJValidator;

public class Util {

    public static void validaCNPJ(String cnpj) throws Exception{
        CNPJValidator validator = new CNPJValidator();
        validator.assertValid(cnpj);
    }
}


