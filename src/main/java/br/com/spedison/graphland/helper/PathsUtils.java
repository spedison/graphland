package br.com.spedison.graphland.helper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsUtils {

    public static String changeExtension(String originalFileName, String newExtension) {
        Path originalPath = Paths.get(originalFileName);
        String baseName = originalPath.getFileName().toString();

        // Remove extensão se existir
        int idx = baseName.lastIndexOf('.');
        if (idx > 0) {
            baseName = baseName.substring(0, idx);
        }

        String novoNome = baseName + "." + newExtension.replace("[.]", "");

        // Junta com o path do diretório original
        Path newPath = originalPath.getParent() != null
                ? originalPath.getParent().resolve(novoNome)
                : Paths.get(novoNome);

        return newPath.toString();
    }
}
