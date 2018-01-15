import javafx.application.Application;
import javafx.stage.Stage;
import model.Bootcamp;
import model.Codecadet;
import model.User;
import navigation.Navigation;
import persistence.ConnectionManager;
import services.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;


public class Main extends Application {
    private ConnectionManager cManager;
    private EntityManagerFactory emf;

    @Override
    public void init() throws Exception {
        cManager = new ConnectionManager();
        emf = new Persistence().createEntityManagerFactory("acDB");
        MockUserService mockUserService = new MockUserService();
       // MockBootcampService mockBootcampService = new MockBootcampService();
        JDBCUserService jdbcService = new JDBCUserService();
        JDBCBootcampService jdbcBootcampService = new JDBCBootcampService();
        JPAUserService jpaUserService = new JPAUserService();
        JPABootcampService jpaBootcampService = new JPABootcampService();

        jdbcBootcampService.setConnectionManager(cManager);
        jdbcService.setConnectionManager(cManager);
        jpaUserService.setConnectionManager(cManager);
        jpaBootcampService.setConnectionManager(cManager);

        jpaUserService.setEmf(emf);
        jpaBootcampService.setEmf(emf);

        ServiceRegistry.getInstance().addService(jpaBootcampService);
        ServiceRegistry.getInstance().addService(jpaUserService);
        ServiceRegistry.getInstance().addService(jdbcBootcampService);
        ServiceRegistry.getInstance().addService(jdbcService);
        ServiceRegistry.getInstance().addService(mockUserService);
       // ServiceRegistry.getInstance().addService(mockBootcampService);

        createForTest(jpaBootcampService,jpaUserService);

        System.out.println(jpaBootcampService.listAllBootcamps().size());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
    }

    @Override
    public void stop() throws Exception {
        cManager.getConnection().close();
        emf.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void createForTest(JPABootcampService mockBootcampService, JPAUserService mockUserService) {
        Bootcamp boot1 = new Bootcamp(1,"Terceira", new java.sql.Date(118,3,10), new java.sql.Date(118,6,21));
        Bootcamp boot2 = new Bootcamp(2,"Lisboa", new java.sql.Date(118,2,21), new java.sql.Date(118,5,30));
        Bootcamp boot3 = new Bootcamp(3,"Fundão", new java.sql.Date(118,5,16), new java.sql.Date(118,8,23));
        User user1 = new User("andre", "andre", "andre"); //ADDED
        User user2 = new User("andre1", "andre", "andre1"); //ADDED
        User user3 = new User("andre2", "andre", "andre"); //ADDED
        User user4 = new User("andre3", "andre", "andre1"); //ADDED
        User user5 = new User("andre4", "andre", "andre"); //ADDED
        User user6 = new User("andre5", "andre", "andre1"); //ADDED
        User user7 = new User("andre6", "andre", "andre"); //ADDED
        User user8 = new User("andre7", "andre", "andre1"); //ADDED
        User user9 = new User("andre8", "andre", "andre"); //ADDED
        User user10 = new User("andre9", "andre", "andre1"); //ADDED
        User user11 = new User("andre10", "andre", "andre"); //ADDED
        User user12 = new User("andre11", "andre", "andre1"); //ADDED
        User user13 = new User("andre12", "andre", "andre"); //ADDED
        User user14 = new User("andre13", "andre", "andre1"); //ADDED
        User user15 = new User("andre14", "andre", "andre"); //ADDED
        User user16 = new User("andre15", "andre", "andre1"); //ADDED
        User user17 = new User("andre16", "andre", "andre"); //ADDED
        User user18 = new User("andre17", "andre", "andre1"); //ADDED
        User user19 = new User("andre18", "andre", "andre"); //ADDED
        User user20 = new User("andre19", "andre", "andre1"); //ADDED

        mockBootcampService.addBootcampToList(boot1);
        mockBootcampService.addBootcampToList(boot2);
        mockBootcampService.addBootcampToList(boot3);

        mockUserService.addUser(user1);
        mockUserService.addUser(user2);
        mockUserService.addUser(user3);
        mockUserService.addUser(user4);
        mockUserService.addUser(user5);
        mockUserService.addUser(user6);
        mockUserService.addUser(user7);
        mockUserService.addUser(user8);
        mockUserService.addUser(user9);
        mockUserService.addUser(user10);
        mockUserService.addUser(user11);
        mockUserService.addUser(user12);
        mockUserService.addUser(user13);
        mockUserService.addUser(user14);
        mockUserService.addUser(user15);
        mockUserService.addUser(user16);
        mockUserService.addUser(user17);
        mockUserService.addUser(user18);
        mockUserService.addUser(user19);
        mockUserService.addUser(user20);

        System.out.println("USER IS: " + mockUserService.findByName("andre"));


        int counter = 0;
       for(User u : mockUserService.userList()) {
            if (counter <= 7) {
                mockBootcampService.addToBootcamp(1, new Codecadet(u, Codecadet.Gender.values()[Randomizer.randomNumber(0, 2)], "Random", "Teste", "XXXXXXXXX", new Date(95, 3, 21)));
            } else if(counter > 7 && counter <= 15) {
                mockBootcampService.addToBootcamp(2, new Codecadet(u, Codecadet.Gender.values()[Randomizer.randomNumber(0, 2)], "Random", "Teste", "XXXXXXXXX", new Date(95, 3, 21)));
            } else {
                mockBootcampService.addToBootcamp(3, new Codecadet(u, Codecadet.Gender.values()[Randomizer.randomNumber(0, 2)], "Random", "Teste", "XXXXXXXXX", new Date(95, 3, 21)));

            }
            counter++;
        }

    }
}

