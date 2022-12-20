package anton.maliar.AirlineReservationSystem.repository;

import anton.maliar.AirlineReservationSystem.repository.model.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminRepositoryTest {
    private AdminRepository adminRepository;

    @Autowired
    public AdminRepositoryTest(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Test
    public void findByFieldsIfAdminExist(){
        Admin admin = new Admin("admin", "admin", "admin");
        adminRepository.save(admin);

        assertNotNull(adminRepository.findByFields("admin", "admin", "admin"));
        adminRepository.delete(admin);
    }
    @Test
    public void findByFieldsIfAdminNotExist(){
        Admin admin = new Admin("admin", "admin", "admin");
        adminRepository.save(admin);

        assertNull(adminRepository.findByFields("newAdmin", "admin", "admin"));
        adminRepository.delete(admin);
    }
}
