package repository;

import Domain.WinterGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WinterGameRepositoryFile extends WinterGameInMemoryRepo {
    private String FileName;
    private static int idGenerator = 0;

    public WinterGameRepositoryFile(String filename) {
        this.FileName = filename;
        ReadFromFile();
    }

    private void ReadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line = br.readLine();
            try {
                idGenerator = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while ((line = br.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 6) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    WinterGame c = new WinterGame(id, el[1], el[2], Integer.parseInt(el[3]), Integer.parseInt(el[4]), el[5]);
                    super.add(c);
                } catch (NumberFormatException nr) {
                    System.err.println("ID not valid." + el[0]);
                }
            }
        } catch (IOException ex) {
            throw new RepositoryException("Error" + ex);
        }
    }

    private void writeToFile() {
        try(PrintWriter pw=new PrintWriter(FileName)){
            pw.println(idGenerator);
            for (WinterGame wg : findAll()){
                String s= wg.getId() + ";" + wg.getName()+ ";" + wg.getType()+ ";"+ wg.getMinAge() + ";" + wg.getMaxAge() + ";" + wg.getDate();
                pw.println(s);
            }
        }catch(IOException ex){
            throw new RepositoryException("Error writing "+ex);
        }
    }

    @Override
    public WinterGame add(WinterGame obj) {
        try {
            obj.setId(getNextId());
            super.add(obj);
            writeToFile();
            return obj;
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
    }



    private static int getNextId() {
        return idGenerator++;
    }
}
