package com.company.storage.file;

import com.company.entity.Role;
import com.company.entity.User;
import com.company.storage.UserStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUserStorage implements UserStorage {
    private static final String PATTERN = "%d %s %s %s %d";
    private static final String USERS_PATH = "users.txt";
    private static final String ROLES_PATH = "roles.txt";
    private static final String SPACE = " ";

    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int LOGIN_INDEX = 2;
    private static final int PASSWORD_INDEX = 3;
    private static final int ROLE_INDEX = 4;

    @Override
    public void save(User user) {
        try {
            FileWriter fileWriter = new FileWriter(USERS_PATH, true);
            String format = String.format(PATTERN, user.getId(), user.getName(), user.getLogin(), user.getPassword(), getRoleId(user.getRole()));
            fileWriter.write(format);
            fileWriter.write("\n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getRoleId(Role role){

        try {
            FileReader fileReader = new FileReader(ROLES_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null){
                String[] splits = line.split(SPACE);
                if(splits[1].equals(role.name())){
                    return Integer.parseInt(splits[0]);
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return  -1;
    }

    @Override
    public void updateName(int id, String name) {
        List<User> all = getAll();
        for (User user : all) {
            if (user.getId() == id) {
                user.setName(name);
                break;
            }
        }
        rewrite(all);
    }

    private void rewrite(List<User> users) {
        List<User> all = getAll();
        for (User user : all) {

            try {
                FileWriter fileWriter = new FileWriter(USERS_PATH);
                String format = String.format(PATTERN, user.getId(), user.getName(), user.getLogin(), user.getPassword(), getRoleId(user.getRole()));
                fileWriter.write(format);
                fileWriter.write("\n");
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        List<User> all = getAll();
        for (User user : all) {
            if (user.getId() == id) {
                user.setPassword(password);
                break;
            }
        }
        rewrite(all);

    }

    @Override
    public void delete(int id) {
        List<User> all = getAll();
        for (User user : all) {
            if (user.getId() == id) {
                all.remove(user.getId());
                break;
            }
        }
        rewrite(all);
    }

    @Override
    public void delete(String login) {
        List<User> all = getAll();
        all.removeIf(user -> user.getLogin().equals(login));
        rewrite(all);

    }

    @Override
    public User getById(int id) {
        List<User> all = getAll();
        for (User user : all) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private Role getRoleById(int id){
        try {
            FileReader fileReader = new FileReader(ROLES_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null){
                String[] splits = line.split(SPACE);
                int idOfTheRole = Integer.parseInt(splits[0]);
                if (idOfTheRole == id){
                    return Role.valueOf(splits[1]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        List<User> all = getAll();
        for (User user : all) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {


        List<User> listOfAllUsers = new ArrayList<>();


        try {
            FileReader fileReader = new FileReader(USERS_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splits = line.split(SPACE);



                listOfAllUsers.add(new User(
                        Integer.parseInt(splits[ID_INDEX]),
                        splits[NAME_INDEX],
                        splits[LOGIN_INDEX],
                        splits[PASSWORD_INDEX],
                        getRoleById(Integer.parseInt(splits[ROLE_INDEX]))));
                return listOfAllUsers;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        List<User> all = getAll();
        for (User user : all) {
            if(user.getId() == id){
                return  true;

            }
            break;
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        List<User> all = getAll();
        for (User user : all) {
            if(user.getLogin().equals(login)){
                return  true;
            }
            break;
        }
        return false;
    }
}
