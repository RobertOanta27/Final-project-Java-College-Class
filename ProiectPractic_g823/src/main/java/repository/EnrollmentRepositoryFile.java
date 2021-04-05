package repository;

import Domain.Enrollment;
import Domain.WinterGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollmentRepositoryFile extends EnrollmentInMemoryRepo{
    private final String fileName;
    private final WinterGameRepo winterGameRepository;
    private static int idGenerator=0;

    public EnrollmentRepositoryFile(String str, WinterGameRepo winterGameRepository) {
        this.fileName = str;
        this.winterGameRepository=winterGameRepository;
        readFromFile();
    }
    private void readFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line=buffer.readLine();

            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0 ");
            }
            while((line = buffer.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 5)
                {
                    System.err.println("Line is not valid!...:" + line);
                    continue;
                }

                try{
                    int wgID=Integer.parseInt(el[4]);
                    int id=Integer.parseInt(el[0]);
                    WinterGame wg= winterGameRepository.findByID(wgID);
                    Enrollment enrollment=new Enrollment(id,el[1], Integer.parseInt(el[2]), el[3], wg);
                    super.add(enrollment);
                }
                catch(NumberFormatException exception){
                    System.err.println("Invalid id format!!" + el[0]);
                }
            }
        }
        catch (IOException exception){
            throw new RepositoryException("File reading error" + exception);
        }
    }
    private void writeToFile(){
        try ( PrintWriter print= new PrintWriter(fileName)){
            print.println(idGenerator);
            for (Enrollment e : findAll()){
                String s= e.getId()+ ";" + e.getNameChild()+ ";" +e.getAge()+ ";" + e.getNameParent()+ ";" + e.getWinterGame().getId();
                print.println(s);
            }
        }
        catch (IOException exception){throw new RepositoryException("Writing error"+ exception );}
    }

    @Override
    public Enrollment add(Enrollment en)
    {
        try{
            en.setId(getNextId());
            super.add(en);
            writeToFile();
            return en;
        }
        catch(RuntimeException e)
        { throw new RepositoryException (e);}
    }

    private static int getNextId(){
        return idGenerator++;
    }
}

