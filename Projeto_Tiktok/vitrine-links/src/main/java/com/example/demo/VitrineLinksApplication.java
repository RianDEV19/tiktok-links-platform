package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // Essa anotação avisa o Java que este arquivo vai responder a requisições da internet
public class VitrineLinksApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitrineLinksApplication.class, args);
	}

	//Aqui criamos a nossa rota
	@GetMapping("/teste") // O endereco que vamos digitar no navegador
	public String minhaPrimeiraRota(){
		return "Servidor rodando perfeitamente!";
	}

}
