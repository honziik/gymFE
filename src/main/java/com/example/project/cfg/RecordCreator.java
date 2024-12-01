package com.example.project.cfg;

import com.example.project.repository.*;
import com.example.project.repository.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
public class RecordCreator implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LogManager.getLogger(RecordCreator.class);
    private final RoomRepository roomRepository;
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final NewsRepository newsRepository;
    private final EntryRepository entryRepository;

    public RecordCreator(RoomRepository roomRepository, UsersRepository usersRepository, RoleRepository roleRepository, NewsRepository newsRepository, EntryRepository entryRepository) {
        this.roomRepository = roomRepository;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.newsRepository = newsRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    public void initData() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
        logger.info(resourceBundle.getString("initData"));

        Role adminRole = new Role();
        adminRole.setName("admin");

        Role userRole = new Role();
        userRole.setName("user");

        Role lectorRole = new Role();
        lectorRole.setName("lector");

        Users admin = new Users();
        admin.setLogin("admin");
        admin.setRole(adminRole);
        admin.setPassword("admin");
        admin.setEmail("admin@admin.com");
        admin.setBalance(1250);

        Users user = new Users();
        user.setLogin("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        user.setRole(userRole);

        Users lector1 = new Users();
        lector1.setLogin("lector1");
        lector1.setPassword("lector1");
        lector1.setEmail("lector1@user.com");
        lector1.setRole(lectorRole);

        Users lector2 = new Users();
        lector2.setLogin("lector2");
        lector2.setPassword("lector2");
        lector2.setEmail("lector2@user.com");
        lector2.setRole(lectorRole);

        Users lector3 = new Users();
        lector3.setLogin("lector3");
        lector3.setPassword("lector3");
        lector3.setEmail("lector3@user.com");
        lector3.setRole(lectorRole);

        Room room1 = new Room();
        room1.setName("Small gym room");
        room1.setCapacity(10);

        Room room2 = new Room();
        room2.setName("Big gym room");
        room2.setCapacity(30);

        News news1 = new News();
        news1.setTitle("Exciting New Feature Released");
        news1.setContent("We are thrilled to announce the release of our latest feature, designed to enhance user experience and streamline workflow. This feature has been in development for months, and we can’t wait for you to try it!");
        news1.setUser(admin);
        news1.setPublishedDate(LocalDateTime.now());

        News news2 = new News();
        news2.setTitle("Company Achieves Record Growth");
        news2.setContent("Our company has reached an incredible milestone this quarter, achieving unprecedented growth in both revenue and customer base. We are committed to continuing this trend of innovation and excellence.");
        news2.setUser(admin);
        news2.setPublishedDate(LocalDateTime.now().minusWeeks(1));

        News news3 = new News();
        news3.setTitle("Upcoming Event: Annual Tech Conference");
        news3.setContent("Join us at our annual tech conference where industry leaders will discuss the future of technology and innovation. This is an event you won’t want to miss!");
        news3.setUser(admin);
        news3.setPublishedDate(LocalDateTime.now().minusYears(1));

        Entry entry1 = new Entry();
        entry1.setEntryTime(LocalDateTime.now());
        entry1.setLeaveTime(LocalDateTime.now().plusHours(2));
        entry1.setUser(admin);

        Entry entry2 = new Entry();
        entry2.setEntryTime(LocalDateTime.now().minusDays(2));
        entry2.setLeaveTime(LocalDateTime.now().minusDays(2).plusHours(3));
        entry2.setUser(admin);

        Entry entry3 = new Entry();
        entry3.setEntryTime(LocalDateTime.now().minusDays(3).plusHours(2));
        entry3.setLeaveTime(LocalDateTime.now().minusDays(3).plusHours(4));
        entry3.setUser(admin);

        Entry entry4 = new Entry();
        entry4.setEntryTime(LocalDateTime.now().minusDays(5));
        entry4.setLeaveTime(LocalDateTime.now().minusDays(5).plusHours(3));
        entry4.setUser(admin);

        Entry entry5 = new Entry();
        entry5.setEntryTime(LocalDateTime.now().minusDays(6).plusHours(1));
        entry5.setLeaveTime(LocalDateTime.now().minusDays(6).plusHours(2));
        entry5.setUser(admin);


        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        roleRepository.save(lectorRole);
        usersRepository.save(admin);
        usersRepository.save(user);
        usersRepository.save(lector1);
        usersRepository.save(lector2);
        usersRepository.save(lector3);
        roomRepository.save(room1);
        roomRepository.save(room2);
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);

        entryRepository.save(entry1);
        entryRepository.save(entry2);
        entryRepository.save(entry3);
        entryRepository.save(entry4);
        entryRepository.save(entry5);
    }
}
