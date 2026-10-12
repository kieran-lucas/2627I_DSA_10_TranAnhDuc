import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DownloadAlgs4Data {
    public static Path download() throws Exception {
        Path archive = Path.of("build", "algs4-data.zip");
        Path destination = Path.of("build", "princeton-data").toAbsolutePath().normalize();
        Files.createDirectories(archive.getParent());
        URLConnection connection = URI.create(
                "https://algs4.cs.princeton.edu/code/algs4-data.zip").toURL().openConnection();
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(60000);
        System.out.println("Downloading algs4-data.zip from Princeton...");
        try (InputStream input = connection.getInputStream()) {
            Files.copy(input, archive, StandardCopyOption.REPLACE_EXISTING);
        }
        int files = 0;
        Files.createDirectories(destination);
        try (ZipInputStream zip = new ZipInputStream(Files.newInputStream(archive))) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                Path target = destination.resolve(entry.getName()).normalize();
                if (!target.startsWith(destination)) {
                    throw new IllegalStateException("Invalid ZIP path: " + entry.getName());
                }
                if (entry.isDirectory()) {
                    Files.createDirectories(target);
                } else {
                    Files.createDirectories(target.getParent());
                    Files.copy(zip, target, StandardCopyOption.REPLACE_EXISTING);
                    files++;
                }
                zip.closeEntry();
            }
        }
        System.out.println("Downloaded " + Files.size(archive) + " bytes; extracted " + files + " files.");
        Path data = destination.resolve("algs4-data");
        if (!Files.isRegularFile(data.resolve("1Kints.txt"))) {
            throw new IllegalStateException("Missing extracted Princeton data");
        }
        return data;
    }
}
