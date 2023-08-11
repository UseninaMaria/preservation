import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SerializationMain {
    public static List <String> directory = new ArrayList<>();
    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(100, 15, 5, 11.5);
        GameProgress gameProgress1 = new GameProgress(200, 20, 10, 20.5);
        GameProgress gameProgress2 = new GameProgress(500, 25, 15, 35.7);
        saveGame("D://Games/savegames/save.dat", gameProgress);
        saveGame("D://Games/savegames/save1.dat", gameProgress1);
        saveGame("D://Games/savegames/save2.dat", gameProgress2);
        zipFiles("D://Games/savegames/zip.zip", directory);
    }

    public static void saveGame(String dir, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(dir)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
            directory.add(dir);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String dir, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dir))) {
            for (int i=0; i<list.size(); i++) {
                FileInputStream fis = new FileInputStream(list.get(i+1));
                ZipEntry entry = new ZipEntry("packed_list.dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
