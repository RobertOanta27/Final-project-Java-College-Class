package repository;

import Domain.Enrollment;
import Domain.WinterGame;

import java.util.List;

public interface EnrollmentRepo  extends Repository<Integer, Enrollment> {
    List<Enrollment> filterByWinterGame(String filterByWhatWinterGameName);
}
