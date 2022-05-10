package com.example.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blogpessoal.model.Usuario;
import com.example.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		
		usuario.orElseThrow(()-> new UsernameNotFoundException(userName + "Usuário não econtrado"));
		
		//Atribui o resultado enocntrado no optional para alimentar o  UserDetails
		return usuario.map(UserDetailsImpl::new).get();
	}


	
}

	