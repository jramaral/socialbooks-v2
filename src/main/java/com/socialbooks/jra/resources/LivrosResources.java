package com.socialbooks.jra.resources;

import com.socialbooks.jra.domain.Livro;
import com.socialbooks.jra.services.LivroServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
@Api(value="Api")
public class LivrosResources {

    @Autowired
    private LivroServices livroServices;

   // @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE
    }) /*aqui vai retornar json ou xml */
    @ApiOperation("Retorna uma lista de livros")
    public ResponseEntity<List<Livro>> Listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livroServices.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Grava as informações do livro")
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
        livro = livroServices.salvarLivro(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Retorna um Livro indicado pelo Id")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro = livroServices.buscarLivro(id);
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Remove um livro")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        livroServices.deletarLivro(id);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation("Atualiza as informações do livro")
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);
        livroServices.atualizarLivro(livro);
        return ResponseEntity.noContent().build();
    }


}
