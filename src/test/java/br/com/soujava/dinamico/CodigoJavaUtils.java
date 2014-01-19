package br.com.soujava.dinamico;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * o objetivo dessa classe é buscar o codigo em um txt, simulando um banco de dados.
 */
public enum CodigoJavaUtils {
INSTANCE;

	private static final String SPLIT_EXPRESSION = "\\.";

	/**
	 * retorna o codigo que se encontra em:
	 * resources.
	 * @param codigo nome do arquivo
	 * @return a string com o codigo, caso nao encontre
	 * retornara null
	 */
	public CodigoJava getCodigo(String codigo) {
		
		try {
			URI uri = CodigoJavaUtils.class.getResource("/" +codigo).toURI();

			Path path = Paths.get(uri);
			
			byte[] result = Files.readAllBytes(path);
			String fonte = new String(result, StandardCharsets.UTF_8);
			String nomeClasse = path.getFileName().toString().split(SPLIT_EXPRESSION)[0];
			
			return new CodigoJava(nomeClasse, fonte);
		} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
				return CodigoJava.VAZIO;
			}
		
		
	}
	

}
