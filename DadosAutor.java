package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        String name, 
        Integer birth_year,
        Integer death_year
) {}

package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        String title,
        List<DadosAutor> authors,
        List<String> languages,
        int download_count
) {}

package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoAPI(
        List<DadosLivro> results
) {}
