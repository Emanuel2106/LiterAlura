package com.example.demo;

import com.example.model.RespuestaAPI;
import com.example.service.GutendexService;
import com.example.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.entity")
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	@Autowired
	private LibroService libroService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			mostrarMenu();
			opcion = scanner.nextInt();
			scanner.nextLine();  // Consumir el salto de línea

			switch (opcion) {
				case 1:
					buscarLibro(scanner);
					break;
				case 2:
					listarLibros();
					break;
				case 3:
					listarAutores();
					break;
				case 4:
					buscarAutoresVivos(scanner);
					break;
				case 5:
					contarLibrosPorIdioma(scanner);
					break;
				case 6:
					System.out.println("👋 ¡Gracias por usar LiterAlura!");
					break;
				default:
					System.out.println("⚠️ Opción no válida. Intente nuevamente.");
			}

		} while (opcion != 6);

		scanner.close();
	}


	private void mostrarMenu() {
		System.out.println("\n📚 *** LiterAlura - Catálogo de Libros *** 📚");
		System.out.println("1. Buscar libro por título");
		System.out.println("2. Listar libros guardados");
		System.out.println("3. Listar autores guardados");
		System.out.println("4. Buscar autores vivos en un año");
		System.out.println("5. Contar libros por idioma");
		System.out.println("6. Salir");
		System.out.print("Seleccione una opción: ");
	}



	private void buscarLibro(Scanner scanner) {
		System.out.print("Ingrese el título del libro a buscar: ");
		String titulo = scanner.nextLine();

		System.out.println("🔍 Buscando el libro '" + titulo + "'...");
		RespuestaAPI respuesta = gutendexService.buscarLibroPorTitulo(titulo);

		if (respuesta != null && respuesta.getLibros() != null && !respuesta.getLibros().isEmpty()) {
			System.out.println("📚 Libro encontrado:");
			respuesta.getLibros().forEach(System.out::println);

			// Guardar el libro usando el servicio
			libroService.guardarLibro(respuesta);
		} else {
			System.out.println("⚠️ No se encontraron resultados para '" + titulo + "'.");
		}
	}

	private void listarLibros() {
		var libros = libroService.listarLibros();


		if (libros.isEmpty()) {
			System.out.println("⚠️ No hay libros guardados.");
		} else {
			System.out.println("\n📚 Lista de libros guardados:");
			libros.forEach(libro -> System.out.println(libro.getTitulo() + " - " + libro.getAutor().getNombre()));
		}
	}

	private void listarAutores() {
		var autores = libroService.listarAutores();
		if (autores.isEmpty()) {
			System.out.println("⚠️ No hay autores guardados.");
		} else {
			System.out.println("\n🖊️ Lista de autores guardados:");
			autores.forEach(autor ->
					System.out.println(autor.getNombre() + " (Nacimiento: " + autor.getAnioNacimiento() + ", Fallecimiento: " + autor.getAnioFallecimiento() + ")"));
		}
	}

	private void buscarAutoresVivos(Scanner scanner) {
		System.out.print("Ingrese el año para buscar autores vivos: ");
		int anio = scanner.nextInt();
		scanner.nextLine();  // Consumir la línea restante

		var autoresVivos = libroService.buscarAutoresVivos(anio);

		if (autoresVivos.isEmpty()) {
			System.out.println("⚠️ No se encontraron autores vivos en el año " + anio);
		} else {
			System.out.println("\n👤 Autores vivos en el año " + anio + ":");
			autoresVivos.forEach(autor -> System.out.println("🖋️ " + autor.getNombre()));
		}
	}

	private void contarLibrosPorIdioma(Scanner scanner) {
		System.out.print("Ingrese el idioma (ejemplo: 'en', 'es', 'fr'): ");
		String idioma = scanner.nextLine();

		long cantidad = libroService.contarLibrosPorIdioma(idioma);
		System.out.println("📚 Hay " + cantidad + " libros en el idioma '" + idioma + "'.");
	}


}
