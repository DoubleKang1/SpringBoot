package com.example.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.blogpessoal.model.Usuario;

//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() {

			repository.save(new Usuario(0L, "Maiar Silva", "isadora@gmail.com","51 e pinga","https://i.imgur.com/FETvs2O.jpg"));
			
			repository.save(new Usuario(0L, "Michael Silva", "michaeltrimundial@gmail.com","nunca fui rebaixado","https://i.imgur.com/FETvs2O.jpg"));
			
			repository.save(new Usuario(0L, "Brocco Oliveira", "broco@gmail.com","broccolis","https://i.imgur.com/FETvs2O.jpg"));
			
			repository.save(new Usuario(0L, "Mayara Silva", "will31smith@gmail.com","cenoura","https://i.imgur.com/FETvs2O.jpg"));
		}
	
	@Test
	@DisplayName("Teste que retorna 1 usuario")
	public void retornaUmUsuario() {
		Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com"));
	}
	
	@Test
	@DisplayName("Teste que retorna 3 usuarios")
	public void retornaTresUsuario() {
		
		List<Usuario> listaDeusuarios = repository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeusuarios.size());
		assertTrue(listaDeusuarios.get(0).getNome().equals("Maiar Silva"));
		assertTrue(listaDeusuarios.get(1).getNome().equals("Michael Silva"));
		assertTrue(listaDeusuarios.get(2).getNome().equals("Mayara Silva"));
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}
	






}
