package service;


import Domain.Enrollment;
import Domain.WinterGame;
import repository.EnrollmentRepo;
import repository.RepositoryException;
import repository.WinterGameRepo;

import java.util.List;
import java.util.stream.Collectors;

public class WinterSportsServices {

    private WinterGameRepo winterGameRepo;

    private EnrollmentRepo enrollmentRepo;

    public WinterGameRepo getWinterGameRepo() {
        return winterGameRepo;
    }

    public WinterSportsServices(WinterGameRepo winterGameRepo, EnrollmentRepo enrollmentRepo) {
        this.winterGameRepo = winterGameRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public int addWinterGame(String name,String type,int minAge,int maxAge,String date) throws ServicesException{
        try {
            WinterGame wg = new WinterGame(name,type,minAge,maxAge,date);
            WinterGame newWG = winterGameRepo.add(wg);
            return newWG.getId();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding WinterGame"+ex);
        }
    }


    public void addEnrollment(String nameChild,int age,String nameParent,WinterGame winterGame) throws ServicesException{
        try {
            Enrollment enrollment = new Enrollment(nameChild,age,nameParent,winterGame);
            enrollmentRepo.add(enrollment);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding Enrollment"+er);
        }

    }

    public List<WinterGame> getAllWG(){
        return winterGameRepo.getAll().stream().collect(Collectors.toList());
    }
    public List<Enrollment> getAllEn(){
        return enrollmentRepo.getAll().stream().collect(Collectors.toList());
    }


    public List<Enrollment> getWinterFilter(String filterByWhatWinterGame) {
        return enrollmentRepo.filterByWinterGame(filterByWhatWinterGame);
    }
}
