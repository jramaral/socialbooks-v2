package com.socialbooks.jra.resources;

import com.socialbooks.jra.domain.Autor;
import com.socialbooks.jra.services.AutoresServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
@CrossOrigin(origins = "*")
@Api(value="Api")
public class AutoresResource {

    @Autowired
    private AutoresServices autoresServices;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Retorna uma lista de Autores")
    public ResponseEntity<List<Autor>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(autoresServices.listarAutores());
    }

    @RequestMapping(value = "/{id}",  method = RequestMethod.GET)
    @ApiOperation("Retorna um Autor indicado pelo Id")
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long autorId){
        return ResponseEntity.status(HttpStatus.OK).body(autoresServices.buscarAutor(autorId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Grava as informações do Autor")
    public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor){
        autor = autoresServices.salvarAutor(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Atualiza as informações do Autor")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long autorId, @RequestBody Autor autor){
        autor.setId(autorId);
        autoresServices.atualizarAutor(autor);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Remove um Autor indicado pelo Id")
    public ResponseEntity<Void> remover(@PathVariable("id") Long autorId){
          autoresServices.removerAutor(autorId);
         return ResponseEntity.noContent().build();
    }


}
