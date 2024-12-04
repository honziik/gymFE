package com.example.project.service;

import com.example.project.api.dto.*;
import com.example.project.api.mapper.NewsMapper;
import com.example.project.repository.backup.*;
import com.example.project.repository.entities.Class;
import com.example.project.repository.entities.*;
import com.example.project.repository.primary.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AllService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final ClassRepository classRepository;
    private final NewsRepository newsRepository;
    private final EntryRepository entryRepository;
    private final TransactionRepository transactionRepository;
    private final MembershipRepository membershipRepository;
    private final UserMembershipRepository userMembershipRepository;

    private final BackupUsersRepository backupusersRepository;
    private final BackupRoleRepository backuproleRepository;
    private final BackupRoomRepository backuproomRepository;
    private final BackupClassRepository backupclassRepository;
    private final BackupNewsRepository backupnewsRepository;
    private final BackupEntryRepository backupentryRepository;
    private final BackupTransactionRepository backuptransactionRepository;
    private final BackupMembershipRepository backupmembershipRepository;
    private final BackupUserMembershipRepository backupuserMembershipRepository;


    public AllService(UsersRepository usersRepository, RoleRepository roleRepository, RoomRepository roomRepository, ClassRepository classRepository, NewsRepository newsRepository, EntryRepository entryRepository, TransactionRepository transactionRepository, MembershipRepository membershipRepository, UserMembershipRepository userMembershipRepository, BackupUsersRepository backupusersRepository, BackupRoleRepository backuproleRepository, BackupRoomRepository backuproomRepository, BackupClassRepository backupclassRepository, BackupNewsRepository backupnewsRepository, BackupEntryRepository backupentryRepository, BackupTransactionRepository backuptransactionRepository, BackupMembershipRepository backupmembershipRepository, BackupUserMembershipRepository backupuserMembershipRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.roomRepository = roomRepository;
        this.classRepository = classRepository;
        this.newsRepository = newsRepository;
        this.entryRepository = entryRepository;
        this.transactionRepository = transactionRepository;
        this.membershipRepository = membershipRepository;
        this.userMembershipRepository = userMembershipRepository;
        this.backupusersRepository = backupusersRepository;
        this.backuproleRepository = backuproleRepository;
        this.backuproomRepository = backuproomRepository;
        this.backupclassRepository = backupclassRepository;
        this.backupnewsRepository = backupnewsRepository;
        this.backupentryRepository = backupentryRepository;
        this.backuptransactionRepository = backuptransactionRepository;
        this.backupmembershipRepository = backupmembershipRepository;
        this.backupuserMembershipRepository = backupuserMembershipRepository;
    }

    public UserDto getUser(final String login, final String password) {
        Users users = usersRepository.findByLogin(login);
        if (!Objects.equals(users.getPassword(), password)) {
            return null;
        }
        return new UserDto(users.getLogin(), users.getPassword(), users.getEmail(), users.getBalance());
    }

    public UserDto getUser(final String email) {
        Users users = usersRepository.findByEmail(email);
        return new UserDto(users.getLogin(), users.getPassword(), users.getEmail(), users.getBalance());
    }

    public void save(final UserDto user) {
        Users users1 = new Users();
        users1.setLogin(user.login());
        users1.setPassword(user.password());
        users1.setEmail(user.email());
        Role role = roleRepository.findByName("user");
        users1.setRole(role);
        usersRepository.save(users1);
    }

    public void saveClass(final String title, final String room, final List<String> users) {
        Class newClass = new Class();
        newClass.setTitle(title);
        Room roomEntity = roomRepository.findByName(room);
        newClass.setRoom(roomEntity);
        Set<Users> usersSet = new HashSet<>();
        users.forEach(email -> {
            Users user = usersRepository.findByEmail(email);
            if (user != null) {
                usersSet.add(user);
            } else {
                throw new RuntimeException("User not found with email: " + email);
            }
        });
        newClass.setUsers(usersSet);
        classRepository.save(newClass);
    }

    public List<ClassDto> getClasses() {
        List<ClassDto> x = new ArrayList<>();
        List<Class> classes = classRepository.findAll();
        for (Class c : classes) {
            Users lector = c.getUsers()
                    .stream()
                    .filter(xy -> Objects.equals(xy.getRole().getName(), "lector"))
                    .findFirst()
                    .get();
            Set<Users> users = c.getUsers();
            users.remove(lector);
            x.add(new ClassDto(c.getTitle(), lector.getEmail(), c.getRoom().getName(), users.stream().map(Users::getEmail).toList()));
        }
        return x;
    }

    public void deleteClass(final String className) {
        Class classToDelete = classRepository.findByTitle(className);
        classToDelete.getUsers().forEach(user -> user.getClasses().remove(classToDelete));
        classToDelete.getUsers().clear();
        classRepository.delete(classToDelete);
    }

    public void updateClass(final String oldClassName, final String newClassName) {
        Class oldClass = classRepository.findByTitle(oldClassName);
        oldClass.setTitle(newClassName);
        classRepository.save(oldClass);
    }

    public List<String> getUsersEmail() {
        List<Users> users = usersRepository.findAll();
        List<String> x = users.stream()
                .filter(user -> user.getRole() != null && "user".equals(user.getRole().getName()))
                .map(Users::getEmail)
                .toList();
        return x;
    }

    public List<LoginEmailDto> getUsers() {
        List<Users> users = usersRepository.findAll();
        List<LoginEmailDto> x = new ArrayList<>();
        users.forEach(user -> {
            x.add(LoginEmailDto.builder().login(user.getLogin()).email(user.getEmail()).balance(user.getBalance()).build());
        });
        return x;
    }

    public List<String> getLectors() {
        List<Users> users = usersRepository.findAll();
        List<String> x = users.stream()
                .filter(user -> user.getRole() != null && "lector".equals(user.getRole().getName()))
                .map(Users::getEmail)
                .toList();
        return x;
    }

    public List<String> getRooms() {
        List<Room> users = roomRepository.findAll();
        return users.stream()
                .map(Room::getName)
                .toList();
    }

    public void updateUser(final String login, final String email, final String oldEmail) {
        Users users = usersRepository.findByEmail(oldEmail);
        users.setEmail(email);
        users.setLogin(login);
        usersRepository.save(users);
    }

    public String updateUser(final String login, final String email, final String oldPassword, final String newPassword) {
        Users users = usersRepository.findByEmail(email);
        if (users.getPassword().equals(oldPassword)) {
            users.setPassword(newPassword);
            usersRepository.save(users);
            return "User´s password successfully changed!";
        }
        throw new RuntimeException("Error: User´s password cannot be changed!");
    }

    public void deleteUser(final String email) {
        Users users = usersRepository.findByEmail(email);
        usersRepository.delete(users);
    }

    public List<NewsDto> getNews() {
        return NewsMapper.MAP(newsRepository.getAll());
    }

    public List<EntryDto> getEntries(final String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<EntryDto> entryDtos = new ArrayList<>();
        List<Entry> entries = entryRepository.findByUserEmail(email);
        entries.forEach(entry -> {
            String entryTimeFormatted = entry.getEntryTime().format(formatter);
            String leaveTimeFormatted = entry.getLeaveTime() != null ? entry.getLeaveTime().format(formatter) : "Still Active";
            entryDtos.add(EntryDtoFactory.createWithTimes(entryTimeFormatted, leaveTimeFormatted));
        });
        return entryDtos;
    }

    public List<TransactionDto> getTransactions(String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Transaction> transactions = transactionRepository.findByUserEmail(email);
        List<TransactionDto> transactionDtos = new ArrayList<>();
        transactions.forEach(t -> {
            transactionDtos.add(new TransactionDto(t.getAmount(), t.getDescription(), t.getDate().format(formatter)));
        });
        return transactionDtos;
    }

    public UserMembership purchaseMembership(String email, Long membershipId) {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        if (user.getBalance() < membership.getPrice()) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - membership.getPrice());

        LocalDateTime purchaseDate = LocalDateTime.now();
        LocalDateTime endDate = purchaseDate.plusDays(membership.getDays());
        Transaction transaction = new Transaction();
        transaction.setAmount(-membership.getPrice());
        transaction.setDescription("Withdraw");
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);
        transactionRepository.save(transaction);
        UserMembership userMembership = new UserMembership(user, membership, purchaseDate, endDate);
        return userMembershipRepository.save(userMembership);
    }

    // Get user memberships
    public List<UserMembership> getUserMemberships(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userMembershipRepository.findByUserId(user.getId());
    }

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public void synchronize() {
        List<Role> roleList = roleRepository.findAll();
        backuproleRepository.saveAll(roleList);

        List<Users> usersList = usersRepository.findAll();
        backupusersRepository.saveAll(usersList);

        // Backup Rooms
        List<Room> roomList = roomRepository.findAll();
        backuproomRepository.saveAll(roomList);

        // Backup Classes
        List<Class> classList = classRepository.findAll();
        backupclassRepository.saveAll(classList);

        // Backup News
        List<News> newsList = newsRepository.findAll();
        backupnewsRepository.saveAll(newsList);

        // Backup Entries
        List<Entry> entryList = entryRepository.findAll();
        backupentryRepository.saveAll(entryList);

        // Backup Transactions
        List<Transaction> transactionList = transactionRepository.findAll();
        backuptransactionRepository.saveAll(transactionList);

        // Backup Memberships
        List<Membership> membershipList = membershipRepository.findAll();
        backupmembershipRepository.saveAll(membershipList);

        // Backup User Memberships
        List<UserMembership> userMembershipList = userMembershipRepository.findAll();
        backupuserMembershipRepository.saveAll(userMembershipList);
    }
}
