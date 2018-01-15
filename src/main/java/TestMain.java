public class TestMain {
    public static void main(String[] args) {
      /*  UserService service = new MockUserService();
        User user1 = new User("andre", "password", "andre@hot"); //ADDED
        User user2 = new User("andre1", "123", "andre@hot"); //ADDED
        User user3 = new User("   ", "password", "andre@hot"); //ERR
        User user4 = new User("Test","1234567", "teste@teste.com");

        System.out.println("Mail user1: " + user1.getEmail() + " Name user1: " + user1.getUsername() + " pw user1: " + user1.getPassword());
        service.addUser(user1);
        System.out.println(((MockUserService)service).getUserList().get(user1.getUsername()));
        System.out.println("user count: " + service.count());
        service.addUser(user1); // ADDS User
        service.addUser(user2);
        System.out.println("user count: " + service.count());
        service.addUser(user3);
        System.out.println("User 1 username: " + ((MockUserService)service).getUserList().get(user1.getUsername()));
        System.out.println("User 2 username: " +((MockUserService)service).getUserList().get(user2.getUsername()));
        System.out.println("User 3 username: " +((MockUserService)service).getUserList().get(user3.getUsername()));
        System.out.println("Login user 1: " + service.autenthicate("andre", "password"));
        System.out.println("Random Login: " + service.autenthicate("ola","1234"));

        System.out.println("\n----------------------//----------TEST BOOCAMP SERVICE----------//-------------------------");

        MockBootcampService bService = new MockBootcampService();
        //CREATING BOOTCAMPS
        Bootcamp boot1 = new Bootcamp(1, "terceira", new Date(2018,03, 16), new Date(2018,06,30));
        Bootcamp boot2 = new Bootcamp(1, "terceira", new Date(2018,03, 16), new Date(2018,06,30));
        Bootcamp boot3 = new Bootcamp(2, "Lisboa", new Date(2018,03, 16), new Date(2018,06,30));

        System.out.println("\n-------------CREATING BOOTCAMPS--------------");
        bService.addBootcampToList(boot1);
        System.out.println("Bootcamp 1: " + boot1);
        bService.addBootcampToList(boot2);
        System.out.println("Bootcamp 1(Same id test): " + boot2);
        System.out.println("Bootcamp list size: " + bService.listAllBootcamps().size());
        bService.addBootcampToList(boot3);
        System.out.println("Bootcamp 2: " + boot3);
        System.out.println("Bootcamp list size: " + bService.listAllBootcamps().size());

        //ADDING AND CREATING CODECADETS
        System.out.println("\n-----------------ADDING AND CREATING CODECADETS----------------");
        bService.addToBootcamp(1, new Codecadet(user1, Codecadet.Gender.MALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(1, new Codecadet(user1, Codecadet.Gender.FEMALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(1, new Codecadet(user2, Codecadet.Gender.FEMALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(2, new Codecadet(user1, Codecadet.Gender.MALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(2, new Codecadet(user1, Codecadet.Gender.FEMALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(2, new Codecadet(user2, Codecadet.Gender.FEMALE, "Azores", "Praia", "XXXXXXXXX",  new Date(1995,03,21)));
        bService.addToBootcamp(2, new Codecadet(user4, Codecadet.Gender.FEMALE, "Azores", "Praia", "XXXXXXXXX", new Date(1995,03,21)));
        System.out.println("Bootcamp 1 size: " + bService.findBootcampById(1).getCodecadets().size());
        System.out.println("Bootcamp 2 size: " + bService.findBootcampById(2).getCodecadets().size());

        //FIND BOOTCAMP
        System.out.println("\n--------------------FIND BOOTCAMP--------------------");
        System.out.println("Seach by id 2: " + bService.findBootcampById(2));
        System.out.println("Seach by id 1: " + bService.findBootcampById(1));

        //FIND CODECADET
        System.out.println("\n------------------FIND CODE CADETS------------------");
        System.out.println("Search user1 name: " + bService.findCodecadet(user1.getUsername()));
        System.out.println("Search random name: " + bService.findCodecadet("123124"));
        System.out.println("Search user2 name: " + bService.findCodecadet(user2.getUsername()));
        System.out.println("Search user3(never registered) name: " + bService.findCodecadet(user3.getUsername()));
        System.out.println("Search user4 name: " + bService.findCodecadet(user4.getUsername()));


        //LIST ALL CODECADETS FROM BOOTCAMP
        System.out.println("\n------------------LIST CODECADETS------------------");
        List<Codecadet> cadets = bService.listAllCadets(boot1);
        System.out.println("BOOTCAMP 1:");
        for(Codecadet c : cadets) {
            System.out.println(c.toString());
        }
        List<Codecadet> cadets2 = bService.listAllCadets(boot3);
        System.out.println("BOOTCAMP 2:");
        for(Codecadet c : cadets2) {
            System.out.println(c.toString());
        }

        //LIST ALL BOOTCAMPS
        System.out.println("\n------------------LIST BOOTCAMPS------------------");
        List<Bootcamp> bootList = bService.listAllBootcamps();
        System.out.println("BOOTCAMPS:");
        for(Bootcamp b : bootList) {
            System.out.println(b.toString());
        }

        //CHANGE CADET BOOTCAMP
        System.out.println("\n------------------CHANGE BOOTCAMP------------------");
        bService.changeBootcamp(user4.getUsername(),1);
        System.out.println("Bootcamp 1 size: " + bService.findBootcampById(1).getCodecadets().size());



    */}
}
