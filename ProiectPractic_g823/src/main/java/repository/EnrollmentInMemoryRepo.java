package repository;

import Domain.Enrollment;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentInMemoryRepo  extends Abstract_Repository<Integer, Enrollment> implements EnrollmentRepo{
    public List<Enrollment> filterByWinterGame(String filterByWhatWinterGameName){
        return getAll().stream().filter(x->x.getWinterGame().getName().equals(filterByWhatWinterGameName)).collect(Collectors.toList());
    }
}
