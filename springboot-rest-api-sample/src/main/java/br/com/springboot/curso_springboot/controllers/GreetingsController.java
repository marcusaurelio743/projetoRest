package br.com.springboot.curso_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_springboot.model.Usuario;
import br.com.springboot.curso_springboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController

public class GreetingsController {
	
	@Autowired//injeção de dependencia da interface UsuarioRepository
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario);
    	
    	return "Olá mundo: "+nome+" !!!!!!";
    }
    
    @GetMapping(value = "listaTodos")
    
    public ResponseEntity<List<Usuario>> listar(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);  
    }
    
    @PostMapping(value = "salvar")
    
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
    }
    
    @PutMapping(value = "atualizar")
    
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado ",HttpStatus.CREATED);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
    }
    
    @DeleteMapping( value = "deletar")
    
    public ResponseEntity<String> deletar( @RequestParam  Long id ){
    	
    	 usuarioRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("usuario Deletado com Sucesso!!!!",HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarUserId")
    
    public ResponseEntity<Usuario> buscar(@RequestParam Long id){
    	
    	Usuario user= usuarioRepository.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    }
    
 @GetMapping(value = "buscarPorNome")
    
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="name") String name){
    	
    	List<Usuario> usuarios = usuarioRepository.buscarNome(name.trim().toUpperCase());
    	
    	
    	return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }
    
    
}
